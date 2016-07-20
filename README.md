# LFT_15_16
Progetto definitivo di LFT anno 15_16

traduttore per programmi ben tipati scritti nel frammento del linguaggio P che permette di stampare sul terminale 
il valore di un’espressione aritmetica-logico, dichiarazione di variabili, assegnazione di valore di espressioni a variabili e l'utilizzo
di variabili in espressioni.

Il programma prende in input un file Output.pas e crea un file Output.j.
Il file Output.j viene parsificato dall'assembler Jasmin producendo il bytecode Output.class eseguito successivamente dalla JVM.

LEGENDA:<br/>
i nomi tra parentesi quadre sono simboli non-terminali mentre tutti gli altri sono simboli terminali;<br/>
epsilon: stringa vuota;<br/>
EOF: end of file character, rappresentato dal simbolo $, da inserire alla fine del file.<br/>
<br/>
***La grammatica del linguaggio P è la seguente:***<br/>
<br/>
**prog --> [declist] [stat] EOF**<br/>
<br/>
**declist --> [dec] ; [declist]**<br/>
**declist --> epsilon**<br/>
<br/>
**dec --> [type] ID [idlist]**<br/>
<br/>
**idlist --> , ID [idlist]**<br/>
**idlist --> epsilon**<br/>
<br/>
**type --> integer**<br/>
**type --> boolean**<br/>
<br/>
**stat --> ID := [orE]**<br/>
**stat --> print( [orE] )**<br/>
**stat --> begin [statlist] end**<br/>
**stat --> while [exp] do [stat]**<br/>
**stat --> if [exp] then [stat]**<br/>
**stat --> if [exp] then [stat] else [stat]**<br/>
<br/>
**exp --> [orE]**<br/>

**statlist --> [stat] [statlist_p]**<br/>
 <br/>
**statlist_p --> ; [stat] [statlist_p]**<br/>
**statlist_p --> epsilon**<br/>
 <br/>
**orE --> [andE] [orE_p]**<br/>
<br/>
**orE_p --> || [andE] [orE_p]**<br/>
**orE_p --> epsilon**<br/>
<br/>
**andE --> [relE] [andE_p]**<br/>
<br/>
**andE_p --> && [relE] [andE_p]**<br/>
**andE_p --> epsilon**<br/>
<br/>
**relE  --> [addE] [relE_p]**<br/>
<br/>
**relE p --> == [addE]**<br/>
**relE_p --> <> [addE]**<br/>
**relE_p --> <= [addE]**<br/>
**relE_p --> >= [addE]**<br/>
**relE_p --> < [addE]**<br/>
**relE_p --> > [addE]**<br/>
**relE_p --> epsilon**<br/>
<br/>
**addE --> [multE] [addE_p]**<br/>
<br/>
**addE_p --> + [multE] [addE_p]**<br/>
**addE_p --> - [multE] [addE_p]**<br/>
**addE_p --> epsilon**<br/>
<br/>
**multE --> [fact] [multE_p]**<br/>
<br/>
**multE_p --> * [fact] [multE_p]**<br/>
**multE_p --> / [fact] [multE p]**<br/>
**multE_p --> epsilon**<br/>
<br/>
**fact --> ( [orE] )**<br/>
**fact --> ID**<br/>
**fact --> NUM**<br/>
**fact --> true**<br/>
**fact --> false**<br/>
<br/>
ESECUZIONE: compilare tutto il progetto.<br/>
1- Eseguire il traduttore della parte 5.3 che prende di default già in input il file Output.pas(è quì che inserirete il vostro snippet).Se il programma non restituisce errori, restituendo invece tutti i token trovati e riconosciuti, genererà il file output.j.<br/>
2- Il file Output.j dovrà essere l'input dell'assembler Jasmin che genererà il file Output.class tramite il comando "java -jar jasmin.jar Output.j".<br/>
3- Per ultimo non resta altro che eseguire il file Output.class con il comando "java Output".<br/>
<br/>
PS: i punti 2 e 3 sono stati automatizzati dal file Run.bat.



