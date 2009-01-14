package charva.awt.event;

/**
 * An abstract adapter class for receiving keyboard focus events.
 * The methods in this class are empty. This class exists as convenience for creating listener objects.<p/>
 *
 * Extend this class to create a FocusEvent listener and override the methods for the events of interest.
 * (If you implement the FocusListener interface, you have to define all of the methods in it.
 * This abstract class defines null methods for them all, so you can only have to define methods for
 * events you care about.)<p/>
 *
 * Create a listener object using the extended class and then register it with a component using the
 * component's addFocusListener method. When the component gains or loses the keyboard focus, the relevant
 * method in the listener object is invoked, and the FocusEvent is passed to it.
 */
public abstract class FocusAdapter implements FocusListener {

    /**
     * Invoked when a component gains the keyboard focus.
     * @param fe_
     */
    public void focusGained(FocusEvent fe_) {

    }

    /**
     * Invoked when a component loses the keyboard focus.
     * @param fe_
     */
    public void focusLost(FocusEvent fe_) {

    }
}
