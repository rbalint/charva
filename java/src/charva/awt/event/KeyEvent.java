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

import charva.awt.*;

/**
 * An event which indicates that a keystroke occurred in an object.
 */
public class KeyEvent
        extends InputEvent {

    private int _keyCode;

    public KeyEvent(int key_, int id_, Component source_) {
        super(source_, id_);
        _keyCode = key_;
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
    public static final char CHAR_UNDEFINED = (char) -1;

    public static final int VK_UNDEFINED = -1;
    public static final int VK_ESCAPE = 0x1b;

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

    public static final int VK_DOWN         = 0xe000;
    public static final int VK_UP           = 0xe001;
    public static final int VK_LEFT         = 0xe002;
    public static final int VK_RIGHT        = 0xe003;
    public static final int VK_HOME         = 0xe004;
    public static final int VK_BACK_SPACE   = 0xe005;
    public static final int VK_F1           = 0xe006;
    public static final int VK_F2           = 0xe007;
    public static final int VK_F3           = 0xe008;
    public static final int VK_F4           = 0xe009;
    public static final int VK_F5           = 0xe00a;
    public static final int VK_F6           = 0xe00b;
    public static final int VK_F7           = 0xe00c;
    public static final int VK_F8           = 0xe00d;
    public static final int VK_F9           = 0xe00e;
    public static final int VK_F10          = 0xe00f;
    public static final int VK_F11          = 0xe010;
    public static final int VK_F12          = 0xe011;
    public static final int VK_F13          = 0xe012;
    public static final int VK_F14          = 0xe013;
    public static final int VK_F15          = 0xe014;
    public static final int VK_F16          = 0xe015;
    public static final int VK_F17          = 0xe016;
    public static final int VK_F18          = 0xe017;
    public static final int VK_F19          = 0xe018;
    public static final int VK_F20          = 0xe019;
    public static final int VK_DELETE       = 0xe01a;
    public static final int VK_INSERT       = 0xe01b;
    public static final int VK_PAGE_DOWN    = 0xe01c;
    public static final int VK_PAGE_UP      = 0xe01d;
    public static final int VK_ENTER        = 0xe01e;
    public static final int VK_BACK_TAB     = 0xe01f;
    public static final int VK_END          = 0xe020;

// Unicode safe area... END

    public static final int VK_COMMA          = 0x2C;

    /**
     * Constant for the "-" key.
     * @since 1.2
     */
    public static final int VK_MINUS          = 0x2D;

    public static final int VK_PERIOD         = 0x2E;
    public static final int VK_SLASH          = 0x2F;

    /** VK_0 thru VK_9 are the same as ASCII '0' thru '9' (0x30 - 0x39) */
    public static final int VK_0              = 0x30;
    public static final int VK_1              = 0x31;
    public static final int VK_2              = 0x32;
    public static final int VK_3              = 0x33;
    public static final int VK_4              = 0x34;
    public static final int VK_5              = 0x35;
    public static final int VK_6              = 0x36;
    public static final int VK_7              = 0x37;
    public static final int VK_8              = 0x38;
    public static final int VK_9              = 0x39;

    public static final int VK_SEMICOLON      = 0x3B;
    public static final int VK_EQUALS         = 0x3D;

    /** VK_A thru VK_Z are the same as ASCII 'A' thru 'Z' (0x41 - 0x5A) */
    public static final int VK_A              = 0x41;
    public static final int VK_B              = 0x42;
    public static final int VK_C              = 0x43;
    public static final int VK_D              = 0x44;
    public static final int VK_E              = 0x45;
    public static final int VK_F              = 0x46;
    public static final int VK_G              = 0x47;
    public static final int VK_H              = 0x48;
    public static final int VK_I              = 0x49;
    public static final int VK_J              = 0x4A;
    public static final int VK_K              = 0x4B;
    public static final int VK_L              = 0x4C;
    public static final int VK_M              = 0x4D;
    public static final int VK_N              = 0x4E;
    public static final int VK_O              = 0x4F;
    public static final int VK_P              = 0x50;
    public static final int VK_Q              = 0x51;
    public static final int VK_R              = 0x52;
    public static final int VK_S              = 0x53;
    public static final int VK_T              = 0x54;
    public static final int VK_U              = 0x55;
    public static final int VK_V              = 0x56;
    public static final int VK_W              = 0x57;
    public static final int VK_X              = 0x58;
    public static final int VK_Y              = 0x59;
    public static final int VK_Z              = 0x5A;

    public static final int VK_OPEN_BRACKET   = 0x5B;
    public static final int VK_BACK_SLASH     = 0x5C;
    public static final int VK_CLOSE_BRACKET  = 0x5D;
    
}
