import java.util.*;


public class StrategyWordsContainingTH extends Strategy{

	@Override
	List<String> algorithm(TrieTree tree) {
		
		List<String> result = new ArrayList<String>();
		while(tree.hasNext())
		{
			String currentWord = tree.next();
			
			if(currentWord.contains("th"))
			{
				result.add(currentWord);
				
			}
			
		}
		
		return result;
	}

}
