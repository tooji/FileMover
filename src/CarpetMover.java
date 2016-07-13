
import java.io.IOException;
import java.util.ArrayList;

import javaxt.io.Directory;

import java.io.*;





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
		
		
		File dir = new File(pCarpetBatch.getABC().getPath()+"//overwrittenFiles");
		if(dir.mkdir()){
		
		for (javaxt.io.File f: aCarpets){
			
			javaxt.io.File fileToBeOverwritten = new javaxt.io.File(pDestination.getPath()+f.getName(true));
			
			if(fileToBeOverwritten.exists()){
				
				
					Directory oDestination = new Directory(dir.getPath());
					fileToBeOverwritten.copyTo(oDestination, true);
					
				
				
				
			}
			
			f.copyTo(pDestination, true);
			
		}
			
		
		}
		
		
	}

}
