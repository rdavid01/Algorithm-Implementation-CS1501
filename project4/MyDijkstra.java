/*the only paart of this file that I adapted form the authors code 
  was the check to make sure no edges are negative.
  The rest is all my work.
*/
package cs1501_p4;

/*the only paart of this file that I adapted form the authors code 
  was the check to make sure no edges are negative.
  The rest is all my work.
*/

public class MyDijkstra {
    public double[] distance;          // distTo[v] = distance  of shortest sourceIndex->v path
    public int[] vertexPath;    // vertexpath[v] = trail of verticies visited along shortest sourceIndex->v path
    public boolean[] visited;      // visited[v] = whether or not this vertex has been visited, as soon as vertex is visited, we have found the shortest path
    AdjNode minEdge; //the min latency edge
    int minEdgeIndex; //the start vertex of minEdge

    public MyDijkstra(AdjacencyList adjList, int sourceIndex, int endPoint) {
        AdjNode[] verticies = adjList.vertexArray;
        //make sure no negative edges in graph
        for(int i=0; i < verticies.length; i++){
            AdjNode curr = verticies[i];
            while(curr != null){
                if (curr.getLatency() < 0){  //make sure that no negative weights, throw exception if there is
                throw new IllegalArgumentException("edge has negative weight, Dijkstra's will not work correctly");
                }
                curr = curr.getNext();
            }
        }

        //distance array and vertex path array should be same length as # of verticies
        distance = new double[adjList.getVertexCard()];
        vertexPath = new int[adjList.getVertexCard()];
        visited = new boolean[adjList.getVertexCard()];
        visited[sourceIndex] = true; //starting at the source, should be true

        //initialize the distance array with positive infinity values
        for (int i = 0; i < adjList.getVertexCard(); i++){
            distance[i] = Double.POSITIVE_INFINITY;  //no distance is being tracked until a vertex is seen for the first time
            //default value of boolean array is false
            vertexPath[i] = -1; //vertexPath has all -1 values to represent nothing being stored
        }                                          
        distance[sourceIndex] = 0.0; //the source index should be 0, because it is our start
        AdjNode enumVertex = verticies[sourceIndex]; //the vertex that will get enumerated

        getMinWeightedPath(adjList, enumVertex, verticies);
    }

    private void getMinWeightedPath(AdjacencyList adjList, AdjNode enumVertex, AdjNode[] verticies){
        //create a weighted shortest path tree
            
        //enumerate new neighbors, add to pq based on latency
        while(enumVertex != null){
            if(!visited[enumVertex.getVertexNumber()]){ //enumerate the edges of neighbors we havent visited yet
                if(distance[enumVertex.getVertexNumber()] > distance[enumVertex.getEdgeNeighbor()] + enumVertex.getLatency()){
                    //update distance array
                    distance[enumVertex.getVertexNumber()] = distance[enumVertex.getEdgeNeighbor()] + enumVertex.getLatency();
                        
                    //add to vertexPath array if we added to distance array
                    //copy the vertex that we used to get to this vertex into minEdgeIndex cell of vertexpath
                    vertexPath[enumVertex.getVertexNumber()] = enumVertex.getEdgeNeighbor();
                }
            }
            enumVertex = enumVertex.getNext(); //move to the next edge in the list
        }

        //find the index of the lowest distance Entry
        Double lowestDistanceEntry = Double.POSITIVE_INFINITY;
        int lowestDistanceIndex = -1;
        for(int i=0; i < distance.length; i++){
            if(!visited[i]){
                if(distance[i] < lowestDistanceEntry){
                    lowestDistanceEntry = distance[i];
                    lowestDistanceIndex = i;
                }
            }
        }
        //if the lowest distance entry has an initialized value, update visit enumVertex
            //if the lowestDistanceIndex comes back as -1, we should not visit anywhere new
        if(lowestDistanceIndex != -1){     
            enumVertex = verticies[lowestDistanceIndex];
            visited[lowestDistanceIndex] = true;
        }
        
        //only make another call if enumVertex is not null
            //if enumVertex == null, we have found our minimum weighted path and are done
                //when enumVertex is null, this means we have moved thourgh all the verticies in a column of our adjList
                //and we did not find a neighbor we can traverse to, so we ended up on a null reference at the bottom of the list
        if(enumVertex != null){
            getMinWeightedPath(adjList, enumVertex, verticies);
        }
    }

}