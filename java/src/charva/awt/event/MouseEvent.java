/* class MouseEvent
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

package charva.awt.event;

import java.awt.AWTEvent;

import charva.awt.*;

/**
 * An event which encapsulates information about a mouse-click.
 */
public class MouseEvent
        extends AWTEvent {

	/* timestamp */
    private long when;
    
	public MouseEvent(Component source_, int id, long when, int modifiers,
                      int x_, int y_, int clickcount_, boolean popup, int button_) {

        super(source_, id);
        this.when = when;
        this.modifiers = modifiers;
        x = x_;
        y = y_;
        clickcount = clickcount_;
        button = button_;
    }

   /* public int getModifiers() {
        return modifiers;
    }*/

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

	public boolean isConsumed() {
		return consumed;
	}
    
    public void consume() {
    	consumed = true;
    }
	
	public int getClickCount() {
        return clickcount;
    }

    public int getButton() {
        return button;
    }

    public String toString() {
        return ("MouseEvent: x=" + x + " y=" + y +
                " modifiers=" + modifiers + " clickcount=" + clickcount +
                " button=" + button + " source=[" + getSource() + "]");
    }

    // INSTANCE VARIABLES ================================================

    /**
     * Specifies whether the button was pressed, released or clicked.
     */
    protected int modifiers;

    protected int x;
    protected int y;

    /**
     * Specified which button was pressed, released or clicked.
     */
    protected int button;

    protected int clickcount;

    // STATIC CONSTANTS ==================================================

    // Buttons
    public static final int BUTTON1 = 1;
    public static final int BUTTON2 = 2;
    public static final int BUTTON3 = 3;

    // Modifiers
    public static final int MOUSE_PRESSED = java.awt.event.MouseEvent.MOUSE_PRESSED;
    public static final int MOUSE_RELEASED = java.awt.event.MouseEvent.MOUSE_PRESSED;
    public static final int MOUSE_CLICKED = java.awt.event.MouseEvent.MOUSE_PRESSED;
}
