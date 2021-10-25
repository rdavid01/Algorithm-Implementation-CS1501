import java.io.*;
import java.util.*;

public class testAutoCompleter{
	public static void main(String [] args){

	String eng_dict_fname = "dictionary.txt";
	String small_dict_fname = "smallDictionary.txt";

	AutoCompleter ac0 = new AutoCompleter(eng_dict_fname);
	AutoCompleter ac1 = new AutoCompleter(eng_dict_fname);
	AutoCompleter ac2 = new AutoCompleter(eng_dict_fname);
	AutoCompleter ac3 = new AutoCompleter(eng_dict_fname);
	AutoCompleter ac4 = new AutoCompleter(eng_dict_fname, "userHistory1.txt");

	AutoCompleter ac5 = new AutoCompleter(small_dict_fname); //using smaller dictionary

	AutoCompleter ac6 = new AutoCompleter(eng_dict_fname, "emptyUserHistory.txt");
	AutoCompleter ac7 = new AutoCompleter(eng_dict_fname, "userHistory6.txt"); //userHistory6.txt should be empty
	AutoCompleter ac8 = new AutoCompleter(eng_dict_fname);
	AutoCompleter ac9 = new AutoCompleter(eng_dict_fname, "doesNotExist.txt"); //second parameter file will not be found

	AutoCompleter ac10 = new AutoCompleter("cantFindFile.txt"); //first parameter file will not be found
	AutoCompleter ac11 = new AutoCompleter(eng_dict_fname, "seeStructTest0.txt"); 
	AutoCompleter ac12 = new AutoCompleter(eng_dict_fname, "seeStructTest1.txt");


	System.out.println("\n\ntesting AutoCompleter 0");

	System.out.println("ac0.nextChar(h): " + ac0.nextChar('h'));
	System.out.println("ac0.nextChar(e): " + ac0.nextChar('e'));
	System.out.println("ac0.nextChar(l): " + ac0.nextChar('l'));

	System.out.println("\nac0.finishWord(held): "); ac0.finishWord("held");
	System.out.println("\nac0.finishWord(held): "); ac0.finishWord("held");


	System.out.println("\nac0.nextChar(h): " + ac0.nextChar('h'));
	System.out.println("ac0.nextChar(e): " + ac0.nextChar('e'));
	System.out.println("ac0.nextChar(l): " + ac0.nextChar('l'));
	System.out.println("ac0.nextChar(p): " + ac0.nextChar('p'));

	System.out.println("\nac0.finishWord(help): "); ac0.finishWord("help");

	System.out.println("\nac0.nextChar(h): " + ac0.nextChar('h'));
	System.out.println("ac0.nextChar(e): " + ac0.nextChar('e'));
	System.out.println("ac0.nextChar(l): " + ac0.nextChar('l'));
	System.out.println("ac0.nextChar(l): " + ac0.nextChar('l'));
	System.out.println("ac0.nextChar(o): " + ac0.nextChar('o'));

	System.out.println("\nac0.finishWord(hello): ");  ac0.finishWord("hello");
	System.out.println("\nac0.finishWord(help): ");  ac0.finishWord("help");
	System.out.println("\nac0.finishWord(help): ");  ac0.finishWord("help");

	System.out.println("\nac0.nextChar(h): " + ac0.nextChar('h'));


	System.out.println("\nac0.saveUserHistory(userHistory0): ");  ac0.saveUserHistory("userHistory0.txt");

	System.out.println("");

	//--------------------------------------------------------------------
	System.out.println("\n\ntesting AutoCompleter 1");

	System.out.println("ac1.nextChar(h): " + ac1.nextChar('h'));
	System.out.println("ac1.nextChar(e): " + ac1.nextChar('e'));
	System.out.println("ac1.nextChar(l): " + ac1.nextChar('l'));

	System.out.println("\nac1.finishWord(held): "); ac1.finishWord("held");
	System.out.println("\nac1.finishWord(held): "); ac1.finishWord("held");


	System.out.println("\nac1.nextChar(h): " + ac1.nextChar('h'));
	System.out.println("ac1.nextChar(e): " + ac1.nextChar('e'));
	System.out.println("ac1.nextChar(l): " + ac1.nextChar('l'));
	System.out.println("ac1.nextChar(p): " + ac1.nextChar('p'));

	System.out.println("\nac1.finishWord(help): "); ac1.finishWord("help");

	System.out.println("\nac1.nextChar(h): " + ac1.nextChar('h'));
	System.out.println("ac1.nextChar(e): " + ac1.nextChar('e'));
	System.out.println("ac1.nextChar(l): " + ac1.nextChar('l'));
	System.out.println("ac1.nextChar(l): " + ac1.nextChar('l'));
	System.out.println("ac1.nextChar(o): " + ac1.nextChar('o'));

	System.out.println("\nac1.finishWord(hello): ");  ac1.finishWord("hello");
	System.out.println("\nac1.finishWord(help): ");  ac1.finishWord("help");
	System.out.println("\nac1.finishWord(help): ");  ac1.finishWord("help");

	System.out.println("\nac1.nextChar(h): " + ac1.nextChar('h'));

	System.out.println("\nac1.finishWord(walrus): ");  ac1.finishWord("walrus");
	System.out.println("\nac1.finishWord(marshall): ");  ac1.finishWord("marshall");
	System.out.println("\nac1.finishWord(PapPap): ");  ac1.finishWord("PapPap");


	System.out.println("\nac1.saveUserHistory(userHistory1): ");  ac1.saveUserHistory("userHistory1.txt");

	System.out.println("");

	//-----------------------------------------------------------------------
	System.out.println("\n\ntesting AutoCompleter 2");

	System.out.println("ac2.nextChar(h): " + ac2.nextChar('h'));
	System.out.println("ac2.nextChar(e): " + ac2.nextChar('e'));
	System.out.println("ac2.nextChar(l): " + ac2.nextChar('l'));

	System.out.println("\nac2.finishWord(held): "); ac2.finishWord("held");
	System.out.println("\nac2.finishWord(held): "); ac2.finishWord("held");


	System.out.println("\nac2.nextChar(h): " + ac2.nextChar('h'));
	System.out.println("ac2.nextChar(e): " + ac2.nextChar('e'));
	System.out.println("ac2.nextChar(l): " + ac2.nextChar('l'));
	System.out.println("ac2.nextChar(p): " + ac2.nextChar('p'));

	System.out.println("\nac2.finishWord(help): "); ac2.finishWord("help");

	System.out.println("\nac2.nextChar(h): " + ac2.nextChar('h'));
	System.out.println("ac2.nextChar(e): " + ac2.nextChar('e'));
	System.out.println("ac2.nextChar(l): " + ac2.nextChar('l'));
	System.out.println("ac2.nextChar(l): " + ac2.nextChar('l'));
	System.out.println("ac2.nextChar(o): " + ac2.nextChar('o'));

	System.out.println("\nac2.finishWord(hello): ");  ac2.finishWord("hello");
	System.out.println("\nac2.finishWord(help): ");  ac2.finishWord("help");
	System.out.println("\nac2.finishWord(help): ");  ac2.finishWord("help");

	System.out.println("\nac2.nextChar(h): " + ac2.nextChar('h'));

	System.out.println("\nac2.finishWord(he): ");  ac2.finishWord("he");
	System.out.println("\nac2.finishWord(he): ");  ac2.finishWord("he");
	System.out.println("\nac2.finishWord(h): ");  ac2.finishWord("h");


	System.out.println("\nac2.saveUserHistory(userHistory2): ");  ac2.saveUserHistory("userHistory2.txt");

	System.out.println("");

	//-----------------------------------------------------------------------------
	System.out.println("\n\n testing AutoCompleter 3");

	System.out.println("ac3.nextChar(h): " + ac3.nextChar('h'));
	System.out.println("ac3.nextChar(e): " + ac3.nextChar('e'));
	System.out.println("ac3.nextChar(l): " + ac3.nextChar('l'));

	System.out.println("\nac3.finishWord(held): "); ac3.finishWord("held");
	System.out.println("\nac3.finishWord(held): "); ac3.finishWord("held");


	System.out.println("\nac3.nextChar(h): " + ac3.nextChar('h'));
	System.out.println("ac3.nextChar(e): " + ac3.nextChar('e'));
	System.out.println("ac3.nextChar(l): " + ac3.nextChar('l'));
	System.out.println("ac3.nextChar(p): " + ac3.nextChar('p'));

	System.out.println("\nac3.finishWord(help): "); ac3.finishWord("help");

	System.out.println("\nac3.nextChar(h): " + ac3.nextChar('h'));
	System.out.println("ac3.nextChar(e): " + ac3.nextChar('e'));
	System.out.println("ac3.nextChar(l): " + ac3.nextChar('l'));
	System.out.println("ac3.nextChar(l): " + ac3.nextChar('l'));
	System.out.println("ac3.nextChar(o): " + ac3.nextChar('o'));

	System.out.println("\nac3.finishWord(hello): ");  ac3.finishWord("hello");
	System.out.println("\nac3.finishWord(help): ");  ac3.finishWord("help");
	System.out.println("\nac3.finishWord(hel): ");  ac3.finishWord("help");

	// System.out.println("\nac3.nextChar(h): " + ac3.nextChar('h'));

	// System.out.println("\nac3.finishWord(walrus): ");  ac3.finishWord("walrus");
	// System.out.println("\nac3.finishWord(marshall): ");  ac3.finishWord("marshall");
	// System.out.println("\nac3.finishWord(PapPap): ");  ac3.finishWord("PapPap");


	System.out.println("\nac3.saveUserHistory(userHistory3): ");  ac3.saveUserHistory("userHistory3.txt");

	System.out.println("");

	//--------------------------------------------------------------------------------
	System.out.println("\n\ntesting AutoCompleter 4");

	System.out.println("\n ac11.userHist.serialize(): " + ac11.userHist.serialize());

	System.out.println("\nac4.finishWord(walrus): ");  ac4.finishWord("walrus");
	System.out.println("\nac4.finishWord(marshall): ");  ac4.finishWord("marshall");
	System.out.println("\nac4.finishWord(walrus)): ");  ac4.finishWord("walrus");
	System.out.println("\nac4.finishWord(walrus): ");  ac4.finishWord("walrus");
	System.out.println("\nac4.finishWord(marshall): ");  ac4.finishWord("marshall");
	System.out.println("\nac4.finishWord(walrus): ");  ac4.finishWord("walrus");

	System.out.println("\nac4.finishWord(Erwin): ");  ac4.finishWord("Erwin");
	System.out.println("\nac4.finishWord(Titusville): ");  ac4.finishWord("Titusville");
	System.out.println("\nac4.finishWord(PapPap): ");  ac4.finishWord("PapPap");

	System.out.println("\nac4.saveUserHistory(userHistory4): ");  ac4.saveUserHistory("userHistory4.txt");

	System.out.println("\nac4.nextChar(h): " + ac4.nextChar('h'));
	System.out.println("");


	//---------------------------------------------------------------------------
	System.out.println("\n===================================================================\n");
	System.out.println("\n\ntesting AutoCompleter 5");

	// System.out.println("\nac5.nextChar(d): " + ac5.nextChar('d'));
	
	ArrayList<String> sugs = ac5.nextChar('d');
	// System.out.println("sugs: " + sugs);
	String[] expected = new String[] {"definite", "dict", "dictionary"};
	for(int i=0; i < expected.length; i++){
		System.out.println("expected[i]: " + expected[i] + ", sugs.get(i): " + sugs.get(i));
	}

	System.out.println("\n----------------------------------");
	ac5.finishWord("dictionary");

	sugs = ac5.nextChar('d');
	expected = new String[] {"dictionary", "definite", "dict"};
	for(int i=0; i < expected.length; i++){
		System.out.println("expected[i]: " + expected[i] + ", sugs.get(i): " + sugs.get(i));
	}

	//------------------------------------------------------------------------------
	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 6");

	System.out.println("ac6.nextChar(h): " + ac6.nextChar('h'));
	System.out.println("ac6.nextChar(e): " + ac6.nextChar('e'));
	System.out.println("ac6.nextChar(l): " + ac6.nextChar('l'));

	System.out.println("\nac6.nextChar(h): " + ac6.nextChar('h'));
	System.out.println("ac6.nextChar(e): " + ac6.nextChar('e'));
	System.out.println("ac6.nextChar(l): " + ac6.nextChar('l'));
	System.out.println("ac6.nextChar(p): " + ac6.nextChar('p'));

	System.out.println("\nac6.saveUserHistory(userHistory6): ");  ac6.saveUserHistory("userHistory6.txt");

	System.out.println("\nac6.nextChar(h): " + ac6.nextChar('h'));
	System.out.println("\nac6.nextChar(z): " + ac6.nextChar('z'));
	System.out.println("");

	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 7");

	System.out.println("ac7.nextChar(h): " + ac7.nextChar('h'));
	System.out.println("ac7.nextChar(e): " + ac7.nextChar('e'));
	System.out.println("ac7.nextChar(l): " + ac7.nextChar('l'));

	System.out.println("\nac7.finishWord(held): "); ac7.finishWord("held");
	System.out.println("\nac7.finishWord(held): "); ac7.finishWord("held");


	System.out.println("\nac7.nextChar(h): " + ac7.nextChar('h'));
	System.out.println("ac7.nextChar(e): " + ac7.nextChar('e'));
	System.out.println("ac7.nextChar(l): " + ac7.nextChar('l'));
	System.out.println("ac7.nextChar(p): " + ac7.nextChar('p'));

	System.out.println("\nac7.finishWord(help): "); ac7.finishWord("help");

	System.out.println("\nac7.saveUserHistory(userHistory7): ");  ac7.saveUserHistory("userHistory7.txt");

	System.out.println("\nac7.nextChar(h): " + ac7.nextChar('h'));
	System.out.println("");

	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 8");

	

	System.out.println("\nac8.nextChar(a): " + ac8.nextChar('a'));
	System.out.println("ac8.nextChar(d): " + ac8.nextChar('d'));
	System.out.println("ac8.nextChar(d): " + ac8.nextChar('d'));
	System.out.println("ac8.nextChar(!!!!z): " + ac8.nextChar('z'));
	System.out.println("ac8.nextChar(!!!!q): " + ac8.nextChar('q'));
	System.out.println("ac8.nectChar(^): " + ac8.nextChar('^'));
	System.out.println("ac8.nextChar(i): " + ac8.nextChar('i'));

	System.out.println("\nac8.finishWord(additive): "); ac8.finishWord("additive");
	System.out.println("\nac8.finishWord(additive): "); ac8.finishWord("additive");


	System.out.println("ac8.nextChar(a): " + ac8.nextChar('a'));
	System.out.println("ac8.nextChar(d): " + ac8.nextChar('d'));
	System.out.println("ac8.nextChar(d): " + ac8.nextChar('d'));
	System.out.println("ac8.nextChar(^): " + ac8.nextChar('^'));




	System.out.println("\nac8.finishWord(add): "); ac8.finishWord("add");
	System.out.println("\nac8.finishWord(add^): "); ac8.finishWord("add^");

	System.out.println("\nac8.saveUserHistory(userHistory8): ");  ac8.saveUserHistory("userHistory8.txt");

	System.out.println("\nac8.nextChar(h): " + ac8.nextChar('h'));
	System.out.println("");

	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 9");

	System.out.println("\nac9.nextChar(a): " + ac9.nextChar('a'));
	System.out.println("ac9.nextChar(d): " + ac9.nextChar('d'));
	System.out.println("ac9.nextChar(d): " + ac9.nextChar('d'));
	System.out.println("ac9.nextChar(!!!!z): " + ac9.nextChar('z'));
	System.out.println("ac9.nextChar(!!!!q): " + ac9.nextChar('q'));
	System.out.println("ac9.nectChar(^): " + ac9.nextChar('^'));
	System.out.println("ac9.nextChar(i): " + ac9.nextChar('i'));

	System.out.println("\nac9.finishWord(additive): "); ac9.finishWord("additive");
	System.out.println("\nac9.finishWord(additive): "); ac9.finishWord("additive");


	System.out.println("ac9.nextChar(a): " + ac9.nextChar('a'));
	System.out.println("ac9.nextChar(d): " + ac9.nextChar('d'));
	System.out.println("ac9.nextChar(d): " + ac9.nextChar('d'));
	System.out.println("ac9.nextChar(^): " + ac9.nextChar('^'));




	System.out.println("\nac9.finishWord(add): "); ac9.finishWord("add");
	System.out.println("\nac9.finishWord(add^): "); ac9.finishWord("add^");

	System.out.println("\nac9.saveUserHistory(userHistory9): ");  ac9.saveUserHistory("userHistory9.txt");

	System.out.println("\nac9.nextChar(h): " + ac9.nextChar('h'));
	System.out.println("");


	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 10");

	System.out.println("\nac10.nextChar(a): " + ac10.nextChar('a'));
	System.out.println("ac10.nextChar(d): " + ac10.nextChar('d'));
	System.out.println("ac10.nextChar(d): " + ac10.nextChar('d'));
	System.out.println("ac10.nextChar(!!!!z): " + ac10.nextChar('z'));
	System.out.println("ac10.nextChar(!!!!q): " + ac10.nextChar('q'));
	System.out.println("ac10.nectChar(^): " + ac10.nextChar('^'));
	System.out.println("ac10.nextChar(i): " + ac10.nextChar('i'));

	System.out.println("\nac10.finishWord(additive): "); ac10.finishWord("additive");
	System.out.println("\nac10.finishWord(additive): "); ac10.finishWord("additive");


	System.out.println("ac10.nextChar(a): " + ac10.nextChar('a'));
	System.out.println("ac10.nextChar(d): " + ac10.nextChar('d'));
	System.out.println("ac10.nextChar(d): " + ac10.nextChar('d'));
	System.out.println("ac10.nextChar(^): " + ac10.nextChar('^'));




	System.out.println("\nac10.finishWord(add): "); ac10.finishWord("add");
	System.out.println("\nac10.finishWord(add^): "); ac10.finishWord("add^");

	System.out.println("\nac10.saveUserHistory(userHistory10): ");  ac10.saveUserHistory("userHistory10.txt");

	System.out.println("\nac10.nextChar(h): " + ac10.nextChar('h'));

	System.out.println("\nac10.finishWord(additive): "); ac10.finishWord("additive");
	System.out.println("\nac10.nextChar(a): " + ac10.nextChar('a'));

	System.out.println("");
	
	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 11");
	System.out.println("\n ac11.userHist.serialize(): " + ac11.userHist.serialize());

	for(int i=0; i<100; i++){
		ac11.finishWord("AFTERMATH");
		ac11.finishWord("XYLOPHONE");
	}
	System.out.println("\nac11.saveUserHistory(userHistory11): ");  ac11.saveUserHistory("userHistory11.txt");


	System.out.println("ac11.nextChar(s): " + ac11.nextChar('s'));
	System.out.println("\nac11.finishWord(additive): "); ac11.finishWord("additive");

	System.out.println("ac11.nextChar(p): " + ac11.nextChar('p'));
	System.out.println("\nac11.finishWord(additive): "); ac11.finishWord("additive");
	
	System.out.println("ac11.nextChar(b): " + ac11.nextChar('b'));
	System.out.println("\nac11.finishWord(additive): "); ac11.finishWord("additive");
	
	System.out.println("ac11.nextChar(l): " + ac11.nextChar('l'));
	System.out.println("\nac11.finishWord(additive): "); ac11.finishWord("additive");

	System.out.println("ac11.nextChar(h): " + ac11.nextChar('h'));
	System.out.println("\nac11.finishWord(additive): "); ac11.finishWord("additive");

	System.out.println("ac11.nextChar(m): " + ac11.nextChar('m'));
	System.out.println("\nac11.finishWord(additive): "); ac11.finishWord("additive");


	System.out.println("\n------------------------------------------------------------------");
	System.out.println("\n\ntesting AutoCompleter 12");
	System.out.println("\n ac12.userHist.serialize(): " + ac12.userHist.serialize());

	for(int i=0; i<100; i++){
		ac12.finishWord("AFTERMATH");
		ac12.finishWord("XYLOPHONE");
	}
	System.out.println("\nac12.saveUserHistory(userHistory12): ");  ac12.saveUserHistory("userHistory12.txt");


	System.out.println("\nac12.nextChar(l): " + ac12.nextChar('l'));

	System.out.println("\nac12.nextChar(a): " + ac12.nextChar('a'));
	
	System.out.println("\nac12.nextChar(m): " + ac12.nextChar('m'));
	
	System.out.println("\nac12.nextChar(p): " + ac12.nextChar('p'));

	System.out.println("\nac12.nextChar(s): " + ac12.nextChar('s'));

	System.out.println("\nac12.nextChar(h): " + ac12.nextChar('h'));

	System.out.println("\nac12.nextChar(a): " + ac12.nextChar('a'));

	System.out.println("\nac12.nextChar(z): " + ac12.nextChar('z'));

	System.out.println("\nac12.nextChar(q): " + ac12.nextChar('q'));


	}
}