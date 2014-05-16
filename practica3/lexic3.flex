package Practica3;
import java_cup.runtime.*;
import Interfaz.IOInterfaz;
      
%%
%public
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


/*RESERVED WORDS*/

ARRAY	=		"array"
OF		=		"of"
IF		=		"if"
THEN	=		"then"
WHILE	=		"while"
DO		=		"do"
MOD		=		"mod"


/*****/

NUMS		= 		[0-9]([0-9])*
CHARS		=		[a-zA-Z]([a-zA-Z])*

MAJOR_THAN	=	">"
MINOR_THAN	=	"<"
MAJOR_EQUALS =	">="
MINOR_EQUALS =	"<="
EQUALS		=	"=="

PLUS		=	"+"
MINUS		=	"-"
TIMES		=	"*"
DIV			=	"/"

OPEN		=	"{"
CLOSE		=	"}" 

BR	=	\n
EOF = \r|\r\n
WHITE  = [ \t\f]


%%



<YYINITIAL> {
   
   /*RESERVED WORDS*/

	{ARRAY}	{ IOInterfaz.print("array ");	return symbol(sym.array);}
	{OF}	{ IOInterfaz.print("of ");		return symbol(sym.of);}
	{IF}	{ IOInterfaz.print("if ");		return symbol(sym.IF); }
	{THEN}	{ IOInterfaz.print("then ");	return symbol(sym.then);}
	{WHILE}	{ IOInterfaz.print("while ");	return symbol(sym.WHILE);}
	{DO}	{ IOInterfaz.print("do ");		return symbol(sym.DO); }
	{MOD}	{ IOInterfaz.print("mod ");		return symbol(sym.mod);}
	
	"integer"	{ IOInterfaz.print("integer ");	return symbol(sym.INTEGER);}
	"real"		{ IOInterfaz.print("real ");	return symbol(sym.REAL);}
	
	
	/*SYMBOLS*/
	
	":="	{ IOInterfaz.print(":= ");		return symbol(sym.ASSIGN);}
	";"		{ IOInterfaz.print("; ");		return symbol(sym.SEMI);}
	":"		{ IOInterfaz.print(": ");		return symbol(sym.DOTS);}
	"["		{ IOInterfaz.print("[ ");		return symbol(sym.OPEN);}
	"]"		{ IOInterfaz.print("] ");		return symbol(sym.CLOSE);}
	
	{OPEN}		{ symTable.push_block(); 	}
	{CLOSE}		{ return symbol(sym.CLOSE);}
	
	/*****/
	
	{NUMS}"."{NUMS}					{ IOInterfaz.print(" (real) "+yytext()+" "); 		return symbol(sym.integer_num, new Float(yytext()));}
	{NUMS}							{ IOInterfaz.print(" (int) "+yytext()+" ");			return symbol(sym.integer_num, new Integer(yytext()));}
	{CHARS}({CHARS}|{NUMS}|"_")*	{ IOInterfaz.print("id:"+yytext()+" ");				return symbol(sym.id, yytext());}
	
	{MAJOR_THAN}			{ IOInterfaz.print("> ");	return symbol(sym.MAJOR_THAN);}
	{MAJOR_EQUALS}			{ IOInterfaz.print(">= ");	return symbol(sym.MAJOR_EQUALS);}
	{MINOR_THAN}			{ IOInterfaz.print("< ");	return symbol(sym.MINOR_THAN);}
	{MINOR_EQUALS}			{ IOInterfaz.print("<= ");	return symbol(sym.MINOR_EQUALS);}
	{EQUALS}				{ IOInterfaz.print("== ");	return symbol(sym.EQUALS);}
	
	
	{PLUS}					{ IOInterfaz.print("+ ");	return symbol(sym.PLUS);}
	{MINUS}					{ IOInterfaz.print("- ");	return symbol(sym.MINUS);}
	{TIMES}					{ IOInterfaz.print("* ");	return symbol(sym.TIMES);}
	{DIV}					{ IOInterfaz.print("/ ");	return symbol(sym.DIV);}
	
	{EOF}       	{ /* do nothing */ }
	{WHITE}       	{ /* do nothing */}
	{BR}       		{ /* do nothing */ IOInterfaz.println("\n..........");}
}
