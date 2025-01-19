package main;

import classes.Edge;
import classes.Graph;
import classes.List;
import classes.Vertex;

/**
 * @author Till Reichardt 
 */
public class Bob {

    private final String DELIMITER = "/";
    private Graph g = new Graph();
    
    public Bob(){
        
    }

    public Graph getGraph(){
        return g;
    }

    public int encode(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        int sum = 0;
        while (vertexList.hasAccess()) {
            if (vertexList.getContent().isMarked()) {
                sum += Integer.parseInt(vertexList.getContent().getID().split(DELIMITER)[1]);
            }
            vertexList.next();
        }
        return sum;
    }


    public void setMarksGraphExample(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while (vertexList.hasAccess()) {
            if ((vertexList.getContent().getID().split(DELIMITER)[0].equals("V5")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V2")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V11"))) {
                vertexList.getContent().setMark(true);
            }
            vertexList.next();
        }
    }

    public void setMarksGraphCycle(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while (vertexList.hasAccess()) {
            if ((vertexList.getContent().getID().split(DELIMITER)[0].equals("V3")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V6")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V9")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V0"))) {
                vertexList.getContent().setMark(true);
            }
            vertexList.next();
        }
    }

    public void setMarksGraphStar(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while (vertexList.hasAccess()) {
            if ((vertexList.getContent().getID().split(DELIMITER)[0].equals("V0"))) {
                vertexList.getContent().setMark(true);
            }
            vertexList.next();
        }
    }

    public void setMarks4(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while (vertexList.hasAccess()) {
            if ((vertexList.getContent().getID().split(DELIMITER)[0].equals("V2")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V5")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V7")) || (vertexList.getContent().getID().split(DELIMITER)[0].equals("V10"))) {
                vertexList.getContent().setMark(true);
            }
            vertexList.next();
        }
    }

    // Graph erstellen
    public Graph graphExample() {
        Vertex[] V = new Vertex[13];
        
        // Vertecies erstellen
        for (int i = 0; i < V.length; i++) {
            V[i] = new Vertex("V" + i);
        }

        Graph g = new Graph();
        // Vertecies hinzugügen
        for (Vertex v : V) {
            g.addVertex(v);
        }

        g.addEdge(new Edge(V[0], V[1], 0));
        g.addEdge(new Edge(V[0], V[4], 0));
        g.addEdge(new Edge(V[0], V[5], 0));
        g.addEdge(new Edge(V[4], V[5], 0));
        g.addEdge(new Edge(V[4], V[9], 0));
        g.addEdge(new Edge(V[5], V[9], 0));
        g.addEdge(new Edge(V[9], V[10], 0));
        g.addEdge(new Edge(V[0], V[10], 0));
        g.addEdge(new Edge(V[1], V[6], 0));
        g.addEdge(new Edge(V[10], V[6], 0));
        g.addEdge(new Edge(V[10], V[11], 0));
        g.addEdge(new Edge(V[6], V[11], 0));
        g.addEdge(new Edge(V[6], V[7], 0));
        g.addEdge(new Edge(V[1], V[2], 0));
        g.addEdge(new Edge(V[2], V[3], 0));
        g.addEdge(new Edge(V[2], V[7], 0));
        g.addEdge(new Edge(V[7], V[8], 0));
        g.addEdge(new Edge(V[3], V[8], 0));
        g.addEdge(new Edge(V[8], V[11], 0));
        g.addEdge(new Edge(V[11], V[12], 0));
        g.addEdge(new Edge(V[12], V[7], 0));

        return g;
    }


    public Graph graphCycle(){
        Vertex[] V = new Vertex[12];
        for (int i = 0; i < V.length; i++) {
            V[i] = new Vertex("V" + i);
        }

        Graph gCycle = new Graph();
        // Vertecies hinzufügen
        for (Vertex v : V) {
            gCycle.addVertex(v);
        }

        // Kanten hinzufügen (Ring)
        for (int i = 0; i < 12; i++) {
            gCycle.addEdge(new Edge(V[i], V[(i + 1) % 12], 0));
        }
        return gCycle;
    }

    public Graph graphStar(){
        Vertex[] V = new Vertex[12];
        for (int i = 0; i < V.length; i++) {
            V[i] = new Vertex("V" + i);
        }

        Graph gStar = new Graph();
        // Vertecies hinzufügen
        for (Vertex v : V) {
            gStar.addVertex(v);
        }

        // Kanten hinzufügen (V0 ist Zentrum)
        for (int i = 1; i < 12; i++) {
            gStar.addEdge(new Edge(V[0], V[i], 0));
        }
        return gStar;
    }

    public Graph graph4(){
        Vertex[] V = new Vertex[12];
        // Vertecies erstellen
        for (int i = 0; i < V.length; i++) {
            V[i] = new Vertex("V" + i);
        }

        Graph gBip = new Graph();
        // Vertecies hinzufügen
        for (Vertex v : V) {
            gBip.addVertex(v);
        }
        
        gBip.addEdge(new Edge(V[0], V[6], 0));
        gBip.addEdge(new Edge(V[0], V[7], 0));
        gBip.addEdge(new Edge(V[1], V[7], 0));
        gBip.addEdge(new Edge(V[1], V[8], 0));
        gBip.addEdge(new Edge(V[2], V[8], 0));
        gBip.addEdge(new Edge(V[2], V[9], 0));
        gBip.addEdge(new Edge(V[3], V[9], 0));
        gBip.addEdge(new Edge(V[3], V[10], 0));
        gBip.addEdge(new Edge(V[4], V[10], 0));
        gBip.addEdge(new Edge(V[4], V[11], 0));
        gBip.addEdge(new Edge(V[5], V[6], 0));
        gBip.addEdge(new Edge(V[5], V[11], 0));

        return gBip;

    }
}