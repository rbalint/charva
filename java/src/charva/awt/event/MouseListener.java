package charva.awt.event;

import java.util.EventListener;

/**
 * The class that is interested in processing a mouse event
 * implements this interface or subclasses the MouseAdapter class.
 * <P>
 * The listener object created from that class is then registered with a
 * component using the component's <code>addMouseListener</code>
 * method. In Charva, a mouse event is generated when the mouse is clicked.
 * Unlike Swing, a mouse event is NOT generated when the mouse cursor enters
 * or leaves a component. When a mouse event
 * occurs, the relevant method in the listener object is invoked, and
 * the <code>MouseEvent</code> is passed to it.
 */
public interface MouseListener extends EventListener{

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    public void mouseClicked(MouseEvent e);

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    public void mousePressed(MouseEvent e);

    /**
     * Invoked when a mouse button has been released on a component.
     */
    public void mouseReleased(MouseEvent e);
}
