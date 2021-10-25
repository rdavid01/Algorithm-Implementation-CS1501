/*
can either test with char or int, can't do both at same time (but can test separately)
*/

public class testHeightBalance{
	public static void main(String [] args){
		BST tree0 = new BST();
		BST tree1 = new BST();
		BST tree2 = new BST(); 
		BST tree3 = new BST();//only has a root node
		BST tree3Half = new BST();
		BST tree3Quarter = new BST();
		BST tree4 = new BST(); 
		BST tree5 = new BST(); //empty
		BST tree6 = new BST();
		BST tree7 = new BST();
		BST tree8 = new BST();
		BST tree9 = new BST();

		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! test with inserting null,   null as arg to functions,  etc.

		// System.out.println("\tputting a");
		// tree0.put('a');
		
		// System.out.println("\tputting b");
		// tree0.put('b');
	
		// System.out.println("\tputting c");
		// tree0.put('c');
		
		// System.out.println("\tputting b");
		// tree0.put('b');
		
		// System.out.println("\tputting x");
		// tree0.put('x');
		
		// System.out.println("\ntesting inOrderTraversal");
		// String t0InOrderTrav = tree0.inOrderTraversal();
		// System.out.println("tree0 in Order Traversal: " + t0InOrderTrav);

		System.out.println("\n\ntesting tree0");

		System.out.println("\ntree0. put, 50, 25, 75, 12, 37, 62, 87, 30, 70");
		tree0.put(50); tree0.put(25); tree0.put(75);
		tree0.put(12); tree0.put(37); tree0.put(62);
		tree0.put(87); tree0.put(30); tree0.put(70);

		System.out.println("\n\ttree0.serialize(): " + tree0.serialize());
		System.out.println("\n\ttree0.height(): " + tree0.height());
		System.out.println("\n\ttree0.isBalanced(): " + tree0.isBalanced());


//------------------------------------------------------------------------------------
//TREE1


		System.out.println("\n\n\ntree1 tests");
		System.out.println("\tput 10, 5, 37, 45, 40, 12, 5, 4, 3, 2, 19, 23:  ");
		tree1.put(10); tree1.put(5); tree1.put(37); tree1.put(45);
		tree1.put(40); tree1.put(12); tree1.put(5); tree1.put(4); tree1.put(3); tree1.put(2);
		tree1.put(19); tree1.put(23); tree1.put(6);
		
		// // System.out.println("\ntesting inOrderTraversal");
		// // String t1InOrderTrav = tree1.inOrderTraversal();
		// // String t1Serialize = tree1.serialize();
		// // System.out.println("tree1 in Order Traversal: " + t1InOrderTrav);
		// // System.out.println("tree1 serialize: " + t1Serialize);

		System.out.println("\ntesting height");		
		System.out.println("\ttree1 height: " + tree1.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree1 balance: " + tree1.isBalanced());

//---------------------------------------------------------------------------------------------
//TREE2
		System.out.println("\n\n\ntree2 tests");
		System.out.println("\tput 10, 5, 37, 45, 40, 19, 5, 2, 12, 23, 20, 6:  ");
		tree2.put(10); tree2.put(5); tree2.put(37); tree2.put(45);
		tree2.put(40); tree2.put(19); tree2.put(5); tree2.put(2);
		tree2.put(12); tree2.put(23); tree2.put(20); tree2.put(6);
		// System.out.println("tree2 in Order Traversal: " + tree2.inOrderTraversal());
		// System.out.println("tree2 serialize: " + tree2.serialize());
		System.out.println("\ntesting height");
		System.out.println("\ttree2 height: " + tree2.height());

		System.out.println("testing balance");
		System.out.println("\ttree2 balance: " + tree2.isBalanced());

		System.out.println("testing traversals");
		System.out.println("\ttree2 in Order Traversal: " + tree2.inOrderTraversal());
		System.out.println("\ttree2 serialize: " + tree2.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree2 = tree2.reverse();  // BST or BST_Inter
		System.out.println("\ttree2.serialize:  " + tree2.serialize());
		System.out.println("\tnewTree2.reverse: " + newTree2.serialize());

		//!!!!!!!!!!!!!!!!!!!!!!!! test delete 6 !!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println("\ntesting delete");
		System.out.println("\tdelete(6)"); tree2.delete(6);
		System.out.println("\ttree2.serialize(): " + tree2.serialize());


//---------------------------------------------------------------------------------------------
//TREE3
		System.out.println("\n\n\ntree3 tests");
		System.out.println("\tput 10");
		tree3.put(10);

		System.out.println("\ntesting height");
		System.out.println("\ttree3 height: " + tree3.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree3 balance: " + tree3.isBalanced());

		System.out.println("\ntesting traversals");
		System.out.println("\ttree3 in Order Traversal: " + tree3.inOrderTraversal());
		System.out.println("\ttree3 serialize: " + tree3.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree3 = tree3.reverse();  // BST or BST_Inter
		System.out.println("\ttree3.serialize:  " + tree3.serialize());
		System.out.println("\tnewTree3.reverse: " + newTree3.serialize());

		System.out.println("\ntesting delete");
		System.out.println("\ttree3.delete(10)"); tree3.delete(10);
		System.out.println("\ttree3.serialize:  " + tree3.serialize());
		
//---------------------------------------------------------------------------------------------
//TREE3Half
		System.out.println("\n\n\ntree3Half tests");
		System.out.println("\tput 10, 5");
		tree3Half.put(10); tree3Half.put(5);

		System.out.println("\ntesting height");
		System.out.println("\ttree3Half height: " + tree3Half.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree3Half balance: " + tree3Half.isBalanced());

		System.out.println("\ntesting traversals");
		System.out.println("\ttree3Half in Order Traversal: " + tree3Half.inOrderTraversal());
		System.out.println("\ttree3Half serialize: " + tree3Half.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree3Half = tree3Half.reverse();  // BST or BST_Inter
		System.out.println("\ttree3Half.serialize:  " + tree3Half.serialize());
		System.out.println("\tnewTree3half.reverse: " + newTree3Half.serialize());

		System.out.println("\ntesting delete");
		System.out.println("\ttree3Half.delete(10)"); tree3Half.delete(10);
		System.out.println("\ttree3Half.serialize:  " + tree3Half.serialize());

//---------------------------------------------------------------------------------------------
//TREE3Quarter
		System.out.println("\n\n\ntree3Quarter tests");
		System.out.println("\tput 10");
		tree3Quarter.put(10); tree3Quarter.put(5); tree3Quarter.put(20);

		System.out.println("\ntesting height");
		System.out.println("\ttree3Quarter height: " + tree3Quarter.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree3Quarter balance: " + tree3Quarter.isBalanced());

		System.out.println("\ntesting traversals");
		System.out.println("\ttree3Quarter in Order Traversal: " + tree3Quarter.inOrderTraversal());
		System.out.println("\ttree3Quarter serialize: " + tree3Quarter.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree3Quarter = tree3Quarter.reverse();  // BST or BST_Inter
		System.out.println("\ttree3Quarter.serialize:  " + tree3Quarter.serialize());
		System.out.println("\tnewTree3Quarter.reverse: " + newTree3Quarter.serialize());

		System.out.println("\ntesting delete");
		System.out.println("\ttree3Quarter.delete(10)"); tree3Quarter.delete(10);
		System.out.println("\ttree3Quarter.serialize:  " + tree3Quarter.serialize());


//---------------------------------------------------------------------------------------------
//TREE4
		

		System.out.println("\n\n\ntree4 tests");
		tree4.put(50); tree4.put(25); tree4.put(75); tree4.put(60);
		tree4.put(40); tree4.put(80); tree4.put(2); tree4.put(1);
		tree4.put(17); tree4.put(41); tree4.put(98); tree4.put(79);
		tree4.put(55); tree4.put(31); tree4.put(72); 
		
		
		System.out.println("\ttesting height");		
		System.out.println("\ttree4 height: " + tree4.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree4 balance: " + tree4.isBalanced());

		System.out.println("\ntesting traversals");
		System.out.println("\ttree4 in Order Traversal: " + tree4.inOrderTraversal());
		System.out.println("tree4 serialize: " + tree4.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree4 = tree4.reverse();  // BST or BST_Inter
		System.out.println("\ttree4.serialize:  " + tree4.serialize());
		System.out.println("\tnewTree4.reverse: " + newTree4.serialize());

//---------------------------------------------------------------------------------------------
//TREE5
		System.out.println("\n\n\ntree5 tests");

		System.out.println("\nempty tree");

		System.out.println("\ntesting contains");
		System.out.println("\ttree5.contains(0): " + tree5.contains(0));
		//System.out.println("\ttree5.contains(null): " + tree5.contains(null));
		System.out.println("\ntesting delete");
		System.out.println("\ttree5.delete(0)");  tree5.delete(0);

		System.out.println("\ntesting height");
		System.out.println("\ttree5 height: " + tree5.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree5 balance: " + tree5.isBalanced());

		System.out.println("\ntesting traversals");
		System.out.println("\ttree5 in Order Traversal: " + tree5.inOrderTraversal());
		System.out.println("\ttree5 serialize: " + tree5.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree5 = tree5.reverse();  // BST or BST_Inter
		System.out.println("\ttree5.serialize:  " + tree5.serialize());
		System.out.println("\tnewTree5.reverse: " + newTree5.serialize());

//---------------------------------------------------------------------------------------------
//TREE6
		

		System.out.println("\n\ntree6 tests");
		System.out.println("\ntesting put");
		tree6.put(183); tree6.put(435); tree6.put(22); tree6.put(672);
		tree6.put(3); tree6.put(5); tree6.put(211); tree6.put(43);
		tree6.put(18); tree6.put(61); tree6.put(68); tree6.put(778);
		tree6.put(67); tree6.put(31); tree6.put(983); 

		tree6.put(53); tree6.put(235); tree6.put(12); tree6.put(62);
		tree6.put(403); tree6.put(85); tree6.put(2); tree6.put(123);
		tree6.put(137); tree6.put(41); tree6.put(98); tree6.put(79);
		tree6.put(45); tree6.put(341); tree6.put(732); 

		System.out.println("\ntesting contains");
		System.out.println("\ttree6.contains(0): " + tree6.contains(0));
		System.out.println("\ttree6.contains(211): " + tree6.contains(211));
		System.out.println("\ttree6.contains(1000): " + tree6.contains(1000));
		System.out.println("\ttree6.contains(183): " + tree6.contains(183));
		
		System.out.println("\ntesting height");		
		System.out.println("\ttree4 height: " + tree6.height());

		System.out.println("\ntesting balance");
		System.out.println("\ttree4 balance: " + tree6.isBalanced());

		System.out.println("\ntesting reverse");
		BST_Inter newTree6 = tree6.reverse();  // BST or BST_Inter
		System.out.println("\ttree6.serialize:  " + tree6.serialize());
		System.out.println("\tnewTree6.reverse: " + newTree6.serialize());
		System.out.println("\tsize:  " + tree6.getSize());

		System.out.println("\ntesting delete");
		// System.out.println("\tdelete(2): ");  tree6.delete(2);
		// System.out.println("\tdelete(983): ");  tree6.delete(983);
		// System.out.println("\ttree6.serialize: "+ tree6.serialize());//"  " + tree6.serialize());
		// System.out.println("\tsize:  " + tree6.getSize());

		// System.out.println("\tdelete(3): ");  tree6.delete(3);
		// System.out.println("\tdelete(45): ");  tree6.delete(45);
		// System.out.println("\ttree6.serialize:  "+ tree6.serialize());//+ tree6.serialize());
		// System.out.println("\tsize:  " + tree6.getSize());

		System.out.println("\tdelete(61): ");  tree6.delete(61);
		// System.out.println("\tdelete(235): ");  tree6.delete(235);
		// System.out.println("\tdelete(435): "); tree6.delete(435);
		System.out.println("\ttree6.serialize:  "+ tree6.serialize());// + tree6.serialize());
		System.out.println("\tsize:  " + tree6.getSize());

		System.out.println("\tdelete(22)"); tree6.delete(22);
		System.out.println("\ttree6.serialize:  "+ tree6.serialize());
		System.out.println("\tsize:  " + tree6.getSize());

		System.out.println("\tdelete(435)"); tree6.delete(435);
		System.out.println("\ttree6.serialize:  "+ tree6.serialize());
		System.out.println("\tsize:  " + tree6.getSize());


		//!!!!!!!!!!!!!!!!!!!!!! test out when deleting root !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1

		//System.out.println("\tsize:  " + tree0.getSize());
		//System.out.println("\troot:  " + tree0.getRoot());
		//System.out.println("\tdelete(): " + delete());
		//System.out.println("");

	//---------------------------------------------------------------------------------------------
	//TREE7

		System.out.println("\n\ntree7 tests");
		System.out.println("\nput 10, 5, 2, 7, 25, 15, 30, 17");
		tree7.put(10); tree7.put(5); tree7.put(2); tree7.put(7);
		tree7.put(25); tree7.put(15); tree7.put(30); tree7.put(17);

		System.out.println("\n\ttree7.serialize(): " + tree7.serialize());
		System.out.println("\n\tdelete(10)");  tree7.delete(10);
		System.out.println("\n\ttree7.serialize(): " + tree7.serialize());
		System.out.println("\n\tdelete(5)");  tree7.delete(5);
		System.out.println("\n\ttree7.serialize(): " + tree7.serialize());
		System.out.println("\n\tdelete(25)"); tree7.delete(25);
		System.out.println("\n\ttree7.serialize(): " + tree7.serialize());

	//--------------------------------------------------------------------------------------
		//tree 8

		System.out.println("\n\n tree8 tests");
		System.out.println("\tput 5, 1, 10");
		tree8.put(5); tree8.put(1); tree8.put(10);

	System.out.println("\n\ttree8.serialize(): " + tree8.serialize());
		System.out.println("\n\tdelete(1)"); tree8.delete(1);
		System.out.println("\n\ttree8.serialize(): " + tree8.serialize());

		System.out.println("\n\t put 1, delete 10");
		tree8.put(1); tree8.delete(10);
				System.out.println("\n\ttree8.serialize(): " + tree8.serialize());



	//
		//tree9
		System.out.println("\n\ntree9");
		// tree9.put(1); tree9.put(2); tree9.put(3); tree9.put(4);
		tree9.put(11); tree9.put(10); tree9.put(9); tree9.put(8);
		System.out.println("\n\t tree9.serialize(): " + tree9.serialize());


	}
}
