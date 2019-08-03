package algorithms;
// Note: The type "T" must have a ".toString" method to work

import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Enumeration;

public class GraphNode<T> { // T is the type of the key
    private T key = null;
    private Hashtable<GraphNode<T>, Double> weightedNeighbors = new Hashtable<>();

    public GraphNode(T data) {
        this.key = data;
    }

    public void addEdge(GraphNode<T> neighbor) {
        if (weightedNeighbors.containsKey(neighbor))
            System.out.println("Updating existing edge weight from "
+ key.toString() + " to " + neighbor.key.toString() + " to 1.0.");
        weightedNeighbors.put(neighbor, 1.0);
    }

    public void addEdge(GraphNode<T> neighbor, Double weight) {
        if (weightedNeighbors.containsKey(neighbor))
            System.out.println("Updating existing edge weight from "
+ key.toString() + " to " + neighbor.key.toString() + " to " + weight.toString() + ".");
        weightedNeighbors.put(neighbor, weight);
    }

    public T getKey() {
        return key;
    }

    public String toString() {
        return key.toString();
    }

    public ArrayList<GraphNode<T>> getAdjacentEdges() {
        Enumeration<GraphNode<T>> edges = weightedNeighbors.keys();
        ArrayList<GraphNode<T>> returnMe = new ArrayList<>();
        while(edges.hasMoreElements())
            returnMe.add(edges.nextElement());
        return returnMe;
    }
    public Double getWeight(GraphNode<T> t) {
    	return weightedNeighbors.get(t);
    }
}

