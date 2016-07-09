import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javaxt.io.Directory;

public class Zipper {
	

	
	public static void Zip(File inputFile, String pParentName, ZipOutputStream zipOutputStream){
		
		try{
			
			ZipEntry zipEntry = new ZipEntry(pParentName+inputFile.getName());
			zipOutputStream.putNextEntry(zipEntry);
			
			FileInputStream fileInputStream = new FileInputStream(inputFile);
			byte[] buf = new byte[1024];
			int bytesRead;
			
			 // Read the input file by chucks of 1024 bytes
			 // and write the read bytes to the zip stream

			while ((bytesRead = fileInputStream.read(buf)) > 0){
				zipOutputStream.write(buf, 0, bytesRead);
				
			}
			
			zipOutputStream.closeEntry();
			
			
			
			
			
			
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
	
		
		
	}
	

	
	public static void zipSimpleFolder(File inputFolder, String parentName, String zipFilePath){
		
		try{
			FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
			
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			
			String myname = parentName + inputFolder.getName()+"\\";
			
			ZipEntry folderZipEntry = new ZipEntry(myname);
			zipOutputStream.putNextEntry(folderZipEntry);
			
			File[] contents = inputFolder.listFiles();
			
			for(File f : contents){
				
				if (f.isFile()) Zip(f, myname, zipOutputStream);
			}
			
			zipOutputStream.closeEntry();
			zipOutputStream.close();
			
			
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
	}

}



