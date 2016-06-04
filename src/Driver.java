import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Driver {
	
	public static void main(String args[]) throws IOException{
		
		File[] ABCDFolders;
		File aDirectory = new File(args[0]);
		File aINDIANIC =  new File(args[1]);
		FileFetcher aFF = new FileFetcher();
		StringFetcher aSF = new StringFetcher();
		
		ArrayList<CarpetBatch> carpetList = new ArrayList<CarpetBatch>();
		
		
		
		
		if (aDirectory.isDirectory() && aINDIANIC.isDirectory()){
			
			ABCDFolders = aFF.getABCDFolders(aDirectory);
			
			for (File ABC : ABCDFolders){
				
				String SKU = aSF.getSKU(ABC);
				
				CarpetBatch aCarpetBatch = new CarpetBatch(SKU);
				
				aCarpetBatch.setEPictures(aFF.getPictures(ABC));
				aCarpetBatch.generateFPictures();
				
				
				
			}
			//do stuff
			
			
			
			
			
		} else {
			System.out.println("Arguments must be DIRECTORIES");
			throw new IOException();
		}
		
		
		
		
		
	
		
	}

}
