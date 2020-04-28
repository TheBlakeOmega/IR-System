package utils;

import java.io.File;
import java.io.FileFilter;

public class PDFFilter implements FileFilter{

	@Override
	public boolean accept(File file) {
		return file.getName().contains(".pdf");
	}

}
