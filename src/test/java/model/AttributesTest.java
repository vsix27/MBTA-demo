package model;

import code.model.Attributes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttributesTest {
    private static Logger log = LoggerFactory.getLogger(AttributesTest.class);
    private static Attributes attributes;

    public AttributesTest() {
        attributes = prepareAttributes("blue", "some long name");
    }

    public static Attributes prepareAttributes(String color, String longName) {
        Attributes item = new Attributes();
        item.setColor(color);
        item.setDescription("dest");
        String[] destinations = {"a-destination", "b-destination"};
        item.setDirectionDestinations(destinations);
        String[] names = {"a-name", "b-name", "c-name"};
        item.setDirection_names(names);
        item.setFareClass("some fare");
        item.setLongName(longName);
        item.setShortName("short-name");
        return item;
    }

    // verify json field renaming
    @Test
    public void test_json() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(attributes);
        log.info(json);

        assertTrue(json.contains("fare_class"));
        assertTrue(json.contains("direction_destinations"));
        assertTrue(json.contains(attributes.getLongName()));
    }
}
