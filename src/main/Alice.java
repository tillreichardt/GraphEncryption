package main;
/**
 * @author Till Reichardt 
 */
import java.util.Random;

import classes.Graph;
import classes.List;
import classes.Vertex;

public class Alice {
    private final String DELIMITER = "/";
    private int geheimZahl;

    public Alice(int zahl){
        geheimZahl = zahl;
    }

    public void changeID(Graph g){
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while(vertexList.hasAccess()){
            Vertex vx = vertexList.getContent(); 
            String id = vx.getID().split(DELIMITER)[0] + DELIMITER + vx.getID().split(DELIMITER)[2];
            vx.setID(id); 
            vertexList.next();
        }
    }
    
    public void graphVerschluesseln(Graph g){
        fillGraph(g);
        sumNeighbourGraph(g);
        changeID(g);
        zeigeGraph(g);
    }

    // Schritt 1
    private void fillGraph(Graph g) {
        int[] inhalt = new int[sizeOfGraph(g)];
        Random rand = new Random();
        int sum = 0;
        for (int i = 0; i < inhalt.length-1; i++) {
            int randomNumber = rand.nextInt(-geheimZahl*inhalt.length, geheimZahl*inhalt.length); // I = [-gz*anzahlKnoten; gz*anzahlKnoten] z.b. gz = 42 und anzahlKnoten = 13; I = [-546; 546]
            inhalt[i] = randomNumber;
            sum += randomNumber;
        }
        inhalt[inhalt.length-1] = geheimZahl-sum; // alle bekommen random Zahl und letzter füllt auf, damit Summe = Geheimzahl ergibt
        
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        int i = 0;
        while(vertexList.hasAccess()){
            // Knoten benennen z.B. V0/...
            vertexList.getContent().setID(vertexList.getContent().getID() + DELIMITER + inhalt[i++]);
            vertexList.next();
        }

    }

    // Schritt 2
    private void sumNeighbourGraph(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while (vertexList.hasAccess()) {
            // Zahl im aktuellen Knoten
            int sum = getNumber(vertexList.getContent().getID());
            List<Vertex> nList = g.getNeighbours(vertexList.getContent());
            nList.toFirst();
            // Nachbarn summieren
            while (nList.hasAccess()) {
                sum += getNumber(nList.getContent().getID());
                nList.remove();
            }
            // Knoten benennen z.B. V0/123/...
            vertexList.getContent().setID(vertexList.getContent().getID() + DELIMITER + sum);
            vertexList.next();
        }
    }

    // gibt random Zahl aus Schritt 1
    private int getNumber(String input) {
        return Integer.parseInt(input.split(DELIMITER)[1]);
    }
    
    private int sizeOfGraph(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        int i = 0;
        while (vertexList.hasAccess()) {
            i++;
            vertexList.next();
        }
        return i;
    }

    public void zeigeGraph(Graph g) {
        List<Vertex> vertexList = g.getVertices();
        vertexList.toFirst();
        while (vertexList.hasAccess()) {
            System.out.println(vertexList.getContent().getID());
            List<Vertex> nList = g.getNeighbours(vertexList.getContent());
            nList.toFirst();
            while (nList.hasAccess()) {
                System.out.println("- " + nList.getContent().getID());
                nList.remove();
            }
            vertexList.next();
        }
    }
}