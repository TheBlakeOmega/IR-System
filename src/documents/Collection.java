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
	private List<BagOfWords> bagCollection;
	private int size;


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
		
		size = i - 1;
		
		bagCollection = new ArrayList<BagOfWords>();
		for (Document doc : collection) {
			bagCollection.add(new BagOfWords(doc));
		}
	}
	
	public List<BagOfWords> getBags() {
		return bagCollection;
	}
	
	public List<Document> getDocuments() {
		return collection;
	}
	
	public int getSize() {
		return size;
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

	public String toString(int type) {
		String out = "";
		int i = 1;
		switch(type) {
			case 1:
				for (Document doc : collection) {
					out = out + i + ") " + doc.getDocument() + "\n";
					i++;
				}
				break;
			case 2:
				for (BagOfWords bag : bagCollection) {
					out = out + i + ") " + bag + "\n";
					i++;
				}
				break;
		}
		return out;
	}


}
