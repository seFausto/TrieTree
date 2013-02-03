import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.AbstractCollection;
import java.util.AbstractSequentialList;


public class TrieTreeTest {
	TrieTree tree ;
	
	@Before
	public void setUp() throws Exception {
		
		tree = new TrieTree();
		tree.AddWord("are");
		tree.AddWord("arena");
		tree.AddWord("amber");
		tree.AddWord("test");
		tree.AddWord("bARato");
		
	}

	@After
	public void tearDown() throws Exception {
		tree = new TrieTree();
	}

	@Test
	public void testAddWord() {
		String wordToAdd = "fausto";
		tree.AddWord(wordToAdd);
		
		assertTrue(tree.FindWordInTree(wordToAdd));
	}

	@Test
	public void testGetWordsFromTree() {
		List<String> result = new ArrayList<String>();
		result = tree.GetWordsFromTree();
		
		assertTrue(result.size() == 5);
	}

	@Test
	public void testFindWordInTree() {
		String wordToFind = "test";
		
		assertTrue(tree.FindWordInTree(wordToFind));
	}

	@Test
	public void testFindWordsWithSubstring() {
		String subString = "ar";
		List<String> result = new ArrayList<String>();
		result = tree.FindWordsWithSubstring(subString);
		
		assertTrue(result.size() == 3);
	}

}
