

public class Start {
	public static void main(String[] args){
		TrieTree t= new TrieTree();
		t.add("are");
		t.add("arena");
		t.add("amber");
		t.add("test");
		t.add("bARato");
		
		t.containsSubstring("ar");
	
		while (t.hasNext())
		{
			String temp = t.next();
	
			if (temp.equals("test"))
			{
				System.out.println("removing test");
				t.remove();
				
			}
			System.out.println(temp);
			
			
		}
		
		
	}
}
