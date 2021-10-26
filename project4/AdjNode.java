package cs1501_p4;

public class AdjNode{
	private AdjNode next;
	private int vertexNumber;
	private int edgeNeighbor;
	private String cableType;
	private int bandwidth;
	private int cableLength;

	public AdjNode(int newVertexNumber, int newEdgeNeighbor, String newCableType, int newBandwidth, int newCableLength){
		next = null;
		vertexNumber = newVertexNumber;
		edgeNeighbor = newEdgeNeighbor;
		cableType = newCableType;
		bandwidth = newBandwidth;
		cableLength = newCableLength;
	}

	public AdjNode getNext(){
		return next;
	}

	public int getVertexNumber(){
		return vertexNumber;
	}

	public int getEdgeNeighbor(){
		return edgeNeighbor;
	}

	public String getCableType(){
		return cableType;
	}

	public int getBandWidth(){
		return bandwidth;
	}

	public int getCableLength(){
		return cableLength;
	}

	// latency is calculated by dividing the length of the cable by the speed at which data can be sent along that cable
	public double getLatency(){
		double cableLengthDouble = cableLength;
		double latency = (cableLengthDouble/200000000);  //latency if cable type is optical

		if(cableType.equals("copper")){ //change the result if the connection is copper
			latency = (cableLengthDouble/230000000);
		}
			
		return latency;
	}

	public void setNext(AdjNode newAdjNode){
		next = newAdjNode;
	}

	public void setVertexNumber(int newVertexNumber){
		vertexNumber = newVertexNumber;
	}

	public void setEdgeNeighbor(int newEdgeNeighbor){
		edgeNeighbor = newEdgeNeighbor;
	}

	public void setCableType(String newCableType){
		cableType = newCableType;
	}

	public void setBandwidth(int newBandwidth){
		bandwidth = newBandwidth;
	}

	public void setCableLength(int newCableLength){
		cableLength = newCableLength;
	}

}