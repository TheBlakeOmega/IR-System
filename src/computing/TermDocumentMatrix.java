package computing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import documents.Collection;
import documents.Document;

public class TermDocumentMatrix {

	private InvertedIndex index;
	private List<DocumentWeights> matrix;
	private Set<Document> coll;
	private static int MAXDOC = 10;
	private DocumentWeights query = null;
	
	public TermDocumentMatrix(Collection coll) {
		index = new InvertedIndex(coll);
		this.coll = coll.getDocuments();
		matrix = new ArrayList<DocumentWeights>();
		
		for (Document doc : coll) {
			matrix.add(new DocumentWeights(doc, index));
		}	
	}
	
	public InvertedIndex getIndex() {
		return index;
	}
	
	public void buildQuery(String text) {
		query = DocumentWeights.buildQuery(text, index);
	}
	
	public Map<Double, Document> computeSimilarity() {
		Map<Double, Document> similarities = new TreeMap<Double,Document>(Collections.reverseOrder());
		for (DocumentWeights tuple : matrix) {
			similarities.put(tuple.cosineSimilarity(query), tuple.getDocument());
		}
		
		Map<Double, Document> out = new TreeMap<Double,Document>(Collections.reverseOrder());
		int i = 0;
		for (Double key : similarities.keySet()) {
			if(i < MAXDOC) {
				out.put(key, similarities.get(key));
				i++;
			} else {
				break;
			}
		}
		return out;
	}
	
	public String toString() {
		String out= index.getTerms().toString() + "\n";
		for (DocumentWeights tuple : matrix) {
			out = out + tuple + "\n";
		}
		return out;
	}
	
}
