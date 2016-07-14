package parte2.Uno;
import java.io.IOException;
import java.util.Hashtable;

public class Lexer {

public int line = 1;
	
	public char peek = ' ';
	
	Hashtable<String,Word> words = new Hashtable<String, Word>();
	
	void reserve (Word w) {	words.put(w.lexeme, w);	}
	
	
	public Lexer(){
		this.reserve(new Word(Tag.VAR, "var"));
		this.reserve(new Word(Tag.PRINT, "print"));
		this.reserve(new Word(Tag.BOOLEAN, "boolean"));
		this.reserve(new Word(Tag.INTEGER, "integer"));
		this.reserve(new Word(Tag.NOT, "not"));
		this.reserve(new Word(Tag.TRUE, "true"));
		this.reserve(new Word(Tag.FALSE, "false"));
	}
	
	private void readch() {
		try{
			peek = (char)System.in.read();
		}catch(IOException ex){
			peek = (char) -1;
		}
	}
	
	private void wsDiscard() {
		while(peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r')
		{
			if(peek == '\n') line++;
			readch();
		}
	}
	
	public Token lexical_scan(){
		wsDiscard();
		switch (peek) {
		
		case ',':
			peek = ' ';//allow me to not take a loop, thus I read next char.
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
			readch();
			if(peek == '&'){
				peek = ' ';
				return Word.and;
			}else {
				System.err.println("Erroneous character after & " + peek);
				return null;
			}
		case '|':
			readch();
			if(peek == '|'){
				peek = ' ';
				return Word.or;
			}else {
				System.err.println("Erroneous character after | " + peek);
				return null;
			}
		case '=':
			readch();
			if(peek == '='){
				peek = ' ';
				return Word.eq;
			}else {
				System.err.println("Erroneous character after = " + peek);
				return null;
			}
		case '<':
			readch();
			if(peek == '='){
				peek = ' ';
				return Word.le;
			}else if(peek == '>'){
				peek = ' ';
				return Word.ne;
			}else if(peek == ' '){
				peek = ' ';
				return Token.lt;
			}else {
				System.err.println("Erroneous character after < " + peek);
				return null;
			}
		case '>':
			readch();
			if(peek == '='){
				peek = ' ';
				return Word.ge;
			}else if(peek == ' '){
				peek = ' ';
				return Token.gt;
			}else {
				System.err.println("Erroneous character after > " + peek);
				return null;
			}
		case ':':
			readch();
			if(Character.isLetter(peek)){
				String s = "";
				do{
					s += peek;
					readch();
				}while(Character.isDigit(peek) || Character.isLetter(peek));
				if((Word)words.get(s) != null) return (Word)words.get(s);
			}
			else if(peek == '='){
					peek = ' ';
					return Word.assign;
			}else {
					System.err.println("Erroneous character after :" + peek);
					return null;
			}
		default:
			if(Character.isLetter(peek))
			{
				String s = "";
				do {
					s += peek;
					readch();
				} while (Character.isDigit(peek) || Character.isLetter(peek));
				
				if((Word)words.get(s) != null) return (Word)words.get(s);
				//nonostante non preveda l'uso di keyword come if,else ecc. li riconosce come identificatori
				//else if(this.javaIdentifier(s)){
				//		Word w = new Word(Tag.ID, s);
				//		words.put(s, w);
				//		return w;
				//else{ System.err.println("invalid pattern identifier: " + s); return null;}
				else {
					Word w = new Word(Tag.ID,s);
					words.put(s, w);
					return w;
				}
				
			}
			else {
				if(Character.isDigit(peek))
				{
					String temp = "";
					do{
						temp += peek;
						readch();
					}while(Character.isDigit(peek));
					
					Word w = new Word(Tag.NUM, temp);
					words.put(temp, w);
					return w;
					
					
				}
				else if(peek == '$'){
					return new Token(Tag.EOF);
					//return new Token(peek);
				}
				else {
					System.err.println("Erroneous character!!" + peek);
					return null;
				}
			}
		}
			
	}
	
	public static void main(String[] args)
	{
		Lexer lex = new Lexer();
		Token tok;
		do{
			tok = lex.lexical_scan();
		}while(tok.tag != Tag.EOF);
		/* uso del file di input esterno
		Lexer lex = new Lexer();
		String inputFileName = new File("").getAbsolutePath().concat("\\src\\").concat(lex.getClass().getPackage().getName().replace('.', '\\').concat("\\Input.txt"));
	    try {
	    	System.out.println();
			BufferedReader br = new BufferedReader(new FileReader(inputFileName));
			Token tok;
			do{
				tok = lex.lexical_scan(br);
				System.out.println("Scan: " + tok.ToString());
			}while(tok.tag != Tag.EOF);
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
		System.out.println("ERROR:");
	}*/
		
		//while(tok.tag != '$');
		
	}

}
