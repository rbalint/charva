package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charvax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import charva.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import charva.awt.Frame;
import java.awt.Color;
import charva.awt.Container;
import charva.awt.BorderLayout;

/**
 * This class is based on the MiscellaneousLayoutTest but it
 * demonstrates how to set the foreground and background colors
 * of dialogs and components.
 */
class ColorLayoutTest
        extends JDialog
        implements ActionListener {
    public ColorLayoutTest(Frame owner_) {
        super(owner_,
                "Layout Test in Color (yellow foreground, green background)");
        setLocation(3, 3);
        setForeground(Color.yellow);
        setBackground(Color.green);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());    // default layout for JDialog

        JPanel toppan = new JPanel();
        toppan.setBorder(new TitledBorder("North Panel (inherits green background)"));
        toppan.setForeground(Color.blue);
        contentPane.add(toppan, BorderLayout.NORTH);

        JRadioButton button1 = new JRadioButton("A JRadioButton...  ");
        JRadioButton button2 = new JRadioButton("And another JRadioButton");
        ButtonGroup buttons = new ButtonGroup();
        buttons.add(button1);
        buttons.add(button2);
        button1.setSelected(true);
        toppan.add(button1);
        toppan.add(button2);

        JPanel westpan = new JPanel();
        westpan.setBorder(new TitledBorder("West Panel"));
        westpan.setLayout(new BoxLayout(westpan, BoxLayout.Y_AXIS));
        JLabel label1 = new JLabel("Magenta label in west panel");
        westpan.add(label1);
        label1.setForeground(Color.magenta);
        JTextField textfield1 = new JTextField("Cyan JTextField, red background");
        textfield1.setForeground(Color.cyan);
        textfield1.setBackground(Color.red);
        westpan.add(textfield1);

        JTextField whiteTextField = new JTextField("White JTextField");
        whiteTextField.setForeground(Color.white);
        westpan.add(whiteTextField);
        JTextField blueTextField = new JTextField("Blue JTextField");
        blueTextField.setForeground(Color.blue);
        westpan.add(blueTextField);
        JTextField yellowTextField = new JTextField("Yellow JTextField");
        yellowTextField.setForeground(Color.yellow);
        westpan.add(yellowTextField);
        JTextField blackTextField = new JTextField("Black JTextField");
        blackTextField.setForeground(Color.black);
        westpan.add(blackTextField);
        contentPane.add(westpan, BorderLayout.WEST);

        JPanel eastpan = new JPanel();
        eastpan.setForeground(Color.black);
        eastpan.setBorder(new TitledBorder("East Panel"));
        eastpan.add(new JTextField("A JTextField"));
        contentPane.add(eastpan, BorderLayout.EAST);

        JPanel centerpan = new JPanel();
        centerpan.setForeground(Color.white);
        centerpan.setBackground(Color.black);
        centerpan.setLayout(new BorderLayout());
        LineBorder centerpan_lineborder = new LineBorder(Color.green);
        TitledBorder centerpan_titledborder =
                new TitledBorder(centerpan_lineborder, "Green border, yellow title",
                        0, 0, null, Color.yellow);
        centerpan.setBorder(centerpan_titledborder);
        centerpan.add(new JLabel("A white label in the center"), BorderLayout.CENTER);
        contentPane.add(centerpan, BorderLayout.CENTER);

        JPanel southpan = new JPanel();
        southpan.setBorder(new TitledBorder("South Panel (white foreground)"));
        southpan.setBackground(Color.blue);
        southpan.setForeground(Color.white);
        JLabel labelsouth = new JLabel("A green label in the south panel ");
        labelsouth.setForeground(Color.green);
        southpan.add(labelsouth);
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);
        contentPane.add(southpan, BorderLayout.SOUTH);
        okButton.setMnemonic(KeyEvent.VK_F10);
        pack();
    }

    public void actionPerformed(ActionEvent ae_) {
        if (ae_.getActionCommand().equals("OK")) {
            hide();
        }
    }
}

