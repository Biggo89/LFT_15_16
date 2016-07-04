package parte1;

public class Quattro {

	/**
	 * accetta tutti i nomi di identificatori in Java; un identificatore è una
	 * sequenza non vuota di lettere, numeri e "underscore" che non comincia con
	 * un numero o con una sequenza di "underscore" superiore ad 1.
	 * 
	 * @param args
	 *            stringa in input
	 * @return true se la stringa è riconosciuta, false altrimenti;
	 */
	public static boolean scan(String args) {
		int state = 0;
		int i = 0;
		
		while (state >= 0 && i < args.length()) {
			final char ch = args.charAt(i++);

			switch (state) {
			case 0:
				if (Character.isLetter(ch))
					state = 2;
				else if (ch == '_')
					state = 1;
				else
					state = -1;
				break;
			case 1:
				if (Character.isLetter(ch) || Character.isDigit(ch))
					state = 2;
				else if (ch == '_')
					break;
				else
					state = -1;
				break;
			case 2:
				if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_')
					break;
				else
					state = -1;
				break;
			}
		}
		return state == 2 ? true : false;
	}

	public static void main(String[] args) {
		System.out.println(Quattro.scan(args[0]) ? "OK" : "NOPE");
	}

}
