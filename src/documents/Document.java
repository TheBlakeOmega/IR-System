package documents;

import java.io.File;


public class Document {

	private String document;
	private String name;
	private String path;

	public Document(File pdf, String text) {
		path = pdf.getPath();
		name = pdf.getName();
		document = text;
	}

	public String getDocument() {
		return document;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object doc) {
		if (doc instanceof Document)
			return ((Document) doc).getName().equals(name);
		else
			return false;
	}

}
