package code.service;

import code.model.minigraph.Edge;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Question3 {
    private static final Logger log = LoggerFactory.getLogger(Question3.class);

    private RestCaller rc = new RestCaller();
    private Question2 question2;
    private HashMap<String, String> allStops;

    public Question3() {
        question2 = new Question2();
        try {
            question2.question_2_additionalInfo(false);
            allStops = question2.getRouteStopsAll();
            //debugAllStops(allStops);
            edges = question2.getEdges();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }

    /**
     * Question 3
     * Extend your program again such that the user can provide any two stops on the subway routes you listed for
     * question 1.
     * List a rail route you could travel to get from one stop to the other. We will not evaluate your solution based
     * upon the efficiency or cleverness of your route-finding solution. Pick a simple solution that answers the
     * question. We will want you to understand and be able to explain how your algorithm performs.
     * Examples:
     * 1. Davis to Kendall/MIT -> Red Line
     * 2. Ashmont to Arlington -> Red Line, Green Line B
     * Hint: It might be tempting to hardcode things in your algorithm that are specific to the MBTA system, but we
     * believe it will make things easier for you to generalize your solution so that it could work for different and/or
     * larger subway systems.
     * How you handle input, represent train routes, and present output is your choice
     * <p>
     * we are dealing with the graph - vertex - stop; edge - line
     * to find connecting lines we do not need actually full graph traverse (can use java guava or other packages)
     * <p>
     * the algo - connect stops stop1 and stop2:
     * 0. fill mini graph with edges - no need to have inner stops, only lines and intersecting lines
     * 1. get lines for of the stop1 - line[] lines1
     * 2. get lines for of the stop2 - line[] lines2
     * 3. for each line1 in lines1 (if line1 has children - line11 - recurse)
     * for each line2 in lines2 (if line2 has children - line22 - recurse)
     * inner connection lines - until line1 == line2
     */


    private void debugMap(HashMap<String, String> items) {
        int k = 0;
        System.out.println(" ---  station                 routes at this stop");
        for (String item : items.keySet())
            System.out.println(String.format(" %-2s. %-27s:  %s", k++, item, items.get(item)));
    }

    private void debugMapList(HashMap<String, List<String>> items) {
        int k = 0;
        System.out.println(" ---  route                 stops");
        for (String item : items.keySet())
            System.out.println(String.format("%s. %-27s: \n\t %s", k++, item, String.join(";", items.get(item))));
    }

    public Pair<Boolean, String> findRouteConnection(Edge edge1, Edge edge2, String path) {
        if (edge1.getName().equals(edge2.getName())) {
            path += String.format(", %s", edge1.getName());
            return new ImmutablePair<>(true, path);
        }

        if (edge1.containsEdge(edge2.getName())) {
            path += String.format(", %s, %s", edge1.getName(), edge2.getName());
            return new ImmutablePair<>(true, path);
        }

        var commonChildrenOf12 = new ArrayList<>(edge1.getEdgeNames());
        commonChildrenOf12.retainAll(edge2.getEdgeNames());

        // edge1 has common children with edge2 => path would be edge1, common children(0), edge2
        if (commonChildrenOf12.size() > 0) {
            path += String.format(", %s, %s, %s", edge1.getName(), commonChildrenOf12.get(0), edge2.getName());
            // extended answer - give all available intersections in brackets:
            //path += String.format(", %s, %s, %s", edge1.getName(), commonChildrenOf12, edge2.getName());
            return new ImmutablePair<>(true, path);
        }

        for (Edge ch1 : edge1.getEdges()) {
            Edge child1 = edges.stream().filter(x -> x.getName().equals(ch1.getName())).findFirst().get();
            if (child1.containsEdge(edge2.getName())) {
                path = String.format(", %s, %s", child1.getName(), edge2.getName());
                return new ImmutablePair<>(true, path);
            }
            var res = findRouteConnection(child1, edge2, path);
            if (res.getKey())
                return new ImmutablePair<>(true, res.getValue());
        }
        return new ImmutablePair<>(false, path);
    }

    private List<Edge> edges;

    public void question_3_connectStops(String station1, String station2) {


        String[] edgeNames1 = null;
        String[] edgeNames2 = null;

        try {
            edgeNames1 = allStops.get(station1).split("; ");
            edgeNames2 = allStops.get(station2).split("; ");
        } catch (Exception ex) {
            if (edgeNames1 == null)
                System.out.println("invalid START stop name provided (not found...): " + station1);
            if (edgeNames2 == null)
                System.out.println("invalid END stop name provided (not found...): " + station2);
            return;
        }

        // routes for stop1 - list of edge names
        var routesForStop1 = Arrays.stream(edgeNames1).collect(Collectors.toCollection(HashSet::new));
        // routes for stop2 - list of edge names
        var routesForStop2 = Arrays.stream(edgeNames2).collect(Collectors.toCollection(HashSet::new));

        String edgePathCombined = "";
        boolean edgePathFound = false;

        for (String edgeName1 : routesForStop1) {
            if (edgePathFound) break;
            var edge1 = edges.stream().filter(x -> x.getName().equals(edgeName1)).findFirst().get();
            for (String edgeName2 : routesForStop2) {
                var edge2 = edges.stream().filter(x -> x.getName().equals(edgeName2)).findFirst().get();
                var res = findRouteConnection(edge1, edge2, edgePathCombined);
                edgePathFound = res.getKey();
                if (edgePathFound) {
                    edgePathCombined = res.getValue();
                    if (edgePathCombined.startsWith(", ")) edgePathCombined = edgePathCombined.substring(2);
                    break;
                }
            }
        }

        if (edgePathFound)
            System.out.println(String.format("%s to %s -> %s", station1, station2, edgePathCombined));
        else
            System.out.println(String.format("%s to %s -> %s", station1, station2, "no connection"));

    }


    /**
     *  82. Packard's Corner           :  Green Line B
     *  83. State                      :  Blue Line; Orange Line
     *  84. Airport                    :  Blue Line
     *  85. Massachusetts Avenue       :  Orange Line
     *  86. JFK/UMass                  :  Red Line
     *  87. Boylston                   :  Green Line C; Green Line B; Green Line E; Green Line D
     *  88. Science Park/West End      :  Green Line E
     *  89. South Station              :  Red Line
     *  90. Cedar Grove                :  Mattapan Trolley
     *  91. Broadway                   :  Red Line
     *  92. Summit Avenue              :  Green Line C
     *  93. Englewood Avenue           :  Green Line C
     *  94. Harvard                    :  Red Line
     *  95. Quincy Center              :  Red Line
     *  96. Maverick                   :  Blue Line
     *  97. Green Street               :  Orange Line
     *  98. Allston Street             :  Green Line B
     */
}
