
import java.io.IOException;
import java.util.ArrayList;

import  javaxt.io.File;



public class CarpetMover {
	
	public static void moveAndOverwrite(CarpetBatch pCarpetBatch, javaxt.io.Directory pDestination, String lang) throws IOException{
		
		ArrayList<javaxt.io.File> aCarpets;
		
		if(lang.matches("Fr")){
			
			aCarpets = pCarpetBatch.getFrPictures();
			
		
			
		}else if (lang.matches("En")){
			
			aCarpets = pCarpetBatch.getEnPictures();
			
			
		}else{
			System.out.println("Language Param invalid");
			throw new IOException();
			
		}
		
		
		for (javaxt.io.File f: aCarpets){
			
			f.copyTo(pDestination, true);
			
		}
			
		
		
		
		
	}

}
