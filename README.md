# LFT_15_16
Progetto definitivo di LFT anno 15_16

traduttore per programmi ben tipati scritti nel frammento del linguaggio P che permette di stampare sul terminale 
il valore di un’espressione aritmetica-logico, dichiarazione di variabili, assegnazione di valore di espressioni a variabili e l'utilizzo
di variabili in espressioni.

Il programma prende in input un file Output.pas e crea un file Output.j.
Il file Output.j viene parsificato dall'assembler Jasmin producendo il bytecode Output.class eseguito successivamente dalla JVM.

LEGENDA:
i nomi tra parentesi quadre sono simboli non-terminali mentre tutti gli altri sono simboli terminali;
epsilon: stringa vuota;
EOF: end of file character, rappresentato dal simbolo $, da inserire alla fine del file.

La grammatica del linguaggio P è la seguente

prog --> [declist] [stat] EOF

declist --> [dec] ; [declist]
declist --> epsilon

dec --> [type] ID [idlist]

idlist --> , ID [idlist]
idlist --> epsilon

type --> integer
type --> boolean

stat --> ID := [orE]
stat --> print( [orE] )
stat --> begin [statlist] end
stat --> while [exp] do [stat]
stat --> if [exp] then [stat]
stat --> if [exp] then [stat] else [stat]

exp --> [orE]

statlist --> [stat] [statlist_p]
 
statlist_p --> ; [stat] [statlist_p]
statlist_p --> epsilon
 
orE --> [andE] [orE_p]

orE_p --> || [andE] [orE_p]
orE_p --> epsilon

andE --> [relE] [andE_p]

andE_p --> && [relE] [andE_p]
andE_p --> epsilon

relE  --> [addE] [relE_p]

relE p --> == [addE] 
relE_p --> <> [addE]
relE_p --> <= [addE]
relE_p --> >= [addE] 
relE_p --> < [addE] 
relE_p --> > [addE]
relE_p --> epsilon

addE --> [multE] [addE_p]

addE_p --> + [multE] [addE_p]
addE_p --> - [multE] [addE_p]
addE_p --> epsilon

multE --> [fact] [multE_p]

multE_p --> * [fact] [multE_p]
multE_p --> / [fact] [multE p]
multE_p --> epsilon

fact --> ( [orE] )
fact --> ID
fact --> NUM
fact --> true
fact --> false

ESECUZIONE: compilare tutto il progetto.
1- Eseguire il traduttore della parte 5.3 che prende di default già in input il file Output.pas(è quì che inserirete il vostro snippet).Se il programma non restituisce errori, restituendo invece tutti i token trovati e riconosciuti, genererà il file output.j.
2- Il file Output.j dovrà essere l'input dell'assembler Jasmin che genererà il file Output.class tramite il comando "java -jar jasmin.jar Output.j".
3- Per ultimo non resta altro che eseguire il file Output.class con il comando "java Output".

PS: i punti 2 e 3 sono stati automatizzati dal file Run.bat.



