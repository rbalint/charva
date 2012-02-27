package charva.awt;

import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.peer.FramePeer;

public class CharvaFramePeer extends CharvaWindowPeer implements FramePeer {

	private String title;

	@Override
	public Rectangle getBoundsPrivate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getState() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBoundsPrivate(int x, int y, int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaximizedBounds(Rectangle bounds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMenuBar(MenuBar mb) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setResizable(boolean resizeable) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setState(int state) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTitle(String title) {
		this.title = title;
		repaint();

	}

	public void paint(Graphics g) {
		Toolkit _term = Toolkit.getDefaultToolkit();
		Rectangle bounds = getBounds();

		/* Draw the enclosing frame (but only if the insets are nonzero).
         */
        int colorpair = Toolkit.getCursesColor(foreground, background);
        _term.blankBox(bounds.getLocation(), bounds.getSize(), colorpair);
        Insets insets = super.getInsets();
        if (insets.top != 0 && insets.bottom != 0)
            _term.drawBox(bounds.getLocation(), bounds.getSize(), colorpair);

        /* Draw the title into the enclosing frame.
         */
        if (title.equals("") == false) {
            _term.setCursor(new Point(bounds.x + 1, bounds.y));
            _term.addChar(' ', 0, colorpair);
            _term.addString(title, 0, colorpair);
            _term.addChar(' ', 0, colorpair);
        }

        /* Draw all the contained components
         */
        super.paint(g);
	}
}
