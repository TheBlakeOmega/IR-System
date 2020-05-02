package computing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import documents.Collection;
import documents.Document;

public class TermDocumentMatrix {

	private InvertedIndex index;
	private List<DocumentWeights> matrix;
	private Set<Document> coll;
	
	
	public TermDocumentMatrix(Collection coll) {
		index = new InvertedIndex(coll);
		this.coll = coll.getDocuments();
		matrix = new ArrayList<DocumentWeights>();
		
		for (Document doc : coll) {
			matrix.add(new DocumentWeights(doc, index));
		}	
	}
	
	public Double getCosineSimilarity() {
		return 1.0;
	}
	
	public InvertedIndex getIndex() {
		return index;
	}
	
	public String toString() {
		String out= index.getTerms().toString() + "\n";
		for (DocumentWeights tuple : matrix) {
			out = out + tuple + "\n";
		}
		return out;
	}
	
}
