package charva.awt;

import java.awt.AWTEvent;
import java.awt.AWTException;
import java.awt.BufferCapabilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.event.PaintEvent;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.VolatileImage;
import java.awt.peer.ComponentPeer;
import java.awt.peer.ContainerPeer;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.awt.RepaintArea;
import sun.awt.CausedFocusEvent.Cause;
import sun.awt.event.IgnorePaintEvent;
import sun.java2d.pipe.Region;

public class CharvaComponentPeer implements ComponentPeer {

    private static final Logger log = Logger.getLogger("charva.awt.CharvaComponentPeer");
    private static final Logger focusLog = Logger.getLogger("charva.awt.focus.CharvaComponentPeer");
    private static final Logger enableLog = Logger.getLogger("charva.awt.enable.CharvaComponentPeer");

    Component target;
    Color foreground;
    Color background;
    RepaintArea paintArea = new RepaintArea();
    boolean enabled;
    ContainerPeer parentPeer;
	private int x;
	private int y;
	private int width;
	private int height;

    @Override
	public void applyShape(Region shape) {
		// TODO Not supported yet

	}

	@Override
	public boolean canDetermineObscurity() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int checkImage(Image img, int w, int h, ImageObserver o) {
		// TODO Fake that image has been loaded properly
		return ImageObserver.ALLBITS | ImageObserver.FRAMEBITS;
	}

	/*
	 * From OpenJDK, GPLv2  
	 */
	public void coalescePaintEvent(PaintEvent e) {
        Rectangle r = e.getUpdateRect();
        if (!(e instanceof IgnorePaintEvent)) {
            paintArea.add(r, e.getID());
        }
        if (true) {
            switch(e.getID()) {
              case PaintEvent.UPDATE:
                  log.finer("XCP coalescePaintEvent : UPDATE : add : x = " +
                            r.x + ", y = " + r.y + ", width = " + r.width + ",height = " + r.height);
                  return;
              case PaintEvent.PAINT:
                  log.finer("XCP coalescePaintEvent : PAINT : add : x = " +
                            r.x + ", y = " + r.y + ", width = " + r.width + ",height = " + r.height);
                  return;
            }
        }
    }

	@Override
	public void createBuffers(int numBuffers, BufferCapabilities caps)
			throws AWTException {
		// TODO Auto-generated method stub

	}

	@Override
	public Image createImage(ImageProducer producer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Image createImage(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VolatileImage createVolatileImage(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroyBuffers() {
		// TODO Auto-generated method stub

	}

    public void enable() {
        setEnabled(true);
    }

    public void disable() {
        setEnabled(false);
    }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void flip(FlipContents flipAction) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getBackBuffer() {
		// TODO Auto-generated method stub
		return null;
	}

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	@Override
	public ColorModel getColorModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FontMetrics getFontMetrics(Font font) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GraphicsConfiguration getGraphicsConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public Point getLocationOnScreen() {
		Point pt = target.getLocation();
		// find the top container and add all relative coordinates
		Container c = target.getParent();
		while (c != null) {
			pt.translate(c.getX(), c.getY());
			c =c.getParent();
		}
		return pt;
	}

    public Dimension getMinimumSize() {
        return target.getSize();
    }

    public Dimension getPreferredSize() {
        return getMinimumSize();
    }


	public Toolkit getToolkit() {
		return Toolkit.getDefaultToolkit();
	}

	@Override
	public void handleEvent(AWTEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean handlesWheelScrolling() {
		return false;
	}

	@Override
	public boolean isFocusable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isObscured() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isReparentSupported() {
		// TODO Auto-generated method stub
		return false;
	}

	public void layout() {
	}

	public Dimension minimumSize() {
		return target.getSize();
	}

    public void repaint() {
        if (!target.isVisible()) {
            return;
        }
        Graphics g = getGraphics();
        if (g != null) {
            try {
                paint(g);
            } finally {
                g.dispose();
            }
        }
    }

    public void paint(Graphics g) {
    }


	public Dimension preferredSize() {
		return getMinimumSize();
	}

	@Override
	public boolean prepareImage(Image img, int w, int h, ImageObserver o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void print(Graphics g) {
		// TODO Auto-generated method stub

	}

	public void repaint(long tm, int x, int y, int width, int height) {
		repaint();
	}

	public void reparent(ContainerPeer newContainer) {
		parentPeer = newContainer;
	}

	@Override
	public boolean requestFocus(Component lightweightChild, boolean temporary,
			boolean focusedWindowChangeAllowed, long time, Cause cause) {
		// TODO Auto-generated method stub
		return false;
	}

    public void reshape(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS);
    }


	public void setBackground(Color c) {
		foreground = c;
		repaint();
	}

    public void setBounds(int x, int y, int width, int height, int op) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        layout();
    }


    /**
     * @see java.awt.peer.ComponentPeer
     */
    public void setEnabled(boolean value) {
        if (enableLog.isLoggable(Level.FINE)) {
            enableLog.log(Level.FINE, "{0}ing {1}",
                          new Object[] {(value?"Enabl":"Disabl"), String.valueOf(this)});
        }
        boolean repaintNeeded = (enabled != value);
        enabled = value;
        if (target instanceof Container) {
            Component list[] = ((Container)target).getComponents();
            for (int i = 0; i < list.length; ++i) {
                boolean childEnabled = list[i].isEnabled();
                ComponentPeer p = list[i].getPeer();
                if ( p != null ) {
                    p.setEnabled(value && childEnabled);
                }
            }
        }
        if (repaintNeeded) {
            repaint();
        }
    }


	@Override
	public void setFont(Font f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setForeground(Color c) {
		foreground = c;
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub

	}

    public void show() {
        setVisible(true);
    }

    public void hide() {
        setVisible(false);
    }

	@Override
	public void updateCursorImmediately() {
		// TODO Auto-generated method stub

	}

}
