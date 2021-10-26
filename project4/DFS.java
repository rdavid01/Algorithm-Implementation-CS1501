//adapted from text author's code for DepthFirstSearch.java
//the authors for that code were:
	// Robert Sedgewick
	// Kevin Wayne

package cs1501_p4;

public class DFS{
    private boolean[] visited;    // visited[v] = is there an s-v path?
    private int count;           // number of vertices connected to s

    public DFS(AdjacencyList adjList, int source) {
        visited = new boolean[adjList.getVertexCard()];
        deapthFirstSearch(adjList, source);
    }

    // depth first search from v
    private void deapthFirstSearch(AdjacencyList adjList, int vertex) {
        count++;
        visited[vertex] = true; //mark that we have visited this vertex

        AdjNode enumerator = adjList.vertexArray[vertex];
        //enumerate neighbors
        while(enumerator != null) {
            if (!visited[enumerator.getVertexNumber()] && enumerator.getCableType().equals("copper")){  //dont do dfs on a vertex that we have already visited
                                                                             //only dfs if the cable is copper (checking for copper only connected)
                deapthFirstSearch(adjList, enumerator.getVertexNumber());
            }
            enumerator = enumerator.getNext();
        }
    }

    //Returns the number of vertices connected to the source 
    public int count() {
        return count;
    }

}