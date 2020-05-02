import java.io.IOException;

import computing.InvertedIndex;
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
		InvertedIndex index = new InvertedIndex(coll);
		System.out.println("Inverted Index:\n" + index);
		} catch(IOException e) {
			System.out.println("Qualcosa storto");
			e.printStackTrace();
		}
	}

}
