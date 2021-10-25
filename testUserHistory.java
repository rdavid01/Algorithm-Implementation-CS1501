import java.io.*;
import java.util.*;

public class testUserHistory{
	public static void main(String [] args){

		UserHistory dlb0 = new UserHistory();
		UserHistory dlb1 = new UserHistory();
		UserHistory dlb2 = new UserHistory();
		UserHistory dlb3 = new UserHistory(); //empty UserHistory
		UserHistory dlb4 = new UserHistory(); //UserHistory with only a single char


		//------------------------------------------------------
		//dlb0 tests

		System.out.println("\ntesting dlb0");
		System.out.println("\n\t testing add and contains");
		dlb0.add("hello");
		dlb0.add("world");
		dlb0.add("him");
		dlb0.add("worse");
		dlb0.add("hello^");
		dlb0.add("worldwide");
		dlb0.add("mutual");

		System.out.println("\n\t\tdlb0 serialized");
		System.out.println("\t\t\t" + dlb0.serialize());

		System.out.println("\n\tcontains(hello): " + dlb0.contains("hello"));
		System.out.println("\tcontains(world): " + dlb0.contains("world"));
		System.out.println("\tcontains(him): " + dlb0.contains("him"));
		System.out.println("\tcontains(worse): " + dlb0.contains("worse"));
		System.out.println("\tcontains(he): " + dlb0.contains("he"));
		System.out.println("\tcontains(help): " + dlb0.contains("help"));
		System.out.println("\tcontains(wor): " + dlb0.contains("wor"));
		System.out.println("\tcontains(victory): " + dlb0.contains("victory"));
		System.out.println("\tcontains(hello^): " + dlb0.contains("hello^"));  //this should return false, didn't explicitly insert terminating char


		System.out.println("\n\tcontainsPrefix(hello): " + dlb0.containsPrefix("hello"));
		System.out.println("\tcontainsPrefix(world): " + dlb0.containsPrefix("world"));
		System.out.println("\tcontainsPrefix(him): " + dlb0.containsPrefix("him"));
		System.out.println("\tcontainsPrefix(worse): " + dlb0.containsPrefix("worse"));
		System.out.println("\tcontainsPrefix(he): " + dlb0.containsPrefix("he"));
		System.out.println("\tcontainsPrefix(help): " + dlb0.containsPrefix("help"));
		System.out.println("\tcontainsPrefix(wor): " + dlb0.containsPrefix("wor"));
		System.out.println("\tcontainsPrefix(victory): " + dlb0.containsPrefix("victory"));
		System.out.println("\tcontainsPrefix(hello^): " + dlb0.containsPrefix("hello^"));  //this should return false, didn't explicitly insert terminating char


		System.out.println("\n\ttesting searchByChar");
		System.out.println("\n\tsearchByChar(h): " + dlb0.searchByChar('h'));
		System.out.println("\tsearchByChar(i): " + dlb0.searchByChar('i'));
		System.out.println("\tsearchByChar(m): " + dlb0.searchByChar('m'));
		System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
		System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		System.out.println("\tsearchByChar(o): " + dlb0.searchByChar('o'));
		System.out.println("\tsearchByChar(r): " + dlb0.searchByChar('r'));
		System.out.println("\tsearchByChar(l): " + dlb0.searchByChar('l'));
		System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		System.out.println("\tsearchByChar(i): " + dlb0.searchByChar('i'));
		System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		System.out.println("\tsearchByChar(s): " + dlb0.searchByChar('s'));

		System.out.println("\n\tdlb0.getCharSearchWord(): " + dlb0.getCharSearchWord());

		System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();

		System.out.println("\n\tdlb0.getCharSearchWord(): " + dlb0.getCharSearchWord());


		System.out.println("\n\ntesting size");
		System.out.println("\tcount: " + dlb0.count());

		System.out.println("\n\ntesting traverse");
		System.out.println("\tdlb0.traverse(): " + dlb0.traverse());
		System.out.println("\n\tdlb0 serialized");
		System.out.println("\t\t" + dlb0.serialize());



		System.out.println("\n\ntesting suggest");
		System.out.println("\n\tsearchByChar(h): " + dlb0.searchByChar('h'));
		System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
		System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		System.out.println("\tsearchByChar(o): " + dlb0.searchByChar('o'));
		System.out.println("\tsearchByChar(r): " + dlb0.searchByChar('r'));
		System.out.println("\tsearchByChar(l): " + dlb0.searchByChar('l'));
		System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		System.out.println("\tsearchByChar(w): " + dlb0.searchByChar('w'));
		System.out.println("\tsearchByChar(i): " + dlb0.searchByChar('i'));
		System.out.println("\tsearchByChar(d): " + dlb0.searchByChar('d'));
		System.out.println("\tsearchByChar(e): " + dlb0.searchByChar('e'));
		System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		System.out.println("\tsearchByChar(^): " + dlb0.searchByChar('^'));
		System.out.println("\tsearchByChar(s): " + dlb0.searchByChar('s'));
		System.out.println("\tdlb0.suggest(): " + dlb0.suggest());
		System.out.println("\n\tresetByChar()\n"); dlb0.resetByChar();
		System.out.println("\tdlb0.suggest(): " + dlb0.suggest());

		// ArrayList<String> dlb0Trav1 = dlb0.traverse();
		// for(int i=0; i< dlb0Trav1.size()-1; i++){
		// 	System.out.println("i: " + i + ", dlb0Trav1.get(i): " + dlb0Trav1.get(i));
		// }


		//------------------------------------------------------
		//dlb1

		System.out.println("\ntesting dlb1");
		System.out.println("\n\ttesting add and contains");
		System.out.println("\t\t adding:  hello, he, he");
		dlb1.add("hello");
		dlb1.add("he");
		dlb1.add("he");



		System.out.println("\n\tdlb1 serialized");
		System.out.println("\t\t" + dlb1.serialize());

		System.out.println("\n\tcontains(hello): " + dlb1.contains("hello"));
		System.out.println("\tcontains(world): " + dlb1.contains("world"));
		System.out.println("\tcontains(he): " + dlb1.contains("he"));

		System.out.println("\n\tcontainsPrefix(hello): " + dlb1.containsPrefix("hello"));
		System.out.println("\tcontainsPrefix(world): " + dlb1.containsPrefix("world"));
		System.out.println("\tcontainsPrefix(he): " + dlb1.containsPrefix("he"));
	
		System.out.println("\n\ntesting count");
		System.out.println("\tcount: " + dlb1.count());

		System.out.println("\n\ttesting searchByChar");
		System.out.println("\n\tsearchByChar(h): " + dlb1.searchByChar('h'));
		System.out.println("\tsearchByChar(i): " + dlb1.searchByChar('i'));
		System.out.println("\tsearchByChar(m): " + dlb1.searchByChar('m'));
		System.out.println("\tsearchByChar(^): " + dlb1.searchByChar('^'));
		System.out.println("\n\tresetByChar()\n"); dlb1.resetByChar();

		System.out.println("\n\tdlb1.getCharSearchWord(): " + dlb1.getCharSearchWord());



		//-------------------------------------------------------
		//dlb2
		System.out.println("\n\n=================================================================================");
		System.out.println("\ntesting dlb2");
		System.out.println("\n\t testing add and contains");
		for(int i = 0; i < 12; i++)
			dlb2.add("hello");
			//hello added 12 times
		if(dlb2.contains("hello") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 12; i++)
			dlb2.add("world");
			//world added 12 times
		if(dlb2.contains("world") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 20; i++)
			dlb2.add("him"); 
			//whim added 20 times
		if(dlb2.contains("him") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 8; i++)
			dlb2.add("worse");
			//worse added 8 times
		if(dlb2.contains("worse") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 2; i++)
			dlb2.add("worldwide"); 
			//worldwide added 2 times
		if(dlb2.contains("worldwide") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 4; i++)
			dlb2.add("mutual"); 
			//mutual added 4 times
		if(dlb2.contains("mutual") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 18; i++)
			dlb2.add("walk");
			//walk added 18 times
		if(dlb2.contains("walk") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 24; i++)
			dlb2.add("he");
			//he added 24 times
		if(dlb2.contains("he") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 12; i++)
			dlb2.add("i");
			//i added 12 times
		if(dlb2.contains("i") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 36; i++)
			dlb2.add("I");
			//I added 36 times
		if(dlb2.contains("I") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 5; i++)
			dlb2.add("Indiana");
			//Indiana added 5 times
		if(dlb2.contains("Indiana") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 12; i++)
			dlb2.add("walks");
			//walks added 12 times
		if(dlb2.contains("walks") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 14; i++)
			dlb2.add("walking");
			//walking added 14 times
		if(dlb2.contains("walking") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 16; i++)
			dlb2.add("walkabout");
			//walkabout added 16 times
		if(dlb2.contains("walkabout") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 8; i++)
			dlb2.add("walker");
			//walker added 8 times
		if(dlb2.contains("walker") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 32; i++)
			dlb2.add("walkie-talkie");
			//walkie-talkie added 32 times
		if(dlb2.contains("walkie-talkie") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 3; i++)
			dlb2.add("island");
			//island added 3 times
		if(dlb2.contains("island") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 4; i++)
			dlb2.add("Igor");
			//Igor added 4 times
		if(dlb2.contains("Igor") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 5; i++)
			dlb2.add("Ip-Man");
			//Ip-Man added 5 times
		if(dlb2.contains("Ip-Man") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 102; i++)
			dlb2.add("wyveryn");
			//wyveryn added 102 times
		if(dlb2.contains("wyveryn") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 43; i++)
			dlb2.add("wunderkind");
		if(dlb2.contains("wunderkind") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 21; i++)
			dlb2.add("wraith");
		if(dlb2.contains("wraith") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 6; i++)
			dlb2.add("wrestling");
		if(dlb2.contains("wrestling") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 18; i++)
			dlb2.add("wrong");
		if(dlb2.contains("wrong") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 11; i++)
			dlb2.add("wrongheaded");
		if(dlb2.contains("wrongheaded") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 67; i++)
			dlb2.add("wrongheadedness");
		if(dlb2.contains("wrongheadedness") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 11; i++)
			dlb2.add("wronged");
		if(dlb2.contains("wronged") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 12; i++)
			dlb2.add("wrongs");
		if(dlb2.contains("wrongs") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");
		for(int i = 0; i < 15; i++)
			dlb2.add("wry");
		if(dlb2.contains("wry") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 19; i++)
			dlb2.add("wryly");
		if(dlb2.contains("wryly") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 12; i++)
			dlb2.add("wrongly");
		if(dlb2.contains("wrongly") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 16; i++)
			dlb2.add("wrongful");
		if(dlb2.contains("wrongful") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 14; i++)
			dlb2.add("wrongest");
		if(dlb2.contains("wrongest") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 7; i++)
			dlb2.add("wind");
		if(dlb2.contains("wind") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 8; i++)
			dlb2.add("windmill");
		if(dlb2.contains("windmill") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		for(int i = 0; i < 23; i++)
			dlb2.add("victory");
		if(dlb2.contains("victory") != true)
			System.out.println("RETURNED FALSE, BUT SHOULD BE TRUE");

		System.out.println("\n\t\tdlb2 serialized");
		System.out.println("\t\t\t" + dlb2.serialize());

		System.out.println("\n\ntesting count");
		System.out.println("\tcount: " + dlb1.count());

		System.out.println("\n\tcontainsPrefix(hello): " + dlb2.containsPrefix("hello"));
		System.out.println("\tcontainsPrefix(world): " + dlb2.containsPrefix("world"));
		System.out.println("\tcontainsPrefix(him): " + dlb2.containsPrefix("him"));
		System.out.println("\tcontainsPrefix(worse): " + dlb2.containsPrefix("worse"));
		System.out.println("\tcontainsPrefix(he): " + dlb2.containsPrefix("he"));
		System.out.println("\tcontainsPrefix(help): " + dlb2.containsPrefix("help"));
		System.out.println("\tcontainsPrefix(wor): " + dlb2.containsPrefix("wor"));
		System.out.println("\tcontainsPrefix(victory): " + dlb2.containsPrefix("victory"));
		System.out.println("\tcontainsPrefix(hello^): " + dlb2.containsPrefix("hello^")); 

		// System.out.println("\n\ttesting searchByChar");
		// System.out.println("\n\tsearchByChar(h): " + dlb2.searchByChar('h'));
		// System.out.println("\tsearchByChar(i): " + dlb2.searchByChar('i'));
		// System.out.println("\tsearchByChar(m): " + dlb2.searchByChar('m'));
		// System.out.println("\tsearchByChar(^): " + dlb2.searchByChar('^'));
		// System.out.println("\n\tresetByChar()\n"); dlb2.resetByChar();
		// System.out.println("\tsearchByChar(w): " + dlb2.searchByChar('w'));
		// System.out.println("\tsearchByChar(o): " + dlb2.searchByChar('o'));
		// System.out.println("\tsearchByChar(r): " + dlb2.searchByChar('r'));
		// System.out.println("\tsearchByChar(l): " + dlb2.searchByChar('l'));
		// System.out.println("\tsearchByChar(d): " + dlb2.searchByChar('d'));
		// System.out.println("\tsearchByChar(w): " + dlb2.searchByChar('w'));
		// System.out.println("\tsearchByChar(i): " + dlb2.searchByChar('i'));
		// System.out.println("\tsearchByChar(d): " + dlb2.searchByChar('d'));
		// System.out.println("\tsearchByChar(e): " + dlb2.searchByChar('e'));
		// System.out.println("\tsearchByChar(^): " + dlb2.searchByChar('^'));
		// System.out.println("\tsearchByChar(s): " + dlb2.searchByChar('s'));

		System.out.println("\n\tdlb2.getCharSearchWord(): " + dlb2.getCharSearchWord());

		System.out.println("\n\tresetByChar()\n"); dlb2.resetByChar();

		System.out.println("\n\tdlb2.getCharSearchWord(): " + dlb2.getCharSearchWord());


		System.out.println("\n\ntesting size");
		System.out.println("\tcount: " + dlb2.count());

		System.out.println("\n\ntesting traverse");
		System.out.println("\tdlb2.traverse(): " + dlb2.traverse());
		System.out.println("\n\tdlb2 serialized");
		System.out.println("\t\t" + dlb2.serialize());



		System.out.println("\n\ntesting suggest");
		System.out.println("\n\tsearchByChar(h): " + dlb2.searchByChar('h'));
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());
		System.out.println("\tsearchByChar(e): " + dlb2.searchByChar('e'));
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());
		
		System.out.println("\n\tresetByChar()\n"); dlb2.resetByChar();
		System.out.println("\tsearchByChar(w): " + dlb2.searchByChar('w'));
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());
		System.out.println("\tsearchByChar(o): " + dlb2.searchByChar('o'));
		System.out.println("\tsearchByChar(r): " + dlb2.searchByChar('r'));
		System.out.println("\tsearchByChar(l): " + dlb2.searchByChar('l'));
		System.out.println("\tsearchByChar(d): " + dlb2.searchByChar('d'));
		System.out.println("\tsearchByChar(w): " + dlb2.searchByChar('w'));
		System.out.println("\tsearchByChar(i): " + dlb2.searchByChar('i'));
		System.out.println("\tsearchByChar(d): " + dlb2.searchByChar('d'));
		System.out.println("\tsearchByChar(e): " + dlb2.searchByChar('e'));
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());
		System.out.println("\tsearchByChar(^): " + dlb2.searchByChar('^'));
		System.out.println("\tsearchByChar(s): " + dlb2.searchByChar('s'));
		
		System.out.println("\n\tresetByChar()\n"); dlb2.resetByChar();
		System.out.println("\tsearchByChar(i): " + dlb2.searchByChar('i'));
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());

		System.out.println("\n\tresetByChar()\n"); dlb2.resetByChar();
		System.out.println("\tsearchByChar(I): " + dlb2.searchByChar('I'));
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());
		
		System.out.println("\n\tresetByChar()\n"); dlb2.resetByChar();
		System.out.println("\tdlb2.suggest(): " + dlb2.suggest());


		System.out.println("\n\n==========================================================================================");
		//-------------------------------------------------------
		//dlb3

		System.out.println("\ntesting dlb3 (empty DLB)");
		System.out.println("\n\ttesting contains");
		

		System.out.println("\n\tdlb1 serialized");
		System.out.println("\t\t" + dlb3.serialize());

		System.out.println("\n\tcontains(hello): " + dlb3.contains("hello"));
		System.out.println("\tcontains(world): " + dlb3.contains("world"));
		System.out.println("\tcontains(he): " + dlb3.contains("he"));

		System.out.println("\n\tcontainsPrefix(hello): " + dlb3.containsPrefix("hello"));
		System.out.println("\tcontainsPrefix(world): " + dlb3.containsPrefix("world"));
		System.out.println("\tcontainsPrefix(he): " + dlb3.containsPrefix("he"));
	
		System.out.println("\n\n\ttesting count");
		System.out.println("\tcount: " + dlb3.count());

		System.out.println("\n\n\ttesting size");
		System.out.println("\tcount: " + dlb3.count());

		System.out.println("\n\n\ttesting traverse");
		System.out.println("\tdlb4.traverse(): " + dlb3.traverse());



		System.out.println("\n\n\ttesting suggest");
		System.out.println("\n\tsearchByChar(h): " + dlb3.searchByChar('h'));
		System.out.println("\tdlb4.suggest(): " + dlb3.suggest());
		System.out.println("\tsearchByChar(e): " + dlb3.searchByChar('e'));
		System.out.println("\tdlb4.suggest(): " + dlb3.suggest());

		//-------------------------------------------------------
		//dlb4

		System.out.println("\ntesting dlb4 (only one char)");
		System.out.println("\n\ttesting add and contains");
		System.out.println("\t\tadding h");
		dlb4.add("h");

		System.out.println("\n\tdlb1 serialized");
		System.out.println("\t\t" + dlb4.serialize());

		System.out.println("\n\tcontains(hello): " + dlb4.contains("hello"));
		System.out.println("\tcontains(world): " + dlb4.contains("world"));
		System.out.println("\tcontains(he): " + dlb4.contains("he"));

		System.out.println("\n\tcontainsPrefix(hello): " + dlb4.containsPrefix("hello"));
		System.out.println("\tcontainsPrefix(world): " + dlb4.containsPrefix("world"));
		System.out.println("\tcontainsPrefix(he): " + dlb4.containsPrefix("he"));
	
		System.out.println("\n\n\ttesting count");
		System.out.println("\tcount: " + dlb4.count());

		System.out.println("\n\n\ttesting size");
		System.out.println("\tcount: " + dlb4.count());

		System.out.println("\n\n\ttesting traverse");
		System.out.println("\tdlb4.traverse(): " + dlb4.traverse());



		System.out.println("\n\n\ttesting suggest");
		System.out.println("\n\tsearchByChar(h): " + dlb4.searchByChar('h'));
		System.out.println("\tdlb4.suggest(): " + dlb4.suggest());
		System.out.println("\tsearchByChar(e): " + dlb4.searchByChar('e'));
		System.out.println("\tdlb4.suggest(): " + dlb4.suggest());
		System.out.println("\n\tresetByChar()\n"); dlb4.resetByChar();
		System.out.println("\tsearchByChar(w): " + dlb4.searchByChar('w'));
		System.out.println("\tdlb4.suggest(): " + dlb4.suggest());

	}
}