package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.FontClass;

/* This class is responsible for the panel that shows a table of 
 * all the registers within the CPU. When the user performs an operation
 * the table will be updated with the new values within the registers!
 */
public class RegisterPanel extends JPanel {

	// Create the GUI componenets
	JPanel menuPanel = new JPanel();
	JLabel title = new JLabel("Registries");
	JScrollPane registerPane;
	DefaultTableModel table = new DefaultTableModel();
	JTable registerTable;

	// Create a constant set to the amount of registers within the CPU
	final int NUM_OF_REGISTERS = 13;

	// constructor creating the JPanel
	public RegisterPanel() {
		// setting up the JPanel
		setVisible(true);
		setSize(450, 800);
		setLayout(null);
		setBackground(new Color(232, 228, 224));

		// creating another Panel for the Menu
		add(menuPanel);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBounds(20, 0, 400, 35);
		menuPanel.add(title);
		menuPanel.setBackground(Color.black);

		// Title label to go on the Menu Panel
		title.setBounds(0, 0, 200, 20);
		title.setFont(FontClass.regular20);
		title.setForeground(Color.WHITE);

		// create the columns for the table
		table.addColumn("Registers");
		table.addColumn("Value");

		// fill the table with all the standard registers
		for (int i = 0; i < NUM_OF_REGISTERS; i++) {
			table.addRow(new Object[] { "r" + i, "00000000" });
		}

		// add the CPSR register
		table.addRow(new Object[] { "cpsr", "" });

		// create the JTable to display the table just made
		registerTable = new JTable(table);
		registerTable.getTableHeader().setFont(FontClass.regular30);
		registerTable.setBounds(10, 20, 300, 300);
		registerTable.setCellSelectionEnabled(false);
		registerTable.setForeground(Color.BLACK);
		registerTable.setRowHeight(30);
		registerTable.setDefaultEditor(Object.class, null);
		registerTable.setFont(FontClass.regular30);

		// Create a scroll pane for the register table
		// this is so that if in the future I want to add more registers
		// (there are more types) I can easily do so
		registerPane = new JScrollPane(registerTable);
		registerPane.setBackground(new Color(232, 228, 224));
		registerPane.setBounds(20, 40, 400, 500);
		add(registerPane);

	}

	// This method is responsible for updating the table after each operation
	public void updateTable(Map<String, String> registers) {

		//go through all the registers
		for (int i = 0; i < NUM_OF_REGISTERS; i++) {

			//if the register's value stored is not null (a value has been stored)
			if (registers.get("r" + i) != null) {
				//find the beginning 0s
				String beginningZeros = "";
				int zerosToAdd = (8 - registers.get("r" + i).length());
				//build the String for the 0s
				for (int j = 0; j < zerosToAdd; j++) {
					beginningZeros += "0";
				}
				//if the value that is on the table and the value within the register of the cpu object
				//are NOT the same, then update it with the new value
				if (!((beginningZeros + registers.get("r" + i)).equals(table.getValueAt(i, 1)))) {
					table.setValueAt(beginningZeros + registers.get("r" + i), i, 1);
				}
			}
		}

		//finally update the CPSR register
		table.setValueAt(registers.get("cpsr"), NUM_OF_REGISTERS, 1);

		//tell the program that the table had some data changes
		table.fireTableDataChanged();

	}

}
