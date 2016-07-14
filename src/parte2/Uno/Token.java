package parte2.Uno;

public class Token {

	public final int tag;

	public Token(int x) {
		this.tag = x;
	}

	public String toString() {
		return "<" + Tag.getName(tag) + ", " + (char) tag + ">";
	}

	public static final Token comma = new Token(','), colon = new Token(':'), semicolon = new Token(';'),
			lpar = new Token('('), rpar = new Token(')'), plus = new Token('+'), minus = new Token('-'),
			mult = new Token('*'), div = new Token('/'), lt = new Token('<'), gt = new Token('>');
}
