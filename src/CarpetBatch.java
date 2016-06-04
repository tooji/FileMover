import java.io.File;
import java.util.ArrayList;

public class CarpetBatch {

	private String aSKU;
	private ArrayList<File> ePictures = new ArrayList<File>();
	private ArrayList<File> fPictures = new ArrayList<File>();

	public CarpetBatch(String pSKU) {

		aSKU = pSKU;
	}

	public void setSKU(String pSKU) {

		aSKU = pSKU;

	}

	public void setEPictures(File[] En) {

		ePictures.clear();

		for (File f : En) {
			ePictures.add(f);

		}

	}

	public void setFPictures(File[] Fr) {

		fPictures.clear();

		for (File f : Fr) {
			fPictures.add(f);

		}

	}

	public void generateFPictures() {

		fPictures.clear();
		
		for(File p : ePictures){
			
			fPictures.add(p);
			
		}
		

	}

}
