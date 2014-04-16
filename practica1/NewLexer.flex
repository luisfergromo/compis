package Practica1;
%%
%class NewLexer
%type Token

L = [a-z]+
D = [0-9]+
white=[ \t\r\n]+
Palabra=[A-Z]([a-z])*
palabra=([a-z])+
particula=("y"|"la"|"de")
num=([0-9])+
num2=({num}{num})
domain={palabra}({palabra}|{num}|"-"|".")*
palabramix=([A-Z]|[a-z])([A-Z]|[a-z]|[0-9]|"."|"_"|"-"|"?"|"=")+
ip=([0-9][0-9]?[0-9]?)"."([0-9][0-9]?[0-9]?)"."([0-9][0-9]?[0-9]?)"."([0-9][0-9]?[0-9]?)

%{
	public String lexeme;
%}
%%


{white} {lexeme=yytext(); return HTMLToken.ERROR;}
"//" {lexeme=yytext(); return HTMLToken.ERROR;}

{Palabra}(_({particula}_)*{Palabra})* {lexeme=yytext(); return HTMLToken.NOMBRE;}

(["("]{num}{num}{num}?[")"]-)?({num2}-{num2}-{num2}-{num2})(-{num2})? {lexeme=yytext(); return HTMLToken.TELEFONO;}

{palabramix}+"@"{domain}("."{palabra})("."{palabra})? {lexeme=yytext(); return HTMLToken.CORREO;}


("http"[s]?"://www.youtube.com") ("/"{palabramix})* {lexeme=yytext(); return HTMLToken.YOUTUBE;}

(("http"[s]?"://"{domain}"."{palabra}) | {ip}) ("/"{palabramix})* {lexeme=yytext(); return HTMLToken.URL;}

. {lexeme=yytext(); return HTMLToken.ERROR;}
