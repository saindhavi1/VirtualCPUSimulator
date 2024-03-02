package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import controller.AssemblyInterpreter;
import model.FontClass;

/* This class is responsible for the actual Simulator Frame. The user will be able to type or choose 
 * the commands they want to perform. They will also be able to see the registers and their values
 * updating while performing their calculations.
 */
public class SimulatorFrame extends JFrame implements ActionListener {

	// Create the GUI components
	Color buttonColor = new Color(52, 73, 102);
	JPanel menuPanel = new JPanel();
	JLabel title = new JLabel("Virtual CPU Simulator");
	JButton instructionButton = new JButton("Instructions");
	RegisterPanel register = new RegisterPanel();

	JLabel textAreaBackground = new JLabel(new ImageIcon("images/textAreaBackground.png"));
	// Create a String variable that will hold the starter code to ARM assembly
	// language
	final String STARTCODE = ".global _start\n_start:\n";
	JTextArea assemblyCode = new JTextArea(STARTCODE);

	JButton submitButton = new JButton("Submit");
	JButton clearButton = new JButton("Clear");

	JPanel commandsPanel = new JPanel();
	JLabel commandsTitle = new JLabel("Commands");
	// Create a String array with all the commands available and the instructions
	// for them
	String[][] instructionsAvailable = { { "Store a Value", "MOV r0, #1" },
			{ "Add Values", "MOV r0, #1\n" + "MOV r1, #3\n" + "ADDS r2,r0,r1" },
			{ "Subtract Values", "MOV r0, #5\n" + "MOV r1, #3\n" + "SUBS r2,r0,r1" },
			{ "Multiply Values", "MOV r0, #4\n" + "MOV r1, #3\n" + "MULS r2,r0,r1" },
			{ "Divide Values", "MOV r0, #4\n" + "MOV r1, #2\n" + "SDIV r2,r0,r1" } };
	JButton[] commandsButton = new JButton[instructionsAvailable.length];

	// constructor to create the Frame
	public SimulatorFrame() {

		// set up the JFrame
		setVisible(true);
		setSize(1425, 1080);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(232, 228, 224));

		// create a panel for the menu
		add(menuPanel);
		menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		menuPanel.setBounds(0, 0, 1920, 35);
		menuPanel.setBackground(Color.black);

		// create the title for the simulator
		title.setFont(FontClass.regular20);
		title.setForeground(Color.WHITE);
		menuPanel.add(title);

		// create a button for the user to go back to the instructions
		instructionButton.setFont(FontClass.regular20);
		// Source:https://java-demos.blogspot.com/2012/12/set-hand-cursor-for-jbutton.html
		// allows for the cursor to change to a hand when it is on the button
		instructionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		instructionButton.setBackground(Color.BLACK);
		instructionButton.setBorderPainted(false);
		instructionButton.setFocusPainted(false);
		instructionButton.setOpaque(true);
		instructionButton.setForeground(Color.white);
		menuPanel.add(instructionButton);
		instructionButton.addActionListener(this);

		// create the blue border around the text area using an image
		textAreaBackground.setBounds(800, 50, 600, 600);
		ImageIcon backgroundPic = new ImageIcon("images/textAreaBackground.png");
		Image backgroundPicScaled = backgroundPic.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
		textAreaBackground.setIcon(new ImageIcon(backgroundPicScaled));
		add(textAreaBackground);

		// style the text area for the user to type assembly code in
		assemblyCode.setBounds(25, 25, 550, 550);
		assemblyCode.setLineWrap(true);
		assemblyCode.setBackground(new Color(232, 228, 224));
		assemblyCode.setFont(FontClass.regular20);
		assemblyCode.setBorder(new EmptyBorder(10, 10, 10, 10));
		textAreaBackground.add(assemblyCode);

		// create a button for the user to submit their code to run it
		submitButton.setBounds(1150, 675, 200, 75);
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setFont(FontClass.medium40);
		submitButton.setBackground(buttonColor);
		submitButton.setBorderPainted(false);
		submitButton.setOpaque(true);
		submitButton.setForeground(Color.white);
		submitButton.addActionListener(this);
		add(submitButton);

		// create a button that will clear the text area when clicked
		clearButton.setBounds(850, 675, 200, 75);
		clearButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		clearButton.setFont(FontClass.medium40);
		clearButton.setBackground(buttonColor);
		clearButton.setBorderPainted(false);
		clearButton.setOpaque(true);
		clearButton.setForeground(Color.white);
		clearButton.addActionListener(this);
		add(clearButton);

		// create a panel with all the commands the simulator can autocode
		commandsPanel.setBounds(460, 250, 300, 300);
		commandsPanel.setBackground(buttonColor);
		commandsPanel.setLayout(null);
		add(commandsPanel);

		// create a title for the commands panel
		commandsTitle.setBounds(0, 20, 300, 30);
		commandsTitle.setHorizontalAlignment(JLabel.CENTER);
		commandsTitle.setFont(FontClass.medium30);
		commandsTitle.setForeground(Color.WHITE);
		commandsPanel.add(commandsTitle);

		// create the register panel
		add(register);
		register.setBounds(0, 100, 450, 600);

		// Creating the JButtons for the commands
		for (int i = 0; i < commandsButton.length; i++) {
			commandsButton[i] = new JButton(instructionsAvailable[i][0]);
			commandsButton[i].setBounds(0, 70 + (i * 40), 300, 30);
			commandsButton[i].setFont(FontClass.regular30);
			commandsButton[i].setOpaque(false);
			commandsButton[i].setBorderPainted(false);
			commandsButton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
			commandsButton[i].setForeground(Color.WHITE);
			commandsButton[i].addActionListener(this);
			commandsPanel.add(commandsButton[i]);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// open the instructions frame if the user presses the instructions button
		if (e.getSource() == instructionButton) {
			dispose();
			new InstructionFrame();
		}
		// if the user submits/wants to execute their code
		else if (e.getSource() == submitButton) {
			// pass in their assembly code and register panel into the interpreter
			// to process the instructions
			new AssemblyInterpreter(assemblyCode.getText().substring(22), register);

		}
		// clear the text area if the user clicks the clear button
		else if (e.getSource() == clearButton) {
			assemblyCode.setText(STARTCODE);

		}

		// for each button, if the user clicks it
		for (int i = 0; i < commandsButton.length; i++) {
			if (e.getSource() == commandsButton[i]) {

				// add the code stored in the instructions array
				if (!assemblyCode.getText().equals(STARTCODE)) {
					assemblyCode.setText(assemblyCode.getText() + "\n" + instructionsAvailable[i][1]);
				} else {
					assemblyCode.setText(assemblyCode.getText() + instructionsAvailable[i][1]);
				}

				// let the user know what they can do with the assembly code (what they can
				// modify)
				JOptionPane.showMessageDialog(null,
						"Change the registry or number you want to use! To change the registry you want to store a value in, \nyou can just change the number beside the 'r' to any number between 1-12. "
								+ "\nTo change the number you want to store, write any number after the '#'. \nThe program accepts hexadecimal numbers (make sure to write it after the '#') "
								+ "\nand will accept instructions in upper and lower case.",
						"Coding Information", JOptionPane.INFORMATION_MESSAGE);

			}
		}
	}

}
