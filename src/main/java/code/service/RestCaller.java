package code.service;

import code.helper.Convert;
import code.helper.Rest;
import code.model.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class RestCaller {
    private static Logger log = LoggerFactory.getLogger(RestCaller.class);

    @Value("${apiUrl}")
    private String apiUrl;

    /**
     * Question 1
     * Write a program that retrieves data representing all, what we'll call "subway" routes:
     * "Light Rail" (type 0) and
     * “Heavy Rail” (type 1).
     * <p>
     * The program should list their “long names” on the console.
     * Partial example of long name output: Red Line, Blue Line, Orange Line...
     * There are two ways to filter results for subway-only routes. Think about the two options below and choose:
     * 1. Download all results from https://api-v3.mbta.com/routes then filter locally
     * 2. Rely on the server API (i.e., https://api-v3.mbta.com/routes?filter[type]=0,1) to filter before results
     * are received
     * Please document your decision and your reasons for it.
     */

    /**
     * get all routes
     *
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public List<Route> getListRoute(String method) throws IOException, JSONException {
        return getListRoute(method, "");
    }

//    @ Autowired
//    public RestCaller(@Value("${apiUrl}") String s) {
//        log.debug("RestCaller s: {}", s);
//        this.apiUrl = s;
//    }

    public RestCaller() {
    }

    /**
     * get routes with filter
     *
     * @param filter
     * @return
     * @throws IOException
     * @throws JSONException
     */
    public List<Route> getListRoute(String method, String filter) throws IOException, JSONException {
        log.debug("apiUrl from app prop: {}", apiUrl);
        final String data = Rest.getApiJsonData(Rest.combineApiPath(method, filter));
        //log.info("--- data: " + data);
        var ss = Convert.jsonToClass(Route[].class, data);
        return Arrays.stream(ss).toList();
    }

    //region debug output

    /**
     * print long names
     * <p>
     * * 0. Red Line
     * * 1. Mattapan Trolley
     * * 2. Orange Line
     * * 3. Green Line B
     * * 4. Green Line C
     * * 5. Green Line D
     * * 6. Green Line E
     * * 7. Blue Line
     *
     * @param routes
     */
    public void debugLongNames(List<Route> routes) {
        for (int k = 0; k < routes.size(); k++)
            System.out.println("" + k + ". " + routes.get(k).getAttributes().getLongName());
    }

    public void debugIds(List<Route> routes) {
        for (int k = 0; k < routes.size(); k++)
            System.out.println("" + k + ". " + routes.get(k).getId());
    }
    //endregion
}
