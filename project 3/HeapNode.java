public class HeapNode{  
		private char let;
		private HeapPQ priceHeap;
		private HeapPQ mileageHeap;
		private HeapNode right;
		private HeapNode down;

		public HeapNode(){
			this.right = null;
			this.down = null;
		}

		public HeapNode(char let){
			this.let = let;
			this.priceHeap = null;
			this.mileageHeap = null;
			this.right = null;
			this.down = null;
		}

		public char getLet(){
			return let;
		}

		public HeapPQ getPriceHeap(){
			return priceHeap;
		}

		public HeapPQ getMileageHeap(){
			return mileageHeap;
		}

		public HeapNode getRight(){
			return right;
		}

		public HeapNode getDown(){
			return down;
		}

		public void setPriceHeap(HeapPQ ph){
			priceHeap = ph;
		}

		public void setMileageHeap(HeapPQ mh){
			mileageHeap = mh;
		}

		public void setRight(HeapNode r){
			right = r;
		}

		public void setDown(HeapNode d){
			down = d;
		}

	}//end HeapNode