import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;



public class StringFetcher{
	
	
	public String getZipFile(Directory pIndianicSub){
		
		
		
		
	}
	
	public String getSKU(File pABCFolder) throws IOException{   // attempts to get the sku from a folder of files
		
		String SKU = "";
		
		if (pABCFolder.isDirectory()) {

			File[] pPictureArray = pABCFolder.listFiles();
			
			for ( File pic : pPictureArray){
				
				if(pic.getName().matches("[A-Z]{1,3}[0-9]+\\.JPG")){
					
					SKU = FilenameUtils.removeExtension(pic.getName());
					
				} else {
					
					System.out.println("A SKU WAS NOT FOUND!!");
					throw new IOException();
				}
				
				
				
			}

			return SKU;

		} else {
			System.out.println("getSKU method exception: input not a folder");
			throw new IOException();
		}
		
		
		
		
	}
	

}
