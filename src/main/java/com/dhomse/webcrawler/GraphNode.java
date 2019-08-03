package com.dhomse.webcrawler;

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Enumeration;

public class GraphNode<T> {

    private T key;
    private Hashtable<GraphNode<T>, Double> weightedNeighbors = new Hashtable<>();

    GraphNode(T data) {
        this.key = data;
    }

    public void addEdge(GraphNode<T> neighbor) {
        if (weightedNeighbors.containsKey(neighbor)) {
            System.out.printf("Updating existing edge weight from %s to %s to 1.0.", key.toString(), neighbor.key.toString());
        }
        weightedNeighbors.put(neighbor, 1.0);
    }

    public void addEdge(GraphNode<T> neighbor, Double weight) {
        if (weightedNeighbors.containsKey(neighbor))
            System.out.println("Updating existing edge weight from "
+ key.toString() + " to " + neighbor.key.toString() + " to " + weight.toString() + ".");
        weightedNeighbors.put(neighbor, weight);
    }

    T getKey() {
        return key;
    }

    public String toString() {
        return key.toString();
    }

    public ArrayList<GraphNode<T>> getAdjacentEdges() {
        Enumeration<GraphNode<T>> edges = weightedNeighbors.keys();
        ArrayList<GraphNode<T>> adjacentEdges = new ArrayList<>();
        while(edges.hasMoreElements()) {
            adjacentEdges.add(edges.nextElement());
        }
        return adjacentEdges;
    }
    public Double getWeight(GraphNode<T> t) {
    	return weightedNeighbors.get(t);
    }
}

