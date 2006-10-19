package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charva.awt.event.ActionListener;
import charva.awt.event.KeyEvent;
import charva.awt.event.ActionEvent;
import charva.awt.Frame;
import charva.awt.Container;
import charva.awt.BorderLayout;

/**
 * This class demonstrates how to use the BorderLayout (which is
 * the default layout for JFrame and JDialog), the BoxLayout and
 * the FlowLayout (which is the default layout for JPanel).
 */
class LayoutTest
        extends JDialog
        implements ActionListener {

    public LayoutTest(Frame owner_) {
        super(owner_, "Miscellaneous Layout Test");
        setLocation(3, 3);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());    // default layout for JDialog

        JPanel toppan = new JPanel();
        toppan.setBorder(new TitledBorder("North Panel"));
        toppan.add(new JLabel("north panel uses FlowLayout"));
        contentPane.add(toppan, BorderLayout.NORTH);

        JPanel westpan = new JPanel();
        westpan.setBorder(new TitledBorder("West Panel"));
        westpan.setLayout(new BoxLayout(westpan, BoxLayout.Y_AXIS));
        westpan.add(new JLabel("west panel uses BoxLayout"));
        westpan.add(new JTextField("JTextField #1."));
        westpan.add(new JTextField("JTextField #2."));
        westpan.add(new JTextField("JTextField #3."));
        westpan.add(new JTextField("JTextField #4."));
        westpan.add(new JTextField("JTextField #5."));
        contentPane.add(westpan, BorderLayout.WEST);

        JPanel eastpan = new JPanel();
        eastpan.setBorder(new TitledBorder("East Panel"));
        eastpan.add(new JTextField("A JTextField"));
        contentPane.add(eastpan, BorderLayout.EAST);

        JPanel centerpan = new JPanel();
        centerpan.setLayout(new BorderLayout());
        centerpan.setBorder(new TitledBorder("Center Panel"));
        centerpan.add(new JLabel("A label in the center"), BorderLayout.CENTER);
        contentPane.add(centerpan, BorderLayout.CENTER);

        JPanel southpan = new JPanel();
        southpan.setBorder(new TitledBorder("South Panel"));
        southpan.add(new JLabel("A label in the south: "));
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

