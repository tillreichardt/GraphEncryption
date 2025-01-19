package main;

import classes.Graph;

/**
 * @author Till Reichardt 
 */
public class Main {
    public static void main(String[] args) {
        int meineGeheimzahl = 69;
        
        Alice alice = new Alice(meineGeheimzahl);
        Bob bob = new Bob();
        
        Graph g = bob.graphExample();       // setMarks...() und bob. ...() müssen übereinstimmen! z.b. bob.graph4()
        bob.setMarksGraphExample(g);   // setMarks...() und bob. ...() müssen übereinstimmen!  z.b. setMarks4(g)

        alice.graphVerschluesseln(g);
        System.out.println("Entschlüsselte Geheimzahl: " + bob.encode(g));
    }   
}
