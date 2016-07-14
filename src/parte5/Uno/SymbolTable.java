package parte5.Uno;

import java.util.*;

public class SymbolTable {

	Map <String, Type> TypeMap = new HashMap <String, Type>(); //symbol table type
    Map <String, Integer> OffsetMap = new HashMap <String,Integer>() ; //symbol table offset

	public void insert( String s, Type t, int address ) {

       //conviene usare le mappe
       //eccezione se e' gia' presente
       //TypeMap.containsKey(s)||OffsetMap.containsKey(s)

       if( !TypeMap.containsKey(s) ) TypeMap.put(s,t);
       else throw new IllegalArgumentException("Variabile gia' dichiarata");

       if( ! OffsetMap.containsValue(address) ) OffsetMap.put(s,address);
       else throw new IllegalArgumentException("Riferimento ad una locazione di memoria gia' occupata da un'altra variabile." );
	}

	public Type lookupType ( String s) {

       //eccezione se la stringa non e' presente
       if( TypeMap.containsKey(s) ) return TypeMap.get(s);
       throw new IllegalArgumentException("Variabile sconosciuta ." + s );

	}

	public int lookupAddress ( String s ) {

       //eccezione se la stringa non e' presente
       if( OffsetMap.containsKey(s) ) return OffsetMap.get(s);
       throw new IllegalArgumentException("Variabile sconosciuta.");
	}
}
