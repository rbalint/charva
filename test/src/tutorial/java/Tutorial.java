/* class Tutorial
 *
 * R Pitman, 2003
 * Last updated: 28 April, 2003.
 *
 * This class performs a general test of the functionality of the
 * CHARVA library.
 */

package tutorial.java;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Tutorial
        extends JFrame
        implements ActionListener {

    private static final Log LOG = LogFactory.getLog(Tutorial.class);

    public Tutorial() {
        super("Charva Demo - copyright R Pitman, 2004-2006");
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        setForeground(Color.black);
        setBackground(Color.white);

        JMenuBar menubar = new JMenuBar();
        JMenu jMenuFile = new JMenu("File");
        jMenuFile.setMnemonic('F');

        JMenuItem jMenuItemFileChooser = new JMenuItem("JFileChooser", 'F');
        jMenuItemFileChooser.addActionListener(this);
        jMenuFile.add(jMenuItemFileChooser);

        JMenuItem jMenuItemCustomFileChooser = new JMenuItem("custom FileChooser", 'c');
        jMenuItemCustomFileChooser.addActionListener(this);
        jMenuFile.add(jMenuItemCustomFileChooser);

        jMenuFile.addSeparator();

        JMenuItem jMenuItemFileExit = new JMenuItem("Exit", 'x');
        jMenuItemFileExit.addActionListener(this);
        jMenuFile.add(jMenuItemFileExit);

        JMenu jMenuLayout = new JMenu("Layouts");
        jMenuLayout.setMnemonic('L');
        JMenuItem jMenuItemLayoutNull = new JMenuItem("Null Layout");
        jMenuItemLayoutNull.setMnemonic('N');
        jMenuItemLayoutNull.addActionListener(this);
        jMenuLayout.add(jMenuItemLayoutNull);

        jMenuLayout.addSeparator();

        JMenuItem jMenuItemLayoutMisc = new JMenuItem("Miscellaneous Layouts");
        jMenuItemLayoutMisc.setMnemonic('M');
        jMenuItemLayoutMisc.addActionListener(this);
        jMenuLayout.add(jMenuItemLayoutMisc);

        JMenuItem jMenuItemLayoutGBL = new JMenuItem("GridBagLayout");
        jMenuItemLayoutGBL.setMnemonic('G');
        jMenuItemLayoutGBL.addActionListener(this);
        jMenuLayout.add(jMenuItemLayoutGBL);

        JMenu jMenuContainers = new JMenu("Containers");
        jMenuContainers.setMnemonic('C');

        JMenuItem jMenuItemContainerJTabbedPane = new JMenuItem("JTabbedPane");
        jMenuItemContainerJTabbedPane.setMnemonic('T');
        jMenuItemContainerJTabbedPane.addActionListener(this);
        jMenuContainers.add(jMenuItemContainerJTabbedPane);

        JMenu jMenuItemContainerJOptionPane = new JMenu("JOptionPane...");
        jMenuItemContainerJOptionPane.setMnemonic('O');
        jMenuContainers.add(jMenuItemContainerJOptionPane);

        JMenuItem jMenuItemShowMessageDialog =
                new JMenuItem("showMessageDialog");
        jMenuItemShowMessageDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowMessageDialog);

        JMenuItem jMenuItemShowConfirmDialog =
                new JMenuItem("showConfirmDialog");
        jMenuItemShowConfirmDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowConfirmDialog);

        JMenuItem jMenuItemShowInputDialog =
                new JMenuItem("showInputDialog");
        jMenuItemShowInputDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowInputDialog);

        JMenuItem jMenuItemShowCustomInputDialog =
                new JMenuItem("show Custom InputDialog");
        jMenuItemShowCustomInputDialog.addActionListener(this);
        jMenuItemContainerJOptionPane.add(jMenuItemShowCustomInputDialog);

        JMenu jMenuWidgets = new JMenu("Widgets");
        jMenuWidgets.setMnemonic('W');

        JMenuItem jMenuItemWidgetText = new JMenuItem("Text components");
        jMenuItemWidgetText.setMnemonic('T');
        jMenuItemWidgetText.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetText);

        JMenuItem jMenuItemWidgetSelection = new JMenuItem("Selection components");
        jMenuItemWidgetSelection.setMnemonic('S');
        jMenuItemWidgetSelection.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetSelection);

        JMenuItem jMenuItemWidgetButtons = new JMenuItem("Buttons");
        jMenuItemWidgetButtons.setMnemonic('B');
        jMenuItemWidgetButtons.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetButtons);

        JMenuItem jMenuItemWidgetJTable = new JMenuItem("JTable");
        jMenuItemWidgetJTable.setMnemonic('J');
        jMenuItemWidgetJTable.addActionListener(this);
        jMenuWidgets.add(jMenuItemWidgetJTable);

        JMenu jMenuEvents = new JMenu("Events");
        jMenuEvents.setMnemonic('E');

        JMenuItem jMenuItemKeyEvents = new JMenuItem("KeyEvents");
        jMenuItemKeyEvents.setMnemonic('K');
        jMenuItemKeyEvents.addActionListener(this);
        jMenuEvents.add(jMenuItemKeyEvents);

        JMenuItem jMenuItemFocusEvents = new JMenuItem("FocusEvents");
        jMenuItemFocusEvents.setMnemonic('F');
        jMenuItemFocusEvents.addActionListener(this);
        jMenuEvents.add(jMenuItemFocusEvents);

        JMenu jMenuThreads = new JMenu("Threads");
        jMenuThreads.setMnemonic('T');

        JMenuItem jMenuItemProgressBar = new JMenuItem("JProgressBar");
        jMenuItemProgressBar.setMnemonic('P');
        jMenuItemProgressBar.addActionListener(this);
        jMenuThreads.add(jMenuItemProgressBar);

        menubar.add(jMenuFile);
        menubar.add(jMenuLayout);
        menubar.add(jMenuContainers);
        menubar.add(jMenuWidgets);
        menubar.add(jMenuEvents);
        menubar.add(jMenuThreads);

        setJMenuBar(menubar);

        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        labelPanel.add(new JLabel("Use LEFT and RIGHT cursor keys to select a menu."));
        labelPanel.add(new JLabel("Use ENTER to invoke a menu or menu-item."));
        labelPanel.add(new JLabel("(You can also use the " +
                "underlined \"mnemonic key\" to invoke a menu.)"));
        labelPanel.add(new JLabel("Use BACKSPACE or ESC to dismiss a menu."));
        contentPane.add(labelPanel, BorderLayout.SOUTH);

        setLocation(0, 0);
        setSize(800, 240);
        validate();
    }

    public static void main(String[] args) {
        Tutorial testwin = new Tutorial();
        testwin.show();
    }

    public void actionPerformed(ActionEvent ae_) {
        String actionCommand = ae_.getActionCommand();
        if (actionCommand.equals("Exit")) {
            System.gc();    // so that HPROF reports only live objects.
            System.exit(0);
        } else if (actionCommand.equals("JFileChooser")) {
            JFileChooser dlg = new JFileChooser();
            dlg.setDialogTitle("A File Chooser");
            dlg.setLocation(2, 2);
            dlg.setForeground(Color.white);
            dlg.setBackground(Color.blue);
            dlg.setFileSelectionMode(JFileChooser.FILES_ONLY);

//  Uncomment this section of code to apply a FileFilter that masks out all
//  files whose names do not end with ".java".
//	    /* Construct an anonymous inner class that extends the abstract
//	     * FileFilter class.
//	     */
//	    charvax.swing.filechooser.FileFilter filter = 
//		new charvax.swing.filechooser.FileFilter() {
//		public boolean accept(File file_) {
//		    String pathname = file_.getAbsolutePath();
//		    return (pathname.endsWith(".java"));
//		}
//	    };
//	    dlg.setFileFilter(filter);

            if (dlg.showDialog(this, "Open File") ==
                    JFileChooser.APPROVE_OPTION) {

                JOptionPane.showMessageDialog(this, "The selected file was " +
                        dlg.getSelectedFile().getAbsolutePath(),
                        "Results of JFileChooser", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The CANCEL button was selected",
                        "Results of JFileChooser", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (actionCommand.equals("custom FileChooser")) {
            //JFileChooser.CANCEL_LABEL = "Cancel (F1)";
            //JFileChooser.CANCEL_ACCELERATOR = KeyEvent.VK_F1;
            (new JFileChooser()).show();
        } else if (actionCommand.equals("Null Layout")) {
            JDialog dlg = new NullLayoutTest(this);
            dlg.show();
        } else if (actionCommand.equals("Miscellaneous Layouts")) {
            JDialog dlg = new LayoutTest(this);
            dlg.show();
        } else if (actionCommand.equals("GridBagLayout")) {
            JDialog dlg = new GridBagLayoutTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("JTabbedPane")) {
            JDialog dlg = new JTabbedPaneTest(this);
            dlg.show();
        } else if (actionCommand.equals("showMessageDialog")) {
            JOptionPane.showMessageDialog(this, "This is an example of a Message Dialog " +
                    "with a single message string",
                    "This is the title", JOptionPane.PLAIN_MESSAGE);
        } else if (actionCommand.equals("showConfirmDialog")) {
            showConfirmDialog();
        } else if (actionCommand.equals("showInputDialog")) {
            showInputDialog();
        } else if (actionCommand.equals("show Custom InputDialog")) {
            showCustomInputDialog();
        } else if (actionCommand.equals("Text components")) {
            TextWidgetTest dlg = new TextWidgetTest(this);
            dlg.show();
        } else if (actionCommand.equals("Selection components")) {
            SelectionTest dlg = new SelectionTest(this);
            dlg.show();
        } else if (actionCommand.equals("Buttons")) {
            (new ButtonTest(this)).show();
        } else if (actionCommand.equals("JTable")) {
            JTableTest dlg = new JTableTest(this);
            dlg.show();
        } else if (actionCommand.equals("KeyEvents")) {
            KeyEventTest dlg = new KeyEventTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("FocusEvents")) {
            FocusEventTest dlg = new FocusEventTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else if (actionCommand.equals("JProgressBar")) {
            ProgressBarTest dlg = new ProgressBarTest(this);
            dlg.setLocationRelativeTo(this);
            dlg.show();
        } else {
            JOptionPane.showMessageDialog(this, "Menu item \"" + actionCommand +
                    "\" not implemented yet",
                    "Error",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    /**
     * Demonstrate the JOptionPane.showConfirmDialog() method.
     */
    private void showConfirmDialog() {
        String[] messages = {
                "This is an example of a Confirm Dialog",
                "that displays an array of Strings"};

        int option = JOptionPane.showConfirmDialog(this, messages, "Select an Option",
                JOptionPane.YES_NO_CANCEL_OPTION);
        String result;
        if (option == JOptionPane.YES_OPTION)
            result = "User selected YES option";
        else if (option == JOptionPane.NO_OPTION)
            result = "User selected NO option";
        else
            result = "User selected Cancel option";
        JOptionPane.showMessageDialog(this, result,
                "Result of showConfirmDialog",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Demonstrate the JOptionPane.showInputDialog() method.
     */
    private void showInputDialog() {
        String[] messages = {
                "This is an example of an Input Dialog",
                "that displays an array of Strings"};
        String result = JOptionPane.showInputDialog(this, messages, "Input a value",
                JOptionPane.QUESTION_MESSAGE);
        String msg;
        if (result == null)
            msg = "User selected Cancel option";
        else
            msg = "User entered \"" + result + "\"";
        JOptionPane.showMessageDialog(this, msg,
                "Result of showInputDialog",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Demonstrate how to customize the JOptionPane by subclassing it.
     */
    private void showCustomInputDialog() {
        String[] messages = {
                "This shows how to customize the buttons",
                "of the JOptionPane."};

        /* Change the (static) labels and accelerators in the
         * JOptionPane. Note that the buttons stay customized
         * for future invocations of the JOptionPane methods,
         * until they are customized again.
         */
        //JOptionPane.OK_LABEL = "OK (F1)";
        //JOptionPane.OK_ACCELERATOR = KeyEvent.VK_F1;
        //JOptionPane.YES_LABEL = "Yes (F2)";
        //JOptionPane.YES_ACCELERATOR = KeyEvent.VK_F2;
        //JOptionPane.NO_LABEL = "No (F3)";
        //JOptionPane.NO_ACCELERATOR = KeyEvent.VK_F3;
        //JOptionPane.CANCEL_LABEL = "Cancel (F4)";
        //JOptionPane.CANCEL_ACCELERATOR = KeyEvent.VK_F4;

        String result = JOptionPane.showInputDialog(this, messages, "Input a value",
                JOptionPane.QUESTION_MESSAGE);
        String msg = null;
        if (result == null)
            msg = "User selected Cancel option";
        else
            msg = "User entered \"" + result + "\"";
        JOptionPane.showMessageDialog(this, msg,
                "Result of showInputDialog",
                JOptionPane.PLAIN_MESSAGE);
    }

}
