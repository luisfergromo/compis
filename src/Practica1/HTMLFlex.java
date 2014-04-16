package Practica1;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

import Practica1.HTMLToken;

public class HTMLFlex {
	
	static Reader 	is;
	static NewLexer lexer;
	static BufferedWriter os;
	
	public static void main(String args[])
	{
		try{
			new HTMLFlex();
		}catch(Exception ex){}
	}
	
	public HTMLFlex() throws IOException
	{
		File f= new File("toHTML.txt");
		if(!f.exists())
			return;
		
		is = new InputStreamReader(new FileInputStream("toHTML.txt"), "UTF8");
		os= new BufferedWriter(new FileWriter("salida.html"));
		
		try{
			os.write(execute());
		}catch(Exception ex){}
		
		try{
			os.close();
		}catch(Exception ex){}
		
		f= new File("salida.html");
		try{
			Desktop.getDesktop().browse(f.toURI());
		}catch (Exception ex){}
	}
	
	public String execute() throws IOException
	{
		String ans ="<html><body><pre>";
		ans+="NOTACION: <font color=\"orange\">nombres</font> <font color=\"red\">telefonos</font>  <font color=\"purple\">YOUTUBE</font>\n\n";
		lexer = new NewLexer (is);
		
		HTMLToken token = lexer.yylex();
		while(token !=null){
			switch(token)
			{
				case NOMBRE: ans+="<font color=\"orange\">"+lexer.lexeme+"</font>"; break;
				case TELEFONO: ans+="<font color=\"red\">"+lexer.lexeme+"</font>"; break;
				case CORREO: ans+="<a href=\"mailto:"+lexer.lexeme+"\">"+lexer.lexeme+"</a>"; break;
				case URL: ans+="<a href="+lexer.lexeme+">"+lexer.lexeme+"</a>\n"; break;
				case YOUTUBE: ans+="<iframe width=420 height=345 src=\""+lexer.lexeme+"\"></iframe>"; break;
				default: ans+=lexer.lexeme; break;
			}
			 
			 token = lexer.yylex();
		}
		
		return ans+"</pre></body></html>";
	}
	
}
