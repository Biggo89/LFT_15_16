package parte2.Tre;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import parte2.Uno.Tag;
import parte2.Uno.Token;
import parte2.Uno.Word;

public class Lexer {
	public int line = 1;

	public char peek = ' ';
	
	public BufferedReader br;

	private int state;

	Hashtable<String, Word> words = new Hashtable<String, Word>();

	void reserve(Word w) {
		words.put(w.lexeme, w);
	}

	void bufferInizializer(String inputFileName) throws IOException
	{
		this.br = new BufferedReader(new FileReader(inputFileName));
	}
	
	private boolean javaIdentifier(String str) {
		state = 0;
		int i = 0;
		while (state >= 0 && i < str.length()) {
			char ch = str.charAt(i++);
			switch (state) {
			case 0:
				if (Character.isLetter(ch))
					state = 2;
				else if (ch == '_')
					state = 1;
				else
					state = -1;
				break;
			case 2:
				if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')
					break;
				else
					state = -1;
				break;
			case 1:
				if (Character.isDigit(ch) || Character.isLetter(ch))
					state = 2;
				else if (ch == '_')
					break;
				else
					state = -1;
				break;
			}
		}
		return state == 2;
	}

	public Lexer(String inputFileName) throws IOException
	{
		inputFileName = new File("").getAbsolutePath().concat("\\src\\")
				.concat(this.getClass().getPackage().getName().replace('.', '\\').concat("\\"+inputFileName));
		this.bufferInizializer(inputFileName);
		this.reserve(new Word(Tag.VAR, "var"));
		this.reserve(new Word(Tag.PRINT, "print"));
		this.reserve(new Word(Tag.BOOLEAN, "boolean"));
		this.reserve(new Word(Tag.INTEGER, "integer"));
		this.reserve(new Word(Tag.NOT, "not"));
		this.reserve(new Word(Tag.TRUE, "true"));
		this.reserve(new Word(Tag.FALSE, "false"));
		this.reserve(new Word(Tag.IF, "if"));
		this.reserve(new Word(Tag.THEN, "then"));
		this.reserve(new Word(Tag.ELSE, "else"));
		this.reserve(new Word(Tag.WHILE, "while"));
		this.reserve(new Word(Tag.DO, "do"));
		this.reserve(new Word(Tag.BEGIN, "begin"));
		this.reserve(new Word(Tag.END, "end"));
	}
	
	
	public Lexer() {
		this.reserve(new Word(Tag.VAR, "var"));
		this.reserve(new Word(Tag.PRINT, "print"));
		this.reserve(new Word(Tag.BOOLEAN, "boolean"));
		this.reserve(new Word(Tag.INTEGER, "integer"));
		this.reserve(new Word(Tag.NOT, "not"));
		this.reserve(new Word(Tag.TRUE, "true"));
		this.reserve(new Word(Tag.FALSE, "false"));
		this.reserve(new Word(Tag.IF, "if"));
		this.reserve(new Word(Tag.THEN, "then"));
		this.reserve(new Word(Tag.ELSE, "else"));
		this.reserve(new Word(Tag.WHILE, "while"));
		this.reserve(new Word(Tag.DO, "do"));
		this.reserve(new Word(Tag.BEGIN, "begin"));
		this.reserve(new Word(Tag.END, "end"));
	}

	private void readch(BufferedReader br) {
		try {
			peek = (char) br.read();
		} catch (IOException ex) {
			peek = (char) -1;
		}
	}

	private void wsDiscard(BufferedReader br) {
		while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
			if (peek == '\n')
				line++;
			readch(br);
		}
	}

	public Token lexical_scan(BufferedReader br) {
		wsDiscard(br);
		switch (peek) {

		case ',':
			peek = ' ';// allow me to not take a loop, thus I read next char.
			return Token.comma;
		case ';':
			peek = ' ';
			return Token.semicolon;
		case '(':
			peek = ' ';
			return Token.lpar;
		case ')':
			peek = ' ';
			return Token.rpar;
		case '+':
			peek = ' ';
			return Token.plus;
		case '-':
			peek = ' ';
			return Token.minus;
		case '*':
			peek = ' ';
			return Token.mult;
		case '/':
			peek = ' ';
			return Token.div;
		case '&':
			readch(br);
			if (peek == '&') {
				peek = ' ';
				return Word.and;
			} else {
				System.err.println("Erroneous character after & " + peek);
				return null;
			}
		case '|':
			readch(br);
			if (peek == '|') {
				peek = ' ';
				return Word.or;
			} else {
				System.err.println("Erroneous character after | " + peek);
				return null;
			}
		case '=':
			readch(br);
			if (peek == '=') {
				peek = ' ';
				return Word.eq;
			} else {
				return Token.colon;
			}
		case '<':
			readch(br);
			if (peek == '=') {
				peek = ' ';
				return Word.le;
			} else if (peek == '>') {
				peek = ' ';
				return Word.ne;
			} else if (peek == ' ') {
				peek = ' ';
				return Token.lt;
			} else {
				System.err.println("Erroneous character after < " + peek);
				return null;
			}
		case '>':
			readch(br);
			if (peek == '=') {
				peek = ' ';
				return Word.ge;
			} else if (peek == ' ') {
				peek = ' ';
				return Token.gt;
			} else {
				System.err.println("Erroneous character after > " + peek);
				return null;
			}
		case ':':
			readch(br);
			if (peek == '=') {
				peek = ' ';
				return Word.assign;
			} else {
				return Token.colon;
			}
		default:
			if (Character.isLetter(peek) || peek == '_') {
				String s = "";
				do {
					s += peek;
					readch(br);
				} while (Character.isDigit(peek) || Character.isLetter(peek) || peek == '_');

				if ((Word) words.get(s) != null)
					return (Word) words.get(s);
				else if (this.javaIdentifier(s)) {
					Word w = new Word(Tag.ID, s);
					words.put(s, w);
					return w;
				} else {
					System.err.println("invalid pattern identifier: " + s);
					return null;
				}

			} else {
				if (Character.isDigit(peek)) {
					String temp = "";
					do {
						temp += peek;
						readch(br);
					} while (Character.isDigit(peek));

					Word w = new Word(Tag.NUM, temp);
					words.put(temp, w);
					return w;

				} else if (peek == '$') {
					return new Token(Tag.EOF);
					// return new Token(peek);
				} else {
					System.err.println("Erroneous character!!" + peek);
					return null;
				}
			}
		}

	}

	public static void main(String[] args) {
		try {
			Lexer lex = new Lexer("Input.txt");
			Token tok;
			do {
				tok = lex.lexical_scan(lex.br);
				System.out.println("Scan: " + tok.toString());
			} while (tok.tag != Tag.EOF);
			lex.br.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("ERROR:");
		}
	}
}
