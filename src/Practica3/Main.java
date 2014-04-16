package Practica3;

import java.io.FileReader;

import Practica3.Lexer;
import Practica3.parser;

public class Main {
	public static void main(String args[])
	{
		try {
		      FileReader fr = new FileReader("practica3/entrada.txt");
		      parser p = new parser(new Lexer(fr));
		      Object result = p.parse().value;
			
		    } catch (Exception e) {
		      /* do cleanup here -- possibly rethrow e */
		      e.printStackTrace();
		    }
	}
}
