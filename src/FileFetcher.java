import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import org.apache.commons.io.*;
import org.apache.commons.io.filefilter.IOFileFilter;
import javaxt.io.Directory;

public class FileFetcher {

	public File[] getABCDFolders(File pDirectory) throws IOException {

		if (pDirectory.isDirectory()) {

			File[] pFolders = pDirectory.listFiles(new FilenameFilter() {
				public boolean accept(File f, String s) {

					if (s.matches("[a-z]|[A-Z]") && f.isDirectory()) {

						return true;

					} else
						return false;

				}
			});

			return pFolders;

		} else {
			System.out.println("ABCFolders method exception: input not a folder");
			throw new IOException();
		}

	}

	public File[] getPictures(File pABC) throws IOException {

		if (pABC.isDirectory()) {

			File[] pPictureArray = pABC.listFiles(new FilenameFilter() {
				public boolean accept(File f, String s) {

					if (!s.matches("DSC\\_[0-9]+.*\\.(jpg|JPG)") && !s.matches("Thumbs.db") && !s.matches(".*\\s-\\sCopy\\.(jpg|JPG)")) {
						
						if(!s.matches("Fr")) {
							return true;
						}else return false;
						
					}	//else if (s.matches("([A-Z]|[a-z]){0,3}[0-9]+(-[1-4])?a?\\.(JPG|jpg)")) {   //change the filter
//
//						return true;
//
//					}
					
					else
						return false;

				}
			});

			return pPictureArray;

		} else {
			System.out.println("getPictures method exception: input not a folder");
			throw new IOException();
		}

	}
	
	public javaxt.io.File getZipFile(Directory pDir) throws IOException{
		
//		FilenameFilter zipFilter = new FilenameFilter() {
//			public boolean accept(File f, String s) {
//
//				if (s.endsWith(".zip")) {  
//
//					return true;
//
//				} else
//					return false;
//
//			}
//		};
		
		javaxt.io.File[] myArray = pDir.getFiles("*.zip", true);
		
		if(myArray.length == 1) return myArray[0];
		
		else if(myArray.length == 0){
			System.out.println("no Zip file found, must generate new one "+pDir.getPath());
			return null;
			
		}else if (myArray.length > 1){
			
			System.out.println("More than one Zip file found in "+pDir.getPath());
			throw new IOException();
		}else{
			
			System.out.println("This should never execute");
			throw new IOException();
		}
		
		
		
	}
	
	
}
