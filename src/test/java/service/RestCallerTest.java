package service;

import code.service.RestCaller;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RestCallerTest {
    private static Logger log = LoggerFactory.getLogger(RestCallerTest.class);
    private RestCaller rc = new RestCaller();

    @Test
    public void testCaller() throws IOException, JSONException {
        var ss0 = rc.getListRoute("/routes", "filter[type]=0");
        var ss1 = rc.getListRoute("/routes", "filter[type]=1");
        var ss01 = rc.getListRoute("/routes", "filter[type]=0,1");
        log.info("--- routes ss0 {} ", ss0.size());
        log.info("--- routes ss1 {} ", ss1.size());
        log.info("--- routes ss01 {} ", ss01.size());
        assertTrue((ss0.size() + ss1.size()) == ss01.size());
    }
}
