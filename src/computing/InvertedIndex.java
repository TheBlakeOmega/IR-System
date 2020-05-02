package computing;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import documents.BagOfWords;
import documents.Collection;
import documents.Document;

public class InvertedIndex implements Iterable<String>{
	
	Map<String,PostingList> index;
	int terms;
	
	public InvertedIndex (Collection coll) {
		index = new HashMap<String,PostingList>();
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
	
	public int getTermOccurrence(String term, Document doc) {
		return index.get(term).getOccurrence(doc);
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
		}
	}
	
	@Override
	public Iterator<String> iterator() {
		return getTerms().iterator();
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