package cs1501_p4;

public class AdjacencyList{
	public int vertexCardinality;
	public AdjNode[] vertexArray; //create an array to access vertecies, the index # represents the vertex of the corresponding #
								   //each idnex of vertexArray will store a linked list of neighbor verticies

	//an array of linked lists will be used to store data about the graph
		//the array will be of length equal to the number of vertexes
	public AdjacencyList(int inputLength){
		vertexCardinality = inputLength;
		vertexArray = new AdjNode[inputLength];
	}

	public void addVertex(int startPoint, AdjNode endpoint){ //startpoint tells us which index of vertexArray to access
														//endpoint is the new neighbor that we want to add to the list at this index
		AdjNode curr = vertexArray[startPoint];

		if(curr != null){ //if there is already at least one neighbor for this vertex, must add endpoint onto the end of the neighbor list
			while(curr.getNext() != null){
				curr = curr.getNext();
			}
			curr.setNext(endpoint); //add startPoint's new neighbor to its list of neghbor verticies
		}
		else{ //if the index is empty, curr becomes the first neighbor
			vertexArray[startPoint] = endpoint;
		}
	}

	public int getVertexCard(){
		return vertexCardinality;
	}
}
