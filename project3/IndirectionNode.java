public class IndirectionNode{  
		private char let;
		private int index;
		private IndirectionNode right;
		private IndirectionNode down;

		public IndirectionNode(){
			this.right = null;
			this.down = null;
		}

		public IndirectionNode(char let){
			this.let = let;
			this.index = -1;
			this.right = null;
			this.down = null;
		}

		public char getLet(){
			return let;
		}

		public int getIndex(){
			return index;
		}

		public IndirectionNode getRight(){
			return right;
		}

		public IndirectionNode getDown(){
			return down;
		}

		public void setIndex(int i){
			index = i;
		}

		public void setRight(IndirectionNode r){
			right = r;
		}

		public void setDown(IndirectionNode d){
			down = d;
		}

	}//end IndirectionNode
