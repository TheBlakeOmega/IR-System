import java.io.IOException;

import computing.TermDocumentMatrix;
import documents.Collection;
import utils.Keyboard;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Insert folder's path: ");
		try {
		Collection coll = new Collection(Keyboard.readString());
		System.out.println("Collection:\n" + coll);
		System.out.println("Collection's bags:\n\n" + coll.toString(2));
		System.out.println("Collection's documents:\n\n" + coll.toString(1));
		TermDocumentMatrix matrix = new TermDocumentMatrix(coll);
		System.out.println("Inverted Index: \n\n" + matrix.getIndex());
		System.out.println("TF-IDF Matrix: \n\n" + matrix);
		} catch(IOException e) {
			System.out.println("Qualcosa storto");
			e.printStackTrace();
		}
	}

}
