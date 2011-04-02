/* class KeyEvent
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
 * An event which indicates that a keystroke occurred in an object.
 */
public class KeyEvent
        extends AWTEvent {

    private int _keyCode;

	private int modifiers;

    public KeyEvent(Component source_, int id_, long when, int modifiers, int key_) {
        super(source_, id_);
        this.modifiers = modifiers;
        _keyCode = key_;
    }

	public boolean isConsumed() {
		return consumed;
	}
    
    public void consume() {
    	consumed = true;
    }
	
    public int getModifiers() {
    	return this.modifiers;
    }
    /**
     * Returns the integer keyCode associated with the key in this event.
     *
     * @return the integer code for an actual key on the keyboard.
     *         For <code>KEY_TYPED</code> events, Swing sets the keyCode to <code>VK_UNDEFINED</code>.
     *         But ncurses has no way of knowing about key-modifiers such as ALT,
     *         so Charva just returns an integer representation of the key character.
     */
    public int getKeyCode() {
        return _keyCode;
    }

    /**
     * Returns the character associated with the key in this event.
     * For example, the key-typed event for shift + "a" returns the
     * value for "A".
     *
     * @return the character defined for this key event.
     *         If no valid character exists for this key event,
     *         Swing returns <code>CHAR_UNDEFINED</code>. But Charva just returns the keyCode
     *         cast as a char.
     */
    public char getKeyChar() {
        return (char) _keyCode;
    }

    /**
     * Set the key code to indicate a physical key was pressed
     */
    public void setKeyCode(int key_) {
        this._keyCode = key_;
    }

    /**
     * Set the keyChar value to indicate a logical character.
     *
     * @param keyChar a char corresponding to to the combination of keystrokes
     *                that make up this event.
     */
    public void setKeyChar(char keyChar) {
        this._keyCode = (int) keyChar;
    }

    /**
     * Returns true if the key is a function key or control key.
     */
    //public boolean isActionKey() { return (_key >= 256); }
    public boolean isActionKey() {
        return (charva.awt.Toolkit.isActionKey(_keyCode));
    }

    public String toString() {
        return ("KeyEvent: key=" + Toolkit.key2ASCII(getKeyCode()) +
                " source=[" + getSource() + "]");
    }


    /**
     * KEY_PRESSED events that don't map to a valid character
     * cause the keyPressed() method to return this value.
     */
    public static final char CHAR_UNDEFINED = java.awt.event.KeyEvent.VK_UNDEFINED;

    public static final int VK_UNDEFINED = java.awt.event.KeyEvent.VK_UNDEFINED;
    public static final int VK_ESCAPE = java.awt.event.KeyEvent.VK_ESCAPE;

// Reallocated to unicode safe area.

//    public static final int VK_DOWN = 0402;
//    public static final int VK_UP = 0403;
//    public static final int VK_LEFT = 0404;
//    public static final int VK_RIGHT = 0405;
//    public static final int VK_HOME = 0406;
//    public static final int VK_BACK_SPACE = 0407;
//    public static final int VK_F1 = 0411;
//    public static final int VK_F2 = 0412;
//    public static final int VK_F3 = 0413;
//    public static final int VK_F4 = 0414;
//    public static final int VK_F5 = 0415;
//    public static final int VK_F6 = 0416;
//    public static final int VK_F7 = 0417;
//    public static final int VK_F8 = 0420;
//    public static final int VK_F9 = 0421;
//    public static final int VK_F10 = 0422;
//    public static final int VK_F11 = 0423;
//    public static final int VK_F12 = 0424;
//    public static final int VK_F13 = 0425;
//    public static final int VK_F14 = 0426;
//    public static final int VK_F15 = 0427;
//    public static final int VK_F16 = 0430;
//    public static final int VK_F17 = 0431;
//    public static final int VK_F18 = 0432;
//    public static final int VK_F19 = 0433;
//    public static final int VK_F20 = 0434;
//    public static final int VK_DELETE = 0512;
//    public static final int VK_INSERT = 0513;
//    public static final int VK_PAGE_DOWN = 0522;
//    public static final int VK_PAGE_UP = 0523;
//    public static final int VK_ENTER = 0527;
//    public static final int VK_BACK_TAB = 0541;
//    public static final int VK_END = 0550;

// Unicode safe area... Unicode Private Use Area on 0xE000 position

    public static final int VK_DOWN         = java.awt.event.KeyEvent.VK_DOWN;
    public static final int VK_UP           = java.awt.event.KeyEvent.VK_UP;
    public static final int VK_LEFT         = java.awt.event.KeyEvent.VK_LEFT;
    public static final int VK_RIGHT        = java.awt.event.KeyEvent.VK_RIGHT;
    public static final int VK_HOME         = java.awt.event.KeyEvent.VK_HOME;
    public static final int VK_BACK_SPACE   = java.awt.event.KeyEvent.VK_BACK_SPACE;
    public static final int VK_F1           = java.awt.event.KeyEvent.VK_F1;
    public static final int VK_F2           = java.awt.event.KeyEvent.VK_F2;
    public static final int VK_F3           = java.awt.event.KeyEvent.VK_F3;
    public static final int VK_F4           = java.awt.event.KeyEvent.VK_F4;
    public static final int VK_F5           = java.awt.event.KeyEvent.VK_F5;
    public static final int VK_F6           = java.awt.event.KeyEvent.VK_F6;
    public static final int VK_F7           = java.awt.event.KeyEvent.VK_F7;
    public static final int VK_F8           = java.awt.event.KeyEvent.VK_F8;
    public static final int VK_F9           = java.awt.event.KeyEvent.VK_F9;
    public static final int VK_F10          = java.awt.event.KeyEvent.VK_F10;
    public static final int VK_F11          = java.awt.event.KeyEvent.VK_F11;
    public static final int VK_F12          = java.awt.event.KeyEvent.VK_F12;
    public static final int VK_F13          = java.awt.event.KeyEvent.VK_F13;
    public static final int VK_F14          = java.awt.event.KeyEvent.VK_F14;
    public static final int VK_F15          = java.awt.event.KeyEvent.VK_F15;
    public static final int VK_F16          = java.awt.event.KeyEvent.VK_F16;
    public static final int VK_F17          = java.awt.event.KeyEvent.VK_F17;
    public static final int VK_F18          = java.awt.event.KeyEvent.VK_F18;
    public static final int VK_F19          = java.awt.event.KeyEvent.VK_F19;
    public static final int VK_F20          = java.awt.event.KeyEvent.VK_F20;
    public static final int VK_DELETE       = java.awt.event.KeyEvent.VK_DELETE;
    public static final int VK_INSERT       = java.awt.event.KeyEvent.VK_INSERT;
    public static final int VK_PAGE_DOWN    = java.awt.event.KeyEvent.VK_PAGE_DOWN;
    public static final int VK_PAGE_UP      = java.awt.event.KeyEvent.VK_PAGE_UP;
    public static final int VK_ENTER        = java.awt.event.KeyEvent.VK_ENTER;
    public static final int VK_END          = java.awt.event.KeyEvent.VK_END;

// Unicode safe area... END

    public static final int VK_COMMA          = java.awt.event.KeyEvent.VK_COMMA;

    /**
     * Constant for the "-" key.
     * @since 1.2
     */
    public static final int VK_MINUS          = java.awt.event.KeyEvent.VK_MINUS;

    public static final int VK_PERIOD         = java.awt.event.KeyEvent.VK_PERIOD;
    public static final int VK_SLASH          = java.awt.event.KeyEvent.VK_SLASH;

    /** VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39) */
    public static final int VK_0              = java.awt.event.KeyEvent.VK_0;
    public static final int VK_1              = java.awt.event.KeyEvent.VK_1;
    public static final int VK_2              = java.awt.event.KeyEvent.VK_2;
    public static final int VK_3              = java.awt.event.KeyEvent.VK_3;
    public static final int VK_4              = java.awt.event.KeyEvent.VK_4;
    public static final int VK_5              = java.awt.event.KeyEvent.VK_5;
    public static final int VK_6              = java.awt.event.KeyEvent.VK_6;
    public static final int VK_7              = java.awt.event.KeyEvent.VK_7;
    public static final int VK_8              = java.awt.event.KeyEvent.VK_8;
    public static final int VK_9              = java.awt.event.KeyEvent.VK_9;

    public static final int VK_SEMICOLON      = java.awt.event.KeyEvent.VK_SEMICOLON;
    public static final int VK_EQUALS         = java.awt.event.KeyEvent.VK_EQUALS;

    /** VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
    public static final int VK_A              = java.awt.event.KeyEvent.VK_A;
    public static final int VK_B              = java.awt.event.KeyEvent.VK_B;
    public static final int VK_C              = java.awt.event.KeyEvent.VK_C;
    public static final int VK_D              = java.awt.event.KeyEvent.VK_D;
    public static final int VK_E              = java.awt.event.KeyEvent.VK_E;
    public static final int VK_F              = java.awt.event.KeyEvent.VK_F;
    public static final int VK_G              = java.awt.event.KeyEvent.VK_G;
    public static final int VK_H              = java.awt.event.KeyEvent.VK_H;
    public static final int VK_I              = java.awt.event.KeyEvent.VK_I;
    public static final int VK_J              = java.awt.event.KeyEvent.VK_J;
    public static final int VK_K              = java.awt.event.KeyEvent.VK_K;
    public static final int VK_L              = java.awt.event.KeyEvent.VK_L;
    public static final int VK_M              = java.awt.event.KeyEvent.VK_M;
    public static final int VK_N              = java.awt.event.KeyEvent.VK_N;
    public static final int VK_O              = java.awt.event.KeyEvent.VK_O;
    public static final int VK_P              = java.awt.event.KeyEvent.VK_P;
    public static final int VK_Q              = java.awt.event.KeyEvent.VK_Q;
    public static final int VK_R              = java.awt.event.KeyEvent.VK_R;
    public static final int VK_S              = java.awt.event.KeyEvent.VK_S;
    public static final int VK_T              = java.awt.event.KeyEvent.VK_T;
    public static final int VK_U              = java.awt.event.KeyEvent.VK_U;
    public static final int VK_V              = java.awt.event.KeyEvent.VK_V;
    public static final int VK_W              = java.awt.event.KeyEvent.VK_W;
    public static final int VK_X              = java.awt.event.KeyEvent.VK_X;
    public static final int VK_Y              = java.awt.event.KeyEvent.VK_Y;
    public static final int VK_Z              = java.awt.event.KeyEvent.VK_Z;

    public static final int VK_OPEN_BRACKET   = java.awt.event.KeyEvent.VK_OPEN_BRACKET;
    public static final int VK_BACK_SLASH     = java.awt.event.KeyEvent.VK_BACK_SLASH;
    public static final int VK_CLOSE_BRACKET  = java.awt.event.KeyEvent.VK_CLOSE_BRACKET;
    
}
