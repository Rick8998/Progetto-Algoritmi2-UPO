package upo.graph.implementation;

import java.util.ArrayList;

public class VertexAdjList {

	private ArrayList<Integer> adjVertex;
	
	public VertexAdjList() {
		
		adjVertex = new ArrayList<Integer>();	
	}

	public ArrayList<Integer> getAdjVertex() {
		return adjVertex;
	}

	public void setAdjVertex(ArrayList<Integer> adjVertex) {
		this.adjVertex = adjVertex;
	}
}
