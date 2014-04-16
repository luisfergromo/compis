
import java_cup.runtime.*;
import Interfaz.IOInterfaz;
      
%%
%class Lexer

%line
%column
%cup
   

%{   
    
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {	  
    	return new Symbol(type, yyline, yycolumn, value);
    }
    
%}
  
OPEN="<"
EOF = \r|\n|\r\n
WHITE     = {EOF} | [ \t\f]

LN="ln"
SIN="sin"
COS="cos"
TAN="tan"
SQRT="sqrt"

PI="PI"
DOT="dot"
MAG="magnitude"
NORM="norm"


	/*LITERAL*/
	NUM = [0-9]([0-9])*
	NUM_LIT = ({NUM}("."{NUM}?)?) | (({NUM})?"."{NUM})
	
	/*VARIABLE*/
	ID = [A-Za-z_][A-Za-z_0-9]*
 
%%  
   /* YYINITIAL is the state */
   
<YYINITIAL> {
   
    ";"                { return symbol(sym.SEMI); }
    "+"                { IOInterfaz.print(" + "); return symbol(sym.PLUS); }
    "-"                { IOInterfaz.print(" - "); return symbol(sym.MINUS); }
    "*"                { IOInterfaz.print(" * "); return symbol(sym.TIMES); }
    "/"                { IOInterfaz.print(" / "); return symbol(sym.DIVIDE); }
    "("                { IOInterfaz.print(" ( "); return symbol(sym.LPAREN); }
    ")"                { IOInterfaz.print(" ) "); return symbol(sym.RPAREN); }
    ","                { IOInterfaz.print(" , "); return symbol(sym.COMA); }
    "<"                { IOInterfaz.print(" < "); return symbol(sym.OPEN); }
    ">"                { IOInterfaz.print(" > "); return symbol(sym.CLOSE); }
    "="                { IOInterfaz.print(" = "); return symbol(sym.EQUALS); }
    "^"                { IOInterfaz.print(" ^ "); return symbol(sym.EXP); }
    
    {SQRT}                { IOInterfaz.print(" sqrt "); return symbol(sym.SQRT); }
    {LN}                { IOInterfaz.print(" sqrt "); return symbol(sym.LN); }
    {SIN}                { IOInterfaz.print(" sqrt "); return symbol(sym.SIN); }
    {COS}                { IOInterfaz.print(" sqrt "); return symbol(sym.COS); }
    {TAN}                { IOInterfaz.print(" sqrt "); return symbol(sym.TAN); }
    
    {PI}                { IOInterfaz.print(" PI "); return symbol(sym.PI); }
    {DOT}                { IOInterfaz.print(" . "); return symbol(sym.DOT); }
    {MAG}                { IOInterfaz.print(" mag "); return symbol(sym.MAG); }
    {NORM}                { IOInterfaz.print(" norm "); return symbol(sym.NORM); }
   
    {NUM_LIT}      { IOInterfaz.print(yytext());
                         return symbol(sym.NUMBER, new Float(yytext())); }
   
    {ID}       		{ IOInterfaz.print(yytext());
                         return symbol(sym.ID, yytext());}
   
    {WHITE}       { /* do nothing */ }   
}

[^]                    { throw new Error("Illegal character "+yytext()); }
