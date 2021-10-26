package cs1501_p4;

import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class NetAnalysis implements NetAnalysis_Inter{

	AdjacencyList adjList; //an adjacency list to represent the graph
	// AdjNode[] verticies;

	//read in the contents of the graph
	public NetAnalysis(String fileName){
		fillAdjacencyList(fileName);
	}

	public ArrayList<Integer> lowestLatencyPath(int u, int w){
		//run Dijkstra's algorithm to find the minimum weighted path form u to w
		MyDijkstra newDijkstra = new MyDijkstra(adjList, u, w);

		//this arraylist will hold the vertexes in the order we visited them from u to w
		ArrayList<Integer> uwPath = new ArrayList<Integer>();

		//add the vertexes along the path we found form u to w
		uwPath.add(w); //add the starting vertex
		int x = w;
		while(newDijkstra.vertexPath[x] != u){ //add the vertexes we visited
			uwPath.add(0, newDijkstra.vertexPath[x]);
			x = newDijkstra.vertexPath[x]; //the next vertex we add is the one we used to get to the current vertex

			if(x == -1){  //if the next entry the vertexPath is -1, return null because the path can't be completed (doesn't exist)
				return null;
			}
		}
		uwPath.add(0, u); //add the ending

		//return the path we constructed
		return uwPath;
	}

	
	public int bandwidthAlongPath(ArrayList<Integer> p) throws IllegalArgumentException{
		boolean found = false;
		int minBandWidth = -1; // minBandWidth will keep track of the lowest valued bandwidth (of an edge) along this path
							   // initialized to -1 so that will compile
									//but during the first iteration of the 4 minBandWidth is reset to be the bandWidth of the first edge in the path

		if(p == null){
			throw new IllegalArgumentException("Invalid Path: path is null"); //throw exception
		}

		//find the edge for each vertex
		for(int i=0; i < p.size()-1; i++){
			
			int currVertex = p.get(i); //p[i] is the startingPoint vertex of an edge

			//search for the endPoint vertex of the same edge
			AdjNode endPoint = adjList.vertexArray[currVertex]; //endPoint will traverse down the list of neighbors to find the correct end point of the desired edge (if one exists)
			while(endPoint != null && !found){
				if(endPoint.getVertexNumber() == p.get(i+1)){ //look to see if the neighbor on this edge matches the next vertex in the arrayList
					if(i == 0){
						minBandWidth = endPoint.getBandWidth(); //initialize minBandWidth if it is the first iteration of the for loop
					}
					else if(endPoint.getBandWidth() < minBandWidth){ //if not first iteration of for loop, compare to see if the current edges bandWidth should be a new min
						minBandWidth = endPoint.getBandWidth();
					}
					break; //break from while loop and start next iteration of for loop
				}
				endPoint = endPoint.getNext();
			}

			if(endPoint == null){
				throw new IllegalArgumentException("Invalid Path: " + p); //throw exception
			}
			
		}

		//also need to throw an exception if the path becomes invalid at any point during the traversal
		return minBandWidth;
	}

	public boolean copperOnlyConnected(){
		//DFS creates a spanning tree that only consists of copper edges
		//if an edge is optical, it is skipped during this DFS
		DFS checkCopperConnected = new DFS(adjList, 0);
		int vertexCount = checkCopperConnected.count();

		//true, if the count for the # of verticies in the spanning tree created by DFS is the same as the cardinality of verticies in the adjacency list
		if(vertexCount == adjList.getVertexCard()){
			return true;
		}

		return false;
	}

	
	public boolean connectedTwoVertFail(){
		ArticulationPointSearch spanningBackedgeTree = new ArticulationPointSearch(adjList, 0);
		boolean containsArticulationPoint = spanningBackedgeTree.isArticulationPointFound();
		//if the number of verticies visited during the articulation point search does not equal the total number of verticies
			//return false because the graph isn't connected to begin with
		if(spanningBackedgeTree.count() != adjList.vertexArray.length){
			return false;
		}
		//if it contains an articulation point, then could fail if remove 2 verticies
		return !containsArticulationPoint;
	}

	
	public ArrayList<STE> lowestAvgLatST(){
//create MST using prim's if connected
		MyPrim newPrim = new MyPrim(adjList, 0);

		//check if connected first
		if(newPrim.visitedTrue != newPrim.visited.length){
			return null;
		}

		//create an ArrayList to store edges
		ArrayList<STE> edgeList = new ArrayList<STE>();
		//add edges from Prim MST into edgeList
		AdjNode[] primMST = newPrim.vertexPath;
		for(int i=0; i < primMST.length; i++){
			STE newEdge = new STE(primMST[i].getEdgeNeighbor(), primMST[i].getVertexNumber());
			edgeList.add(newEdge);
		}

		return edgeList;
	}


	//-----------------------------------------------------------------
	//fillAdjacencyList

	private void fillAdjacencyList(String graphFile){
		try{
			Scanner graphReader = new Scanner(new File(graphFile));

			int adjListLength = graphReader.nextInt(); //collect the value for the # of verticies
			adjList = new AdjacencyList(adjListLength); //create a new adjacency list with length == # of verticies
			// verticies = new AdjNode[adjListLength]; //create an array storing all the verticies

			while(graphReader.hasNext()){
				// gather input specifying what each edge of the graph looks like
				//each edge represents a bidirectional connection
				int startPoint = graphReader.nextInt();
				int endPoint = graphReader.nextInt();
				String cableType = graphReader.next();
				int bandWidth = graphReader.nextInt();
				int cableLength = graphReader.nextInt();

				//create new nodes that will represent the connection a vertex has with it's neighbor
				AdjNode newNeighbor1 = new AdjNode(startPoint, endPoint, cableType, bandWidth, cableLength);
				AdjNode newNeighbor2 = new AdjNode(endPoint, startPoint, cableType, bandWidth, cableLength);
				//the graph is bidirectional, so add each vertex to the neighbor list in the adjacency list at the index that matches the other endpoint
				adjList.addVertex(startPoint, newNeighbor2);
				adjList.addVertex(endPoint, newNeighbor1);
			}

		}
		catch(FileNotFoundException e){
			System.out.println("file not found -> " + graphFile);
		}
	}

}