package view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.FontClass;

/* This frame is the first frame the user will see and will welcome the user
 * to the simulator. The welcome frame leads them to the instructions or
 * straight to the simulator
 */
public class WelcomeFrame extends JFrame implements ActionListener{

	//Create the GUI components
	FontClass fonts = new FontClass();
	Color buttonColor = new Color(52,73,102);
	JLabel titleLabel = new JLabel("Welcome to the Virtual CPU Simulator!");
	JLabel explainationLabel = new JLabel("Ready to explore how the CPU works? Click below!");
	JButton instructionButton = new JButton("Instructions");
	JButton simulatorButton = new JButton("Simulator");
	
	//constructor to build the welcome frame
	public WelcomeFrame() {
		//set up the frame
		setVisible(true);
		setSize(1425, 1080);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(232, 228, 224));
		
		//create the title for the welcome frame
		titleLabel.setBounds(0, 0, 1425, 300);
		titleLabel.setFont(FontClass.bold50);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		add(titleLabel);
		
		//create the little blurp of what the simulator is about
		explainationLabel.setBounds(0, 0, 1425, 500);
		explainationLabel.setHorizontalAlignment(JLabel.CENTER);
		explainationLabel.setFont(FontClass.regular30);
		add(explainationLabel);
		
		//create a button that leads to the instructions frame
		instructionButton.setBounds(200, 400, 450, 150);
		instructionButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		instructionButton.setFont(FontClass.medium40);
		instructionButton.setBackground(buttonColor);
		instructionButton.setBorderPainted(false);
		instructionButton.setOpaque(true);
		instructionButton.setForeground(Color.white);
		instructionButton.addActionListener(this);
		add(instructionButton);
		
		//create a button that leads straight to the simulator
		simulatorButton.setBounds(750, 400, 450, 150);
		simulatorButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		simulatorButton.setFont(FontClass.medium40);
		simulatorButton.setBackground(buttonColor);
		simulatorButton.setBorderPainted(false);
		simulatorButton.setOpaque(true);
		simulatorButton.setForeground(Color.white);
		simulatorButton.addActionListener(this);
		add(simulatorButton);
		
	}

	@Override
	//if the user wants to see the instructions, open the instructions frame
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == instructionButton) {
			new InstructionFrame();
			dispose();
		}
		
		//else if the user wants to see the simulator, open the simulator frame
		else if (e.getSource() == simulatorButton) {
			new SimulatorFrame();
			dispose();
		}
		
	}
}
