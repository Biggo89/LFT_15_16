package parte1;

public class Sei {

	public static boolean scan(String args) {
		int state = 0;
		int i = 0;

		while(state >= 0 && i < args.length())
		{
			final char ch = args.charAt(i++);
			
			switch(state)
			{
			case 0:
				if(ch == '/') state = 1;
				else state = -1;
				break;
			case 1:
				if(ch == '*') state = 2;
				else state = -1;
				break;
			case 2:
				if(ch == 'a' || ch == '/') break;
				else if(ch == '*') state = 3;
				else state = -1;
				break;
			case 3:
				if(ch == '*') break;
				else if(ch == 'a') state = 2;
				else if(ch == '/') state = 4;
				else state = -1;
				break;
			}
		}
		
		return state == 4;
	}

	public static void main(String[] args) {
		System.out.println(Sei.scan(args[0]) ? "OK" : "NOPE");
	}

}
