
public class BST<T extends Comparable<T>> implements BST_Inter<T>{

	private int size;
	private BTNode root;

	public BST(){ // is this needed?
		size = 0;	
		root = null;
	}

	public T getRoot(){
		//doesn't work if root == null
		return (T) this.root.getKey();
	}

	public BTNode getRootRef(){
		return this.root;
	}

	public int getSize(){
		if(size == 0)
			return -1;
		return this.size;
	}

	

	//-----------------------------------------------------------------------------------
	//put(T key)
	
	/* 
	The put() function places a new node with a key chosen by the user in the BST.  
	The key is a generic value, so any data type can be used and compared, as long as
	the same data type is used for all the nodes in a tree.
	Null arguments are not valid for this function.
	If the size of the BST calling put is 0, a new root node is created.
	Otherwise, findInsert() will be called.  findInsert() searches through the BST
	*/
	public void put(T key){
		BTNode newInsertNode = new BTNode<>(key);  
		
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument is null
		}
		else if(!(size == 0)){  //only try to insert using findInsert if BST already has at least one node
			findInsert(key, newInsertNode, root); 
		}
		else{  //make a new root node if the BST is empty
			root = newInsertNode;
			size++;
		}
		
	}//end put


	/*
	findInsert() searches through the BST and adds a new node in the correct place.
	If the key value < curr node's value:
		and the current node's left child is null, a new node with the key value will be added
		otherwise, findInsert is called on the current Node's left subtree.
	IF the key value > curr node's value:
		and the current node's right child is ull, a new node with the key value will be added
		otherwise, findInsert is called on the current Node's right subtree.

	*/
	private void findInsert(T key, BTNode keyNode, BTNode curr){ 
		T currVal = (T) curr.getKey();

		if(key.compareTo(currVal) < 0){ // key of insert node is less than the current node key, look at left child
			if(curr.getLeft() == null){
				curr.setLeft(keyNode); //insert new node and increase size if at null reference
				size++;
			}
			else{
				findInsert(key, keyNode, curr.getLeft());  //if not at null reference, have to travel in down left subtree 
			}
		}
		else if(key.compareTo(currVal) > 0){ //key of insert node is > current node key
			if(curr.getRight() == null){
				curr.setRight(keyNode); //insert new node and increase size if at null reference
				size++;
			}
			else{
				findInsert(key, keyNode, curr.getRight()); //in not at null reference, must travel down right subtree
			}
		}
		//else keyVal == node, nothing happens
		//do nothing if the key already exists
	}//end findInsert()
	
	//end of put()
	//-----------------------------------------------------------------------------------
	//contains(T key)
	
	/*
	The contains() function searches through the BST to see if a node with a specific key exists in the tree.
	It returns true if the key exits and false if it is not present.
	Null arguments are not valid for this function.
	If the tree is empty, the function will return false
	Otherwise, a call to checkCotnains will be made to actually search through the BST for the desired key.
	*/
	public boolean contains(T key){
		if(key == null){
			throw new IllegalArgumentException("argument is null"); //throw exception if null argument
		}
		else if(size == 0){ //BST cannot contain anything if it is empty
			return false;
        }
		return checkContains(key, this.root);  //start search at root

	}

	/*
	checkContains() travels thorugh the BST, comparing the desired key to the current node's key in order
	to find if a node with such a key is present.  returns true if found and false if not present.
	check contains recursively moves to the left or right child to search a subtree depending on whether 
	the current node's key is greater than or less than the desired key respectively.  
	If the address of the next child to be checked is null, then a value of false will be returned to reflect
	that the key is not contained within the BST.
	*/
	private boolean checkContains(T key, BTNode curr){
		T currVal = (T) curr.getKey();

		if(key.compareTo(currVal) == 0){ //if the key is equal to the current node's key, then the search was successful
			return true;
		}
		else if(key.compareTo(currVal) < 0){ //key < current node's key, need to search in left subtree
			if(curr.getLeft() == null){
				return false;  //if the left child of curr is null, the search is at an end, return false to show key was not found
			}
			else{
				return checkContains(key, curr.getLeft()); //if left child not null, continue search w/ recursive call using left child as an argument
			}
		}
		else if(key.compareTo(currVal) > 0){ //key > current node key, need to search right subtree
			if(curr.getRight() == null){
				return false; //right child of curr is null, search is at an end, return fasle to show unsuccessful
			}
			else{
				return checkContains(key, curr.getRight()); //right child not null, continue search w/ recursive call using right child as argument
			}
		}
		return false; // check later
	}//end checkContains

	//end of contains()
	//-----------------------------------------------------------------------------------
	//delete()

	/*
	delete() removes a node with a specific key, if such a node exsists in the BST.
	null is not a vlaid argument for this function.
	delete only tries to find a target node to delete if the size is > 0.
	NOTE: target will be a term I use to refer to the node that we want to delete
	*/
	public void delete(T key){
		if(key == null){
			throw new IllegalArgumentException("argument is null");// throw exception if argument null
		}
		else if (size != 0){  //only try to delete if BST contains something
			T rootKey = (T) root.getKey();

			if (key.compareTo(rootKey) == 0){ //key == root, special case
				performDeleteRoot(root); //delete root and decrease size
				size--;
			}
			else if(key.compareTo(rootKey) > 0){  //key > root key, search right subtree
				findDeleteTarget(key, root, root.getRight());
			}
			else if (key.compareTo(rootKey) < 0){ //key < root key, search left subtree
				findDeleteTarget(key, root, root.getLeft());
			}

		}

	}

	/*
	searches through the BST until key is found.  
	decreases size of BST if found, nothing happens if not found
	*/
	private void findDeleteTarget(T key, BTNode parent, BTNode curr){
		boolean lcNull = (curr.getLeft() == null);
		boolean rcNull = (curr.getRight() == null);
		T parentKey = (T) parent.getKey();
		T currKey = (T) curr.getKey();


		if(key.compareTo(currKey) == 0){
			performDelete(key, parent, curr);  // if key found, decrease size and pass both the node with the target key and it's parent into performDelete()
			size--;
		}
		else if(key.compareTo(currKey) < 0){  //search left subtree, stop search if left child is a null reference
			if(!lcNull){
				findDeleteTarget(key, curr, curr.getLeft());
			}
		}
		else if(key.compareTo(currKey) > 0){  //search right subtree, stop search if right child is null reference
			if(!rcNull){
				findDeleteTarget(key, curr, curr.getRight());
			}
		}

	}// end findDeleteTarget

	/*
	special case of deleteing root
	deltion can be preformed by reassigning root if the original root has at least one null node
	otherwise, must search for replacement node.  replacemetn node will be the least significant key in the right subtree
	*/
	private void performDeleteRoot(BTNode rootNode){
		BTNode rootLeft = rootNode.getLeft();
		BTNode rootRight = rootNode.getRight();
		if(rootLeft == null){  // if one of root's children is a null reference, root can be deleted by reassigning root to be the other child
			root = rootRight;//replace with right node
		}
		else if(rootRight == null){ //if both children are null, then the BST will become empty and root will point to a null reference
			root = rootLeft;//replace with left node
		}
		else{ //neither node is null
			rootReplacement(rootNode, rootRight);  //recursively search for least sig key in right subtree

		}
	}//end performDeleteRoot

	/*
	if at least one child is a null reference, deletion can be preformed by replacing the target as the new child of the target's parent 
	otherwise, must search for a replacement node.  replacement will be thw least significant key in the right subtree
	*/
	private void performDelete(T key, BTNode parent, BTNode target){ //pass both parent and target, can replace target if have a reference to its parent
		BTNode targetLeft = target.getLeft();
		BTNode targetRight = target.getRight();
		T parentKey = (T) parent.getKey();
		if(targetLeft == null){  //make new child for target's parent by using the right child of target
			if(key.compareTo(parentKey) < 0){ //target key < parent, replacement node will be parent's new left child
				parent.setLeft(targetRight);
			}
			if(key.compareTo(parentKey) > 0){ //target key > parent, replacement will be parent's new right child
				parent.setRight(targetRight);
			}
		}
		else if(targetRight == null){ //make new child for target's parent by using the left child of traget
			if(key.compareTo(parentKey) < 0){ //target key < parent, rplacement will be parent's new left child
				parent.setLeft(targetLeft);
			}
			if(key.compareTo(parentKey) > 0){ //target key > parent, replacement will be parent's new right child
				parent.setRight(targetLeft);
			}
		}
		else{ //neither node is null

			findReplacement(key, parent, target, target, targetRight); //look for replacement, replacement will be the least sig key in right subtree
		}
	}//end performDelete

	/*
	replaces root if neither target child is null
	finds least sig key in right subtree of root
	*/
	private void rootReplacement(BTNode parent, BTNode curr){
		
		boolean lcNull = (curr.getLeft() == null);
		if(!lcNull){  //recursively search for the min key in the right subtree
			rootReplacement(curr, curr.getLeft());
		}
		//after found min, two options 
		//1. minNode is not the right child of the target
		else if (curr != parent.getRight()){  
			BTNode minNode = curr; //the least significant key in the right subtree
			BTNode origRootRight = root.getRight();
			BTNode origRootLeft = root.getLeft();  //store references to the original roots children

			parent.setLeft(minNode.getRight());
			parent.setLeft(minNode.getRight());
			
			root = minNode; //make the minNOde the new root

			root.setRight(origRootRight);
			root.setLeft(origRootLeft); //give new root the old root's children
		}
		//2. minNode is the right child of the target
		//have to treat as special case, otherwise will be circular references between minNode and root
			//would cause infinite recursion and stack overflow if tried to recurse
		else{ //curr == parent.getRight
			BTNode minNode = curr; //the least sig key in the right subtree, happens to be root's right child
			BTNode origRootLeft = parent.getLeft(); //store root's left child
			root = curr; //replace old root with minNode
			root.setLeft(origRootLeft); //give minNode the old root's left children,  can just keep it's right children since it was old root's right node
		}
	}

	private void findReplacement(T key, BTNode targetParent, BTNode target, BTNode parent, BTNode curr){  //finds a node with which to replace the deleted node
		boolean lcNull = (curr.getLeft() == null);
		if(!lcNull){
			findReplacement(key, targetParent, target, curr, curr.getLeft()); //recursivelys earch for the min key in the right subtree
		}
		//after found min, two options
		//1. minNode is not the right child of target
		else if(curr != parent.getRight()){  
			T targetParentKey = (T) targetParent.getKey();
			BTNode minNode = curr;  //least sig key in right subtree
			BTNode targetRight = target.getRight();
			BTNode targetLeft = target.getLeft(); //store target's children

			if(key.compareTo(targetParentKey) < 0){ //target key < parent, minNode will be parent's new right child
				parent.setLeft(minNode.getRight()); //make minNode's parent gets minNodes right child

				targetParent.setLeft(minNode); //make minNode target's parents new left child
				minNode.setLeft(targetLeft);
				minNode.setRight(targetRight); //give minNodes target's chidren
			}
			if(key.compareTo(targetParentKey) > 0){ //target key > parent, minNode will be parent's new right child
				parent.setLeft(minNode.getRight());  //minNode's parent gets minNodes right child

				targetParent.setRight(minNode); //make minNode target's parent's new right child
				minNode.setLeft(targetLeft);
				minNode.setRight(targetRight);//give minNode target's children
			}
		}
		//2. minNode is right child of target
		//have to treat as special case, otherwise will be circular references between minNode and root
			//would cause infinite recursion and stack overflow
		else{ //curr == parent.getRight()
			BTNode minNode = curr; //least sig key in right subtree
			
			T targetParentKey = (T) targetParent.getKey();
			BTNode targetParentLeft = targetParent.getLeft(); //not needed for function
			BTNode targetLeft = parent.getLeft();
			BTNode targetRight = parent.getRight(); //store target children

			if(key.compareTo(targetParentKey) < 0){ //target key < parent 
				targetParent.setLeft(minNode);
				minNode.setLeft(targetLeft); //give targetparent minNode as child, 

			}
			if(key.compareTo(targetParentKey) > 0){ //target key > parent
				targetParent.setRight(minNode);
				minNode.setLeft(targetLeft);
			}
		}
	}//end findReplacement

	//end of delete()
	//-----------------------------------------------------------------------------------
	//height()

	/*
	The height of a BST is 0 if it is empty, and 1 if there is only a root node.
	height() returns the height of a BST by returning the greatest length from the root to a leaf node.
	*/
	public int height(){
		int height = 0;
		if(size != 0){
			height = descend(root, 1);
		}

		return height;
	} //end height

	/*
	descend() recursively travels from the root to each leaf node, and counts the number of levels deep each path is.
	Two variables maxLeft and MaxRight keep track of the maximum number of levels in the right and left subtrees of 
	whichever node is currntly being accessed.  The right max and left max are compared, and whichever is greater will be 
	returned to the previous call of descend, which becomes the new right or left max.  subTree heights are compared like
	this all the way up the tree until the right and left subtrees of the root are compared.  At this point, maxleft and 
	maxRight should contain the length of the longest path from root to leaf on each side of root.  Comparing these values
	to find the greater will give the height of the BST.  If maxLeft and maxRight are equal at any point in the recursive 
	process, maxRight is chosen becuase it will not make a difference which value is used.
	*/
	private int descend(BTNode curr, int depth){
		boolean lcNull = (curr.getLeft() == null);
		boolean rcNull = (curr.getRight() == null);
		int maxLeft = 0;
		int maxRight = 0;

		if(!lcNull){ //recursively search left until run into null reference
			maxLeft = descend(curr.getLeft(), depth+1); //search left subtree path, increase depth to reflect going down another level
		}

		if(!rcNull){ //recursively search right until run into null reference
			maxRight = descend(curr.getRight(), depth+1); //search right subtree path, increase depth to refelct going down another level
		}

		if(lcNull && rcNull){ //can't go left or right, at a leaf node
			//only want to enter if both children null 
			return depth; //Base Case:  leaf node reached, return depth of this leaf node
		}

		if(maxLeft > maxRight){ //can compare number of levels from each subtree, return whichever value is greater.  Doing this for each path will return the length of the longest path, that is the height.
			return maxLeft;  //Base Case:  greter depth comes from left branch
		}
		else{
			return maxRight;  //Base Case:  greater depth comes from right branch
		}
	}//end descend

	//end height()
	//-----------------------------------------------------------------------------------
	//isBalanced()

	/*
	isBalanced() checks whether the BST is height balanced.  A BST is height balanced if the left and
	right subtrees of all nodes differ by no more than 1.
	An empty tree is always considered balanced.
	*/
	public boolean isBalanced(){
		//int height = 0;
		if(size != 0){
			return checkBalance(root);
		}
		return true;
	}

	/*
	checkBalance() checks 3 things to determine whether a tree is height balanced.  For each node it determines
	(1)whether the left subtree is height balanced, (2)whether the right subtree is height balanced, and (3)whether the 
	absolute value of the difference between the heights of the left and right subtrees is <= 1.
	It starts by recursively traveling to the left most node, then when return to a previous call checkBalance() determines
	whether the three conditions are satisfied in order to be considered height balanced.
	*/
	private boolean checkBalance(BTNode curr){
		boolean lcNull = (curr.getLeft() == null);
		boolean rcNull = (curr.getRight() == null);
		boolean leftSubBalanced = true;
		boolean rightSubBalanced = true;
		int leftHeight = 0;
		int rightHeight = 0;

		if(!lcNull){ //recursively search left until null reference
			leftSubBalanced = checkBalance(curr.getLeft()); //go to bottom of left branch
			leftHeight = descend(curr.getLeft(), 1);  // check height of branch from current node
		}

		if(!rcNull){ //recursively search right until null ref
			rightSubBalanced = checkBalance(curr.getRight()); //go to bottom of right branch
			rightHeight = descend(curr.getRight(), 1);  //check height of branch from current node
		}

		boolean absValBtwnOne = ((leftHeight - rightHeight >= -1) && (leftHeight - rightHeight <= 1)); // a value i used for testing to store whether the difference in heights of subtrees has an absolute value of <= 1
																										//this could replace part of the conditional below, but I chose not to

		if(rightSubBalanced && leftSubBalanced && (leftHeight - rightHeight >= -1) && (leftHeight - rightHeight <= 1)){
			return true;  //Base Case:  both sub trees of this node are balanced and the difference between the heights of both brances is <= 1.  
		}
		else{
			return false;  //Base Case:  all three criteria are not met
		}
	}


	//-----------------------------------------------------------------------------------
	//inOrderTraversal()

	/*
	inOrderTraversal() travels thorughout the entire BST and adds all the keys of the nodes to a stringBuilder.
	The traversal follows the pattern of printing the left most node, then that nodes parent, then restarts that pattern on the right subtree.
	Information tracking the path through the BST is added to a stringBuilder which is returned as a String.
	ex.
			10
		   /  \
		  5    20
		take the path of:  5, 10, 20
	*/
	public String inOrderTraversal(){
		StringBuilder travPath = new StringBuilder("");

		if(size != 0){
			traverse(root, travPath);
			travPath.deleteCharAt(travPath.length()-1);  //delete extra : at end of SB
							//instead of having own conditional if( length() > 0), put inside here
		//!!!! could change when append : and then delete at positoin 0 !!!!!!!!!!!
		}

		return travPath.toString();
	}

	/*
	traverse() actually moves through the BST.  
	traverse() travels left in the BST until the next left child is null (this effectively finds the node with the minimum value key).
	That left most node is added to the traversal path, which is stored in a strinbBuilder.
	Then try to recurse right and restart the process.
	When the traversal cannot move to a new left or right child, the process picks up after the recursivecall to the left child.
	The node in the previous recursive function call is added (this is the parent node of the last node added).
	This process repeats through the entire tree.

	Each node is added to the stringBuilder passed in from inOrderTraversal so that the path through the BST can be tracked and returned.
	*/
	private void traverse(BTNode curr, StringBuilder travPath){
		if(curr.getLeft() != null){
			traverse(curr.getLeft(), travPath);  //recuse down left subtree
		}
		travPath.append(curr.getKey().toString() + ":"); //add key to stringuilder

		if(curr.getRight() != null){
			traverse(curr.getRight(), travPath);  //recurse down right subtree
		}
	}

	//-----------------------------------------------------------------------------------
	// serialize()

	/*
	serialize() travels thorughout the entrie BST and adds all the keys of the nodes to a stringBuilder and whether they are a root, leaf, or interior node.
	null reerences of interior nodes are also added to the stringbuilder.
	The traversal follows the pattern of adding the parent node, then the left node, then the right node.  This process follows this pattern for each subtree.
	ex.
			10
		   /  \
		  5   20
		takes the path:  R(10), L(5), L(20)
	*/
	public String serialize(){
		StringBuilder travPath = new StringBuilder("");

		if(size != 0){
			travPath.append("R(" + root.getKey() + ")"); //append root data to SB
			boolean lcNull = (root.getLeft() == null);
			boolean rcNull = (root.getRight() == null);

			if(!lcNull){
				preOrderTrav(root.getLeft(), travPath);//recurse left of root
			}
			else if(!rcNull){
				travPath.append(",X(NULL)");  //add a null reference if root has only one child
			}

			if(!rcNull){
				preOrderTrav(root.getRight(), travPath);//recurse right of root
			}
			else if(!lcNull){
				travPath.append(",X(NULL)");  //add a null reference if root has only one child
			}
		}

		return travPath.toString();
	}

	/*
	preOrderTrav() actually moves through the BST and adds keys to the stringBuilder.
	follows this pattern through each subtree:
		add root node
		add left node
		add right node
	*/
	private void preOrderTrav(BTNode curr, StringBuilder travPath){
		boolean lcNull = (curr.getLeft() == null);
		boolean rcNull = (curr.getRight() == null);

		if(lcNull  && rcNull){  //if leafNode
			travPath.append(",L(" + curr.getKey() + ")"); //add the node
		}
		else{ //if interior node
			travPath.append(",I(" + curr.getKey() + ")");//add the node
			
			if(lcNull){ //if left child null 
				travPath.append(",X(NULL)");
			}
			else{ //traverse left subtree
				preOrderTrav(curr.getLeft(), travPath);
			}

			if(rcNull){ //if right child null
				travPath.append(",X(NULL)");
			}
			else{ //traverse right sub tree
				preOrderTrav(curr.getRight(), travPath);
			}
		}

	}
	//-----------------------------------------------------------------------------------
	//reverse()

	/*
	In a new BST, reverses the ordering of a trees nodes so that nodes with lower keys are on the left of a root, and noes with higher keys are on the right of a root.
	*/
	public BST_Inter<T> reverse(){ 
		BST newTree = new BST();

		if(size != 0){
			newTree = reverseTree(this);
			newTree.size = this.size;
		}

		return newTree; //return (BST_Inter) newTree;  

	}//end reverse

	/*
	returns a reversed copy of the original tree
	*/
	private BST reverseTree(BST origTree){
		BST revBST = new BST ();  

		revBST.root = new BTNode(this.getRoot());  //make the root for the new BST, this will be the only node in the same position as the original BST

		reverseNodes(origTree.getRootRef() , revBST.getRootRef());

	 	return revBST;
	} //end reverseTree

	/*
	If a node is a left child of its parent in the original tree, make it a right child of the same node in the reversed tree.
	If a node is a right child of tis parent in the original tree, make it a left child of the same node in the reversed tree.
	*/
	private void reverseNodes(BTNode origNode, BTNode revNode) {
		if (origNode.getLeft() != null) { //do the folloeing until next left child is a null reference
	 		BTNode ln = new BTNode(origNode.getLeft().getKey());  //make a new node with the same key as the original node
	  		revNode.setRight(ln);  //since this came from the left side of the original tree, put it in the right side of the reversed tree
	  	 	reverseNodes(origNode.getLeft(), revNode.getRight()); //recurse, take the next left node (want to get smaller child) of orig tree, use reference to the newly added reverse tree node (want to add next smaller child as the next right node in reversed tree)
	 	}

		if (origNode.getRight() != null) { //do the following until next right child is null reference
			BTNode rn = new BTNode(origNode.getRight().getKey());  //make a new node with same key as original node
	  		revNode.setLeft(rn);// origNode came rom left branch of original tree, put it in the right branch of the reversed tree
	  	 reverseNodes(origNode.getRight(), revNode.getLeft()); // get references to next smallest node in original tree and where the where you want to add next smallest node in reversed tree
	 	}
	}//end reverseNodes

	//end reverse()


}//end class
