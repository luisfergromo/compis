package Practica2;
/*
  This example comes from a short article series in the Linux 
  Gazette by Richard A. Sevenich and Christopher Lopes, titled
  "Compiler Construction Tools". The article series starts at

  http://www.linuxgazette.com/issue39/sevenich.html

  Small changes and updates to newest JFlex+Cup versions 
  by Gerwin Klein
*/

/*
  Commented By: Christopher Lopes
  File Name: Main.java
  To Create: 
  After the scanner, lcalc.flex, and the parser, ycalc.cup, have been created.
  > javac Main.java
  
  To Run: 
  > java Main test.txt
  where test.txt is an test input file for the calculator.
*/

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.SwingUtilities;

import Interfaz.IOInterfaz;
   
@SuppressWarnings("serial")
public class Main extends IOInterfaz{
  @SuppressWarnings("resource")
public Main() {
		super("Cup Test", 600, 500, false,false,300);
		
		inText.setEditable(false);
		
		//up
	    /* Start the parser */
	    try {
	      FileReader fr = new FileReader("cup.txt");
	      parser p = new parser(new Lexer(fr));
	      Object result = p.parse().value;
		
	    } catch (Exception e) {
	      /* do cleanup here -- possibly rethrow e */
	      e.printStackTrace();
	    }
		
		//down
		try{
			FileReader fr = new FileReader("cup.txt");
			BufferedReader reader= new BufferedReader(fr);
			
			String content="";
			while(true)
			{
				String s=reader.readLine();
				if(s==null)
					break;
				content+=s+"\n";
			}
			
			setInput(content);
		}catch(Exception ex){}
	}

static public void main(String argv[]) 
{
	try{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}catch(Exception ex){}
  }

@Override
public void sendInput(KeyEvent arg0) {}
}


