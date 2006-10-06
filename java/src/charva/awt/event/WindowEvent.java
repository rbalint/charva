/* class WindowEvent
 *
 * Copyright (C) 2001  R M Pitman
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
 * A low-level event which indicates that a Window has changed its status.
 * This event is generated by a Window when it is opened or closed.
 *
 * The event is passed to each WindowListener object that is registered to
 * receive such events using the window's addWindowListener method.
 */
public class WindowEvent
    extends AWTEvent
{

    public WindowEvent(Window source_, int id_) {
	super(source_, id_);

	/* Only two valid ID's at this stage...
	 */
	if (id_ != AWTEvent.WINDOW_CLOSING &&
		id_ != AWTEvent.WINDOW_OPENED) {

	    throw new IllegalArgumentException(
		"Invalid WindowEvent type");
	}

    }

    /**
     * Returns the Window that is changing its state.
     */
    public Window getWindow() { return (Window) getSource(); }
}
