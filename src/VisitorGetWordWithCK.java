import java.util.*;
public class VisitorGetWordWithCK extends Visitor{
	@Override
	List<String> visitTrieTree(TrieTree tree) {
		List<String> allWords = new ArrayList<String>();
		allWords = tree.containsSubstring("ck");
		
		return allWords;
	}
	
}
