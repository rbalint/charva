package tutorial.charva;

import charvax.swing.*;
import charvax.swing.border.TitledBorder;
import charvax.swing.event.ListSelectionListener;
import charvax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import charva.awt.*;

/**
 * This class demonstrates how to use the GridBagLayout.
 */
class GridBagLayoutTest
        extends JDialog
        implements ActionListener, ListSelectionListener {

    private JTextField lastnameField = new JTextField(25);
    private JTextField initialsField = new JTextField(5);
    private JTextField address1Field = new JTextField(20);
    private JTextField address2Field = new JTextField(20);
    private JTextField cityField = new JTextField(20);
    private JTextField postcodeField = new JTextField(8);
    private JTextField stateField = new JTextField(15);

    public GridBagLayoutTest(Frame owner_) {
        super(owner_, "GridBagLayout Test");
        Container contentPane = getContentPane();

        JPanel centerpan = new JPanel();
        centerpan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        centerpan.add(new JLabel("Last name: "), gbc);

        gbc.gridy = 1;
        centerpan.add(new JLabel("Address line 1: "), gbc);

        gbc.gridy = 2;
        centerpan.add(new JLabel("Address line 2: "), gbc);

        gbc.gridy = 3;
        centerpan.add(new JLabel("City: "), gbc);

        gbc.gridy = 4;
        centerpan.add(new JLabel("Postal code: "), gbc);

        gbc.gridy = 5;
        centerpan.add(new JLabel("State: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        centerpan.add(lastnameField, gbc);

        gbc.gridx = 2;
        gbc.anchor = GridBagConstraints.EAST;
        centerpan.add(new JLabel(" Initials: "), gbc);

        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.WEST;
        centerpan.add(initialsField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        centerpan.add(address1Field, gbc);

        gbc.gridy = 2;
        centerpan.add(address2Field, gbc);

        gbc.gridy = 3;
        centerpan.add(cityField, gbc);

        gbc.gridy = 4;
        centerpan.add(postcodeField, gbc);

        gbc.gridy = 5;
        centerpan.add(stateField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 6;
        gbc.insets = new Insets(1, 1, 1, 1);
        String[] countries = {"Portugal", "Spain", "France",
                "Italy", "Germany", "Poland", "Austria", "Belgium",
                "Denmark", "Norway", "Sweden"};
        _countryList = new JList(countries);
        _countryList.setVisibleRowCount(6);
        _countryList.setColumns(12);
        _countryList.addListSelectionListener(this);
        JScrollPane scrollpane = new JScrollPane(_countryList);
        TitledBorder viewportBorder = new TitledBorder("Countries");
        scrollpane.setViewportBorder(viewportBorder);
        centerpan.add(scrollpane, gbc);

        JPanel southpan = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);
        southpan.add(okButton);

        contentPane.add(centerpan, BorderLayout.CENTER);
        contentPane.add(southpan, BorderLayout.SOUTH);
        pack();
    }

    public void actionPerformed(ActionEvent ae_) {
        if (ae_.getActionCommand().equals("OK")) {
            hide();
        }
    }

    /**
     * This method implements the ListSelectionListener interface,
     * and is called when an item is selected or deselected in the
     * JList.
     */
    public void valueChanged(ListSelectionEvent e_) {
        _countryList.repaint();
    }

    private JList _countryList;
}

