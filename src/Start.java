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

		VisitorStrategy vs = new VisitorStrategyImplementation();
		Strategy s = new StrategyContainsTwoVowels();
		vs.setStrategy(s);
		List<String> words = vs.visitTree(t);
		
		printStringList(words);
	}

	private static void printStringList(List<String> words) {
		for(int i = 0; i<words.size(); i++)
		{
			System.out.println(words.get(i));
			
		}
	}
}
