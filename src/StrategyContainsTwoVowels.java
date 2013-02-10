import java.util.*;

public class StrategyContainsTwoVowels extends Strategy {

	private final String LETTERSTOCHECK = "aeiou";
	private final int NUMBEROFLETTESTOFIND = 2;

	@Override
	List<String> algorithm(TrieTree tree) {
		List<String> result = new ArrayList<String>();

		while (tree.hasNext()) {
			String currentWord = tree.next();
			int countOfMatches = 0;
			for (int j = 0; j < LETTERSTOCHECK.length(); j++) {
				if (currentWord.indexOf(LETTERSTOCHECK.charAt(j)) >= 0) {
					countOfMatches++;
				}
			}

			if (countOfMatches >= NUMBEROFLETTESTOFIND) {
				result.add(currentWord);
			}
		}

		return result;
	}

}