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
	ArrayList<Variable> variables;
	public Block()
	{
		variables = new ArrayList<Variable>();
	}
}

public class symTable {
	
	public static String report="";
	public static Stack<Block> blocks= new Stack<Block>();
	
	public static void push_block()
	{
		report+=("\n----------block.push----------\n");
		blocks.push(new Block());
	}
	
	public static void pop_block()
	{
		report+=("\n----------block.pop----------\n");
		blocks.pop();
	}
	
	public static boolean insert_id ( String name , String type )
	{
		if(blocks.isEmpty())
			push_block();
		
		if(get_id(name,false)!=null)
			return false;
		
		report+=("\n----------"+"id "+name +" > "+type+"----------\n");
		blocks.get(blocks.size()-1).variables.add(new Variable(name, type));
		
		return true;
	}
	
	public static Variable get_id(String name, boolean global)
	{
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
