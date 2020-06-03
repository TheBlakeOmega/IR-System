package computing;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.lang.Math;

import documents.BagOfWords;
import documents.Collection;
import documents.Document;

public class InvertedIndex implements Iterable<String>{

	Map<String,PostingList> index;
	List<String> stopWords;
	int terms;
	int collectionSize;

	public InvertedIndex (Collection coll) {
		collectionSize = coll.getSize();
		index = new TreeMap<String,PostingList>();
		List<BagOfWords> bags = coll.getBags();
		for (BagOfWords bag : bags) {
			for (String term : bag.getWords()) {
				if (index.containsKey(term)) {
					if (index.get(term).contains(bag.getDocument())) {
						index.get(term).increment(bag.getDocument());
					} else {
						index.get(term).add(bag.getDocument());
					}
				} else {
					PostingList posting = new PostingList();
					posting.add(bag.getDocument());
					index.put(term, posting);
					terms++;
				}
			}
		}
		checkStopWords(coll);
	}

	public int getTermsSize() {
		return terms;
	}

	public Set<String> getTerms() {
		return index.keySet();
	}

	public PostingList getPostingList(String term) {
		return index.get(term);
	}

	public boolean isConteined(String term, Document doc) {
		return index.get(term).contains(doc);
	}

	private void checkStopWords(Collection coll) {
		LinkedList<String> toRemove = new LinkedList<String>();
		for (String term : getTerms()) {
			if (index.get(term).getSize() == coll.getSize()) {
				toRemove.add(term);
			}
		}
		for (String term : toRemove) {
			index.remove(term);
			stopWords = new LinkedList<String>();
			stopWords.add(term);
		}
	}

	public List<String> getStopWords() {
		return stopWords;
	}
	
	@Override
	public Iterator<String> iterator() {
		return getTerms().iterator();
	}
	
	public double TF_IDF(String term, Document doc) {		//I choose to divide the computation of TF-IDF weight, with another two functions, to show how to compute this weight
		return sublinearTFScaling(term,doc) * inverseDocumentFrequency(term);
	}
	
	public double query_TF_IDF(String term, BagOfWords doc) {
		if (doc.getOccurrence(term) != 0) {
			double sublinearTFScaling = 1 + Math.log10(doc.getOccurrence(term));
			return sublinearTFScaling * inverseDocumentFrequency(term);
		}
		return 0;
	}
	
	private double inverseDocumentFrequency(String term) {		// idf = log10(collection's_size / document_frequency(term))
		return Math.log10(collectionSize / getPostingList(term).getSize());
	}

	private double sublinearTFScaling(String term, Document doc) {		//sublinear scaling for TF = 1 + log10(term_frequency(term,document))
		if (isConteined(term,doc)) {
			return 1 + Math.log10(index.get(term).getOccurrence(doc));
		}
		return 0;
	}

	public String toString() {
		String out = "";
		int i = 1;
		for (String term : getTerms()) {
			out = out + i + ") " + term + " => " + index.get(term) + "\n";
			i++;
		}
		return out;
	}
	
}


