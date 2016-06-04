import java.io.IOException;

import javaxt.io.File;


public class VerifierTool {
	
	public static boolean checkSameParent(javaxt.io.File[] fArray) throws IOException{
		
		String parent = "";
		
		if(fArray == null){
			System.out.println("Null array exception");
			throw new IOException();
			
		}
		
		for(File f: fArray){
			
			if(parent.equals("")){
				parent = f.getParentDirectory().getPath();
			} else if (!(f.getParentDirectory().getPath().equals(parent))){
				return false;
			}
			
		}
		
		if(parent.equals("")) {
			
			System.out.println("Empty String exception");
			throw new IOException();
			
			
		}
		
		return true;
		
		
	}
	
	public static String getSameParent(javaxt.io.File[] fArray) throws IOException{
		
		
		if (fArray[0] == null){
			System.out.println("array is null");
			throw new IOException();
		}
		
		if (checkSameParent(fArray)) return fArray[0].getParentDirectory().getPath();
		
		else{
			System.out.println("\n check same parent failed, cannot extract parent\n");
			throw new IOException();
		}
		
		
	}

	
}
