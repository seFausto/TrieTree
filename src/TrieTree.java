import java.util.*;

public class TrieTree{

	List<Character> letters;
	List<Integer> linkedIndeces;
	//this list contains the index of the last character of the word
	//to facilitate rebuilding the word from the tree
	List<Integer> lastIndeces;

	TrieTree() {
		letters = new ArrayList<Character>();
		linkedIndeces = new ArrayList<Integer>();
		lastIndeces = new ArrayList<Integer>();
	}

	
	public int add(String word) {
		int result = -1;

		char[] charactersInWord = word.toLowerCase().toCharArray();
		int indexOfPreviousChar = -1;
 
		//iterate through each letter to add it to the tree
		for (int letterCount = 0; letterCount < word.length(); letterCount++) {
			//find out if that letter is already in the tree 
			//and the correct index
			if (this.findNextIndexOfChar(charactersInWord[letterCount],
					indexOfPreviousChar) > -1) {
				//If so, get the next index, and continue
				indexOfPreviousChar = this.findNextIndexOfChar(
						charactersInWord[letterCount], indexOfPreviousChar);
				continue;
			}

			//Add the letter to the list
			letters.add(charactersInWord[letterCount]);
			//add the index of the previous letter
			linkedIndeces.add(indexOfPreviousChar);

			//because we added a new letter to the tree
			//the new index is the last inserted one
			indexOfPreviousChar = letters.size() - 1;
		}

		lastIndeces.add(letters.size() - 1);

		return result;
	}

	private int findNextIndexOfChar(char c, int index) {
		int result = -1;

		for (int letterCount = 0; letterCount < letters.size(); letterCount++) {

			if (linkedIndeces.get(letterCount) == index) {
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

		List<Character> newWord = new ArrayList<Character>();

		// iterate through the lastIndeces to know where each word ends
		//and build back to the first letter
		for (int lastIndex = lastIndeces.size() - 1; lastIndex >= 0; lastIndex--) {
			int letterIndex = lastIndeces.get(lastIndex);

			do {
				//add the letter to build the word
				newWord.add(letters.get(letterIndex));
				letterIndex = linkedIndeces.get(letterIndex);
			} while (letterIndex > -1);

			//once we have the word, we need to correct the order
			result.add(new String(reverseString(newWord).toString()));

			newWord.clear();
		}

		return result;
	}

	private String reverseString(List<Character> newWord) {
		//Reverse the order of a word
		String reversedWord = "";
		for (int count = newWord.size() - 1; count >= 0; count--) {
			reversedWord += newWord.get(count).toString();
		}
		return reversedWord;
	}

	public Boolean findWordInTree(String s) {
		
		return  this.getWordsFromTree().contains(s);
	}

	public List<String> findWordsWithSubstring(String s) {
		
		List<String> result = new ArrayList<String>();

		//convert the string to char array to loop through
		char[] charactersToCheck = s.toLowerCase().toCharArray();
		//set the max index, we're looping backwards
		int characterIndex = charactersToCheck.length - 1;

		
		List<Character> word = new ArrayList<Character>();
		//This array will contain if the letters match in the correct order
		//and are continuous. Default to false
		Boolean[] matchesCharacter = new Boolean[charactersToCheck.length];
		Arrays.fill(matchesCharacter, false);

		Boolean startCharacterCheck = false;

		//Loop through all indexes
		for (int lastIndex = lastIndeces.size() - 1; lastIndex >= 0; lastIndex--) {
		
			int letterIndex = lastIndeces.get(lastIndex);

			do {
				//add the letter to build the word
				word.add(letters.get(letterIndex));
				letterIndex = linkedIndeces.get(letterIndex);

				//check the indexes to make sure we don't go out of range
				if (characterIndex >= 0 && letterIndex >= 0) {
					int tempIndex = characterMatchcheck(charactersToCheck,
					characterIndex, matchesCharacter,
					startCharacterCheck, letterIndex);
					
					if(tempIndex < characterIndex){
						startCharacterCheck = true;
						characterIndex = tempIndex;
					}
					else if (characterIndex == -1){
						//do nothing because we found a match
					}
					else if (startCharacterCheck){
						startCharacterCheck = false;
					}
				}

			} while (letterIndex > -1);

			//if it's a match, add it to the result
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
			//if the letters match
			//make sure they are continuous with the "startCharactercheck"
			//startCharacterCheck = true;
			matchesCharacter[characterIndex] = true;
			characterIndex--;

		} else if (characterIndex == -1) {
			// do nothing, because we already found a match
		} else if (startCharacterCheck) {
			//startCharacterCheck = false;
		}
		return characterIndex;
	}


	private Boolean checkIfAllTrue(Boolean[] matchesCharacter) {
		Boolean addToResult = true;
		//check all of the matches, if one is false, then it's not a match
		for (int i = 0; i < matchesCharacter.length; i++) {
			if (!matchesCharacter[i]) {
				addToResult = false;
				break;
			}
		}
		return addToResult;
	}

	//public String toString
}
