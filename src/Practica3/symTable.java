package Practica3;

import java.util.ArrayList;
import java.util.Stack;

class Variable{
	public String name;
	public String type;
	public String h;
	public String s;
	
	public Variable(String name, String type)
	{
		this.name=name;
		this.type=type;
	}
}

class Block{
	boolean declarations_over=false;
	ArrayList<Variable> variables;
	public Block()
	{
		variables = new ArrayList<Variable>();
	}
}

public class symTable {
	
	public static String report="";
	public static Stack<Block> blocks= new Stack<Block>();
	
	static int block_status=-2;
	public static boolean is_balanced(){return block_status==1;}
	public static boolean declarations_over(){return blocks.get(blocks.size()-1).declarations_over;}
	public static void close_declarations(){blocks.get(blocks.size()-1).declarations_over=true;}
	
	public static void push_block()
	{
		if(block_status==-2) /*FIRST USE*/
			block_status=1;
		
		report+=("\n----------block.push----------\n");
		block_status+=1;
		blocks.push(new Block());
	}
	
	public static void pop_block()
	{
		report+=("\n----------block.pop----------\n");
		if(block_status <=1 )
			System.out.println("!can't_close_block");
		else
		{
			block_status-=1;
			blocks.pop();
		}
	}
	
	public static boolean insert_id ( String name , String type )
	{
		if(block_status==-2) /*FIRST USE*/
			push_block();
		
		if(declarations_over())
			return false;
		
		if(get_id(name,false,false)!=null)
			return false;
		
		report+=("\n----------"+"id "+name +" > "+type+"----------\n");
		blocks.get(blocks.size()-1).variables.add(new Variable(name, type));
		
		return true;
	}
	
	public static Variable get_id(String name, boolean global, boolean edit)
	{
		if(edit)
			close_declarations();
			
		for ( int i=blocks.size()-1; i>=0 ; i--)
		{
			for( Variable v: blocks.get(i).variables )
			{
				if ( v.name.contentEquals(name) )
					return v;
			}
			
			if(!global)
				return null;
		}
		
		return null;
	}
	
	public static String report()
	{
		return "\n=========================\nsymTable LOG\n"+report;
	}
	
}
