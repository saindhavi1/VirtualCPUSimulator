
package view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import model.FontClass;

/* This class is responsible for displaying the instructions to the simulator
 * on a JFrame. The instructions will also have a button that will lead 
 * to a link for beginners to learn ARM v7 assembly code.
 */
public class InstructionFrame extends JFrame implements ActionListener {

	// initialize the fonts and color commonly used in the GUI
	FontClass font = new FontClass();
	Color buttonColor = new Color(52, 73, 102);

	// Initialize the GUI components
	JLabel instructionTitle = new JLabel("Instructions");
	JTextPane instructions = new JTextPane();
	JScrollPane instructionsPane = new JScrollPane(instructions);
	JButton backButton = new JButton("Back to Welcome");
	JButton toSimulatorButton = new JButton("To Simulator");
	// This is the text that will be in the JScrollPane
	String htmlText = "<html><style>ul{font-family: Kumbh Sans; font-size: 30px;}</style>Welcome to the Virtual CPU Simulator!\n"
			+ "<ul><li>When entering the simulator, you will see a list of commands the simulator supports. As of right now, the simulator can support...</li>"
			+ "<ul><li>MOV: moving values into registers (can move numbers and values within registers into another register)</li><li>ADDS: add two numbers stored within 2 registers</li>"
			+ "<li>SUBS: subtract two numbers stored within 2 registers</li><li>MULS: multiply two numbers stored within 2 registers</li>"
			+ "<li>SDIV: divide two numbers stored within 2 registers</li>"
			+ "<li>The Simulator does accept hexadecimal numbers as operands and is not case sensitive!</li></ul>"
			+ "<li>This simulator does not accept negative numbers (will take the positive number instead) and decimal numbers.</li>"
			+ "<li>This CPU simulates an ARM v7 16 bit CPU. Click on the 'Learn Assembly Code' button below if you want to look at some ARM v7 Assembly Code documentation.</li>"
			+ "<li>When the user clicks the submit button, see the CPU at work storing values in its registry and do their calculations!</li>"
			+ "<ul><li>A Panel will pop up to show you what occurred within the registers</li>"
			+ "<li>This simulator also have a special register called the CPSR (Current Program Status Register). This register will tell if the answer to an operation"
			+ "is an Integer (I), Negative number (N), Zero (Z), has a Carry (C) or overflows (V) by showing their respective flags in the brackets.</li></ul></li>"
			+ "<li>If you need to refer to the instructions again, you can look at the menu and click the instructions button!</li>"
			+ "<li>When you are ready, you can press the 'To Simulator' button to start your journey!</li>"
			+ "<li>HAVE FUN!!</li></ul></html>";

	JButton assemblyCodeLink = new JButton("Learn Assembly Code!");

	// constructor in creating a frame for the instructions of the simulator
	public InstructionFrame() {
		// style the frame
		setVisible(true);
		setTitle("Instructions");
		setSize(1425, 1080);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(232, 228, 224));

		// style the title of the frame
		instructionTitle.setBounds(0, 0, 1425, 200);
		instructionTitle.setHorizontalAlignment(JLabel.CENTER);
		instructionTitle.setFont(FontClass.bold50);
		add(instructionTitle);

		// styling the instructions in HTML so that I can have bullet points
		instructions.setContentType("text/html");
		instructions.setText(htmlText);
		instructions.setEditable(false);
		// makes the jscrollpane start at the top of the text instead of at the bottom
		instructions.setCaretPosition(0);

		// setting up the scroll pane
		instructionsPane.setBounds(100, 150, 1200, 500);
		add(instructionsPane);

		// Create the back button
		backButton.setBounds(50, 20, 250, 75);
		backButton.setFont(FontClass.medium25);
		backButton.setBackground(buttonColor);
		backButton.setBorderPainted(false);
		backButton.setOpaque(true);
		backButton.setForeground(Color.white);
		backButton.addActionListener(this);
		add(backButton);

		// Create a button to lead to the actual simulator
		toSimulatorButton.setBounds(1150, 20, 200, 75);
		toSimulatorButton.setFont(FontClass.medium25);
		toSimulatorButton.setBackground(buttonColor);
		toSimulatorButton.setBorderPainted(false);
		toSimulatorButton.setOpaque(true);
		toSimulatorButton.setForeground(Color.white);
		toSimulatorButton.addActionListener(this);
		add(toSimulatorButton);

		// Create a button to bring the user to ARM v7 documentation
		assemblyCodeLink.setBounds(950, 675, 350, 75);
		assemblyCodeLink.setFont(FontClass.medium25);
		assemblyCodeLink.setBackground(buttonColor);
		assemblyCodeLink.setBorderPainted(false);
		assemblyCodeLink.setOpaque(true);
		assemblyCodeLink.setForeground(Color.white);
		assemblyCodeLink.addActionListener(this);
		add(assemblyCodeLink);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if the user clicks the back button
		if (e.getSource() == backButton) {
			// dispose of this frame and open a Welcome frame
			dispose();
			new WelcomeFrame();
		}
		// else if the user clicks the to simulator button
		else if (e.getSource() == toSimulatorButton) {
			// dispose of this frame and open a Simulator frame
			dispose();
			new SimulatorFrame();
		}

		// if the user wants to learn more about assembly code
		else if (e.getSource() == assemblyCodeLink) {
			// create a desktop object
			Desktop desktop = Desktop.getDesktop();

			// open the link to the ARM v7 documentation on the user's desktop
			try {
				desktop.browse(URI.create(
						"https://developer.arm.com/documentation/ddi0403/d/Application-Level-Architecture?lang=en"));
			} catch (IOException e1) {

				e1.printStackTrace();
			}
		}

	}
}
