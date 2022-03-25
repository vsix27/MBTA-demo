package code.model.minigraph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Edge {
    private String name;
    //edges which intersect with the current edge
    private List<Edge> edges;

    public Edge(String name) {
        this.name = name;
        edges = new ArrayList<>();
    }

    public void addEdge(String[] names) {
        for (String name : names)
            addEdge(name);
    }

    public void addEdge(String name) {
        if (this.name.equals(name))
            return;

        if (edges == null) {
            edges = new ArrayList<>();
            edges.add(new Edge(name));
            return;
        }
        if (!containsEdge(name))
            edges.add(new Edge(name));

//        boolean alreadyPresent = containsEdge(name);
//        Edge e = alreadyPresent
//                ? edges.stream().filter(x -> x.name.equals(name)).findFirst().get()
//                : new Edge(name);
//        edges.add(e);
    }

    /**
     * true if list of inner edges contains edge with the name: name
     *
     * @param name
     * @return
     */
    public boolean containsEdge(String name) {
        return getEdges().stream().anyMatch(x -> x.name.equals(name));
    }

    //region get/set
    public String getName() {
        return name;
    }

    /**
     * edges which intersect with the current edge
     *
     * @return
     */
    public List<Edge> getEdges() {
        return edges;
    }

    public List<String> getEdgeNames() {
        return edges.stream().map(x -> x.getName()).collect(Collectors.toList());
    }

    //endregion

}
