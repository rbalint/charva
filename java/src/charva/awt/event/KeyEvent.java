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

    public static final int KEY_PRESSED = 1;
    public static final int KEY_TYPED = 2;

    /**
     * KEY_PRESSED events that don't map to a valid character
     * cause the keyPressed() method to return this value.
     */
    public static final char CHAR_UNDEFINED = (char) -1;

    public static final int VK_UNDEFINED = -1;
    public static final int VK_ESCAPE = 0x1b;
    public static final int VK_DOWN = 0402;
    public static final int VK_UP = 0403;
    public static final int VK_LEFT = 0404;
    public static final int VK_RIGHT = 0405;
    public static final int VK_HOME = 0406;
    public static final int VK_BACK_SPACE = 0407;
    public static final int VK_F1 = 0411;
    public static final int VK_F2 = 0412;
    public static final int VK_F3 = 0413;
    public static final int VK_F4 = 0414;
    public static final int VK_F5 = 0415;
    public static final int VK_F6 = 0416;
    public static final int VK_F7 = 0417;
    public static final int VK_F8 = 0420;
    public static final int VK_F9 = 0421;
    public static final int VK_F10 = 0422;
    public static final int VK_F11 = 0423;
    public static final int VK_F12 = 0424;
    public static final int VK_F13 = 0425;
    public static final int VK_F14 = 0426;
    public static final int VK_F15 = 0427;
    public static final int VK_F16 = 0430;
    public static final int VK_F17 = 0431;
    public static final int VK_F18 = 0432;
    public static final int VK_F19 = 0433;
    public static final int VK_F20 = 0434;
    public static final int VK_DELETE = 0512;
    public static final int VK_INSERT = 0513;
    public static final int VK_PAGE_DOWN = 0522;
    public static final int VK_PAGE_UP = 0523;
    public static final int VK_ENTER = 0527;
    public static final int VK_BACK_TAB = 0541;
    public static final int VK_END = 0550;
}
