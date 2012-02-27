/* class SwingUtilities
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

package charvax.swing;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;


/**
 * A collection of utility methods for Swing.
 */
public class SwingUtilities {

    /**
     * Causes the runnable.run() method to be executed asynchronously on
     * the AWT event dispatching thread.
     */
    public static void invokeLater(Runnable runnable) {
        EventQueue.invokeLater(runnable);
    }

    /**
     * Returns the first Window ancestor of c, or null if c is not contained inside a Window.
     *
     * @param c
     * @return the first Window ancestor of c, or null if c is not contained inside a Window.
     */
    public static Window windowForComponent(Component c) {
        return charva.awt.Toolkit.getAncestorWindow(c);
    }

    /**
     * Returns true if the current thread is the event dispatching thread.
     * @return true if the current thread is the event dispatching thread.
     */
    public static boolean isEventDispatchThread() {
        return ("event dispatcher".equals(Thread.currentThread().getName()));
    }
}
