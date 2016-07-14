package parte4.Uno;
import parte2.Uno.Word;
import parte2.Uno.Tag;
import parte2.Uno.Token;
import parte2.Tre.Lexer;
import java.io.*;

public class Valutatore {
	private Lexer lex;
	private Token look;
	private BufferedReader br;

	public Valutatore(String inputFileName) throws IOException {
		inputFileName = new File("").getAbsolutePath().concat("\\src\\").concat(this.getClass().getPackage().getName().replace('.', '\\').concat("\\"+inputFileName));
		lex = new Lexer();
		this.br = new BufferedReader(new FileReader(inputFileName));
		move();
	}

	/**
	 * 
	 */
	private void move() {
		look = lex.lexical_scan(this.br);
		System.out.println("token = " + look.toString());
	}

	/**
	 * 
	 * @param t
	 */
	private void match(int t) {
		if (look.tag == t) {
			if (look.tag != Tag.EOF)
				move();
		} else
			this.error("syntax error");
	}

	/**
	 * 
	 * @param s
	 */
	private void error(String s) {
		throw new Error("near line" + lex.line + ":" + look.toString());
	}

	/**
	 * 
	 */
	public void start() throws IOException {
		// int expr_val;
		// expr_val = expr();
		int expr_val = expr();

		match(Tag.EOF);

		br.close();

		System.out.println(expr_val);
	}

	private int expr() {
		return exprp(term());
	}

	private int exprp(int exprp_i) {
		int exprp_val;

		switch (look.tag) {
		case '+':
			match('+');
			exprp_val = exprp(exprp_i + term());
			break;
		case '-':
			match('-');
			exprp_val = exprp(exprp_i - term());
			break;
		default:

			exprp_val = exprp_i;
		}
		return exprp_val;
	}

	private int term() {
		return termp(fact());
	}

	private int termp(int termp_i) {
		int termp_val;
		switch (look.tag) {
		case '*':
			match('*');
			termp_val = termp(termp_i * term());
			break;
		case '/':
			match('/');
			termp_val = termp(termp_i / term());
			break;
		default:
			termp_val = termp_i;
		}
		return termp_val;
	}

	private int fact() {
		int fact_val = 0;
		switch (look.tag) {
		case '(':
			match('(');
			fact_val = expr();
			match(')');
			break;
		case Tag.NUM:
			Word cast = (Word) look;
			fact_val = Integer.parseInt(cast.lexeme);
			match(Tag.NUM);
			break;
		}
		return fact_val;
	}

	/*
	 **
	 * @param args
	 */
	public static void main(String[] args) {
		String inputFileName = "InputValutatore.txt";
		try {
			Valutatore val = new Valutatore(inputFileName);
			val.start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
