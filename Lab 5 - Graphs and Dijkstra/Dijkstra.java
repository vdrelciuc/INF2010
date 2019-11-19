import java.util.*;


public class Dijkstra {

    private Graph graph;
    // not functional
    private Map<Node, Edge> dijkstraTable[];
    // an another dijkstra table since initial one is not functional
    private ArrayList<HashMap<Node, Edge>> myDijkstraTable;
    private Stack<Edge> path;


    public Dijkstra(Graph g) {
        this.graph = g;
    }

    public void findPath(Node s, Node d) {
        // this is not functional
        // dijkstraTable = new HashMap[graph.getNodes().size()];
        myDijkstraTable = new ArrayList<>();
        path = new Stack<Edge>();
        // current iteration in dijkstra table
        HashMap<Node, Edge> iteration = new HashMap<>();
        // iteration updates flag
        Boolean iterationValueModifie = false;
        // Variable use in algorithm

        // Current node in iteration
        Node current;
        // Edge adjacent to current node
        List<Edge> agencyList;
        // Map with distance from start for each node
        HashMap<Node, Double> distanceFromStart = new HashMap<>();
        // List of node already visited
        List<Node> visited = new ArrayList<>();
        // List of next nodes to visit
        List<Node> toVisit = new ArrayList<>();
        // Current distance
        double distance;
        // Use to remember if node was in visited
        boolean isVisited;

        // Set all distance from start to infinity
        for (Node node : graph.getNodes()) {
            distanceFromStart.put(node, Double.POSITIVE_INFINITY);
        }
        // Add start to node to visit
        toVisit.add(s);
        // Set distance from start to start to 0
        distanceFromStart.replace(s, 0.0);

        // initialise iteration
        for (Node node : graph.getNodes())
            iteration.put(node, null);
        // first iteration updates is start with distance of 0
        iteration.replace(s, new Edge(s, s, 0));
        myDijkstraTable.add(new HashMap<>(iteration));

        // While there is node to visit ...
        while (toVisit.size() != 0) {

            // Set current node to first node in the to visit list
            current = toVisit.get(0);
            // Check if current is the one with the shortest distance
            for (Node node : toVisit) {
                if (distanceFromStart.get(node).intValue() < distanceFromStart.get(current).intValue())
                    current = node;
            }
            // If current is end destination break
            if (current == d)
                break;
            // Remove current node from node to visit and add it to visited node
            toVisit.remove(current);
            visited.add(current);
            // Get edges adjacent to current node
            agencyList = graph.getEdgesGoingFrom(current);

            for (Edge edge : agencyList) {
                isVisited = false;
                // Check if edge destination is already visited
                for (Node node : visited) {
                    if (node.getId() == edge.getDestination().getId()) {
                        isVisited = true;
                        break;
                    }
                }
                // If edge destination was not visited
                if (!isVisited) {

                    distance = distanceFromStart.get(current).intValue() + edge.getDistance();
                    // If new distance is shorter than assign it, add edge's destination to node to visit
                    // and add edge to path
                    if (distance < distanceFromStart.get(edge.getDestination()).intValue()) {
                        // update distance from start for destination node
                        distanceFromStart.replace(edge.getDestination(), distance);
                        // add destination node to list to be visit
                        toVisit.add(edge.getDestination());
                        // add edge to possible path list
                        path.add(edge);
                        // Updates iteration
                        iteration.replace(edge.getDestination(), new Edge(edge.getSource(), edge.getDestination(), (int) (distance)));
                        // iteration updates flag set to true
                        iterationValueModifie = true;
                    }
                }
            }
            // if iteration has modified value, then update dijkstra table
            if (iterationValueModifie) {
                // Set remove current node from dijkstra table
                iteration.replace(current, null);
                // add a new iteration
                myDijkstraTable.add(new HashMap<>(iteration));
                // set iteration mod flag to false
                iterationValueModifie = false;
            }
        }
    }

    private Node getMinimum(Map<Node, Edge> map) {
        Edge min = null;
        for (Node Key : map.keySet()) {
            if (min == null || map.get(Key).getDistance() < min.getDistance()) {
                min = map.get(Key);
            }
        }
        return min.getDestination();
    }

    private Edge getMinimum(Edge e1, Edge e2) {
        // return edge with the shortest distance
        if (e1.getDistance() < e2.getDistance())
            return e1;
        else
            return e2;
    }

    public String afficherCourtChemin(Node source, Node destination) {
        Node prev = destination;
        StringBuilder sb = new StringBuilder();
        // Retrace path from end
        for (int i = path.size() - 1; i >= 0; --i) {
            // If destination of current element is the prev element than it is the next node in the path
            // Else it need to be remove
            if (path.get(i).getDestination().getId() == prev.getId())
                prev = path.get(i).getSource();
            else
                path.remove(i);
        }
        sb.append("Chemin le plus court: { ");
        for (Edge edge : path) {
            sb.append(edge.getSource().getName() + ", ");

        }
        sb.append(path.get(path.size() - 1).getDestination().getName() + " }");
        return sb.toString();
    }

    public void afficherTable() {
        System.out.println("Ma table :");
        System.out.println("A  B  C  D  E  F  G");
        for (HashMap<Node, Edge> map : myDijkstraTable) {
            for (Node node : graph.getNodes()) {
                if (map.get(node) != null)
                    System.out.print(map.get(node).getDistance() + map.get(node).getSource().getName() + " ");
                else
                    System.out.print("   ");
            }
            System.out.print('\n');
        }
    }
}
