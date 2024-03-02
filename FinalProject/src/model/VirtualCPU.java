package model;

import java.util.HashMap;
import java.util.Map;

/* This class represents the CPU. It contains fields such as its regular registers
 * and a CPSR register. This object will perform the operations and store the values
 * in the appropriate register
 */
public class VirtualCPU {

	// fields
	Map<String, String> registers = new HashMap<>();
	CPSRRegister cpsr = new CPSRRegister(false, false, false, false, true);

	// constructor (no parameters since registers will be created when operation is
	// performed)
	public VirtualCPU() {
		super();
	}

	// getters and setters
	public Map<String, String> getRegisters() {
		return registers;
	}

	public void setRegisters(Map<String, String> registers) {
		this.registers = registers;
	}

	public CPSRRegister getCpsr() {
		return cpsr;
	}

	public void setCpsr(CPSRRegister cpsr) {
		this.cpsr = cpsr;
	}

	// Utility methods that will perform each other the CPU's operations
	// that the simulator supports

	// This method will store values into registers or move values into a register
	public void move(String register, String num) {

		// If the number is not actually a register (the user does not want to move a
		// value
		// already in one register into another)
		// then put the number in the register spot
		if (!(num.contains("r"))) {

			getRegisters().put(register, num);
		}
		// else if the user is moving a value from one register into another
		// then do so
		else {
			getRegisters().put(register, getRegisters().get(num));
		}

		// For testing
//		for (int i = 0; i < getRegisters().size(); i++) {
//			System.out.println("r" + i + ":" + registers.get("r" + i));
//		}
	}

	// This method takes care of the adding in the CPU
	public void add(String answerRegister, String operand1, String operand2) {
		// System.out.println("Adding");

		// perform the operation
		int answer = Integer.parseInt(getRegisters().get(operand1)) + Integer.parseInt(getRegisters().get(operand2));
		// check if we need to flag overflow
		getCpsr().checkV(answer);

		// update the register with the answer
		updateRegister(answerRegister, answer);
	}

	// This method takes care of the subtracting
	public void subtract(String answerRegister, String operand1, String operand2) {
		// find the integer values of the operands (will be using these values later)
		int operandNum1 = Integer.parseInt(getRegisters().get(operand1));
		int operandNum2 = Integer.parseInt(getRegisters().get(operand2));
		// perform the operation
		int answer = operandNum1 - operandNum2;

		// check for the carry and overflow flag
		getCpsr().checkC(operandNum1, operandNum2);
		getCpsr().checkV(answer);
		// update the register with the answer
		updateRegister(answerRegister, answer);
	}

	// this method takes care of the multiplication
	public void multiply(String answerRegister, String operand1, String operand2) {
		// multiply the two operands
		int answer = Integer.parseInt(getRegisters().get(operand1)) * Integer.parseInt(getRegisters().get(operand2));
		// no need to check for carry and overflow flags since those are mostly
		// set during addition and subtraction

		// update the register with the answer
		updateRegister(answerRegister, answer);
	}

	// This method takes care of division
	public void divide(String answerRegister, String operand1, String operand2) {
		// find the integer value of the operands and perform the operation
		int answer = Integer.parseInt(getRegisters().get(operand1)) / Integer.parseInt(getRegisters().get(operand2));

		// update the register with the answer
		updateRegister(answerRegister, answer);
	}

	// This method update the register witht he asnwer and tests for the
	// remaining flags
	public void updateRegister(String answerRegister, int answer) {
		// put the answer in the answer register
		getRegisters().put(answerRegister, Integer.toString(answer));

		// check for the negative and zero flags
		getCpsr().checkN(answer);
		getCpsr().checkZ(answer);

		// create a string that will hold the letters shown in the CPSR register
		String cpsrString = "";

		// Test if each flag is set to true individually since some flags could be
		// set true at the same time
		if (getCpsr().isN())
			cpsrString += "N";
		if (getCpsr().isZ())
			cpsrString += "Z";
		if (getCpsr().isC())
			cpsrString += "C";
		if (getCpsr().isV())
			cpsrString += "V";

		// Add the integer flag since we are always dealing with integers
		cpsrString += "I";

		// Add the flags to the cpsr register
		getRegisters().put("cpsr", cpsrString);

	}

	// toString method
	@Override
	public String toString() {
		return "VirtualCPU [registers=" + registers + ", cpsr=" + cpsr + "]";
	}

}
