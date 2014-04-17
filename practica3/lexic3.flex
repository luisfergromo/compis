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
OPREL		=		( ">" | "<" | ">=" | "<=" | "==" )
OPARIT		=		( "+" | "-" | "*" | "/")

BR	=	\n
EOF = \r|\r\n
WHITE     = {EOF} | [ \t\f]


%%



<YYINITIAL> {
   
   /*RESERVED WORDS*/

	{ARRAY}	{ System.out.print("array ");	return symbol(sym.array);}
	{OF}	{ System.out.print("of ");		return symbol(sym.of);}
	{IF}	{ System.out.print("if ");		return symbol(sym.IF); }
	{THEN}	{ System.out.print("then ");	return symbol(sym.then);}
	{WHILE}	{ System.out.print("while ");	return symbol(sym.WHILE);}
	{DO}	{ System.out.print("do ");		return symbol(sym.DO); }
	{MOD}	{ System.out.print("mod ");		return symbol(sym.mod);}
	
	"integer"	{ System.out.print("integer ");	return symbol(sym.INTEGER);}
	"real"		{ System.out.print("real ");	return symbol(sym.REAL);}
	
	
	/*SYMBOLS*/
	
	":="	{ System.out.print(":= ");		return symbol(sym.ASSIGN);}
	";"		{ System.out.print("; ");		return symbol(sym.SEMI);}
	":"		{ System.out.print(": ");		return symbol(sym.DOTS);}
	"["		{ System.out.print("[ ");		return symbol(sym.OPEN);}
	"]"		{ System.out.print("] ");		return symbol(sym.CLOSE);}
	"^"		{ System.out.print("^ ");		return symbol(sym.PTR);}
	
	/*****/
	
	{NUMS}"."{NUMS}					{ System.out.print("real_num "); 		return symbol(sym.real_num);}
	{NUMS}							{ System.out.print("int_num ");			return symbol(sym.integer_num);}
	{CHARS}({CHARS}|{NUMS}|"_")*	{ System.out.print("id ");				return symbol(sym.id, yytext());}
	{OPREL}							{ System.out.print("oprel ");			return symbol(sym.oprel);}
	{OPARIT}						{ System.out.print("oparit ");			return symbol(sym.oparit);}
	
	{WHITE}       	{ /* do nothing */}
	{BR}       		{ /* do nothing */ System.out.println("");}
}
