package computing;

import java.util.LinkedList;
import java.util.List;

import documents.Document;

class PostingList {		//package class

	private List<Pair> posting;
	private int size;

	PostingList() {
		posting = new LinkedList<Pair>();
		size=0;
	}

	void add(Document doc) {
		Pair pair = new Pair(doc);
		posting.add(pair);
		size++;
	}

	Pair getPair(Document doc) {
		for (Pair temp : posting) {
			if (temp.getDoc().equals(doc)) {
				return temp;
			}
		}
		return null;
	}

	void increment(Document doc) {
		for (Pair temp : posting) {
			if (temp.getDoc().equals(doc)) {
				temp.increment();
			}
		}
	}

	Integer getOccurrence(Document doc) {
		for (Pair temp : posting) {
			if (temp.getDoc().equals(doc)) {
				return temp.getOccurrence();
			}
		}
		return null;
	}

	int getSize() {
		return size;
	}

	boolean contains(Document doc) {
		for (Pair temp : posting) {
			if (temp.getDoc().equals(doc)) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		String out = "";
		for (Pair pair : posting) {
			out = out + " [" + pair + "]";
		}
		out = out + "  Size = " + size;
		return out;
	}

	// inner class
	private class Pair {	
		private Document doc;
		private int occurrence;

		Pair(Document doc) {
			this.doc = doc;
			this.occurrence = 1;
		}
		Document getDoc() {
			return doc;
		}
		int getOccurrence() {
			return occurrence;
		}
		void increment() {
			occurrence++;
		}
		public String toString() {
			String out = doc + " : " + occurrence;
			return out;
		}
	}
}
