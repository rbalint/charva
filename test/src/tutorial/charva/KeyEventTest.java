package tutorial.charva;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charva.awt.event.*;
import charva.awt.*;

/**
 * This class demonstrates how to listen for KeyEvents generated
 * by a component, and modify the component's default reaction
 * to such KeyEvents.
 */
class KeyEventTest
        extends JDialog
        implements ActionListener, KeyListener {

    private JCheckBox _checkBox1 = new JCheckBox("System ON");
    private JCheckBox _checkBox2 = new JCheckBox("Alarm ON");
    private JCheckBox _checkBox3 = new JCheckBox("System Armed");
    private JTextField _capsField;

    KeyEventTest(Frame owner_) {
        super(owner_, "KeyEvent Test");
        Container contentPane = getContentPane();

        contentPane.add(makeNorthPanel(), BorderLayout.NORTH);

        contentPane.add(makeCenterPanel(), BorderLayout.CENTER);

        JPanel southpan = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);

        contentPane.add(southpan, BorderLayout.SOUTH);

        pack();
    }

    /**
     * Implements the ActionListener interface
     */
    public void actionPerformed(ActionEvent e_) {
        String cmd = e_.getActionCommand();
        if (cmd.equals("OK"))
            hide();
    }

    /**
     * Implements the KeyListener interface
     */
    public void keyPressed(KeyEvent e_) {
        Component src = (Component) e_.getSource();
        int key = e_.getKeyCode();
        if (key == KeyEvent.VK_DOWN &&
                (src == _checkBox1 ||
                        src == _checkBox2 || src == _checkBox3)) {

            /* Move the keyboard input focus to the textfield below.
             */
            _capsField.requestFocus();

            /* "Consume" the keystroke so that it is not interpreted
             * further by the JCheckBox which generated it.
             */
            e_.consume();

            /* Repaint the dialog-box to update the cursor position.
             */
            repaint();
        }
    }

    /**
     * Implements the KeyListener interface
     */
    public void keyTyped(KeyEvent e_) {
    }

    public void keyReleased(KeyEvent e_) {
    }

    private JPanel makeNorthPanel() {
        JPanel northpan = new JPanel();
        northpan.setBorder(new TitledBorder("A set of JCheckBoxes"));
        northpan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(1, 1, 1, 1);
        JLabel label = new JLabel("Press CURSOR-DOWN to move to the text-field below");
        northpan.add(label, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        northpan.add(_checkBox1, gbc);
        _checkBox1.addKeyListener(this);

        gbc.gridx = 1;
        northpan.add(_checkBox2, gbc);
        _checkBox2.addKeyListener(this);

        gbc.gridx = 2;
        northpan.add(_checkBox3, gbc);
        _checkBox3.addKeyListener(this);

        return northpan;
    }

    private JPanel makeCenterPanel() {
        JPanel centerpan = new JPanel();
        centerpan.setBorder(new TitledBorder("A Text Field that converts to uppercase"));
        centerpan.setLayout(new BorderLayout());

        JPanel southpan = new JPanel();
        southpan.add(new JLabel("CapsTextField: "));
        _capsField = new JTextField("THIS FIELD AUTOMATICALLY CONVERTS TO UPPERCASE");
        _capsField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                if (ke.getKeyChar() >= 'a' && ke.getKeyChar() <= 'z') {
                    ke.setKeyChar((char) (ke.getKeyChar() - ('a' - 'A')));
                }
            }
        });
        southpan.add(_capsField);
        centerpan.add(southpan, BorderLayout.SOUTH);

        return centerpan;
    }
}
