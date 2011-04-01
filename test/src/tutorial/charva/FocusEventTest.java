package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charvax.swing.border.EmptyBorder;
import charva.awt.event.FocusListener;
import charva.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import charva.awt.event.FocusEvent;
import charva.awt.*;

/**
 * This class demonstrates how to listen for FocusEvents
 * which are generated when a component gains or loses the
 * keyboard input focus.
 */
class FocusEventTest
        extends JDialog
        implements FocusListener, ActionListener {
    
    private JTextField _floatField = new JTextField(15);
    private JTextField _focusLostBy = new JTextField(15);
    private JTextField _focusGainedBy = new JTextField(15);
    private JButton _okButton = new JButton("OK");

    FocusEventTest(Frame owner_) {
        super(owner_, "FocusEvent Test");
        Container contentPane = getContentPane();

        contentPane.add(makeNorthPanel(), BorderLayout.NORTH);

        contentPane.add(makeCenterPanel(), BorderLayout.CENTER);

        JPanel southpan = new JPanel();
        _okButton.addActionListener(this);
        _okButton.addFocusListener(this);
        southpan.add(_okButton);
        contentPane.add(southpan, BorderLayout.SOUTH);

        pack();
    }

    public void actionPerformed(ActionEvent e_) {
        if (e_.getActionCommand().equals("OK")) {
            hide();
        }
    }

    public void focusGained(FocusEvent e_) {
        Object src = e_.getSource();
        if (src == _floatField) {
            _focusGainedBy.setText("_floatField");
        } else if (src == _okButton) {
            _focusGainedBy.setText("_okButton");
        }
    }

    public void focusLost(FocusEvent e_) {
        Object src = e_.getSource();
        if (src == _floatField) {
            _focusLostBy.setText("_floatField");

            float f = 0;
            try {
                f = Float.parseFloat(_floatField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Must be a valid floating-point number",
                        "Error", JOptionPane.PLAIN_MESSAGE);
                _floatField.requestFocus();
            }
        } else if (src == _okButton) {
            _focusLostBy.setText("_okButton");
        }
    }

    private JPanel makeNorthPanel() {
        JPanel northpan = new JPanel();
        northpan.setBorder(new TitledBorder("A floating-point input field"));
        northpan.setLayout(new BorderLayout());

        JLabel label1 = new JLabel("Enter a non-numeric value, and then");
        label1.setBorder(new EmptyBorder(1, 1, 0, 1));
        northpan.add(label1, BorderLayout.NORTH);

        JLabel label2 = new JLabel("try pressing TAB");
        label2.setBorder(new EmptyBorder(0, 1, 1, 1));
        northpan.add(label2, BorderLayout.CENTER);

        northpan.add(_floatField, BorderLayout.SOUTH);
        _floatField.addFocusListener(this);

        return northpan;
    }

    private JPanel makeCenterPanel() {
        JPanel centerpan = new JPanel();
        centerpan.setBorder(new TitledBorder("Status"));
        centerpan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.EAST;
        centerpan.add(new JLabel("Focus lost by: "), gbc);

        gbc.gridy = 1;
        centerpan.add(new JLabel("Focus gained by: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        centerpan.add(_focusLostBy, gbc);
        _focusLostBy.setEnabled(false);

        gbc.gridy = 1;
        centerpan.add(_focusGainedBy, gbc);
        _focusGainedBy.setEnabled(false);

        return centerpan;
    }
}

