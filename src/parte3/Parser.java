package parte3;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import parte2.Tre.*;
import parte2.Uno.Token;
import parte2.Uno.Tag;

public class Parser {
	private Lexer lex;
	private Token look;
	private BufferedReader buffer;

	
	public Parser(String inputFileName) throws IOException {
		inputFileName = new File("").getAbsolutePath().concat("\\src\\")
				.concat(this.getClass().getPackage().getName().replace('.', '\\').concat("\\"+inputFileName));
		lex = new Lexer();
		buffer = new BufferedReader(new FileReader(inputFileName));
		move();
	}

	private void move() {
		look = lex.lexical_scan(this.buffer);
		System.out.println("token = " + look.toString());
	}

	private void error(String s) {
		throw new Error("near line" + lex.line + ":" + look.toString());
	}

	private void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			this.error("syntax error");
	}

	public void start() throws IOException{
		expr();
		match(Tag.EOF);
		this.buffer.close();
	}

	private void expr() {
		term();
		exprp();
	}

	private void exprp() {
		switch (look.tag) {
		case '+':
			match('+');
			term();
			exprp();
			break;
		case '-':
			match('-');
			term();
			exprp();
			break;
			
		}
	}

	private void term() {
		fact();
		termp();
	}

	private void termp() {
		switch (look.tag) {
		case '*':
			match('*');
			fact();
			termp();
			break;
		case '/':
			match('/');
			fact();
			termp();
			break;
		}
	}

	private void fact() {
		switch (look.tag) {
		case '(':
			match('(');
			expr();
			match(')');
			break;
		case Tag.NUM:
			match(Tag.NUM);
			break;
		default:
			this.error("syntax error near char:" + lex.peek);
		}
	}

	public static void main(String[] args) {
		String inputFileName = "InputParser.txt";
	    try {
			Parser parser = new Parser(inputFileName);
			parser.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
