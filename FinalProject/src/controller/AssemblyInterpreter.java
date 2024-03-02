package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import model.VirtualCPU;
import view.RegisterPanel;

/*This class is responsible for getting the assembly code written and interpret it.
* The class takes into account many cases such as if the user typed in lower case,
* if the user types in a hexadecimal number and more.
*/
public class AssemblyInterpreter {
	// Create an arraylist for the instructions the user wants to perform
	ArrayList<String> instructions = new ArrayList<String>();

	// Create a CPU object to perform the instructions
	VirtualCPU cpu = new VirtualCPU();

	// Source:
	// https://docs.oracle.com/javase/8/docs/api/java/util/regex/Pattern.html
	// This pattern will help detect whether the user typed in a hexadecimal number
	Pattern hexPattern = Pattern.compile("\\b(?:0[xX])?[0-9a-fA-F]+\\b");

	// This will allow me to detect if there were any errors within the instructions
	boolean noError = true;

	// constructor that takes the assembly code written by the user
	// and the register panel (so that the register panel will get updated
	// correctly)
	public AssemblyInterpreter(String assemblyCode, RegisterPanel register) {
		// Source for Pattern and Matcher: https://www.w3schools.com/java/java_regex.asp
		// This pattern is used to detect the assembly code instructions
		// This makes it easier for me to break down the code into the instructions and
		// its operands
		Pattern instructionPattern = Pattern.compile("\\b\\w+\\b|#\\w+|,\\s*", Pattern.CASE_INSENSITIVE);

		// A matcher that will take the pattern and match it with the assembly code
		// the user coded
		Matcher matcher = instructionPattern.matcher(assemblyCode);

		// while the matcher can find a match
		while (matcher.find()) {
			// get the String from the match and trim it (no spaces)
			String match = matcher.group().trim();
			// if the string is not empty then add it to the instructions list
			// this will continue until all instructions are in the list with their operands
			if (!(match.isEmpty())) {
				instructions.add(match);
			}
		}

		// fix the formatting of the instructions within the arraylist
		fixFormatting();

		// go through all the instructions of the list
		for (int i = 0; i < instructions.size(); i++) {

			// if the user wants to move a value
			if (instructions.get(i).trim().equalsIgnoreCase("MOV")) {
				// first check if there are at least 2 instructions within the list
				if (instructions.size() > 2) {
					// if the second operands is not a register (they do not
					// want to move values between registers)
					if (!instructions.get(i + 2).contains("r")) {
						// find the hex value of the number inputed
						instructions.set(i + 2, findDecimalFromHex(instructions.get(i + 2)));
					}
					// have the CPU actually perform the operation
					cpu.move(instructions.get(i + 1), instructions.get(i + 2));
					// skip to the next instruction
					i += 2;
				}

			}

			// if the user wants to add
			else if (instructions.get(i).trim().equalsIgnoreCase("ADDS")) {

				// get the CPU to perform the operation
				cpu.add(instructions.get(i + 1), instructions.get(i + 2), instructions.get(i + 3));
				// go to the next instruction
				i += 3;

			}

			// if the user wants to subtract
			else if (instructions.get(i).trim().equalsIgnoreCase("SUBS")) {
				// get the cpu to perform the operation with the operands
				cpu.subtract(instructions.get(i + 1), instructions.get(i + 2), instructions.get(i + 3));
				// skip to the next operation
				i += 3;

			}

			// if the user wants to multiply
			else if (instructions.get(i).trim().equalsIgnoreCase("MULS")) {
				// cpu performs the multiplication operation
				cpu.multiply(instructions.get(i + 1), instructions.get(i + 2), instructions.get(i + 3));
				// skip to the next operation
				i += 3;
			}

			// if the user wants to divide
			else if (instructions.get(i).trim().equalsIgnoreCase("SDIV")) {
				// cpu performs division with the operands
				cpu.divide(instructions.get(i + 1), instructions.get(i + 2), instructions.get(i + 3));
				// skip to the next operation
				i += 3;
			}
			// if there was no match, then there is an error
			else {
				// set the error flag to false
				noError = false;
				// show a error message
				JOptionPane.showMessageDialog(null,
						"Error: Input does not follow ARM assembly language syntax. Please refer to the instructions or click on options on your left.",
						"Error", JOptionPane.ERROR_MESSAGE);
				// break out of the loop as soon as there is an error
				break;
			}

		}
		// if there are no errors then update the table in the register panel
		if (noError) {
			register.updateTable(cpu.getRegisters());
		}

	}

	// This method is responsible for converting a decimal number
	// to a hexadecimal number
	private String findDecimalFromHex(String hexNum) {
		// String variable to hold the temp hex number
		String temp = "";
		// create a matcher that will match the hex number with the corresponding regex
		Matcher hexMatcher = hexPattern.matcher(hexNum);

		// if the number entered is an integer
		// then use the regular number
		if (hexNum.matches("[0-9]+")) {
			return hexNum;
		}

		// else if it is a hex number
		else if (hexMatcher.find()) {
			// get the hex number
			String hexValue = hexMatcher.group();
			// replace any characters that aren't included in a standard
			// hexadecimal number
			temp = hexValue.replaceAll("[^0-9a-fA-F]", "");
			// convert the hexadecimal number to an integer
			temp = Integer.toString(Integer.parseInt(temp, 16));
		}

		// return the number (in form of a String to display on the table)
		return temp;
	}

	// This method is repsonsible for fixing the formatting of the
	// instructions. For example removing commas, taking care of upper case
	// characters
	// and removing '#' symbols
	private void fixFormatting() {
		// Using a try catch in case we get any errors
		try {
			// for every instructions
			for (int i = 0; i < instructions.size(); i++) {
				// If there is a comma in the instructions, remove it
				if (instructions.get(i).trim().equals(",")) {
					instructions.remove(i);
					// lower the index by 1 since we have changed the size of the
					// instructions list
					i--;
				}

				// if the instructions have a "#" then replace it with nothing
				// this makes it easier to get an integer value later on
				instructions.set(i, instructions.get(i).replaceAll("#", ""));

				// in case the user uses uppercase for the registry
				instructions.set(i, instructions.get(i).replaceAll("R", "r"));

				// if the instruction is a reference to a register
				if (instructions.get(i).contains("r")) {
					// check if the register is within the range, if not then set
					// the error flag to false
					if (Integer.parseInt(instructions.get(i).substring(1)) == 13
							|| instructions.get(i).substring(1) == null) {
						noError = false;
					}
				}
			}
		} catch (NumberFormatException n) {
			// if there was some error within the instructions, set the error flag to false
			noError = false;

		}

	}

}