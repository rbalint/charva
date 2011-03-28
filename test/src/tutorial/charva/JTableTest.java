package tutorial.charva;

import java.awt.Dimension;

import charvax.swing.*;
import charvax.swing.border.EmptyBorder;
import charvax.swing.border.TitledBorder;
import charvax.swing.border.LineBorder;
import charva.awt.event.*;
import charva.awt.*;

/**
 * This class demonstrates how to use the JTable component
 * in a JScrollPane.
 */
class JTableTest
        extends JDialog
        implements ActionListener, ItemListener {

    private JTable _table;
    private JTextField _selectedColumns = new JTextField(10);
    private JTextField _selectedRows = new JTextField(10);
    private JCheckBox _checkBoxAllowRowSelection =
            new JCheckBox("Allow row selection");
    private JCheckBox _checkBoxAllowColumnSelection =
            new JCheckBox("Allow column selection");
    private JCheckBox _checkBoxAllowMultipleSelection =
            new JCheckBox("Allow multiple selection");
    private JButton _okButton;
//    private JScrollBar _scrollbar;

    JTableTest(Frame owner_) {
        super(owner_, "JTable in a JScrollPane");
        _insets = new Insets(3, 3, 3, 3);
        Container contentPane = getContentPane();

        JPanel northpan = new JPanel();
        northpan.setBorder(new EmptyBorder(1, 1, 1, 1));
        northpan.add(new JLabel("Press ENTER to select/deselect columns/rows"));
        contentPane.add(northpan, BorderLayout.NORTH);

        contentPane.add(makeCenterPanel(), BorderLayout.CENTER);

        contentPane.add(makeEastPanel(), BorderLayout.EAST);

        _okButton = new JButton("OK");
        _okButton.addActionListener(this);
        contentPane.add(_okButton, BorderLayout.SOUTH);

        pack();
    }

    public void actionPerformed(ActionEvent e_) {
        hide();
    }

    public void itemStateChanged(ItemEvent e_) {
        Object source = e_.getSource();
        if (source == _checkBoxAllowRowSelection) {
            boolean allowed = _checkBoxAllowRowSelection.isSelected();
            _table.setRowSelectionAllowed(allowed);
        } else if (source == _checkBoxAllowColumnSelection) {
            boolean allowed = _checkBoxAllowColumnSelection.isSelected();
            _table.setColumnSelectionAllowed(allowed);
        } else if (source == _checkBoxAllowMultipleSelection) {
            boolean allowed = _checkBoxAllowMultipleSelection.isSelected();
            if (allowed)
                _table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            else
                _table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }

    private JPanel makeCenterPanel() {
        JPanel centerpan = new JPanel();
        centerpan.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 1));
        String[] headings = {"Name", "Color", "Composition", "Mass", "Radius", "Orbit"};
        String[][] data = {{"Mars", "Red", "Dust", "1.5e10", "2.7e6", "Elliptical"},
                {"Pluto", "Blue", "Rock", "2.3e11", "2.9e7", "Circular"},
                {"Luna", "Green", "Cheese", "1.3e5", "2.3e12", "Square"},
                {"Venus", "White", "Gas", "4.3e5", "2.3e12",
                        "A funny irregular shape whose name is longer than the table width"},
                {"Jupiter", "Black", "Marshmallow", "4.3e6", "2.3e12", "Zigzag"},
                {"Neptune", "Purple", "Gas", "1.2e6", "2.4e2", "Elliptical"},
                {"Saturn", "Yellow", "Gas", "1.1e7", "1.4e6", "Circular"}};

        /* The following inner class overrides the processKeyEvent() method
         * of JTable, so that we can display the selected rows and columns.
         */
        _table = new JTable(data, headings) {
            /* Gets called when the user presses a key in the JTable.
             */
            public void processKeyEvent(KeyEvent e_) {
                super.processKeyEvent(e_);
                if (e_.getKeyCode() != KeyEvent.VK_ENTER)
                    return;

                int[] rows = getSelectedRows();
                StringBuffer buf = new StringBuffer();
                for (int i = 0; i < rows.length; i++) {
                    buf.append(rows[i]);
                    buf.append(' ');
                }
                _selectedRows.setText(buf.toString());

                int[] columns = getSelectedColumns();
                buf = new StringBuffer();
                for (int i = 0; i < columns.length; i++) {
                    buf.append(columns[i]);
                    buf.append(' ');
                }
                _selectedColumns.setText(buf.toString());
            }
        };
        _table.setPreferredScrollableViewportSize(new Dimension(30, 5));
        //_table.setValueAt("Yellow", 5, 2);
        //_table.setValueAt("Red", 7, 4);
        //_table.setValueAt("Magenta", 1, 5);
        JScrollPane scrollPane = new JScrollPane(_table);
        TitledBorder border = new TitledBorder(new LineBorder(Color.cyan));
        border.setTitle("The Heavenly Bodies");
        scrollPane.setViewportBorder(border);
//	scrollPane.setSize(25, 6);
        centerpan.add(scrollPane);

        return centerpan;
    }

    private JPanel makeEastPanel() {
        JPanel eastpan = new JPanel();
        eastpan.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 2;
        eastpan.add(_checkBoxAllowRowSelection, gbc);
        _checkBoxAllowRowSelection.addItemListener(this);
        _checkBoxAllowRowSelection.setSelected(true);

        gbc.gridy = 1;
        eastpan.add(_checkBoxAllowColumnSelection, gbc);
        _checkBoxAllowColumnSelection.addItemListener(this);
        _checkBoxAllowColumnSelection.setSelected(true);

        gbc.gridy = 2;
        eastpan.add(_checkBoxAllowMultipleSelection, gbc);
        _checkBoxAllowMultipleSelection.addItemListener(this);
        _checkBoxAllowMultipleSelection.setSelected(false);

        gbc.gridy = 3;
        gbc.gridwidth = 1;
        eastpan.add(new JLabel(""), gbc);

        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridy = 4;
        eastpan.add(new JLabel("selected columns: "), gbc);

        gbc.gridy = 5;
        eastpan.add(new JLabel("selected rows: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        _selectedColumns.setEnabled(false);
        eastpan.add(_selectedColumns, gbc);

        gbc.gridy = 5;
        _selectedRows.setEnabled(false);
        eastpan.add(_selectedRows, gbc);

        return eastpan;
    }
}

