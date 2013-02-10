import java.util.*;

public class VisitorGetWords extends Visitor {

	@Override
	List<String> visitTrieTree(TrieTree tree) {
		List<String> allWords = new ArrayList<String>();
		allWords = tree.getWordsFromTree();

		return allWords;
	}

}
