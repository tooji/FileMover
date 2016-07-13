import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;



public class StringFetcher{
	
	
	
	public String getSKU(File pABCFolder) throws IOException{   // attempts to get the sku from a folder of files
		
		String SKU = "";
		
		if (pABCFolder.isDirectory()) {

			File[] pPictureArray = pABCFolder.listFiles();
			ArrayList<String> SKUcompareList = new ArrayList<String>();
			
			for ( File pic : pPictureArray){
				
				
			//	SKU = FilenameUtils.removeExtension(pic.getName());
				
			//	SKU = SKU.replaceAll("(-[1-4])?","");
				
				if(!pic.getName().matches("DSC\\_[0-9]+\\.(jpg|JPG)") && !pic.getName().matches("Thumbs.db")){
					
				
				 
					
						SKU = FilenameUtils.removeExtension(pic.getName());
						SKU = SKU.replaceAll("(-[1-4])?","");
						
						SKUcompareList.add(SKU);
				
					
				
				}
				
				
				
			}
			
//			int counter = 0;
//			String temp = "";
//			for(String s: SKUcompareList){
//				
//				if (counter == 0){
//					temp = s;
//				}else{
//					
//					if(!temp.matches(s)){
//						System.out.println("A SKU WAS NOT FOUND!!");
//						throw new IOException();
//					}else{
//						counter++;
//					}
//					
//				}
//				
//				
//			}
			
			
			
			if (SKU.matches("")) {
				
				System.out.println("A SKU WAS NOT FOUND!!");
				throw new IOException();
			}

			return SKU;

		} else {
			System.out.println("getSKU method exception: input not a folder");
			throw new IOException();
		}
		
		
		
		
	}
	

}
