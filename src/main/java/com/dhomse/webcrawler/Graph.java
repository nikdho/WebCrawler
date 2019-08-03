package com.dhomse.webcrawler;

//Note: The type "T" must have a ".toString" method to work

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;


public class Graph <T> {
    private ArrayList < GraphNode < T >> vertices = new ArrayList < > ();

    public Graph(T[] keys) {
        for (int i = 0; i < keys.length; ++i)
            this.addVertex(new GraphNode<>(keys[i]));
    }

    public Graph() { }

    public int addEdge(GraphNode < T > from, GraphNode < T > to) {
        int index1 = vertices.indexOf(from), index2 = vertices.indexOf(to);
        if (index1 == -1 || index2 == -1)
            return -1; // Says 'I didn't find the vertices's
        vertices.get(index1).addEdge(to);
        return 1; // Says 'I found the vertices'
    }

    public int addEdgeSym(GraphNode < T > from, GraphNode < T > to) {
        int index1 = vertices.indexOf(from), index2 = vertices.indexOf(to);
        if (index1 == -1 || index2 == -1)
            return -1;
        vertices.get(index1).addEdge(to);
        vertices.get(index2).addEdge(from);
        return 1;
    }

    public int addEdge(GraphNode < T > from, GraphNode < T > to, Double weight) {
        int index1 = vertices.indexOf(from), index2 = vertices.indexOf(to);
        if (index1 == -1 || index2 == -1)
            return -1;
        vertices.get(index1).addEdge(to, weight);
        return 1;
    }

    public int addEdgeSym(GraphNode < T > from, GraphNode < T > to, Double weight) {
        int index1 = vertices.indexOf(from), index2 = vertices.indexOf(to);
        if (index1 == -1 || index2 == -1)
            return -1; // Says 'I didn't find the vertices'
        vertices.get(index1).addEdge(to, weight);
        vertices.get(index2).addEdge(from, weight);
        return 1; // Says 'I found the vertices'
    }

    public void addVertex(GraphNode < T > newVertex) {
        if (vertices.indexOf(newVertex) == -1)
            vertices.add(newVertex);
        else
            System.out.println("You tried adding the same vertex " + newVertex.getKey().toString() + " twice!");
    }

    public String toString() {
        String tmp = "";
        for (int i = 0; i < vertices.size(); i++)
            tmp = tmp + vertices.get(i).toString();
        return tmp;
    }

    public ArrayList < GraphNode < T >> getVertices() {
        return vertices;
    }
    public GraphNode < T > getVertex(int i) {
        return vertices.get(i);
    }
    public int vertexIndex(GraphNode < T > vertex) {
        return vertices.indexOf(vertex);

    }

    public GraphNode < T > BFS(GraphNode <T> startV, T searchKey) {
        Queue <GraphNode<T>> faj = new PriorityBlockingQueue<>();
        ArrayList <GraphNode<T>> discovered = new ArrayList<>();
        discovered.add(startV);
        GraphNode <T> v;
        faj.add(startV);
        while (faj.peek() != null) {
            v = faj.remove();
            if (v.getKey().equals(searchKey))
                return v;
            ArrayList <GraphNode<T>> newOnes;
            newOnes = startV.getAdjacentEdges();
            int count;
            for (int j = 0; j < newOnes.size(); j++) {
                count = 0;
                for (int i = 0; i < discovered.size(); i++) {
                    if (discovered.get(i) == newOnes.get(j))
                        count++;
                }
                if (count == 0) {
                    discovered.add(newOnes.get(j));
                    faj.remove(newOnes.get(j));
                }
            }
        }
        return null;
    }
    public GraphNode < T > DFS(GraphNode < T > v, T searchKey) {
        ArrayList < GraphNode < T >> disc = new ArrayList < GraphNode < T >> ();
        Stack < GraphNode < T >> s = new Stack < GraphNode < T >> ();
        s.push(v);
        while (s.peek() != null) {
            v = s.pop();
            if (v.getKey().equals(searchKey))
                return v;
            for (int i = 0; i < disc.size(); i++)
                if (v == disc.get(i))
                    break;
                else if (i == disc.size() - 1 && disc.get(i) != v) {
                    disc.add(v);
                    ArrayList < GraphNode < T >> adjEdges = v.getAdjacentEdges();
                    for (int j = 0; j < adjEdges.size(); j++)
                        s.push(adjEdges.get(j));
                }
        }
        return null;
    }
    /*public int[] DijDist(GraphNode<T> source, GraphNode<T> end) {
    	ArrayList<GraphNode<T>> store = new ArrayList<GraphNode<T>>();
    	int[] dist = new int[vertices.size()];
    	int[] prev = new int[vertices.size()];
    	for(int i=0;i<dist.length;i++) {
    		dist[i] = Integer.MAX_VALUE;
    		store.add(test.getVertex(i));
    	}
    	dist[test.vertexIndex(source)] = 0;
    	int u = dist[0];
    	while(store.size()!=0) {
    	for(int i=1;i<dist.length;i++) {
    		if(dist[i]<u)
    			u=dist[i];
    	}
    	store.remove(test.getVertex(u));

    	}
    }*/
    public String toStringWithWeights() {
        StringBuilder total = new StringBuilder();
        for (GraphNode < T > vertex: vertices) {
            total.append(vertex.toString() + " is connected to:\t");
            ArrayList < GraphNode < T >> adjList = vertex.getAdjacentEdges();
            for (int i = 0; i < adjList.size(); ++i) {
                total.append("\"" + adjList.get(i).toString() + "\" (weight " + vertex.getWeight(adjList.get(i)) + ")");
                if (i < adjList.size() - 1)
                    total.append(", ");
                if (i == adjList.size() - 2)
                    total.append("and ");
            }
            total.append("\n\n");
        }
        return total.toString();
    }
    public static void main(String[] args) {
        Integer[] test = {
                3,
                121,
                90
        };
        Graph < Integer > testGraph = new Graph<>(test);
        testGraph.addEdgeSym(testGraph.getVertex(0), testGraph.getVertex(1), 34.0);
        testGraph.addEdgeSym(testGraph.getVertex(1), testGraph.getVertex(2), 72.0);
        System.out.print(testGraph.BFS(testGraph.getVertex(0), test[1]));
    }
}