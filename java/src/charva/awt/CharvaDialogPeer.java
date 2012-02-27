package charva.awt;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import charva.awt.Toolkit;
import java.awt.Window;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ContainerPeer;
import java.awt.peer.DialogPeer;
import java.util.List;

import sun.awt.CausedFocusEvent.Cause;
import sun.java2d.pipe.Region;

public class CharvaDialogPeer extends CharvaWindowPeer implements DialogPeer {

    String title;

    @Override
	public void blockWindows(List<Window> windows) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResizable(boolean resizeable) {
		// TODO Auto-generated method stub
		
	}

	public void setTitle(String title) {
		this.title = title;
		repaint();
	}

	public void paint(Graphics g) {
        
		Toolkit _term = Toolkit.getDefaultToolkit();
		Rectangle bounds = getBounds();

		/* Draw the enclosing frame
         */
        int colorpair = Toolkit.getCursesColor(foreground, background);
        _term.blankBox(bounds.getLocation(), bounds.getSize(), colorpair);
        int boxwidth = bounds.width - 2;
        int boxheight = bounds.height - 2;
        _term.drawBox(new Point(bounds.x + 1, bounds.y + 1),
                new Dimension(boxwidth, boxheight),
                colorpair);

        /* Draw the title into the enclosing frame.
         */
        if (title != null && title.length() != 0) {
            _term.setCursor(new Point(bounds.x + 2, bounds.y + 1));
            _term.addChar(' ', 0, colorpair);
            _term.addString(title, 0, colorpair);
            _term.addChar(' ', 0, colorpair);
        }

        /* Draw all the contained components
         */
        super.paint(g);

    }

	
}
