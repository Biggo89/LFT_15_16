package parte5.Uno;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import parte2.Tre.Lexer;
import parte2.Uno.Tag;
import parte2.Uno.Token;
import parte2.Uno.Word;

public class Traduttore {

	private Lexer lex;
	private Token look;
	private BufferedReader br;
	private CodeGenerator generator;

	public Traduttore(String inputFileName) throws IOException {
		inputFileName = new File("").getAbsolutePath().concat("\\src\\")
				.concat(this.getClass().getPackage().getName().replace('.', '\\').concat("\\" + inputFileName));
		generator = new CodeGenerator();
		lex = new Lexer();
		this.br = new BufferedReader(new FileReader(inputFileName));
		move();
	}

	private void move() {
		look = lex.lexical_scan(this.br);
		System.out.println("token = " + look.toString());
	}

	private void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			this.error("syntax error");
	}

	private void error(String s) {
		throw new Error("near line" + lex.line + ":" + look.toString());
	}

	public void prog() {
		Type orE_type;
		match(Tag.PRINT);
		match('(');
		orE_type = orE();
		match(')');
		match(Tag.EOF);
		System.out.println(orE_type);
		if (orE_type == Type.INTEGER)
			generator.emit(OpCode.invokestatic, 1);
		else if (orE_type == Type.BOOLEAN)
			generator.emit(OpCode.invokestatic, 0);
		try {
			generator.toJasmin();
		} catch (IOException ex) {
			error("IO error");
		}
	}

	private Type orE() {
		Type andE_type, orE_p_type;
		andE_type = andE();
		orE_p_type = orE_p(andE_type);
		return orE_p_type;
	}

	private Type orE_p(Type orE_p_i) {
		Type andE_type;
		if (look.tag == Tag.OR) {
			if (orE_p_i == Type.INTEGER)
				error("invalid operand for or operation, on orE_p");
			match(Tag.OR);
			generator.emit(OpCode.ior);
			andE_type = andE();
			orE_p_i = orE_p(andE_type);
			if (andE_type == Type.INTEGER || orE_p_i == Type.INTEGER)
				error("error in orE_p");
		}
		// caso in cui non trovo un or...allora ritorno il valore dell'attributo
		// ereditato
		return orE_p_i;
	}

	private Type andE() {
		Type relE_type, andE_p_type;
		relE_type = relE();
		andE_p_type = andE_p(relE_type);
		return andE_p_type;
	}

	private Type andE_p(Type andE_p_i) {
		Type relE_type;
		if (look.tag == Tag.AND) {
			if (andE_p_i == Type.INTEGER)
				error("in andE_p, invalid operand for AND op");
			match(Tag.AND);
			relE_type = relE();
			generator.emit(OpCode.iand);
			andE_p_i = andE_p(relE_type);
			if (relE_type == Type.INTEGER || andE_p_i == Type.INTEGER)
				error("in orE_p");
		}
		return andE_p_i;
	}

	private Type relE() {
		Type addE_type, relE_p_type;
		addE_type = addE();
		relE_p_type = relE_p(addE_type);
		return relE_p_type;
	}

	private Type relE_p(Type relE_p_i) {
		Type addE_type;
		switch (look.tag) {
		case Tag.EQ:
			match(Tag.EQ);
			addE_type = addE();
			if (addE_type != relE_p_i)
				error("type mismatch in EQ op");
			generate(OpCode.if_icmpeq);
			relE_p_i = Type.BOOLEAN;
			break;
		case Tag.NE:
			match(Tag.NE);
			addE_type = addE();
			if (addE_type != relE_p_i)
				error("type mismatch in NE op");
			generate(OpCode.if_icmpne);
			relE_p_i = Type.BOOLEAN;
			break;
		case Tag.LE:
			match(Tag.LE);
			addE_type = addE();
			if (addE_type != Type.INTEGER || relE_p_i != Type.INTEGER)
				error("type mismatch in LE op");
			generate(OpCode.if_icmple);
			relE_p_i = Type.BOOLEAN;
			break;
		case Tag.GE:
			match(Tag.GE);
			addE_type = addE();
			if (addE_type != Type.INTEGER || relE_p_i != Type.INTEGER)
				error("type mismatch in GE op");
			generate(OpCode.if_icmpge);
			relE_p_i = Type.BOOLEAN;
			break;
		case '<':
			match('<');
			addE_type = addE();
			if (addE_type != Type.INTEGER || relE_p_i != Type.INTEGER)
				error("type mismatch in LT op");
			generate(OpCode.if_icmplt);
			relE_p_i = Type.BOOLEAN;
			break;
		case '>':
			match('>');
			addE_type = addE();
			if (addE_type != Type.INTEGER || relE_p_i != Type.INTEGER)
				error("type mismatch in GT op");
			generate(OpCode.if_icmpgt);
			relE_p_i = Type.BOOLEAN;
			break;
		}
		return relE_p_i;
	}

	private Type addE() {
		Type multE_type, addE_p_type;
		multE_type = multE();
		addE_p_type = addE_p(multE_type);
		return addE_p_type;
	}

	private Type addE_p(Type addE_p_i) {
		Type multE_type;
		switch (look.tag) {
		case ('+'):
			match('+');
			multE_type = multE();
			addE_p_i = addE_p(multE_type);
			if (multE_type != Type.INTEGER || addE_p_i != Type.INTEGER)
				error("tipe mismatch in addE_p");
			generator.emit(OpCode.iadd);
			addE_p_i = Type.INTEGER;
			break;
		case ('-'):
			match('-');
			multE_type = multE();
			addE_p_i = addE_p(multE_type);
			if (multE_type != Type.INTEGER || addE_p_i != Type.INTEGER)
				error("tipe mismatch in addE_p");
			generator.emit(OpCode.isub);
			addE_p_i = Type.INTEGER;
			break;
		}
		return addE_p_i;
	}

	private Type multE() {
		Type fact_type, multE_p_type;
		fact_type = fact();
		multE_p_type = multE_p(fact_type);
		return multE_p_type;
	}

	private Type multE_p(Type multE_p_i) {
		Type fact_type;
		switch (look.tag) {
		case ('*'):
			match('*');
			fact_type = fact();
			multE_p_i = multE_p(fact_type);
			if (fact_type != Type.INTEGER || multE_p_i != Type.INTEGER)
				error("tipe mismatch in mulE_p");
			generator.emit(OpCode.imul);
			multE_p_i = Type.INTEGER;
			break;
		case ('/'):
			match('/');
			fact_type = fact();
			multE_p_i = multE_p(fact_type);
			if (fact_type != Type.INTEGER || multE_p_i != Type.INTEGER)
				error("tipe mismatch in addE_p");
			generator.emit(OpCode.idiv);
			multE_p_i = Type.INTEGER;
			break;
		}
		return multE_p_i;
	}

	private Type fact() {
		Type type = Type.INTEGER;
		switch (look.tag) {
		case '(':
			match('(');
			type = orE();
			match(')');
			break;
		case Tag.NUM:
			type = Type.INTEGER;
			generator.emit(OpCode.ldc, Integer.parseInt(((Word) look).lexeme));
			match(Tag.NUM);
			break;
		case Tag.TRUE:
			match(Tag.TRUE);
			type = Type.BOOLEAN;
			generator.emit(OpCode.ldc, 1);
			break;
		case Tag.FALSE:
			match(Tag.FALSE);
			type = Type.BOOLEAN;
			generator.emit(OpCode.ldc, 0);
			break;
		}
		return type;
	}

	private void generate(OpCode code) {
		int ltrue = generator.newLabel();
		int lnext = generator.newLabel();
		generator.emit(code, ltrue);
		generator.emit(OpCode.ldc, 0);
		generator.emit(OpCode.GOto, lnext);
		generator.emitLabel(ltrue);
		generator.emit(OpCode.ldc, 1);
		generator.emitLabel(lnext);
	}

	public static void main(String[] args) {
        try{
        	Traduttore translater = new Traduttore("InputTraduttore.txt");
        	translater.prog();
        }catch(IOException ex)
        {
        	System.err.println(ex.getMessage());
        }
        
        
    }

}
