import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class TrieTreeTest {
	TrieTree tree;

	@Before
	public void setUp() throws Exception {

		tree = new TrieTree();
		tree.add("are");
		tree.add("arena");
		tree.add("amber");
		tree.add("test");
		tree.add("bARato");
	}

	@After
	public void tearDown() throws Exception {
		tree = new TrieTree();
	}

	@Test
	public void testAddWord() {
		String wordToAdd = "fausto";
		tree.add(wordToAdd);

		assertTrue(tree.findWordInTree(wordToAdd));
	}

	@Test
	public void testGetWordsFromTree() {
		List<String> result = new ArrayList<String>();
		result = tree.getWordsFromTree();

		assertTrue(result.size() == 5);
	}

	@Test
	public void testFindWordInTree() {
		String wordToFind = "test";

		assertTrue(tree.findWordInTree(wordToFind));
	}

	@Test
	public void testFindWordsWithSubstring() {
		String subString = "ar";
		List<String> result = new ArrayList<String>();
		result = tree.containsSubstring(subString);

		assertTrue(result.size() == 3);
	}

	@Test
	public void testHasNext() {

		assertTrue(tree.hasNext());

	}

	@Test
	public void testNotHasNext() {
		tree = new TrieTree();

		assertFalse(tree.hasNext());

	}

	@Test
	public void testNext() {
		int count = 0;

		while (tree.hasNext()) {

			tree.next();
			count++;

		}
		assertTrue(count == 5);

	}

	@Test
	public void testRemove() {
		while (tree.hasNext()) {
			tree.remove();
		}

		assertTrue(tree.size() == 0);
	}
}
