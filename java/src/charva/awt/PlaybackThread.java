/* class PlaybackThread
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

package charva.awt;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * This class reads a scriptfile line by line, parses each line
 * into a time-interval and a gesture-specifier (either KEY or MOUSE), and
 * fires the specified keystroke (or mouse-click) after the specified delay.
 */
public class PlaybackThread extends Thread {

    PlaybackThread(BufferedReader scriptReader_) {
        _scriptReader = scriptReader_;
        _toolkit = Toolkit.getDefaultToolkit();
    }

    public void run() {
        String line;
        int lineno = 0;

        try {
            while ((line = _scriptReader.readLine()) != null) {

                lineno++;
                StringTokenizer st = new StringTokenizer(line);
                String delayToken = st.nextToken();
                long delay = Long.parseLong(delayToken);

                String gestureToken = st.nextToken();
                if (gestureToken.equals("KEY")) {
                    String keycodeToken = st.nextToken();
                    int keycode = Integer.parseInt(keycodeToken, 16);

                    if (delay != 0) {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException ei) {
                        }
                    }

                    _toolkit.fireKeystroke(keycode);

                } else if (gestureToken.equals("MOUSE")) {
                    // It wasn't a keystroke, it must have been a mouse-click
                    String buttonToken = st.nextToken();
                    int button = Integer.parseInt(buttonToken);
                    String xToken = st.nextToken();
                    int x = Integer.parseInt(xToken);
                    String yToken = st.nextToken();
                    int y = Integer.parseInt(yToken);
                    MouseEventInfo mouseEventInfo = new MouseEventInfo(button, x, y);

                    if (delay != 0) {
                        try {
                            Thread.sleep(delay);
                        } catch (InterruptedException ei) {
                        }
                    }
//                    System.err.println("Mouse-click (button " + button +
//                            ") at (" + mouseEventInfo.getX() + "," + mouseEventInfo.getY() + ")");
                    _toolkit.fireMouseEvent(mouseEventInfo);
                } else {
                    throw new RuntimeException("Parse error [" + line + "] on line " + lineno + " of script file ");
                }
            }
        } catch (IOException ei) {
            throw new RuntimeException("Error reading script file", ei);
        }
    }

    //====================================================================
    // INSTANCE VARIABLES

    private BufferedReader _scriptReader;

    private Toolkit _toolkit;
}
