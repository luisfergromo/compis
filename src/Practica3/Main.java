package Practica3;

import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.SwingUtilities;

import Interfaz.IOInterfaz;
import Practica3.Lexer;
import Practica3.parser;

@SuppressWarnings("serial")
public class Main extends IOInterfaz{
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
	
	  public Main() {
			super("Practica 3", 600, 500, false,false,300);
			
			inText.setEditable(false);
			
			//up
		    /* Start the parser */
		    try {
		      FileReader fr = new FileReader("practica3/entrada.txt");
		      parser p = new parser(new Lexer(fr));
		      Object result = p.parse().value;
			
		    } catch (Exception e) {
		      /* do cleanup here -- possibly rethrow e */
		      e.printStackTrace();
		    }
			
			//down
			try{
				FileReader fr = new FileReader("practica3/entrada.txt");
				BufferedReader reader= new BufferedReader(fr);
				
				String content="";
				while(true)
				{
					String s=reader.readLine();
					if(s==null)
						break;
					content+=s+"\n";
				}
				
//			      System.out.println(symTable.report());
				setInput(content);
			}catch(Exception ex){}
		}
	
	@Override
	public void sendInput(KeyEvent arg0) {}
}
