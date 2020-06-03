package computing;

import java.util.LinkedList;
import java.util.List;

import documents.BagOfWords;
import documents.Document;

class DocumentWeights {
	
	private List<Double> tuple;
	private Document doc;
	private InvertedIndex index;
	private Double norm = 0.0;
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
		for (Double val : tuple) {
			if (val != 0.0) {
				norm = norm + Math.pow(val, 2);
			}
		}
		norm = Math.sqrt(norm);
		System.out.println("norm: " +norm);
	}
	
	private DocumentWeights (String text, InvertedIndex index) {
		Document query = new Document(text);
		BagOfWords queryBag = new BagOfWords(query);
		this.doc = query;
		this.index = index;
		tuple = new LinkedList<Double>();
		for (String term : index) {
			tuple.add(index.query_TF_IDF(term, queryBag));
		}
		for (Double val : tuple) {
			if (val != 0.0) {
				norm = norm + Math.pow(val, 2);
			}
		}
		norm = Math.sqrt(norm);
		System.out.println("norm: " +norm);
	}
	
	List<Double> getWeights() {
		return tuple;
	}
	
	Document getDocument() {
		return doc;
	}
	
	int getSize() {
		return size;
	}
	
	Double getNorm() {
		return norm;
	}
	
	static DocumentWeights buildQuery(String text, InvertedIndex index) {
		return new DocumentWeights(text, index);
	}
	
	Double cosineSimilarity(DocumentWeights query) {
		Double score = 0.0;
		int i = 0;
		for (Double val : tuple) {
			score = score + (val * query.getWeights().get(i));
			i++;
		}
		if (!score.equals(0.0))
			score = score / (norm * query.getNorm());
		return score;
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
