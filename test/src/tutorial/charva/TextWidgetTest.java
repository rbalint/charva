package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charvax.swing.border.EmptyBorder;
import charva.awt.event.ActionListener;
import charva.awt.event.ActionEvent;
import charva.awt.event.ItemListener;
import charva.awt.event.ItemEvent;
import charva.awt.*;

/**
 * This class demonstrates how to use the JTextField, the
 * JPasswordField and the JTextArea.
 */
class TextWidgetTest
        extends JDialog
        implements ActionListener {

    TextWidgetTest(Frame owner_) {
        super(owner_, "Text Widget Test");
        Container contentPane = getContentPane();

        JPanel centerpan = new JPanel();
        centerpan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        centerpan.add(new TextFieldPanel(), gbc);

        gbc.gridx = 1;
        centerpan.add(new PasswordFieldPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerpan.add(new TextAreaPanel(), gbc);

        JPanel southpan = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);

        contentPane.add(centerpan, BorderLayout.CENTER);
        contentPane.add(southpan, BorderLayout.SOUTH);
        pack();
    }

    public void actionPerformed(ActionEvent e_) {
        if (e_.getActionCommand().equals("OK"))
            hide();
    }

    /**
     * An inner class to display a JTextField.
     */
    class TextFieldPanel extends JPanel
            implements ItemListener {
        private JCheckBox _enabled;
        private JCheckBox _visible;
        private JTextField _textfield;

        TextFieldPanel() {
            setLayout(new BorderLayout());
            setBorder(new TitledBorder("JTextField"));

            JPanel northpan = new JPanel();
            _enabled = new JCheckBox("Enabled");
            _enabled.setSelected(true);
            _enabled.addItemListener(this);
            northpan.add(_enabled);
            _visible = new JCheckBox("Visible");
            _visible.setSelected(true);
            _visible.addItemListener(this);
            northpan.add(_visible);

            JPanel southpan = new JPanel();
            _textfield = new JTextField("This is some text.....");
            _textfield.setBorder(new EmptyBorder(1, 1, 1, 1));
            southpan.add(_textfield);

            add(northpan, BorderLayout.NORTH);
            add(southpan, BorderLayout.SOUTH);
        }

        public void itemStateChanged(ItemEvent e_) {
            if (e_.getSource() == _enabled) {
                _textfield.setEnabled(_enabled.isSelected());
            } else {
                _textfield.setVisible(_visible.isSelected());
            }
        }
    }

    /**
     * An inner class to display a JPasswordField.
     */
    class PasswordFieldPanel extends JPanel
            implements ItemListener {
        private JCheckBox _enabled;
        private JCheckBox _visible;
        private JPasswordField _textfield;

        PasswordFieldPanel() {
            setLayout(new BorderLayout());
            setBorder(new TitledBorder("JPasswordField"));

            JPanel northpan = new JPanel();
            _enabled = new JCheckBox("Enabled");
            _enabled.setSelected(true);
            _enabled.addItemListener(this);
            northpan.add(_enabled);
            _visible = new JCheckBox("Visible");
            _visible.setSelected(true);
            _visible.addItemListener(this);
            northpan.add(_visible);

            JPanel southpan = new JPanel();
            _textfield = new JPasswordField("This is some text.....");
            _textfield.setBorder(new EmptyBorder(1, 1, 1, 1));
            southpan.add(_textfield);

            add(northpan, BorderLayout.NORTH);
            add(southpan, BorderLayout.SOUTH);
        }

        public void itemStateChanged(ItemEvent e_) {
            if (e_.getSource() == _enabled) {
                _textfield.setEnabled(_enabled.isSelected());
            } else {
                _textfield.setVisible(_visible.isSelected());
            }
        }
    }

    /**
     * An inner class to display a JTextArea.
     */
    class TextAreaPanel extends JPanel
            implements ItemListener {
        private JCheckBox _linewrap;
        private JCheckBox _linewrapstyle;
        private JTextArea _textarea;

        TextAreaPanel() {
            setLayout(new BorderLayout());
            setBorder(new TitledBorder("JTextArea in a JScrollPane"));

            JPanel northpan = new JPanel();
            _linewrap = new JCheckBox("Line Wrap ");
            _linewrap.setSelected(false);
            _linewrap.addItemListener(this);
            northpan.add(_linewrap);
            _linewrapstyle = new JCheckBox("Line Wrap Style = Word");
            _linewrapstyle.setSelected(false);
            _linewrapstyle.setEnabled(false);
            _linewrapstyle.addItemListener(this);
            northpan.add(_linewrapstyle);

            _textarea = new JTextArea("Contents of the JTextArea...", 8, 50);
            JScrollPane scrollpane = new JScrollPane(_textarea);
            scrollpane.setViewportBorder(new TitledBorder("Text Area"));

            add(northpan, BorderLayout.NORTH);
            add(scrollpane, BorderLayout.SOUTH);
        }

        public void itemStateChanged(ItemEvent e_) {
            Component source = (Component) e_.getSource();
            if (source == _linewrap) {
                _textarea.setLineWrap(_linewrap.isSelected());
                if (_textarea.getLineWrap() == false)
                    _linewrapstyle.setSelected(false);
                _linewrapstyle.setEnabled(_textarea.getLineWrap());
            } else {
                _textarea.setWrapStyleWord(_linewrapstyle.isSelected());
            }
        }
    }
}

