import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();
        List<Edge> listEgdes = new ArrayList<>();
        List<Node> listNodes = new ArrayList<>();

        Node A = new Node('A', "A");
        Node B = new Node('B', "B");
        Node C = new Node('C', "C");
        Node D = new Node('D', "D");
        Node E = new Node('E', "E");
        Node F = new Node('F', "F");
        Node G = new Node('G', "G");

        // Adding node to list
        listNodes.add(A);
        listNodes.add(B);
        listNodes.add(C);
        listNodes.add(D);
        listNodes.add(E);
        listNodes.add(F);
        listNodes.add(G);

        // Adding edge going from A to list
        listEgdes.add(new Edge(A, B, 2));
        listEgdes.add(new Edge(A, C, 1));

        // Adding edge going from B to list
        listEgdes.add(new Edge(B, A, 2));
        listEgdes.add(new Edge(B, C, 2));
        listEgdes.add(new Edge(B, D, 1));
        listEgdes.add(new Edge(B, E, 3));

        // Adding edge going from C to list
        listEgdes.add(new Edge(C, A, 1));
        listEgdes.add(new Edge(C, B, 2));
        listEgdes.add(new Edge(C, D, 4));
        listEgdes.add(new Edge(C, E, 3));
        listEgdes.add(new Edge(C, F, 5));

        // Adding edge going from D to list
        listEgdes.add(new Edge(D, B, 1));
        listEgdes.add(new Edge(D, C, 4));
        listEgdes.add(new Edge(D, F, 6));
        listEgdes.add(new Edge(D, G, 5));

        // Adding edge going from E to list
        listEgdes.add(new Edge(E, B, 3));
        listEgdes.add(new Edge(E, C, 3));
        listEgdes.add(new Edge(E, F, 1));

        // Adding edge going from F to list
        listEgdes.add(new Edge(F, C, 5));
        listEgdes.add(new Edge(F, D, 6));
        listEgdes.add(new Edge(F, E, 1));
        listEgdes.add(new Edge(F, G, 2));

        // Adding edge going from G to list
        listEgdes.add(new Edge(G, D, 5));
        listEgdes.add(new Edge(G, F, 2));

        // Add nodes and edges to graph
        g.setNodes(listNodes);
        g.setEdges(listEgdes);


        // Partie 2: A completer : Implémentation de l’algorithme Dijkstra

        Dijkstra d = new Dijkstra(g);
        d.findPath(A, G);
        d.afficherTable();

        // Partie 3 : Afficher le chemin le plus court
        System.out.println(d.afficherCourtChemin(A, G));

    }
}
