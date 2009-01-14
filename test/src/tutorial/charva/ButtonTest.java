package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charvax.swing.border.EmptyBorder;
import charva.awt.event.*;
import charva.awt.Frame;
import charva.awt.Container;
import charva.awt.BorderLayout;
import charva.awt.Component;

/**
 * This class demonstrates how to use the various types of Buttons.
 */
class ButtonTest
        extends JDialog
        implements ActionListener, KeyListener, ItemListener {
    
    private ButtonGroup _buttons = new ButtonGroup();
    private JRadioButton _strawberry = new JRadioButton("Strawberry");
    private JRadioButton _chocolate = new JRadioButton("Chocolate");
    private JRadioButton _vanilla = new JRadioButton("Vanilla");
    private JRadioButton _pistachio = new JRadioButton("Pistachio");
    private JRadioButton _lime = new JRadioButton("Lime");
    private JTextField _selectedFlavor = new JTextField(15);
    private JCheckBox _nutTopping = new JCheckBox("Nuts ");
    private JCheckBox _syrupTopping = new JCheckBox("Syrup ");
    private JCheckBox _candyTopping = new JCheckBox("Candy ");
    private JCheckBox _waferTopping = new JCheckBox("Wafer ");

    ButtonTest(Frame owner_) {
        super(owner_, "Button Test");
        Container contentPane = getContentPane();

        contentPane.add(makeNorthPanel(), BorderLayout.NORTH);

        contentPane.add(makeCenterPanel(), BorderLayout.CENTER);

        JPanel southpan = new JPanel();
        JButton okButton = new JButton("OK (F9)");
        okButton.setActionCommand("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);

        contentPane.add(southpan, BorderLayout.SOUTH);

        /* Add a KeyListener for this entire window; any key
         * pressed on any component in this window will cause
         * keyPressed (or keyTyped) to be called.
         */
        addKeyListener(this);
        pack();
    }

    /**
     * Implements the ActionListener interface.
     */
    public void actionPerformed(ActionEvent e_) {
        String cmd = e_.getActionCommand();
        if (cmd.equals("OK")) {
            hide();
        }
    }

    /**
     * Implements ItemListener interface
     */
    public void itemStateChanged(ItemEvent e_) {

        int statechange = e_.getStateChange();
        Component source = (Component) e_.getSource();
        if (statechange == ItemEvent.SELECTED) {
            JRadioButton button = (JRadioButton) source;
            _selectedFlavor.setText(button.getText());
        }
    }

    public void keyPressed(KeyEvent e_) {
        int key = e_.getKeyCode();
        Object src = e_.getSource();

        if (key == KeyEvent.VK_F9) {
            /* Consume the event so it doesn't get processed
             * further by the component that generated it.
             */
            e_.consume();
            hide();
            return;
        }

        if (key == KeyEvent.VK_UP) {
            if (src == _chocolate)
                _strawberry.requestFocus();
            else if (src == _vanilla)
                _chocolate.requestFocus();
            else if (src == _pistachio)
                _vanilla.requestFocus();
            else if (src == _lime)
                _pistachio.requestFocus();
            e_.consume();
            repaint();
        } else if (key == KeyEvent.VK_DOWN) {
            if (src == _strawberry)
                _chocolate.requestFocus();
            else if (src == _chocolate)
                _vanilla.requestFocus();
            else if (src == _vanilla)
                _pistachio.requestFocus();
            else if (src == _pistachio)
                _lime.requestFocus();
            else if (src == _lime)
                _nutTopping.requestFocus();
            e_.consume();
            repaint();
        }
    }

    public void keyTyped(KeyEvent e_) {
    }

    public void keyReleased(KeyEvent e_) {
    }

    private JPanel makeNorthPanel() {
        JPanel northpan = new JPanel();
        northpan.setBorder(new TitledBorder("Select a flavor"));
        northpan.setLayout(new BoxLayout(northpan, BoxLayout.Y_AXIS));

        northpan.add(_strawberry);
        northpan.add(_chocolate);
        northpan.add(_vanilla);
        northpan.add(_pistachio);
        northpan.add(_lime);

        _strawberry.addItemListener(this);
        _strawberry.setActionCommand("Strawberry");
        _chocolate.addItemListener(this);
        _chocolate.setActionCommand("Chocolate");
        _vanilla.addItemListener(this);
        _vanilla.setActionCommand("Vanilla");
        _pistachio.addItemListener(this);
        _pistachio.setActionCommand("Pistachio");
        _lime.addItemListener(this);
        _lime.setActionCommand("Lime");

        JPanel panel = new JPanel();
        panel.add(new JLabel("Selected flavor: "));
        panel.add(_selectedFlavor);
        _selectedFlavor.setEnabled(false);
        panel.setBorder(new EmptyBorder(1, 1, 1, 1));
        northpan.add(panel);

        _buttons.add(_strawberry);
        _strawberry.setSelected(true);    // select one button in the group
        _buttons.add(_chocolate);
        _buttons.add(_vanilla);
        _buttons.add(_pistachio);
        _buttons.add(_lime);

        return northpan;
    }

    private JPanel makeCenterPanel() {
        JPanel centerpan = new JPanel();
        centerpan.setBorder(new TitledBorder("Select one or more toppings"));
        centerpan.add(_nutTopping);
        centerpan.add(_syrupTopping);
        centerpan.add(_candyTopping);
        centerpan.add(_waferTopping);

        return centerpan;
    }
}

