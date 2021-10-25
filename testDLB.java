import java.io.*;
import java.util.*;

public class testDLB{
	public static void main(String [] args){

		DLB dlb0 = new DLB();
		DLB dlb1 = new DLB();
		DLB dlb2 = new DLB();
		DLB dlb3 = new DLB(); //empty DLB
		DLB dlb4 = new DLB(); //dlb with only a single char


		//------------------------------------------------------
		//dlb0 tests

		// System.out.println("\ntesting dlb0");
		// System.out.println("\n\t testing add and contains");
		// dlb0.add("hello");
		// dlb0.add("world");
		// dlb0.add("him");
		// dlb0.add("worse");
		// dlb0.add("hello^");
		// dlb0.add("worldwide");
		// dlb0.add("mutual");

		// System.out.println("\n\t\tdlb0 serialized");
		// System.out.println("\t\t\t" + dlb0.serialize());

		// System.out.println("\n\tcontains(hello): " + dlb0.contains("hello"));
		// System.out.println("\tcontains(world): " + dlb0.contains("world"));
		// System.out.println("\tcontains(him): " + dlb0.contains("him"));
		// System.out.println("\tcontains(worse): " + dlb0.contains("worse"));
		// System.out.println("\tcontains(he): " + dlb0.contains("he"));
		// System.out.println("\tcontains(help): " + dlb0.contains("help"));
		// System.out.println("\tcontains(wor): " + dlb0.contains("wor"));
		// System.out.println("\tcontains(victory): " + dlb0.contains("victory"));
		// System.out.println("\tcontains(hello^): " + dlb0.contains("hello^"));  //this should return false, didn't explicitly insert terminating char
		// System.out.println("\tcontains(helr): " + dlb0.contains("helr"));

		// System.out.println("\n\tcontainsPrefix(hello): " + dlb0.containsPrefix("hello"));
		// System.out.println("\tcontainsPrefix(world): " + dlb0.containsPrefix("world"));
		// System.out.println("\tcontainsPrefix(him): " + dlb0.containsPrefix("him"));
		// System.out.println("\tcontainsPrefix(worse): " + dlb0.containsPrefix("worse"));
		// System.out.println("\tcontainsPrefix(he): " + dlb0.containsPrefix("he"));
		// System.out.println("\tcontainsPrefix(help): " + dlb0.containsPrefix("help"));
		// System.out.println("\tcontainsPrefix(wor): " + dlb0.containsPrefix("wor"));
		// System.out.println("\tcontainsPrefix(victory): " + dlb0.containsPrefix("victory"));
		// System.out.println("\tcontainsPrefix(hello^): " + dlb0.containsPrefix("hello^"));  //this should return false, didn't explicitly insert terminating char


		// System.out.println("\n\ttesting searchByChar");
		// System.out.println("\n\tsearchByChar(h): " + dlb0.searchByChar('h'));
		// System.out.println("\tsearchByChar(i): " + dlb0.searchByChar('i'));
		// System.out.println("\tsearchByChar(m): " + dlb0.searchByChar('m'));
		// System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		// System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
		// System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		// System.out.println("\tsearchByChar(o): " + dlb0.searchByChar('o'));
		// System.out.println("\tsearchByChar(r): " + dlb0.searchByChar('r'));
		// System.out.println("\tsearchByChar(l): " + dlb0.searchByChar('l'));
		// System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		// System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		// System.out.println("\tsearchByChar(i): " + dlb0.searchByChar('i'));
		// System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		
		// System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		// System.out.println("\tsearchByChar(s): " + dlb0.searchByChar('s'));
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		// System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		// System.out.println("\n\tdlb0.getCharSearchWord(): " + dlb0.getCharSearchWord());

		// System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();

		// System.out.println("\n\tdlb0.getCharSearchWord(): " + dlb0.getCharSearchWord());


		// System.out.println("\n\ntesting size");
		// System.out.println("\tcount: " + dlb0.count());

		// System.out.println("\n\ntesting traverse");
		// System.out.println("\tdlb0.traverse(): " + dlb0.traverse());



		// System.out.println("\n\ntesting suggest");
		// System.out.println("\n\tsearchByChar(h): " + dlb0.searchByChar('h'));
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		// System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		// System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
		// System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		// System.out.println("\tsearchByChar(o): " + dlb0.searchByChar('o'));
		// System.out.println("\tsearchByChar(r): " + dlb0.searchByChar('r'));
		// System.out.println("\tsearchByChar(l): " + dlb0.searchByChar('l'));
		// System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		// System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		// System.out.println("\tsearchByChar(i): " + dlb0.searchByChar('i'));
		// System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		// System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		// System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		// System.out.println("\tsearchByChar(s): " + dlb0.searchByChar('s'));
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		// System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());

		// System.out.println("\tsearchByChar(h): " + dlb0.searchByChar('h'));
		// System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		// System.out.println("\tsearchByChar(l): " + dlb0.searchByChar('l'));
		// System.out.println("\tsearchByChar(l): " + dlb0.searchByChar('l'));
		// System.out.println("\tsearchByChar(o): " + dlb0.searchByChar('o'));
		// System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		// System.out.println("\tdlb0.suggest(): " + dlb0.suggest());

		// ArrayList<String> dlb0Trav1 = dlb0.traverse();
		// for(int i=0; i< dlb0Trav1.size()-1; i++){
		// 	System.out.println("i: " + i + ", dlb0Trav1.get(i): " + dlb0Trav1.get(i));
		// }






		// System.out.println("\tsearchByChar(): " + dlb0.searchByChar());

		// String fileName = "testDLBOutput.bin";
		// try{
		// 	ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
		// 	os.writeObject(dlb0);
		// 	os.close();
		// } 
		// catch (FileNotFoundException e){
		// 	e.printStackTrace();
		// }
		// catch (IOException e){
		// 	e.printStackTrace();
		// }
		// System.out.println("done writing dlb0");

		//------------------------------------------------------
		//dlb1

		// System.out.println("\ntesting dlb1");
		// System.out.println("\n\ttesting add and contains");
		// System.out.println("\t\t adding:  hello, he, he");
		// dlb1.add("hello");
		// dlb1.add("he");
		// dlb1.add("he");



		// System.out.println("\n\tdlb1 serialized");
		// System.out.println("\t\t" + dlb1.serialize());

		// System.out.println("\n\tcontains(hello): " + dlb1.contains("hello"));
		// System.out.println("\tcontains(world): " + dlb1.contains("world"));
		// System.out.println("\tcontains(he): " + dlb1.contains("he"));

		// System.out.println("\n\tcontainsPrefix(hello): " + dlb1.containsPrefix("hello"));
		// System.out.println("\tcontainsPrefix(world): " + dlb1.containsPrefix("world"));
		// System.out.println("\tcontainsPrefix(he): " + dlb1.containsPrefix("he"));
	
		// System.out.println("\n\ntesting count");
		// System.out.println("\tcount: " + dlb1.count());

		// System.out.println("\n\ttesting searchByChar");
		// System.out.println("\n\tsearchByChar(h): " + dlb1.searchByChar('h'));
		// System.out.println("\tsearchByChar(i): " + dlb1.searchByChar('i'));
		// System.out.println("\tsearchByChar(m): " + dlb1.searchByChar('m'));
		// System.out.println("\tsearchByChar(^): " + dlb1.searchByChar('^'));
		// System.out.println("\n\tresetByChar()\n"); dlb1.resetByChar();

		// System.out.println("\n\tdlb1.getCharSearchWord(): " + dlb1.getCharSearchWord());

		// System.out.println("\n\ntesting suggest");
		// System.out.println("\n\tsearchByChar(h): " + dlb1.searchByChar('h'));
		// System.out.println("\tdlb1.suggest(): " + dlb1.suggest());
		// System.out.println("\tsearchByChar(e): " + dlb1.searchByChar('e'));
		// System.out.println("\tdlb1.suggest(): " + dlb1.suggest());
		// System.out.println("\n\tresetByChar()\n"); dlb1.resetByChar();
		// System.out.println("\tdlb1.suggest(): " + dlb1.suggest());




		//-------------------------------------------------------
		//dlb2
		

		fillDLB(dlb2, "dictionary.txt");

		System.out.println("\ntesting dlb0");
		System.out.println("\ntesting contains");

		System.out.println("\n\tcontains(hello): " + dlb0.contains("hello"));
		System.out.println("\tcontains(world): " + dlb0.contains("world"));
		System.out.println("\tcontains(him): " + dlb0.contains("him"));
		System.out.println("\tcontains(worse): " + dlb0.contains("worse"));
		System.out.println("\tcontains(he): " + dlb0.contains("he"));
		System.out.println("\tcontains(help): " + dlb0.contains("help"));
		System.out.println("\tcontains(wor): " + dlb0.contains("wor"));
		System.out.println("\tcontains(victory): " + dlb0.contains("victory"));
		System.out.println("\tcontains(hello^): " + dlb0.contains("hello^"));  //this should return false, didn't explicitly insert terminating char
		System.out.println("\tcontains(helr): " + dlb0.contains("helr"));

		System.out.println("\ntesting containsPrefix");
		System.out.println("\n\tcontainsPrefix(hello): " + dlb0.containsPrefix("hello"));
		System.out.println("\tcontainsPrefix(world): " + dlb0.containsPrefix("world"));
		System.out.println("\tcontainsPrefix(him): " + dlb0.containsPrefix("him"));
		System.out.println("\tcontainsPrefix(worse): " + dlb0.containsPrefix("worse"));
		System.out.println("\tcontainsPrefix(he): " + dlb0.containsPrefix("he"));
		System.out.println("\tcontainsPrefix(help): " + dlb0.containsPrefix("help"));
		System.out.println("\tcontainsPrefix(wor): " + dlb0.containsPrefix("wor"));
		System.out.println("\tcontainsPrefix(victory): " + dlb0.containsPrefix("victory"));
		System.out.println("\tcontainsPrefix(hello^): " + dlb0.containsPrefix("hello^"));  //this should return false, didn't explicitly insert terminating char
		System.out.println("\tcontains(helr): " + dlb0.contains("helr"));

		System.out.println("size: " + dlb2.count());



		



		//-------------------------------------------------------
		//dlb3

		// System.out.println("\ntesting dlb3 (empty DLB)");
		// System.out.println("\n\ttesting contains");
		

		// System.out.println("\n\tdlb1 serialized");
		// System.out.println("\t\t" + dlb3.serialize());

		// System.out.println("\n\tcontains(hello): " + dlb3.contains("hello"));
		// System.out.println("\tcontains(world): " + dlb3.contains("world"));
		// System.out.println("\tcontains(he): " + dlb3.contains("he"));

		// System.out.println("\n\tcontainsPrefix(hello): " + dlb3.containsPrefix("hello"));
		// System.out.println("\tcontainsPrefix(world): " + dlb3.containsPrefix("world"));
		// System.out.println("\tcontainsPrefix(he): " + dlb3.containsPrefix("he"));
	
		// System.out.println("\n\n\ttesting count");
		// System.out.println("\tcount: " + dlb3.count());

		// System.out.println("\n\n\ttesting size");
		// System.out.println("\tcount: " + dlb3.count());

		// System.out.println("\n\n\ttesting traverse");
		// System.out.println("\tdlb4.traverse(): " + dlb3.traverse());



		// System.out.println("\n\n\ttesting suggest");
		// System.out.println("\n\tsearchByChar(h): " + dlb3.searchByChar('h'));
		// System.out.println("\tdlb4.suggest(): " + dlb3.suggest());
		// System.out.println("\tsearchByChar(e): " + dlb3.searchByChar('e'));
		// System.out.println("\tdlb4.suggest(): " + dlb3.suggest());

		//-------------------------------------------------------
		//dlb4

		// System.out.println("\ntesting dlb4 (only one char)");
		// System.out.println("\n\ttesting add and contains");
		// System.out.println("\t\tadding h");
		// dlb4.add("h");

		// System.out.println("\n\tdlb1 serialized");
		// System.out.println("\t\t" + dlb4.serialize());

		// System.out.println("\n\tcontains(hello): " + dlb4.contains("hello"));
		// System.out.println("\tcontains(world): " + dlb4.contains("world"));
		// System.out.println("\tcontains(he): " + dlb4.contains("he"));

		// System.out.println("\n\tcontainsPrefix(hello): " + dlb4.containsPrefix("hello"));
		// System.out.println("\tcontainsPrefix(world): " + dlb4.containsPrefix("world"));
		// System.out.println("\tcontainsPrefix(he): " + dlb4.containsPrefix("he"));
	
		// System.out.println("\n\n\ttesting count");
		// System.out.println("\tcount: " + dlb4.count());

		// System.out.println("\n\n\ttesting size");
		// System.out.println("\tcount: " + dlb4.count());

		// System.out.println("\n\n\ttesting traverse");
		// System.out.println("\tdlb4.traverse(): " + dlb4.traverse());



		// System.out.println("\n\n\ttesting suggest");
		// System.out.println("\n\tsearchByChar(h): " + dlb4.searchByChar('h'));
		// System.out.println("\tdlb4.suggest(): " + dlb4.suggest());
		// System.out.println("\tsearchByChar(e): " + dlb4.searchByChar('e'));
		// System.out.println("\tdlb4.suggest(): " + dlb4.suggest());
		// System.out.println("\n\tresetByChar()\n"); dlb4.resetByChar();
		// System.out.println("\tsearchByChar(w): " + dlb4.searchByChar('w'));
		// System.out.println("\tdlb4.suggest(): " + dlb4.suggest());

	}

	private static void fillDLB(DLB dlb, String dictionary){
		try{
			Scanner keyReader = new Scanner(new File(dictionary)); //created a scanner to read through the file
			while(keyReader.hasNext()){ //while there is another thing to read in the file
					
				String storedWord = keyReader.nextLine(); //read the next line in the file
				dlb.add(storedWord); //add this line from file to the dlb
			}
			keyReader.close(); //close the scanner
		}
		catch(FileNotFoundException e){
			System.out.println("dictionary file not found -> " + dictionary);
		}
	}
}