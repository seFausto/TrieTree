import java.util.*;

public class TrieTree implements Iterator<String> {

	private List<Character> letters;
	private List<Integer> linkedIndexes;
	// this list contains the index of the last character of the word
	// to facilitate rebuilding the word from the tree
	private List<Integer> lastIndexes;

	private int iteratorIndex = 0;

	TrieTree() {
		letters = new ArrayList<Character>();
		linkedIndexes = new ArrayList<Integer>();
		lastIndexes = new ArrayList<Integer>();
	}

	public Boolean add(String word) {
		Boolean result = false;

		char[] charactersInWord = word.toLowerCase().toCharArray();
		int indexOfPreviousChar = -1;

		try {
			// iterate through each letter to add it to the tree
			for (int letterCount = 0; letterCount < word.length(); letterCount++) {
				// find out if that letter is already in the tree
				// and the correct index
				if (this.findNextIndexOfChar(charactersInWord[letterCount],
						indexOfPreviousChar) > -1) {
					// If so, get the next index, and continue
					indexOfPreviousChar = this.findNextIndexOfChar(
							charactersInWord[letterCount], indexOfPreviousChar);
					continue;
				}

				// Add the letter to the list
				letters.add(charactersInWord[letterCount]);
				// add the index of the previous letter
				linkedIndexes.add(indexOfPreviousChar);

				// because we added a new letter to the tree
				// the new index is the last inserted one
				indexOfPreviousChar = letters.size() - 1;
				result = true;
			}
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}

		lastIndexes.add(letters.size() - 1);

		return result;
	}

	private int findNextIndexOfChar(char c, int index) {
		int result = -1;

		for (int letterCount = 0; letterCount < letters.size(); letterCount++) {

			if (linkedIndexes.get(letterCount) == index) {
				if (letters.get(letterCount) == c) {
					result = letterCount;
					break;
				}
			}
		}

		return result;

	}

	public List<String> getWordsFromTree() {
		List<String> result = new ArrayList<String>();
		// iterate through the lastIndeces to know where each word ends
		// and build back to the first letter
		for (int lastIndex = lastIndexes.size() - 1; lastIndex >= 0; lastIndex--) {
			int letterIndex = lastIndexes.get(lastIndex);
			// once we have the word, we need to correct the order
			result.add(getWordByIndexOfLastChar(letterIndex));
		}
		return result;
	}

	private String getWordByIndexOfLastChar(int letterIndex) {

		List<Character> newWord = new ArrayList<Character>();
		do {
			// add the letter to build the word
			newWord.add(letters.get(letterIndex));

			letterIndex = linkedIndexes.get(letterIndex);
		} while (letterIndex > -1);

		return new String(reverseString(newWord).toString());
	}

	private String reverseString(List<Character> newWord) {
		String reversedWord = "";
		for (int count = newWord.size() - 1; count >= 0; count--) {
			reversedWord += newWord.get(count).toString();
		}
		return reversedWord;
	}

	public Boolean findWordInTree(String s) {

		return this.getWordsFromTree().contains(s);
	}

	public List<String> containsSubstring(String s) {

		List<String> result = new ArrayList<String>();

		char[] charactersToCheck = s.toLowerCase().toCharArray();
		// set the max index, we're looping backwards
		int characterIndex = charactersToCheck.length - 1;
		List<Character> word = new ArrayList<Character>();
		// This array will contain if the letters match in the correct order
		// and are continuous. Default to false
		Boolean[] matchesCharacter = new Boolean[charactersToCheck.length];
		Arrays.fill(matchesCharacter, false);

		Boolean startCharacterCheck = false;

		// Loop through all indexes
		for (int lastIndex = lastIndexes.size() - 1; lastIndex >= 0; lastIndex--) {

			int letterIndex = lastIndexes.get(lastIndex);

			do {
				// add the letter to build the word
				word.add(letters.get(letterIndex));
				letterIndex = linkedIndexes.get(letterIndex);

				// check the indexes to make sure we don't go out of range
				if (characterIndex >= 0 && letterIndex >= 0) {
					int tempIndex = characterMatchcheck(charactersToCheck,
							characterIndex, matchesCharacter,
							startCharacterCheck, letterIndex);

					if (tempIndex < characterIndex) {
						startCharacterCheck = true;
						characterIndex = tempIndex;
					} else if (characterIndex == -1) {
						// do nothing because we found a match
					} else if (startCharacterCheck) {
						startCharacterCheck = false;
					}
				}

			} while (letterIndex > -1);

			// if it's a match, add it to the result
			if (checkIfAllTrue(matchesCharacter))
				result.add(new String(reverseString(word).toString()));

			word.clear();
			Arrays.fill(matchesCharacter, false);
			characterIndex = charactersToCheck.length - 1;
		}

		return result;

	}

	private int characterMatchcheck(char[] charactersToCheck,
			int characterIndex, Boolean[] matchesCharacter,
			Boolean startCharacterCheck, int letterIndex) {
		if (letters.get(letterIndex) == charactersToCheck[characterIndex]) {
			// if the letters match
			// make sure they are continuous with the "startCharactercheck"
			// startCharacterCheck = true;
			matchesCharacter[characterIndex] = true;
			characterIndex--;

		} else if (characterIndex == -1) {
			// do nothing, because we already found a match
		} else if (startCharacterCheck) {
			// startCharacterCheck = false;
		}
		return characterIndex;
	}

	private Boolean checkIfAllTrue(Boolean[] matchesCharacter) {
		Boolean addToResult = true;
		// check all of the matches, if one is false, then it's not a match
		for (int i = 0; i < matchesCharacter.length; i++) {
			if (!matchesCharacter[i]) {
				addToResult = false;
				break;
			}
		}
		return addToResult;
	}

	@Override
	public boolean hasNext() {
		if (iteratorIndex < lastIndexes.size())
			return true;
		else
			return false;
	}

	@Override
	public String next() {
		String result = "";
		result = getWordByIndexOfLastChar(lastIndexes.get(iteratorIndex));
		iteratorIndex++;
		return result;
	}

	@Override
	public void remove() {
		lastIndexes.remove(iteratorIndex);
	}

	public String toString() {
		return "Trie that contains " + lastIndexes.size() + " words";

	}

	public List<String> accept(Visitor v) {
		return v.visitTrieTree(this);

	}

	public int size()
	{
		return lastIndexes.size();
	}
}
