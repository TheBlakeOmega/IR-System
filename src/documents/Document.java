package documents;

import java.io.File;


public class Document implements Comparable<Document>{

	private String document;
	private String name;
	private String path;

	public Document(File pdf, String text) {
		path = pdf.getPath();
		name = pdf.getName();
		document = text;
	}
	
	public Document(String query) {		//constructor to build an User's query as a Document
		path = null;
		name = "Query";
		document = query;
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

	@Override
	public int compareTo(Document doc) {
		return name.compareTo(doc.getName());
	}


}
