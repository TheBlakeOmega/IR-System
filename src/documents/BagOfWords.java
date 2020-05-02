package documents;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BagOfWords {

	private Document doc;
	private Map<String, Integer> bag;

	public BagOfWords(Document doc) {
		this.doc = doc;

		bag = new HashMap<String, Integer>();
		String text = doc.getDocument().toLowerCase();
		String[] words = text.split("\\s+");
		for (int i=0; i < words.length; i++) {
			words[i] = words[i].replaceAll("[^a-z ]", "");
		}
		int repetitions;
		for (int i=0; i<words.length; i++) {
			if (isWord(words[i])) {
				if (!words[i].equals("/")) {
					repetitions = 1;
					for (int j=i+1 ; j<words.length; j++) {
						if (words[j].equals(words[i])) {
							repetitions++;
							words[j] = "/";
						}
					}
					bag.put(words[i], repetitions);
					words[i] = "/";
				}
			}
		}
	}

	public Map<String, Integer> getBag(){
		return bag;
	}

	public String getName() {
		return doc.getName();
	}

	public Document getDocument() {
		return doc;
	}

	public Set<String> getWords() {
		return bag.keySet();
	}
	
	public int getOccurrence(String word) {
		return bag.get(word);
	}

	private static boolean isWord(String word) {
		return word.length() > 1;
	}

	public String toString() {
		String out = "{";
		for (String word : bag.keySet()) {
			out = out + word + " : " + bag.get(word) + " || ";
		}
		out = out + "}";
		return out;
	}

}







