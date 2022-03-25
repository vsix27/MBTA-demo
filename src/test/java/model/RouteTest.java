package model;

import code.helper.Convert;
import code.model.Line;
import code.model.Links;
import code.model.Relationships;
import code.model.Route;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteTest {
    private static Logger log = LoggerFactory.getLogger(RouteTest.class);

    private Relationships prepareRelationships(String... ss) {
        Relationships item = new Relationships();
        item.setLine(prepareLine());
        return item;
    }

    private Line prepareLine() {
        Line.Data data = new Line().new Data();
        data.setId("id-data-line");
        data.setType("type-data-line");
        Line line = new Line();
        line.setData(data);
        return line;
    }

    private Links prepareLinks() {
        Links item = new Links();
        item.setFirst("aaa");
        item.setLast("zzz");
        item.setNext("nnn");
        item.setPrev("ppp");
        item.setSelf("?");
        return item;
    }

    private Route prepareRoute(String id) {
        Route route = new Route();
        route.setAttributes(AttributesTest.prepareAttributes("green " + id, "some info long " + id));
        route.setId(id);
        route.setType("route " + id);
        route.setRelationships(prepareRelationships("close", "prev", "next"));
        route.setLinks(prepareLinks());
        return route;
    }

    @Test
    public void test_route_json() throws JsonProcessingException {
        Route route = prepareRoute("12");

        String json = Convert.toJsonPretty(route);
        //log.info(json);

        assertTrue(json.contains("short_name"));
        assertTrue(json.contains("direction_destinations"));
        assertTrue(json.contains(route.getAttributes().getDirectionDestinations()[0]));

        Route route1 = Convert.jsonToClass(Route.class, json);
        assertTrue(route.getAttributes().getLongName().equals(route1.getAttributes().getLongName()));
    }

    @Test
    public void test_routes_json() {
        Route[] routes = {prepareRoute("01"), prepareRoute("12")};

        String json = Convert.toJson(routes);
        log.info(json);

        Route[] routes2 = Convert.jsonToClass(Route[].class, json);
        assertTrue(routes2.length == 2);
    }
}
