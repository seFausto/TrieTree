import java.util.*;

public class Start {

	public static void main(String[] args) {
		TrieTree t = new TrieTree();
		t.add("are");
		t.add("arena");
		t.add("amber");
		t.add("test");
		t.add("bARato");
		t.add("checks");
		t.add("checkmate");

		t.containsSubstring("ar");

		Visitor v = new VisitorGetWordWithCK();

		List<String> words = new ArrayList<String>();

		// words = t.containsSubstring("ck");
		words = t.accept(v);

		for (int i = 0; i < words.size(); i++) {
			System.out.println(words.get(i));
		}

		// List<String> wordsWithCK = new ArrayList<String>();
		//
		//
		// while (t.hasNext()) {
		// String currentWord = t.next();
		// if (currentWord.contains("ck"))
		// {
		// wordsWithCK.add(currentWord);
		// }
		// }
		//
		//
		// for(int i =0; i<wordsWithCK.size(); i++)
		// {
		// System.out.println(wordsWithCK.get(i));
		//
		// }
		//
		// System.out.println(t.toString());

	}
}
