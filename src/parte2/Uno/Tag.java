package parte2.Uno;

public class Tag {
	public static final int EOF = 36, NUM = 256, ID = 257, AND = 258, OR = 259, VAR = 260, INTEGER = 261, BOOLEAN = 262,
			ASSIGN = 263, EQ = 264, GE = 265, LE = 266, NE = 267, TRUE = 268, FALSE = 269, NOT = 270, PRINT = 271,
			IF = 272, THEN = 273, ELSE = 274, WHILE = 275, DO = 276, BEGIN = 277, END = 278;
	public static String getName(int tag)
	{
		switch(tag){
		case 36:  return "EOF";
		case 256: return "NUM";
		case 257: return "ID";
		case 258: return "AND";
		case 259: return "OR";
		case 260: return "VAR";
		case 261: return "INTEGER";
		case 262: return "BOOLEAN";
		case 263: return "ASSIGN";
		case 264: return "EQ";
		case 265: return "GE";
		case 266: return "LE";
		case 267: return "NE";
		case 268: return "TRUE";
		case 269: return "FALSE";
		case 270: return "NOT";
		case 271: return "PRINT";
		case 272: return "IF";
		case 273: return "THEN";
		case 274: return "ELSE";
		case 275: return "WHILE";
		case 276: return "DO";
		case 277: return "BEGIN";
		case 278: return "END";
		default: return "MORE";
		}
	}
}
