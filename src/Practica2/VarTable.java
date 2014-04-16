package Practica2;
import java.util.ArrayList;


public class VarTable {
	
	static class Var
	{
		String id;
		Float[] value;
		
		public Var(String id, float value)
		{
			this.id=id;
			this.value=new Float[]{value};
		}
		
		public Var(String id, float value1, float value2)
		{
			this.id=id;
			this.value=new Float[]{value1,value2};
		}
	}
	
	static ArrayList<Var> variables= new ArrayList<Var>();
	
	static float addFloat(String id, float value)
	{
		for(Var v:variables)
			if(v.id.contentEquals(id))
			{
				v.value= new Float[]{value};
				return value;
			}
		
		variables.add(new Var(id, value));
		return value;
	}
	
	static float getFloat(String id)
	{		
		for(Var v:variables)
			if(v.id.contentEquals(id))
				return v.value[0];
		
		return (float) 0.0;
	}
	
	static Float[] getVector(String id)
	{
		for(Var v:variables)
			if(v.id.contentEquals(id))
				return v.value;
		
		return new Float[]{(float) 0,(float) 0};
	}
	
	static void addVector(String id, float value1, float value2)
	{
		for(Var v:variables)
			if(v.id.contentEquals(id))
			{
				v.value= new Float[]{value1,value2};
				return;
			}
		
		variables.add(new Var(id, value1,value2));
	}
	
	static Float[] sumVector(Float[]v1, Float[]v2)
	{
		return new Float[]{(v1[0]+v2[0]),(v1[1]+v2[1])};
	}
	
	static Float[] resVector(Float[]v1, Float[]v2)
	{
		return new Float[]{(v1[0]-v2[0]),(v1[1]-v2[1])};
	}
	
	static Float[] dotVector(Float[]v1, Float[]v2)
	{
		return new Float[]{(v1[1]+v2[0]),(v1[0]+v2[1])};
	}
	
	static Float[] mulVector(Float[]v1, Float v2)
	{
		return new Float[]{(v1[0]*v2),(v1[1]*v2)};
	}
	
	static Float[] divVector(Float[]v1, Float v2)
	{
		return new Float[]{(v1[0]/v2),(v1[1]/v2)};
	}
	
	static Float[] expVector(Float[]v1, Float v2)
	{
		return new Float[]{(float) (Math.pow(v1[0], v2)), (float) (Math.pow(v1[1], v2))};
	}
	
	static Float magVector(Float[]v1)
	{
		float f= (v1[0]*v1[0])+(v1[1]*v1[1]);
		return (float) Math.sqrt(f);
	}
	
	static Float[] normVector(Float[]v1)
	{
		if(v1[0]<v1[1])
		{
			for(int i= (int) (v1[0]/1);i>0;i--)
				if(v1[0]%i==0 && v1[1]%i==0)
					return new Float[]{(v1[0]/i),(v1[1]/i)};
		}
		else
		{
			for(int i= (int) (v1[1]/1);i>0;i--)
				if(v1[0]%i==0 && v1[1]%i==0)
					return new Float[]{(v1[0]/i),(v1[1]/i)};
		}
		
		return v1;
	}
}
