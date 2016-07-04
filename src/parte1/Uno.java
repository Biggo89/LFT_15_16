package parte1;

public class Uno {

	/**
	 * Metodo che riconosce le stringhe contenenti almeno 3 zeri
	 * @param str stringa in input
	 * @return boolean: true se riconosciuta, false altrimenti
	 */
	public static boolean scan(String str) {
		int state = 0;
		int i = 0;
		while (state >= 0 && i < str.length()) {
			final char ch = str.charAt(i++);

			switch (state) {

			case 0:
				if (ch == '0')
					state = 1;
				else if (ch == '1')
					state = 0;
				else
					state = -1;
				break;
			case 1:
				if (ch == '0')
					state = 2;
				else if (ch == '1')
					state = 0;
				else
					state = -1;
				break;
			case 2:
				if (ch == '0')
					state = 3;
				else if (ch == '1')
					state = 0;
				else
					state = -1;
				break;
			case 3:
				if (ch == '0' || ch == '1')
					state = 3;
				else
					state = -1;
				break;
			}
		}

		return state == 3;
	}

	/**
	 * Metodo che riconosce un linguaggio in cui non compaiono
	 * 3 zeri consecutivi
	 * @param str stringa in input
	 * @return boolean: true se riconosciuta, false altrimenti
	 */
	public static boolean scan1(String str) {

		int state = 0;
		int i = 0;

		while (i < str.length() && state >= 0) {

			final char ch = str.charAt(i++);
			switch (state) {

			case 0:
				if (ch == '0')
					state = 1;
				else if (ch == '1')
					state = 2;
				else
					state = -1;
				break;
			case 1:
				if (ch == '0')
					state = 3;
				else if (ch == '1')
					state = 2;
				else
					state = -1;
				break;
			case 2:
				if (ch == '0')
					state = 1;
				else if (ch == '1')
					state = 2;
				else
					state = -1;
				break;
			case 3:
				if (ch == '1')
					state = 2;
				else
					state = -1;
				break;
			}
		}
		return (state == 2 || state == 3 || state == 4);

	}

	public static void main(String[] args) {
		System.out.println(scan(args[0]) ? "OK" : "NOPE");
	}

}
