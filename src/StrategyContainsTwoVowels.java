import java.util.*;

public class StrategyContainsTwoVowels extends Strategy {

	private final String LETTERSTOCHECK = "aeiou";
	private final int NUMBEROFLETTESTOFIND = 2;

	@Override
	List<String> algorithm(List<String> words) {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < words.size(); i++) {
			int countOfMatches = 0;
			for (int j = 0; j < LETTERSTOCHECK.length(); j++) {
				if (words.get(i).contains(
						String.valueOf(LETTERSTOCHECK.charAt(j)))) {
					countOfMatches++;
				}
			}
			
			if (countOfMatches > NUMBEROFLETTESTOFIND)
			{
				result.add(words.get(i));
			}
		}
		
		return result;

	}

}
