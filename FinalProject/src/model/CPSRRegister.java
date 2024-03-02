package model;

//This class will simulate the CPSR register. It contains tests for whether the 
//corresponding flags will be set to true for each arithmetic operation
public class CPSRRegister {

	// field or flags for the CPSR register
	private boolean N;
	private boolean Z;
	private boolean C;
	private boolean V;
	private boolean I;

	// constructor (I is always set to true)
	public CPSRRegister(boolean n, boolean z, boolean c, boolean v, boolean i) {
		super();
		N = n;
		Z = z;
		C = c;
		V = v;
		I = i;
	}

	// Getters and setters
	public boolean isN() {
		return N;
	}

	public void setN(boolean n) {
		N = n;
	}

	public boolean isZ() {
		return Z;
	}

	public void setZ(boolean z) {
		Z = z;
	}

	public boolean isC() {
		return C;
	}

	public void setC(boolean c) {
		C = c;
	}

	public boolean isV() {
		return V;
	}

	public void setV(boolean v) {
		V = v;
	}

	public boolean isI() {
		return I;
	}

	public void setI(boolean i) {
		I = i;
	}

	// Utility Methods
	// This methods will check if the flags will be true or not

	// Checks if the answer is negative
	public void checkN(int answer) {
		// if the answer is less than 0, then the negative flag is set to true
		if (answer < 0) {
			setN(true);
		}
		// else it is set to false
		else {
			setN(false);
		}
	}

	// Check if the answer is 0
	public void checkZ(int answer) {
		// if the answer is 0, set the zero flag to true
		if (answer == 0) {
			setZ(true);

		}
		// else set it to false
		else {
			setZ(false);
		}
	}

	// Check if there is a carry within the operation
	public void checkC(int operand1, int operand2) {

		//if the second operand is bigger than the first, a carry must occur therefore
		//setting the carry flag to true
		if (operand1 < operand2) {
			setC(true);

		} 
		//else set the carry flag to false
		else {
			setC(false);
		}

	}

	// Check if there is an overflow within the operation
	public void checkV(int answer) {
		//if the answer is within the range, if it is not then set the overflow flag to true
		if (answer > 32767 || answer < -32767) {
			setV(true);

		} 
		// else set the overflow flag to false
		else {
			setV(false);

		}
	}

	//toString method
	@Override
	public String toString() {
		return "CPSRRegister [N=" + N + ", Z=" + Z + ", C=" + C + ", V=" + V + ", I=" + I + "]";
	}
	
	

}
