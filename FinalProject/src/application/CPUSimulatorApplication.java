package application;

import view.WelcomeFrame;

/* Name: Saindhavi Thevathasan
 * Date: Friday January 19, 2024
 * Course: ICS4U1 - 03 Mr. Fernandes
 * Title of the Project: Virtual CPU Simulator
 * Description: This virtual CPU Simulator will allow users to see how the CPU works,
 * 				specifically with how numbers are stored within registers and how they
 * 				perform arthimetic operations. 
 * Features:
 * 		- Assembly Code Interpreter -> allows for the user to enter code to store values,
 * 			add, subtract, multiply and divide values
 * 			- The interpreter allows for no case sensitivity (can type in lower or upper case)
 * 			- The interpreter also allows hexadecimal numbers for values to put in the registers
 * 		- Visual of registers -> when the user performs these operations, they can see the values
 * 			being updated in the appropriate registers
 * 		- CPSR register -> shows certain flags when the answer to an operation is an integer, negative, zero, 
 * 			has a carry or is an overflow.
 * Major Skills:
 * 		- Object Oriented Programming
 * 			- created objects for Virtual CPU and CPSR register 
 * 			- used composition
 * 		- Coding with Pattern, Matcher and Regexes (used it to decode the assembly code the user inputted
 * 		- Data Structures
 * 			- Used Arrays, ArrayLists and HashMaps within my code
 * Areas of Concern:
 * 		- May be concerned on some of the error handling within the code (Ex. if the user forgets to type 
 * 			a register value, it may display a error in the console but still does show the error JOptionPane
 * 			when the error occurs.
 * 		- The CPSR carry and overflow flag may not be accurate for a real CPU due to the simplified condition 
 * 			to set them to true
 */
public class CPUSimulatorApplication {

	public static void main(String[] args) {
		new WelcomeFrame();
	}
}
