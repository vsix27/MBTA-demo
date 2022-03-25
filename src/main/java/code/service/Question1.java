package code.service;

import code.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Question1 {
    private static final Logger log = LoggerFactory.getLogger(Question1.class);
    private RestCaller rc = new RestCaller();

    /**
     * Question 1
     * Write a program that retrieves data representing all, what we'll call "subway" routes: "Light Rail" (type 0) and
     * “Heavy Rail” (type 1). The program should list their “long names” on the console.
     * Partial example of long name output: Red Line, Blue Line, Orange Line...
     * There are two ways to filter results for subway-only routes. Think about the two options below and choose:
     * 1. Download all results from https://api-v3.mbta.com/routes then filter locally
     * 2. Rely on the server API (i.e., https://api-v3.mbta.com/routes?filter[type]=0,1) to filter before results
     * are received
     * Please document your decision and your reasons for it
     */

    // preferred:  get filtered names using api parameter
    //    reason: get only needed data from the server
    public void question_1_serverFilter() throws JSONException, IOException {
        List<Route> routesSubway = rc.getListRoute("/routes", "filter[type]=0,1");
        //rc.debugLongNames(routesSubway);
        String longNames = String.join(",",
                routesSubway.stream().map(x -> x.getAttributes().getLongName()).collect(Collectors.toList()));
        //Red Line,Mattapan Trolley,Orange Line,Green Line B,Green Line C,Green Line D,Green Line E,Blue Line
        System.out.println(longNames);
    }


    // longer - filter locally - has extra data to filter out
    public void question_1_filterLocally() throws JSONException, IOException {
        List<Route> routes = rc.getListRoute("/routes");

        log.info("routes.size - all: {} ", routes.size());
        //rc.debugLongNames(routes);

        List<Route> routesFiltered01 = routes.stream()
                .filter(x -> Arrays.asList(0, 1).contains(x.getAttributes().getType()))
                .collect(Collectors.toList());

        log.info("routesFiltered01.size: {} ", routesFiltered01.size());
    }
}
