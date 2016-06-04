import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javaxt.io.Directory;

public class Driver {
	
	public static void main(String args[]) throws IOException{
		
		String engREGEX = "([A-Z]|[a-z]){1,3}[0-9]+(-[1-4])?\\.(JPG|jpg)";
		String frREGEX = "([A-Z]|[a-z]){1,3}[0-9]+(F|f)(-[1-4])?\\.(JPG|jpg)";
		
		File[] ABCDFolders;
		File aDirectory = new File(args[0]);
		Directory aINDIANIC =  new Directory(args[1]);
		FileFetcher aFF = new FileFetcher();
		StringFetcher aSF = new StringFetcher();
		

		
		ArrayList<CarpetBatch> carpetList = new ArrayList<CarpetBatch>();
		
		
		
		
		if (aDirectory.isDirectory() ){
			
			ABCDFolders = aFF.getABCDFolders(aDirectory);
			
			for (File ABC : ABCDFolders){
				
				String SKU = aSF.getSKU(ABC);
				
				CarpetBatch aCarpetBatch = new CarpetBatch(SKU);
				
				aCarpetBatch.setEPictures(aFF.getPictures(ABC));
				aCarpetBatch.generateFPictures();
				
				FilenameFilter engFilter = new FilenameFilter() {
					public boolean accept(File f, String s) {

						if (s.matches(SKU+"(-[1-4])?\\.(JPG|jpg)")) {   //change the filter

							return true;

						} else
							return false;

					}
				};
				
				FilenameFilter frFilter = new FilenameFilter() {
					public boolean accept(File f, String s) {

						if (s.matches(SKU+"(f|F)(-[1-4])?\\.(JPG|jpg)")) {   //change the filter

							return true;

						} else
							return false;

					}
				};
				
				javaxt.io.File[] engIndianicPics = aINDIANIC.getFiles(engFilter, true);
				
				if(!VerifierTool.checkSameParent(engIndianicPics)){
					
					System.out.println("files found do not share same parent");
					throw new IOException();
					
					
				}
				
				Directory engSubDir = new Directory(VerifierTool.getSameParent(engIndianicPics));
				Directory folderSubDir = engSubDir.getParentDirectory();
				
				
				javaxt.io.File[] frIndianicPics = folderSubDir.getFiles(frFilter, true);
				
				Directory frSubDir = new Directory(VerifierTool.getSameParent(frIndianicPics));
				
				CarpetMover.moveAndOverwrite(aCarpetBatch, engSubDir, "En");
				CarpetMover.moveAndOverwrite(aCarpetBatch, frSubDir, "Fr");
				
				
				
				
			}
			//do stuff
			
			
			
			
			
		} else {
			System.out.println("Arguments must be DIRECTORIES");
			throw new IOException();
		}
		
		
		
		
		
	
		
	}

}
