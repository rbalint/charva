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
import charvax.swing.JScrollBar;

import java.awt.Adjustable;
import java.awt.Point;
import java.awt.AWTEvent;
import java.awt.event.AdjustmentEvent;
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
        extends Container {

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
    protected void processWindowEvent(charva.awt.event.WindowEvent evt_) {
        if (_windowListeners == null)
            return;

        Enumeration e = _windowListeners.elements();
        while (e.hasMoreElements()) {
            WindowListener wl = (WindowListener) e.nextElement();
            switch (evt_.getID()) {

                case java.awt.event.WindowEvent.WINDOW_CLOSING:
                    wl.windowClosing(evt_);
                    break;

                case java.awt.event.WindowEvent.WINDOW_OPENED:
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

        WindowEvent we = new WindowEvent(this, java.awt.event.WindowEvent.WINDOW_OPENED);
        _term.getSystemEventQueue().postEvent(we);
        /* We may want to delay sync-s like in Charva 1.1.4 */
        _term.getSystemEventQueue().postEvent(new SyncEvent(this));

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
        WindowEvent we = new WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING);
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

    //====================================================================
    // INSTANCE VARIABLES

    private Window _owner;
    protected Toolkit _term;
    public boolean _windowClosed = false;

    private Vector _windowListeners = null;

    private static boolean _dispatchThreadRunning = false;

}
