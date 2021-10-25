/*
can either test with char or int, can't do both at same time (but can test separately)
*/

public class testPutContains{
	public static void main(String [] args){
		BST tree0 = new BST();
		BST tree1 = new BST();
		

		System.out.println("testing put");
		//System.out.println("root:  " + tree0.getRoot());
		System.out.println("\tsize:  " + tree0.getSize());
		System.out.println("\tputting a");
		tree0.put('a');
		System.out.println("\tsize:  " + tree0.getSize());
		System.out.println("\troot:  " + tree0.getRoot());
		System.out.println("\tputting b");
		tree0.put('b');
		System.out.println("\tsize:  " + tree0.getSize());
		System.out.println("\troot:  " + tree0.getRoot());
		System.out.println("\tputting c");
		tree0.put('c');
		System.out.println("\tsize:  " + tree0.getSize());
		System.out.println("\troot:  " + tree0.getRoot());
		System.out.println("\tputting b");
		tree0.put('b');
		System.out.println("\tsize:  " + tree0.getSize());
		System.out.println("\troot:  " + tree0.getRoot());
		System.out.println("\tputting x");
		tree0.put('x');
		System.out.println("\tsize:  " + tree0.getSize());
		System.out.println("\troot:  " + tree0.getRoot());
		System.out.println("\tputting null");
		tree0.put(null);
		System.out.println("\tsize: " + tree0.getSize());


		
		
		System.out.println("testing contains");
		System.out.println("\tcontains a: " + tree0.contains('a'));
		System.out.println("\tcontains b: " + tree0.contains('b'));
		System.out.println("\tcontains c: " + tree0.contains('c'));
		System.out.println("\tcontains z: " + tree0.contains('z'));
		System.out.println("\tcontains null: " + tree0.contains(null));


		// System.out.println("\n\ntree1 tests");
		// System.out.println("\tcontains 10:  " + tree1.contains(10));
		// System.out.println("\tput 10, 5, 37, 45, 40, 12, 5, 2:  ");
		// tree1.put(10); tree1.put(5); tree1.put(37); tree1.put(45);
		// tree1.put(40); tree1.put(12); tree1.put(5); tree1.put(2);
		// System.out.println("\tcontains 10, 5, 37, 45, 40, 12, 5, 2, 19, 23:  " + tree1.contains(10) + tree1.contains(5) 
		// 															   + tree1.contains(37) + tree1.contains(45) 
		// 															   + tree1.contains(40) + tree1.contains(12)
		// 															   + tree1.contains(5) + tree1.contains(2)
		// 															   + tree1.contains(19) + tree1.contains(23));
		// System.out.println("\tsize:  " + tree1.getSize());
		// System.out.println("\troot:  " + tree1.getRoot());
		// System.out.println("\tput 19, 23");
		// tree1.put(19); tree1.put(23);
		// System.out.println("\tcontains 19, 23:  " + tree1.contains(19) + tree1.contains(23));
		// System.out.println("\tsize:  " + tree1.getSize());
		// System.out.println("\troot:  " + tree1.getRoot());

		
		

		//System.out.println("");
	}
}


/*
//TREE4
		

		System.out.println("\n\ntree4 tests");
		tree4.put(50); tree4.put(25); tree4.put(75); tree4.put(60);
		tree4.put(40); tree4.put(80); tree4.put(2); tree4.put(1);
		tree4.put(17); tree4.put(41); tree4.put(98); tree4.put(79);
		tree4.put(55); tree4.put(31); tree4.put(72); 
		
		
		System.out.println("testing height");		
		System.out.println("\ttree4 height: " + tree4.height());

		System.out.println("testing balance");
		System.out.println("\ttree4 balance: " + tree4.isBalanced());

*/