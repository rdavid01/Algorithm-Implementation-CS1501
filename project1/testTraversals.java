/*
can either test with char or int, can't do both at same time (but can test separately)
*/

public class testTraversals{
	public static void main(String [] args){
		BST tree0 = new BST();
		BST tree1 = new BST();
		BST tree2 = new BST(); //only has a root node
		BST tree3 = new BST();

		// System.out.println("testing put");
		// //System.out.println("root:  " + tree0.getRoot());
		// System.out.println("\tsize:  " + tree0.getSize());
		// System.out.println("\tputting a");
		// tree0.put('a');
		// System.out.println("\tsize:  " + tree0.getSize());
		// System.out.println("\troot:  " + tree0.getRoot());
		// System.out.println("\tputting b");
		// tree0.put('b');
		// System.out.println("\tsize:  " + tree0.getSize());
		// System.out.println("\troot:  " + tree0.getRoot());
		// System.out.println("\tputting c");
		// tree0.put('c');
		// System.out.println("\tsize:  " + tree0.getSize());
		// System.out.println("\troot:  " + tree0.getRoot());
		// System.out.println("\tputting b");
		// tree0.put('b');
		// System.out.println("\tsize:  " + tree0.getSize());
		// System.out.println("\troot:  " + tree0.getRoot());
		// System.out.println("\tputting x");
		// tree0.put('x');
		// System.out.println("\tsize:  " + tree0.getSize());
		// System.out.println("\troot:  " + tree0.getRoot());
		
		
		// System.out.println("testing contains");
		// System.out.println("\tcontains a: " + tree0.contains(a));
		// System.out.println("\tcontains b: " + tree0.contains(b));
		// System.out.println("\tcontains c: " + tree0.contains(c));
		// System.out.println("\tcontains d: " + tree0.contains(d));

		// System.out.println("\ntesting inOrderTraversal");
		// String t0InOrderTrav = tree0.inOrderTraversal();
		// System.out.println("tree0 in Order Traversal: " + t0InOrderTrav);

//------------------------------------------------------------------------------------
//TREE1


		System.out.println("\n\ntree1 tests");
		// System.out.println("\tcontains 10:  " + tree1.contains(10));
		System.out.println("\tput 10, 5, 37, 45, 40, 12, 5, 2:  ");
		tree1.put(10); tree1.put(5); tree1.put(37); tree1.put(45);
		tree1.put(40); tree1.put(12); tree1.put(5); tree1.put(2);
		// System.out.println("\tcontains 10, 5, 37, 45, 40, 12, 5, 2, 19, 23:  " + tree1.contains(10) + tree1.contains(5) 
																	   // + tree1.contains(37) + tree1.contains(45) 
																	   // + tree1.contains(40) + tree1.contains(12)
																	   // + tree1.contains(5) + tree1.contains(2)
																	   // + tree1.contains(19) + tree1.contains(23));
		// System.out.println("\tsize:  " + tree1.getSize());
		// System.out.println("\troot:  " + tree1.getRoot());
		System.out.println("\tput 19, 23");
		tree1.put(19); tree1.put(23);
		// System.out.println("\tcontains 19, 23:  " + tree1.contains(19) + tree1.contains(23));
		// System.out.println("\tsize:  " + tree1.getSize());
		// System.out.println("\troot:  " + tree1.getRoot());


		System.out.println("\ntesting inOrderTraversal");
		System.out.println("tree1 in Order Traversal: " + tree1.inOrderTraversal());
		System.out.println("tree1 serialize: " + tree1.serialize());
		// System.out.println("deleting 5");
		// tree1.delete(5);
		// System.out.println("tree1 serialize: " + tree1.serialize());
		// System.out.println("deleting 10");
		// tree1.delete(10);
		// System.out.println("tree1 serialize: " + tree1.serialize());
		// System.out.println("deleting 55");
		// tree1.delete(55);
		// System.out.println("tree1 serialize: " + tree1.serialize());
		// System.out.println("deleting 1");
		// tree1.delete(1);
		// System.out.println("tree1 serialize: " + tree1.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree1 = tree1.reverse();
		System.out.println("tree1.serialize:  " + tree1.serialize());
		System.out.println("newTree1.serialize: " + newTree1.serialize());

//---------------------------------------------------------------------------------------------
//TREE2
		// System.out.println("\n\ntree2 tests");
		// System.out.println("\tput 10");
		// tree2.put(10);
		// System.out.println("tree2 in Order Traversal: " + tree2.inOrderTraversal());
		// System.out.println("tree2 serialize: " + tree2.serialize());

//--------------------------------------------------------------------------------------------
//TREE3
		System.out.println("\n\ntesting tree3");
		tree3.put(5); tree3.put(1); tree3.put(10);

		System.out.println("\ntesting inOrderTraversal");
		System.out.println("tree3 in Order Traversal: " + tree3.inOrderTraversal());
		System.out.println("tree3 serialize: " + tree3.serialize());
		// System.out.println("deleting 5");
		// tree1.delete(5);
		// System.out.println("tree3 serialize: " + tree3.serialize());
		// System.out.println("deleting 10");
		// tree1.delete(10);
		// System.out.println("tree3 serialize: " + tree3.serialize());
		// System.out.println("deleting 55");
		// tree3.delete(55);
		System.out.println("tree3 serialize: " + tree3.serialize());
		// System.out.println("deleting 1");
		// tree3.delete(1);
		System.out.println("tree3 serialize: " + tree3.serialize());

		System.out.println("\ntesting reverse");
		BST_Inter newTree3 = tree3.reverse();
		System.out.println("tree3.serialize:  " + tree3.serialize());
		System.out.println("newTree3.reverse: " + newTree3.serialize());
		System.out.println("tree3.put(22): " ); tree3.put(22);
		System.out.println("tree3.serialize:  " + tree3.serialize());
		System.out.println("newTree3.reverse: " + newTree3.serialize());
		
		//System.out.println("");
	}
}

/*
public BST_Inter<T> reverse(){ //BST_Inter<T> or BST?? //interface satys BST_Inter<T>
		//check to see if I change original BST, does reversed BST get changed as well (if it does, then it is not a deep copy)
	BST newTree = getCopyOfTree(this);	
	return (BST_Inter) newTree;

}
	private BST getCopyOfTree(BST oldTree){
		BST revBST = new BST ();  //is this correct way to return a interface type????????

		revBST.root = new BTNode(this.getRoot());
 copy(oldTree.getRootRef() , revBST.getRootRef());
 return revBST;
}

private void copy(BTNode oldNode, BTNode newNode) {
	BTNode ln = new BTNode(oldNode.getLeft().getKey());
	BTNode rn = new BTNode(oldNode.getRight().getKey());

 if (oldNode.getLeft() != null) { 
  newNode.setLeft(ln);
  copy(oldNode.getLeft(), newNode.getLeft()); 
 }

 if (oldNode.getRight() != null) {
  newNode.setRight(rn);
  copy(oldNode.getRight(), newNode.getRight());
 }
}

*/