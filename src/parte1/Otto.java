package parte1;

public class Otto {
	/**
	 * Procedura che converte l'epsilon-NFA nella figura del documento in un DFA tramite ECLOSE degli stati
	 * @return true se la stringa è riconosciuta, false altrimenti;
	 */
	public static boolean scan(String args)
	{
		int state = 0;
		int i = 0;
		while(state >= 0 && i < args.length())
		{
			final char ch = args.charAt(i++);
			
			switch(state)
			{
			case 0:
				if(ch == 'a' || ch == 'A') state = 1;
				else if(ch == 'b' || ch == 'B') state = 2;
				else state = -1;
				break;
			case 1:
				if(ch == 'a' || ch == 'A') break;
				else if(ch == 'b' || ch == 'B') state = 3;
				else state = -1;
				break;
			case 2: 
				if(ch == 'a') state = 4;
				else state = -1;
				break;
			case 3:
				state = -1;
				break;
			case 4:
				if(ch == 'a') break;
				else state = -1;
				break;
			}
		}
		return state == 4 || state == 3;
	}

	public static void main(String[] args) {
		System.out.println(Otto.scan(args[0]) ? "OK" : "NOPE");

	}

}
