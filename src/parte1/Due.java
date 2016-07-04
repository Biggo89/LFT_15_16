package parte1;


public class Due {
	
	public static boolean checkPlusMinus(char temp){
		return ((temp == '-') ||  (temp == '+')) ? true : false;
	}

	/**
	 * DFA che ricosce le costanti numeriche(positive e negative) in virgola mobile,
	 * con esponenti (positivi e negativi)
	 * @param args stringa in input
	 * @return true se la stringa è riconosciuta, false altrimenti;
	 */
	public static boolean scan(String args) {
		int state = 0;
		int i = 0;
		
		while (i < args.length() && state >= 0) {
			final char ch = args.charAt(i++);

			switch (state) {

			case 0:
				if (ch == '.')
					state = 3;
				else if (Due.checkPlusMinus(ch))
					state = 2;
				else if (Character.isDigit(ch))
					state = 1;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;

			case 1:
				if (Character.isDigit(ch))
					state = 1;
				else if (ch == '.')
					state = 3;
				else if (ch == 'e')
					state = 5;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;
			case 2:
				if (Character.isDigit(ch))
					state = 1;
				else if (ch == '.')
					state = 3;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;
			case 3:
				if (Character.isDigit(ch))
					state = 4;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;
			case 4:
				if (Character.isDigit(ch))
					state = 4;
				else if (ch == 'e')
					state = 5;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;
			case 5:
				if (Character.isDigit(ch))
					state = 6;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;
			case 6:
				if (Character.isDigit(ch))
					state = 6;
				else if (Character.isWhitespace(ch))
					break;
				else
					state = -1;
				break;
			}
		}
		return state == 1 || state == 4 || state == 6;
	}

	public static void main(String[] args) {
		System.out.println(Due.scan(args[0]) ? "OK" : "NOPE");
	}

}
