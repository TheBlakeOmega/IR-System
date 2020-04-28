import java.io.IOException;

import documents.Collection;
import utils.Keyboard;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Insert folder's path: ");
		try {
		Collection coll = new Collection(Keyboard.readString());
		System.out.println("Collection:\n" + coll);
		System.out.println("Collection's textes:\n" + coll.toString("documents"));
		} catch(IOException e) {
			System.out.println("Qualcosa storto");
			e.printStackTrace();
		}
	}

}
