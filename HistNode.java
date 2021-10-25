public class HistNode{  
		private char let;
		private int frequency;
		private HistNode right;
		private HistNode down;

		public HistNode(){
			this.right = null;
			this.down = null;
		}

		public HistNode(char let){
			this.let = let;
			this.frequency = 0;
			this.right = null;
			this.down = null;
		}

		public char getLet(){
			return let;
		}

		public int getFrequency(){
			return frequency;
		}

		public HistNode getRight(){
			return right;
		}

		public HistNode getDown(){
			return down;
		}

		public void increaseFrequency(){
			frequency++;
		}

		public void setRight(HistNode r){
			right = r;
		}

		public void setDown(HistNode d){
			down = d;
		}

	}//end HistNode