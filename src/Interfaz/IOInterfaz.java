package Interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public abstract class IOInterfaz extends JFrame {
	
	static int 					MAX_X;
	static int 					MAX_Y;
	static String 					title;
	
	static JSplitPane				split;
	static public JTextPane 		inText;
	static JScrollPane 				inScroll;
	static public JTextPane 		outText;
	static JScrollPane 				outScroll;
	static StyledDocument	 		doc;
	
	public IOInterfaz(String title, int max_x, int max_y, boolean maximized, boolean horizontal, int divloc)
	{
		IOInterfaz.title=title;
		if(maximized)
		{
			Dimension screenSize= Toolkit.getDefaultToolkit().getScreenSize();
			MAX_X=(int)screenSize.width;
			MAX_Y=(int)screenSize.height;
		}
		else
		{
			MAX_X=max_x;
			MAX_Y=max_y;
		}
		
		init(maximized,horizontal,divloc);
		addListeners();
	}
	
	private void init(boolean maximized, boolean horizontal, int divloc)
	{
		try{
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			super.setTitle(title);
			super.setSize(MAX_X, MAX_Y);
			super.setVisible(true);
			if(maximized)
				super.setExtendedState(MAXIMIZED_BOTH);
			else
				super.setLocationRelativeTo(null);
			
		}catch(Exception ex){};
		
		//OUPUT
		outText= new JTextPane();
		outText.setEditable(false);
		outScroll= new JScrollPane(outText);
		doc = outText.getStyledDocument();
		
		outText.setBackground(new Color(230,230,250));
		outText.setForeground(Color.blue);
		
		//IN
		inText= new JTextPane();
		inText.setEditable(true);
		inScroll= new JScrollPane(inText);
		
		//SPLIT
		if(horizontal)
		{
			split= new JSplitPane(JSplitPane.VERTICAL_SPLIT, outScroll, inScroll);
			split.setDividerLocation(divloc);
		}
		else
		{
			split= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, inScroll, outScroll);
			split.setDividerLocation(divloc);
		}
		
		inText.requestFocus();
		
		//PANEL
		super.add(split);
	}
	
	private void addListeners()
	{
		KeyListener key_listener= new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==10)
					sendInput(arg0);
			}
		};
		
		inText.addKeyListener(key_listener);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////
	/*
	 * METHODS
	 */
	public abstract void sendInput(KeyEvent arg0);
	
	public static void clearInput()
	{
		inText.setText("");
	}
	
	public static void clearOutput()
	{
		try{
			doc.remove(0, doc.getLength());
		}catch(Exception ex){}
	}
	
	public static void setInput(String s)
	{
		inText.setText(s);
		inText.requestFocus();
	}
	
	public static void print(String s)
	{
		try{
			doc.insertString(doc.getLength(), s, null );
		}catch(Exception ex){}
	}
	
	public static void println(String s)
	{
		try{
			doc.insertString(doc.getLength(), s+"\n", null );
		}catch(Exception ex){}
	}
}
