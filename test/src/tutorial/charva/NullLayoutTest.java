package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import charva.awt.Frame;
import java.awt.Container;

/**
 * This class demonstrates how to lay out components manually,
 * by setting the LayoutManager to "null".
 * In Charva, you can set a component's position within
 * its container with "setLocation()"; but in Swing, you
 * have to use "setBounds()". Also, of course, in Charva
 * the units are rows and columns whereas in Swing they
 * are pixels.
 */
class NullLayoutTest
        extends JDialog
        implements ActionListener {

    public NullLayoutTest(Frame owner_) {
        super(owner_, "Null Layout Test");
        setLocation(3, 3);
        setSize(60, 20);
        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        JLabel label0 = new JLabel("Demonstrates how to lay components out manually");
        contentPane.add(label0);
        label0.setLocation(2, 2);

        JPanel panel1 = new JPanel();
        panel1.setLayout(null);
        contentPane.add(panel1);
        panel1.setLocation(2, 3);
        panel1.setSize(40, 6);
        panel1.setBorder(new TitledBorder("Panel1"));

        JLabel label1 = new JLabel("Label 1:");
        panel1.add(label1);
        label1.setLocation(1, 2);

        JTextField textfield1 = new JTextField("Text Field 1");
        panel1.add(textfield1);
        textfield1.setLocation(11, 2);

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        contentPane.add(panel2);
        panel2.setLocation(2, 10);
        panel2.setSize(40, 6);
        panel2.setBorder(new TitledBorder("Panel2"));

        JLabel label2 = new JLabel("Label 2:");
        panel2.add(label2);
        label2.setLocation(1, 2);

        JTextField textfield2 = new JTextField("Text Field 2");
        panel2.add(textfield2);
        textfield2.setLocation(11, 2);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        contentPane.add(okButton);
        okButton.setLocation(25, 17);
        okButton.setMnemonic(0x18); // CTRL-X
    }

    public void actionPerformed(ActionEvent ae_) {
        if (ae_.getActionCommand().equals("OK")) {
            hide();
        }
    }
}

