//adapted from text author's code for DepthFirstSearch.java
//I modified it so that it will search for articulation points
//the authors for that code were:
	// Robert Sedgewick
	// Kevin Wayne
package cs1501_p4;

public class ArticulationPointSearch{
    private boolean[] visited;    // visited[v] = is there an s-v path?
    private int[] numValArr;        // numVal[v] = the numVal of vertex v
    private int[] lowValArr;        // lowVal[v] = the lowVal of vertex v
    private int count;           // number of vertices connected to s
    private boolean articulationPointFound = false;
    private int rootChildren = 0;

   
    public ArticulationPointSearch(AdjacencyList adjList, int source) {
        //set up a variable currNumVal to keep track of the changing numVal as we iterate
        int currNumVal = 0;
        //initialize arrays to appropriate lengths
        numValArr = new int[adjList.getVertexCard()];
        lowValArr = new int[adjList.getVertexCard()];
        visited = new boolean[adjList.getVertexCard()];
        deapthFirstSearch(adjList, source, currNumVal);
    }

    // depth first search from v
    private int deapthFirstSearch(AdjacencyList adjList, int vertex, int currNumVal) {
        numValArr[vertex] = currNumVal; //place the current numVal in the index of the array corresponding to its vertex
        lowValArr[vertex] = currNumVal; //initialize lowVal as the numVal of the current vertex
                         //if there are no edges coming out of this vertex, this will remain the lowVal
        count++;
        //mark that we have visited the vertex
        visited[vertex] = true;

        //neighbor enumeration
        AdjNode enumerator = adjList.vertexArray[vertex]; //a variable to go therough the linked list at the index specified by the current vertex
        while(enumerator != null && !articulationPointFound) { //look at all of vertex's neighbors
            // System.out.println("enumerator.getVertexNumber: " + enumerator.getVertexNumber() + ", enumerator.getEdgeNeighbor(): " + enumerator.getEdgeNeighbor());
            if (!visited[enumerator.getVertexNumber()] ){  //dont do dfs on a vertex that we have already visited
                //if we are at the sourceIndex/root, check how many children it has
                if(numValArr[vertex] == 0){
                    //if the source vertex has more than two children in the spanning tree/backedge graph, it is an articulaiton point
                    rootChildren++;
                    if(rootChildren > 1){
                        articulationPointFound = true;
                    }
                }
                //recurse
                int childLowVal = deapthFirstSearch(adjList, enumerator.getVertexNumber(), ++currNumVal); //++numVal because we want numVal to remain incremented after backtracking too
                //if the child's lowVal is lower than the current vertex's lowVal, update the current vertex's lowVal
                if(childLowVal < lowValArr[vertex]){
                    lowValArr[vertex] = childLowVal;
                }
                //if the child's lowVal is >= the current vertex's numVal, we have found an articulation point
                    //this is for non-root nodes only
                 else if(childLowVal >= numValArr[vertex] && numValArr[vertex] != 0){
                    articulationPointFound = true;
                }
            }
            else{ //create a backedge to a neighbor we have already seen
                //assign lowVal if backedge leads to lower numVal
                if(numValArr[enumerator.getVertexNumber()] < lowValArr[vertex]){
                    lowValArr[vertex] = numValArr[enumerator.getVertexNumber()];
                }
            }

            enumerator = enumerator.getNext();
        }

        return lowValArr[vertex];
    }

    //Returns the number of vertices connected to the source 
    public int count() {
        return count;
    }
    
    //returns whether there is an articulation point in this graph/component
    public boolean isArticulationPointFound(){
        return articulationPointFound;
    }

}