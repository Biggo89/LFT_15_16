package parte1;

public class Cinque {

	/**
	 * DFA che riconosce le stringhe binare, il cui valore decimale è multiplo di 3.
	 * es 1.5
	 * @return true se la stringa è riconosciuta, false altrimenti;
	 */
	public static boolean scan(String args) 
	{
		int state = 0;
		int i = 0;
		
		while(state >= 0 && i < args.length()){
			final char ch = args.charAt(i++);
			
			switch(state){
			
			case 0:
				if(ch == '1') state = 1;
				else if(ch == '0') state = 0;
				else state = -1;
				break;
			case 1:
				if(ch == '0') state = 2;
				else if(ch == '1') state = 0;
				else state = -1;
				break;
			case 2:
				if(ch == '0') state = 1;
				else if(ch == '1') state = 2;
				else state = -1;
				break;
			}
			
		}
		return state == 0;
	}

	public static void main(String[] args) {
		System.out.println(Cinque.scan(args[0]) ? "OK" : "NOPE");
	}

}
