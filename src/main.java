

public class main {
	public static void main(String[] args){
		TrieTree t= new TrieTree();
		t.AddWord("are");
		t.AddWord("arena");
		t.AddWord("amber");
		t.AddWord("test");
		t.AddWord("bARato");
		
		t.FindWordsWithSubstring("ar");
		
		
		
		System.out.print(t.FindWordInTree("amber"));
		
		
	}
}
