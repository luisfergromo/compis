package Generators;
import java.io.File;

public class GenerateLexer {

	public static final String path ="practica3/lexic3.flex";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		generarLexer(path);
	}
	
	public static void generarLexer(String path){
		File file = new File(path);
		jflex.Main.generate(file);
	}
	

}
