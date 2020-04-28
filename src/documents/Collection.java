package documents;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import utils.DocumentExtractor;
import utils.PDFFilter;

public class Collection implements Iterable<Document>{

	private List<Document> collection;
	private File folder;


	public Collection(String path) throws IOException {
		folder = new File(path);
		collection = new ArrayList<Document>();

		DocumentExtractor extractor = new DocumentExtractor();
		int i = 1;
		for (File pdf : folder.listFiles(new PDFFilter())) {
			System.out.println("Carico documento:" + i);
			collection.add(extractor.getDocument(pdf));
			i++;
		}
	}


	@Override
	public Iterator<Document> iterator() {
		return collection.iterator();
	}


	public String toString() {
		String coll = "";
		int i = 1;
		for (Document doc : collection) {
			coll = coll + i + ") " + doc + "\n";
			i++;
		}
		return coll;
	}

	public String toString(String type) {
		String coll = "";
		int i = 1;
		if(type.equals("documents")) {
			for (Document doc : collection) {
				coll = coll + i + ") " + doc.getDocument() + "\n";
				i++;
			}
		}
		return coll;
	}


}
