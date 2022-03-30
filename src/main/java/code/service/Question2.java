package code.service;

import code.helper.Convert;
import code.helper.Rest;
import code.model.Route;
import code.model.Stop;
import code.model.minigraph.Edge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Question2 {
    private static final Logger log = LoggerFactory.getLogger(Question2.class);

    private RestCaller rc = new RestCaller();

    /**
     * Question 2
     * Extend your program so it displays the following additional information.
     * 1. The name of the subway route with the most stops as well as a count of its stops.
     * 2. The name of the subway route with the fewest stops as well as a count of its stops.
     * 3. A list of the stops that connect two or more subway routes along with the relevant route names for
     * each of those stop
     */

    private HashMap<String, Integer> mapRouteStopsCounts;
    private HashMap<String, String> mapRoutes;
    private HashMap<String, String> mapRouteStopsAll;
    private HashMap<String, String> mapRouteIntersectionsOnly;
    private HashMap<String, List<String>> mapStopsInRoutes;
    private List<Edge> edges;

    public HashMap<String, String> getRouteStopsAll() {
        return mapRouteStopsAll;
    }

    public HashMap<String, List<String>> getStopsInRoutes() {
        return mapStopsInRoutes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * dictionary - routeId -> routeName
     *
     * @return
     */
    public Map<String, String> getRoutes() {
        return mapRoutes;
    }

    public void question_2_additionalInfo() throws JSONException, IOException {
        question_2_additionalInfo(true);
    }

    // preferred - get filtered names using api parameter - get only needed data from the server
    public void question_2_additionalInfo(boolean printConsole) throws JSONException, IOException {
        List<Route> routesSubway = rc.getListRoute("/routes", "filter[type]=0,1");

        mapRoutes = new HashMap<>();
        for (Route r : routesSubway) mapRoutes.put(r.getId(), r.getAttributes().getLongName());

        mapRouteStopsCounts = new HashMap<>();
        mapRouteStopsAll = new HashMap<>();
        mapRouteIntersectionsOnly = new HashMap<>();
        mapStopsInRoutes = new HashMap<>();
        edges = new ArrayList<>();

        int k = 0;
        for (String routeId : mapRoutes.keySet()) {
            String filter = "include=route&filter[route]=" + routeId;
            final String data = Rest.getApiJsonData(Rest.combineApiPath("/stops", filter));

            //init new route station map
            String routeName = mapRoutes.get(routeId);
            mapStopsInRoutes.put(routeName, new ArrayList<>());

            edges.add(new Edge(routeName));

            var stops = Convert.jsonToClass(Stop[].class, data);

            for (Stop stop : stops) {
                mapStopsInRoutes.get(routeName).add(stop.getAttributes().getName());

                String stopName = stop.getAttributes().getName();
                if (!mapRouteStopsAll.containsKey(stopName))
                    mapRouteStopsAll.put(stopName, routeName);
                else {
                    mapRouteStopsAll.put(stopName, mapRouteStopsAll.get(stopName) + "; " + routeName);

                    //add lines to the current line
                    String[] curLines = mapRouteStopsAll.get(stopName).split("; ");
                    edges.get(edges.size() - 1).addEdge(curLines);

                    //verify previous lines against current - add current to already processed
                    for (String ln : curLines) {
                        Optional<Edge> ed = edges.stream().filter(x -> x.getName().equals(ln)).findFirst();
                        if (ed.isPresent())
                            ed.get().addEdge(routeName);
                    }
                }
            }

            mapRouteStopsCounts.put(routeName, stops.length);
        }


        //reduce mapRouteStopsAll
        for (String s : mapRouteStopsAll.keySet()) {
            if (mapRouteStopsAll.get(s).contains(";"))
                mapRouteIntersectionsOnly.put(s, mapRouteStopsAll.get(s));
        }

        var sortedRouteStop = mapRouteStopsCounts.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        // do not print results
        if (!printConsole)
            return;

        printRouteStops(1, "most stops", sortedRouteStop.get(sortedRouteStop.size() - 1));
        printRouteStops(2, "fewest stops", sortedRouteStop.get(0));

        // print stations connectors
        k = 0;
        System.out.println("3. --- connecting station            routes at this stop");
        for (String station : mapRouteIntersectionsOnly.keySet())
            System.out.println(String.format(" 3.%-2s. %-27s:  %s", k++, station, mapRouteIntersectionsOnly.get(station)));
    }

    private void printRouteStops(int k, String most, Map.Entry<String, Integer> m) {
        System.out.println(
                String.format("%s. route with the %-12s:  %-20s stops: %s", k, most, m.getKey(), m.getValue()));
    }
}
