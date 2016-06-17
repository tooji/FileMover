import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javaxt.io.Directory;

public class Zipper {
	
	static final int BUFFER = 128000;
	
	public static void Zip(Directory pDir, String pName){
		
	
		try {
			FileOutputStream dest = new FileOutputStream(pDir.getPath()+"pName");
			byte data[] = new byte[BUFFER];
			File f = new File(".");
			
			String files[] = f.list();
			
			for(int i =0; i<files.length; i++){
				
				FileInputStream fi = new FileInputStream(files[i]);
				
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
