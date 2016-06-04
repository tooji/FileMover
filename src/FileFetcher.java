import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import org.apache.commons.io.*;
import org.apache.commons.io.filefilter.IOFileFilter;

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

					if (s.matches("[A-Z]{1,3}[0-9]+(-[1-4])?\\.JPG")) {   //change the filter

						return true;

					} else
						return false;

				}
			});

			return pPictureArray;

		} else {
			System.out.println("getPictures method exception: input not a folder");
			throw new IOException();
		}

	}
	
	 private void search(File file, String pQuery) {

			if (file.isDirectory()) {
			  System.out.println("Searching directory ... " + file.getAbsoluteFile());
				
		            //do you have permission to read this directory?	
			    if (file.canRead()) {
				for (File temp : file.listFiles()) {
				    if (temp.isDirectory()) {
					search(temp);
				    } else {
					if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {			
					    result.add(temp.getAbsoluteFile().toString());
				    }

				}
			    }

			 } else {
				System.out.println(file.getAbsoluteFile() + "Permission Denied");
			 }
		      }

		  }
}
