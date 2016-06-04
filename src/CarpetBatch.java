import java.io.File;
import java.util.ArrayList;

public class CarpetBatch {

	private String aSKU;
	private ArrayList<File> ePictures = new ArrayList<File>();
	private ArrayList<File> fPictures = new ArrayList<File>();

	public CarpetBatch(String pSKU) {

		aSKU = pSKU;
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

		for (File p : ePictures) {

			fPictures.add(p);

		}

	}

}
