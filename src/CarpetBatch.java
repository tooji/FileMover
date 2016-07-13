import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.*;

import javaxt.io.Directory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CarpetBatch {

	private String aSKU;
	private javaxt.io.Directory aABC;
	private ArrayList<File> ePictures = new ArrayList<File>();
	private ArrayList<File> fPictures = new ArrayList<File>();

	public CarpetBatch(String pSKU, String pABCPath) {
		
		aABC = new javaxt.io.Directory(pABCPath);
		aSKU = pSKU;
	}
	
	public javaxt.io.Directory getABC(){
		return aABC;
	}

	public ArrayList<javaxt.io.File> getEnPictures() {

		ArrayList<javaxt.io.File> ePicClone = new ArrayList<javaxt.io.File>();

		for (File f : ePictures) {

			javaxt.io.File xtFile = new javaxt.io.File(f.getAbsolutePath());
			ePicClone.add(xtFile);

		}

		return ePicClone;
	}

	public ArrayList<javaxt.io.File> getFrPictures() {

		ArrayList<javaxt.io.File> fPicClone = new ArrayList<javaxt.io.File>();

		for (File f : fPictures) {

			javaxt.io.File xtFile = new javaxt.io.File(f.getAbsolutePath());
			fPicClone.add(xtFile);

		}

		return fPicClone;
	}

	public void setSKU(String pSKU) {

		aSKU = pSKU;

	}
	
	public void setEPictures(File[] En) {
		assert(En != null);
		ePictures.clear();
		for(File f:En){
			ePictures.add(f);
		}
		
	}

//	public void setEPictures(File[] En) {
//
//		assert(En != null);
//		
//		ePictures.clear();
//
//		int counter = 0;
//		String temp;
//		
//		
//		temp = FilenameUtils.getBaseName(En[0].getName());
//		temp = temp.replaceFirst(".*-","");
//		ePictures.add(En[0]);
//		
//		for (File f : En) {
//			if(counter == 0){
//				continue;
//			}else{
//				String temp1 = FilenameUtils.getBaseName(f.getName());
//				String temp2 = temp1.replaceFirst(".*-", "");
//				if(temp2.matches(temp)){
//					ePictures.add(f);
//				}
//				
//			}
//			
//			counter++;
//		}
//
//	}

	public void setFPictures(File[] Fr) {

		fPictures.clear();

		for (File f : Fr) {
			fPictures.add(f);

		}

	}

	public void generateFPictures(File pABC) {
		
		String newName = null;
		File frenchFile = null;
		
		
		
		if(ePictures.isEmpty()){
			 try {
				throw new IOException("English pictures must be set before french pictures can be generated");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

		fPictures.clear();
		
		
		Path frenchDir = Paths.get(pABC.getPath()+"\\Fr");
		
		try {
			frenchDir = Files.createDirectory(frenchDir);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
		}
		
		for (File p : ePictures) {
			Path source = Paths.get(p.getPath());
			
			int n = 0;
			try {
				String temp = FilenameUtils.getBaseName(p.getName());
			    n = Integer.parseInt (temp.replaceFirst(".*-",""));
			    
			} catch (Exception e) {}
			
			
			if (n == 0){
				
				String temp = FilenameUtils.getBaseName(p.getName());
				newName = temp+"F.jpg";
			}else if (n > 0){
				
				String temp = FilenameUtils.getBaseName(p.getName()); 
				String bSKU = temp.replaceFirst("-[0-9]", "");
				newName = bSKU+"F-"+n+".jpg";
			}else{
				
				System.out.println("Something went wrong while generating a french picture");
				
			}
			
			
			try {
				//Files.move(source, frenchDir.resolveSibling(newName),REPLACE_EXISTING);
				if(!newName.isEmpty()){
					Path target = Paths.get(frenchDir.toString()+"//"+source.getFileName());
					Path frPath = Files.copy(source, target.resolveSibling(newName), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
					frenchFile = new File(frPath.toString());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			fPictures.add(frenchFile);

		}

	}



}
