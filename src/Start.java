

public class Start {
	public static void main(String[] args){
		TrieTree t= new TrieTree();
		t.add("are");
		t.add("arena");
		t.add("amber");
		t.add("test");
		t.add("bARato");
		
		t.findWordsWithSubstring("ar");
		
		
		
		System.out.print(t.findWordInTree("amber"));
		
		
	}
}
