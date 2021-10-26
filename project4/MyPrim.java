package cs1501_p4;

public class MyPrim{

    public AdjNode[] vertexPath;    // vertexpath[v] = trail of verticies visited along shortest sourceIndex->v path
    public boolean[] visited;      // visited[v] = whether or not this vertex has been visited, as soon as vertex is visited, we have found the shortest path    
    private HeapPQ lowLatency = new HeapPQ();    // priority queue of vertices
	public int visitedTrue = 0; //visitedTrue keeps track of the number of verticies we have visited

	public MyPrim(AdjacencyList adjList, int sourceIndex){
		AdjNode[] verticies = adjList.vertexArray;

		//visited array should be same length as # of verticies
        //vertexPath array should be same length as # of edges (if graph is connected # of edges will be v-1, only run MyPrim if graph is connected)
		vertexPath = new AdjNode[adjList.getVertexCard()-1];
        visited = new boolean[adjList.getVertexCard()];
        visited[sourceIndex] = true; //starting at the source, should be true
        visitedTrue++;

        //create an MST
   		AdjNode enumVertex = verticies[sourceIndex];
   		int counter = 0;

   		buildMST(adjList, enumVertex, verticies, counter);
   	}

   	private void buildMST(AdjacencyList adjList, AdjNode enumVertex, AdjNode[] verticies, int counter){
       while(enumVertex != null){
	            if(!visited[enumVertex.getVertexNumber()]){ //enumerate the edges of neighbors we havent visited yet
	                lowLatency.add(enumVertex); //add the edge to the prioirty queue, will be organized based on lowest latency
	            }
	            enumVertex = enumVertex.getNext(); //move to the next edge in the list
	        }

	    while(lowLatency.getLogSize() > 0){ //break once we find an edge we can follow
	        if(!visited[lowLatency.heap[0].getVertexNumber()]){
		        //add the lowest latency edge to the MST
		        vertexPath[counter] = lowLatency.heap[0];
			    //visit the neighbor along the lowest latency edge
			    enumVertex = verticies[lowLatency.heap[0].getVertexNumber()]; //set enumVertex to the vertex we are visiting
			    visited[enumVertex.getEdgeNeighbor()] = true;  //getEdgeNeighbor returns the number of the vertex whose column we moved enumVertex to
			    visitedTrue++;
		        //pop the edge we just added from the priority queue
		        lowLatency.pop();
		        break;
			}
		    //pop the edge we just added from the priority queue
		    lowLatency.pop();
	    }
   	 
	    counter++; //increment counter, because we will be at the next position in the vertexPath array during the next call

	    //only make another call if the enumVertex isn't null
	    	//if enumvertex == null, then we are fnished creaing the MST
	    		//when enumVertex is null, this means we have moved thourgh all the verticies in a column of our adjList
                //and we did not find a neighbor we can traverse to, so we ended up on a null reference at the bottom of the list
	    if(enumVertex != null){
	    	buildMST(adjList, enumVertex, verticies, counter);
	    }
	}
	
}