/* class Window
 *
 * Copyright (C) 2001-2003  R M Pitman
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package charva.awt;

import charva.awt.event.*;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The Window class represents a "toplevel" window with
 * no decorative frame. The window is initially invisible; you must use
 * the show() method to make it visible.
 */
public class Window
        extends Container
        implements Runnable {

    private static final Log LOG = LogFactory.getLog(Window.class);

    public Window(Window owner_) {
        _owner = owner_;
        init();
    }

    public Window(Frame owner_) {
        _owner = (Window) owner_;
        init();
    }

    private void init() {
        _term = Toolkit.getDefaultToolkit();
        super._layoutMgr = new BorderLayout();
        _visible = false;

        // The window inherits the colors of its parent if there is one,
        // otherwise use the default colors as set in charva.awt.Toolkit.
        if (_owner != null) {
            super.setForeground(_owner.getForeground());
            super.setBackground(_owner.getBackground());
        } else {
            super.setForeground(Toolkit.getDefaultForeground());
            super.setBackground(Toolkit.getDefaultBackground());
        }
    }

    /**
     * Return the Window that is the "owner" of this Window.
     */
    public Window getOwner() {
        return _owner;
    }

    /**
     * Register a WindowListener object for this window.
     */
    public void addWindowListener(WindowListener listener_) {
        if (_windowListeners == null)
            _windowListeners = new Vector();

        _windowListeners.add(listener_);
    }

    /**
     * Process window events occurring on this window by dispatching them
     * to any registered WindowListener objects.
     */
    protected void processWindowEvent(WindowEvent evt_) {
        if (_windowListeners == null)
            return;

        Enumeration e = _windowListeners.elements();
        while (e.hasMoreElements()) {
            WindowListener wl = (WindowListener) e.nextElement();
            switch (evt_.getID()) {

                case AWTEvent.WINDOW_CLOSING:
                    wl.windowClosing(evt_);
                    break;

                case AWTEvent.WINDOW_OPENED:
                    wl.windowOpened(evt_);
                    break;
            }
        }
    }

    /**
     * Returns true if this Window is currently displayed.
     */
    public boolean isDisplayed() {
        return _term.isWindowDisplayed(this);
    }

    /**
     * Causes this Window to be sized to fit the preferred sizes and
     * layouts of its contained components.
     */
    public void pack() {
        setSize(minimumSize());
        super.doLayout();   // call method in Container superclass
    }

    /**
     * Lay out the contained components, draw the window and its contained
     * components, and then read input events off the EventQueue and send
     * them to the component that has the input focus.
     */
    public void show() {

        if (_visible)
            return;    // This window is already visible.

        _visible = true;
        _term.addWindow(this);

        /* call doLayout in Container superclass.  This will lay out all of
         * the contained components (i.e. children) and their descendants,
         * and in the process will set the valid flag of all descendants.
         */
        super.doLayout();    // call method in Container superclass

        this.adjustLocation();   // ensure it fits inside the screen

        this.draw();

        WindowEvent we = new WindowEvent(this, AWTEvent.WINDOW_OPENED);
        _term.getSystemEventQueue().postEvent(we);

        /* Rather than call Toolkit.sync() directly here, we put a SyncEvent
         * onto the SyncQueue. The SyncThread will read it off the SyncQueue
         * and then sleep for 50msec before putting the SyncEvent onto
         * the EventQueue, from which it will be picked up by the active
         * Window (i.e. an instance of this class).  The active Window
         * will then call Toolkit.sync() directly.  This slight delay speeds
         * up the case where a window opens and then immediately opens a
         * new window (and another...).
         */
        SyncQueue.getInstance().postEvent(new SyncEvent(this));

        if (_dispatchThreadRunning)
            run();
        else {
            _dispatchThreadRunning = true;
            Thread dispatchThread = new Thread(this);
            dispatchThread.setName("event dispatcher");
            dispatchThread.start();

            /* If "charva.script.playback" is defined, we start up
             * a thread for playing back the script. Keys from both the
             * script and the keyboard will cause "fireKeystroke()" to be
             * invoked.
             * The playback thread is started _after_ "addWindow()" is
             * called for the first time, to make sure that _windowList
             * is non-empty when the playback thread calls "fireKeystroke()".
             */
            startPlayback();

        }
    }

    public void run() {
        /* Loop and process input events until the window closes.
         */
        EventQueue evtQueue = _term.getSystemEventQueue();
        try {
            for (_windowClosed = false; _windowClosed != true;) {

                // This returns immediately if there is a keystroke available,
                // otherwise it blocks for up to 100 msec waiting for one.
                _term.checkForKeystroke();

                while (!evtQueue.queuesAreEmpty()) {

                    java.util.EventObject evt = evtQueue.getNextEvent();

                    /* The event object should always be an AWTEvent. If not,
                     * we will get a ClassCastException.
                     */
                    processEvent((AWTEvent) evt);
                }
            }    // end FOR loop
        } catch (Exception e) {
            LOG.error("Exception occurred in event dispatch thread ", e);
            System.exit(1);
        }
    }

    /**
     * Process an event off the event queue.  This method can be extended by
     * subclasses of Window to deal with application-specific events.
     */
    protected void processEvent(AWTEvent evt_) {
        Object source = evt_.getSource();

        if (evt_ instanceof AdjustmentEvent)
            ((Adjustable) source).processAdjustmentEvent((AdjustmentEvent) evt_);

        else if (evt_ instanceof ScrollEvent) {
            ((Scrollable) source).processScrollEvent((ScrollEvent) evt_);
            requestFocus();
            super.requestSync();
        } else if (evt_ instanceof PaintEvent) {

            /* Unless the affected component is totally obscured by
             * windows that are stacked above it, we must redraw its
             * window and all the windows above it.
             */
            if (!((Component) source).isTotallyObscured()) {

                Vector windowlist = _term.getWindowList();
                synchronized (windowlist) {

                    /* We have to draw the window rather than just the affected
                     * component, because setVisible(false) may have been set on
                     * the component.
                     */
                    Window ancestor = ((Component) source).getAncestorWindow();
                    ancestor.draw();

                    /* Ignore windows that are underneath the window that
                     * contains the component that generated the PaintEvent.
                     */
                    Window w = null;
                    int i;
                    for (i = 0; i < windowlist.size(); i++) {
                        w = (Window) windowlist.elementAt(i);
                        if (w == ancestor)
                            break;
                    }

                    /* Redraw all the windows _above_ the one that generated
                     * the PaintEvent.
                     */
                    for (; i < windowlist.size(); i++) {
                        w = (Window) windowlist.elementAt(i);
                        w.draw();
                    }
                }

                super.requestSync();
            }
        } else if (evt_ instanceof SyncEvent) {
            _term.sync();
        } else if (evt_ instanceof WindowEvent) {
            WindowEvent we = (WindowEvent) evt_;
            we.getWindow().processWindowEvent(we);

            /* Now, having given the WindowListener objects a chance to
             * process the WindowEvent, we must check if it was a
             * WINDOW_CLOSING event sent to this window.
             */
            if (we.getID() == AWTEvent.WINDOW_CLOSING) {

                we.getWindow()._windowClosed = true;

                /* Remove this window from the list of those displayed,
                 * and blank out the screen area where the window was
                 * displayed.
                 */
                _term.removeWindow(we.getWindow());
                _term.blankBox(_origin, _size);

                /* Now redraw all of the windows, from the bottom to the
                 * top.
                 */
                Vector winlist = _term.getWindowList();
                Window window = null;
                synchronized (winlist) {
                    for (int i = 0; i < winlist.size(); i++) {
                        window = (Window) winlist.elementAt(i);
                        window.draw();
                    }
                    if (window != null)        // (there may be no windows left)
                        window.requestFocus();
                }

                /* Put a SyncEvent onto the SyncQueue.  The SyncThread will
                 * sleep for 50 msec before putting it onto the EventQueue,
                 * from which it will be picked up by the active Window
                 * (i.e. an instance of this class), which will then call
                 * Toolkit.sync() directly.  This is done to avoid calling
                 * sync() after the close of a window and again after the
                 * display of a new window which might be displayed
                 * immediately afterwards.
                 */
                if (window != null)
                    SyncQueue.getInstance().postEvent(new SyncEvent(window));
            }
        }    // end if WindowEvent
        else if (evt_ instanceof GarbageCollectionEvent) {
            SyncQueue.getInstance().postEvent(evt_);
        } else if (evt_ instanceof InvocationEvent) {
            ((InvocationEvent) evt_).dispatch();
        } else {
            /* It is a KeyEvent, MouseEvent, ActionEvent, ItemEvent,
             * FocusEvent or a custom type of event.
             */
            ((Component) source).processEvent(evt_);
        }
    }

    /**
     * Hide this window and all of its contained components.
     * This is done by putting a WINDOW_CLOSING event onto the queue.
     */
    public void hide() {

        if (!_visible) {
            LOG.warn("Trying to hide window " + this + " that is already hidden!");
            return;    // This window is already hidden.
        }

        _visible = false;
        WindowEvent we = new WindowEvent(this, AWTEvent.WINDOW_CLOSING);
        _term.getSystemEventQueue().postEvent(we);
    }

    /**
     * Draw all the components in this window, and request the keyboard focus.
     */
    public void draw() {
        super.draw();
        requestFocus();
    }

    /**
     * Overrides the method in the Component superclass, because a Window
     * has no parent container.
     * Note that we return a COPY of the origin, not a reference to it, so
     * that the caller cannot modify our location via the returned value.
     */
    public Point getLocationOnScreen() {
        return new Point(_origin);
    }

    /**
     * A Window component will not receive input focus during keyboard focus
     * traversal using Tab and Shift-Tab.
     */
    public boolean isFocusTraversable() {
        return false;
    }

    /**
     * Adjust the position of the window so that it fits inside the screen.
     */
    public void adjustLocation() {
        int bottom = _origin.y + getHeight();
        if (bottom > _term.getScreenRows())
            _origin.y -= bottom - _term.getScreenRows();

        if (_origin.y < 0)
            _origin.y = 0;

        int right = _origin.x + getWidth();
        if (right > _term.getScreenColumns())
            _origin.x -= right - _term.getScreenColumns();

        if (_origin.x < 0)
            _origin.x = 0;
    }

    public void debug(int level_) {
        LOG.debug("Window origin=" + _origin + " size=" + _size);
        super.debug(1);
    }

    private void startPlayback() {
        String scriptfilename;
        if ((scriptfilename = System.getProperty("charva.script.playbackFile")) == null)
            return;

        File scriptFile = new File(scriptfilename);
        if (!scriptFile.canRead()) {
            LOG.warn("Cannot read script tile \"" + scriptfilename + "\"");
            return;
        }


        PlaybackThread thr = new PlaybackThread(scriptFile);
        thr.setDaemon(true);
        thr.setName("playback thread");
        thr.start();
    }

    //====================================================================
    // INSTANCE VARIABLES

    private Window _owner;
    protected Toolkit _term;
    private boolean _windowClosed = false;

    private Vector _windowListeners = null;

    private static boolean _dispatchThreadRunning = false;

}
