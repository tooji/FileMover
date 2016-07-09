import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import javaxt.io.Directory;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import net.lingala.zip4j.core.ZipFile;

public class Driver {
	
	public static void main(String args[]) throws IOException{
		
		
		
		File[] ABCDFolders;
		File aDirectory = new File(args[0]);
		Directory aINDIANIC =  new Directory(args[1]);
		//Directory aINDIANIC =  new Directory("C:\\Users\\Samina\\Desktop\\TestIndianic");
		FileFetcher aFF = new FileFetcher();
		StringFetcher aSF = new StringFetcher();
		ZipParameters parameters = new ZipParameters();
		
		parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
		parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
		

		
		ArrayList<CarpetBatch> carpetList = new ArrayList<CarpetBatch>();
		
		
		
		
		if (aDirectory.isDirectory() ){
			
			ABCDFolders = aFF.getABCDFolders(aDirectory);
			
			for (File ABC : ABCDFolders){
				
				String SKU = aSF.getSKU(ABC);
			
				
			
				
				
				System.out.println("Working on carpet SKU:   " + SKU);
				
				CarpetBatch aCarpetBatch = new CarpetBatch(SKU);
				
				aCarpetBatch.setEPictures(aFF.getPictures(ABC));
				aCarpetBatch.generateFPictures(ABC);
				
				

				
				String[] eArray = {SKU+".jpg", SKU+"-1.jpg", SKU+"-2.jpg", SKU+"-3.jpg", SKU+"-4.jpg"};
				
				//slow placeholder version
				javaxt.io.File[] engIndianicPics = aINDIANIC.getFiles(eArray, true);
				
				if (engIndianicPics == null){
					
					System.out.println("engIndianicPics array is null for sku: " + SKU);
					
				}
				
				if (engIndianicPics.length == 0){
					
					System.out.println("eng indianic pic array is empty for sku: "+ SKU);
					System.out.println("Carpet could not be located on Indianic, continuing to next carpet");
					continue;
					
				}
				
				if(!VerifierTool.checkSameParent(engIndianicPics)){
					
					System.out.println("files found do not share same parent");
					throw new IOException();
					
					
				}
				
				
				
				Directory engSubDir = new Directory(VerifierTool.getSameParent(engIndianicPics));
				Directory folderSubDir = engSubDir.getParentDirectory();
				
				String[] fArray = {SKU+"f.jpg", SKU+"f-1.jpg", SKU+"f-2.jpg", SKU+"f-3.jpg", SKU+"f-4.jpg"};
				javaxt.io.File[] frIndianicPics = folderSubDir.getFiles(fArray, true);
				
				
				Directory frSubDir = new Directory(VerifierTool.getSameParent(frIndianicPics));
				
				CarpetMover.moveAndOverwrite(aCarpetBatch, engSubDir, "En");
				CarpetMover.moveAndOverwrite(aCarpetBatch, frSubDir, "Fr");
				
				javaxt.io.File ZipEng = aFF.getZipFile(engSubDir);
				javaxt.io.File ZipFr = aFF.getZipFile(frSubDir);
				
				String ZStrEng = ZipEng.getPath()+ZipEng.getName(true);
				
				String ZStrFr = ZipFr.getPath()+ZipFr.getName(true);
				
				
				ZipEng.delete();
				ZipFr.delete();
				
				//Zipper.zipSimpleFolder(new File(ZipEng.getPath()),"", ZStrEng);
				
				//Zipper.zipSimpleFolder(new File(ZipFr.getPath()),"", ZStrFr);
				
				String folderToZipE = ZipEng.getPath();
				String folderToZipF = ZipFr.getPath();
				
				try{
					ZipFile zipFileEng = new ZipFile(ZStrEng);
					ZipFile zipFileFr = new ZipFile(ZStrFr);
					
					zipFileEng.addFolder(folderToZipE, parameters);
					zipFileFr.addFolder(folderToZipF, parameters);
					
				}catch(ZipException e){
					e.printStackTrace();
				}
				
				
				
				
				
			}
			//do stuff
			
			
			
			
			
		} else {
			System.out.println("Arguments must be DIRECTORIES");
			throw new IOException();
		}
		
		
		
		
		
	
		
	}

}
