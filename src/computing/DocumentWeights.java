package computing;

import java.util.LinkedList;
import java.util.List;

import documents.Document;

class DocumentWeights {
	
	private List<Double> tuple;
	private Document doc;
	private InvertedIndex index;
	int size;
	
	DocumentWeights (Document doc, InvertedIndex index) {
		this.doc = doc;
		this.index = index;
		size = 0;
		tuple = new LinkedList<Double>();
		for (String term : index) {
			tuple.add(index.TF_IDF(term, doc));
			size++;
		}
	}
	
	DocumentWeights (String text, InvertedIndex index) {
		Document query = new Document(text);
		this.doc = query;
		this.index = index;
		tuple = new LinkedList<Double>();
		for (String term : index) {
			tuple.add(index.TF_IDF(term, doc));
		}
	}
	
	List<Double> getWeights() {
		return tuple;
	}
	
	public String toString() {
		String out = doc.getName();
		for (Double weight : tuple) {
			out = out + "  ";
			out = out + weight;
		}
		return out;		
	}
}
