
import java.util.*;

public class Start {
	
	public static void main(String[] args) {
		TrieTree t = new TrieTree();
		t.add("are");
		t.add("arena");
		t.add("amber");
		t.add("test");
		t.add("bARato");
		t.add("check");
		t.add("checkmate");

		t.containsSubstring("ar");



		List<String> wordsWithCK = new ArrayList<String>();
		
		
		while (t.hasNext()) {
			String currentWord = t.next();
			if (currentWord.contains("ck"))
			{
				wordsWithCK.add(currentWord);
			}
		}
		
		
		for(int i =0; i<wordsWithCK.size(); i++)
		{
			System.out.println(wordsWithCK.get(i));
			
		}
		
		
		
		//System.out.println(t.toString());

	}
}
