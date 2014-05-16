package Practica3;

import java.util.ArrayList;
import java.util.Stack;

import Interfaz.IOInterfaz;

class Block{
	boolean declarations_over=false;
	@SuppressWarnings("rawtypes")
	ArrayList<Variable> variables;
	@SuppressWarnings("rawtypes")
	public Block()
	{
		variables = new ArrayList<Variable>();
	}
}

public class symTable {
	
	public static String report="";
	public static Stack<Block> blocks= new Stack<Block>();
	
	static int block_status=0;
	public static boolean is_balanced(){return (block_status==0);}
	public static boolean declarations_over()
	{
		if(blocks.isEmpty())
			return false;
		else
			return blocks.get(blocks.size()-1).declarations_over;
	}
	public static void close_declarations(){blocks.get(blocks.size()-1).declarations_over=true;}
	
	public static void push_block()
	{
		report+=("\n----------block.push----------\n");
		IOInterfaz.print("pushing-block ");
		block_status+=1;
		blocks.push(new Block());
	}
	
	public static void pop_block()
	{
		report+=("\n----------block.pop----------\n");
		if(block_status <1 )
			IOInterfaz.println("!can't_close_block");
		else
		{
			block_status-=1;
			blocks.pop();
			
			IOInterfaz.print("closing-block ");			
		}
	}
	
	public static boolean insert_id ( Variable<?> var )
	{
		if(block_status==0 && blocks.isEmpty()) /*FIRST USE*/
			blocks.push(new Block());
		
		if(declarations_over())
		{
			IOInterfaz.println("ERROR: declaraciones cerradas"); 
			return false;
		}
		
		for( Variable<?> v: blocks.get(blocks.size()-1).variables )
		{
			if ( v.name.contentEquals(var.name) )
			{
				IOInterfaz.println("ERROR: identificador duplicado"); 
				return false;
			}
		}
		
		report+=("\n----------"+"id "+var.name +" > "+var.type+"----------\n");
		blocks.get(blocks.size()-1).variables.add(var);
			
		return true;
	}
	
	public static Variable<?> get_id(String name)
	{
		
		for (int i=blocks.size()-1 ; i>=0 ; i-- )
		{
			for( Variable<?> v: blocks.get(i).variables )
			{
				if ( v.name.contentEquals(name) )
					return v;
			}
		}
		
		return null;
	}
	
	public static String report()
	{
		
		
		return "\n=========================\nsymTable LOG\n"+report+"\nBlocks balanced: "+is_balanced();
	}
	
}
