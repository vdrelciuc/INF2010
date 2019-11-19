import java.util.ArrayList;
import java.util.List;


public class Graph {

    private List<Node> nodes; // Noeuds
    private List<Edge> edges; // Les arcs

    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public List<Edge> getEdgesGoingFrom(Node source) {
        // List use to return edges
        List<Edge> list = new ArrayList<>();
        // Check all edges in the graph
        for (Edge edge : edges) {
            // Add to return list only on source id match
            if (edge.getSource().getId() == source.getId())
                list.add(edge);
        }
        return list;
    }

    public List<Edge> getEdgesGoingTo(Node dest) {
        // List use to return edges
        List<Edge> list = new ArrayList<>();
        // Check all edges in the graph
        for (Edge edge : edges) {
            // Add to return list only on destination id match
            if (edge.getDestination().getId() == dest.getId())
                list.add(edge);
        }
        return list;
    }

    // Accesseurs
    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

}
