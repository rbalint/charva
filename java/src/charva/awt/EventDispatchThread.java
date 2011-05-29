/**
 * Temporary hack class, mainly from OpenJDK 1.6, GPLv2
 */
package charva.awt;

import java.awt.AWTEvent;
import java.awt.ActiveEvent;

public class EventDispatchThread extends Thread{
    private boolean doDispatch = true;

    class StopDispatchEvent extends AWTEvent implements ActiveEvent {
        /*
         * serialVersionUID
         */
        static final long serialVersionUID = -3692158172100730735L;

        public StopDispatchEvent() {
            super(EventDispatchThread.this,0);
        }

        public void dispatch() {
            doDispatch = false;
        }
    }
   
    void stopDispatchingImpl(boolean wait) {

        StopDispatchEvent stopEvent = new StopDispatchEvent();
        EventQueue evtQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
        evtQueue.postEvent(stopEvent);
    }

    public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
    	/* Loop and process input events.
         */
        EventQueue evtQueue = toolkit.getSystemEventQueue();
        try {
            while (doDispatch) {

                // This returns immediately if there is a keystroke available,
                // otherwise it blocks for up to 100 msec waiting for one.
                toolkit.checkForKeystroke();

                while (!evtQueue.queuesAreEmpty()) {

                    java.util.EventObject evt = evtQueue.getNextEvent();

                    /* The event object should always be an AWTEvent. If not,
                     * we will get a ClassCastException.
                     */
                    evtQueue.dispatchEvent((AWTEvent) evt);
                }
            }    // end FOR loop
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
