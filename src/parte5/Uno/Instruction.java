package parte5.Uno;

public class Instruction {

	OpCode opCode;
	int operand;

	public Instruction(OpCode opCode) {
		this.opCode = opCode;
	}

	public Instruction(OpCode opCode, int operand) {
		this.opCode = opCode;
		this.operand = operand;
	}

	public String toJasmin() {
		String temp = "";
		switch (this.opCode) {
		case ldc:
			temp = " ldc " + operand + "\n";
			break;
		case GOto:
			temp = " goto L" + operand + "\n";
			break;
		case iadd:
			temp = " iadd \n"; 
			break;
		case iand:
			temp = " iand \n";
			break;
		case idiv:
			temp = " idiv \n";
			break;
		case if_icmpeq:
			temp = " if_icmpeq L" + operand + "\n";
			break;
		case if_icmpge:
			temp = " if_icmpge L" + operand + "\n";
			break;
		case if_icmpgt:
			temp = " if_icmpgt L" + operand + "\n";
			break;
		case if_icmple:
			temp = " if_icmple L" + operand + "\n";
			break;
		case if_icmplt:
			temp = " if_icmplt L" + operand + "\n";
			break;
		case if_icmpne:
			temp = " if_icmpne L" + operand + "\n";
			break;
		case ifne:
			temp = " ifne L" + operand + "\n";
			break;
		case iload:
			temp = " iload" + operand + "\n";
			break;
		case imul:
			temp = " imul \n";
			break;
		case ineg:
			temp = " ineg \n";
			break;
		case invokestatic:
			break;
		case ior:
			break;
		case istore:
			temp = " istore "+ operand + "\n";
			break;
		case isub:
			temp = " isub \n";
			break;
		case label:
			temp = "L" + operand + ":\n";
			break;
		}
		return temp;
	}
}
