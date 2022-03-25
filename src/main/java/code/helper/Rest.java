package code.helper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rest {
    private static Logger log = LoggerFactory.getLogger(Rest.class);

    //TODO: read from application.properties?
    private static String apiUrl = "https://api-v3.mbta.com";

    /**
     * get url to call filtered routes for subway
     *
     * @param method /routes, /stops
     * @param filter filter[type]=0,1 - to filter by type -include only 0,1 - see swagger
     * @return
     */
    public static String combineApiPath(String method, String filter) {
        String queryArgs = "";
        if (!StringUtils.isBlank(filter))
            queryArgs = "?" + filter;
        return apiUrl + method + queryArgs;
    }

    public static String getApiJsonData(String apipath) throws IOException, JSONException {
        return getApiJson(apipath, "data");
    }

    public static String getApiJson(String apipath, String section) throws IOException, JSONException {
        URL url = new URL(apipath);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

        String output;
        String all = "";
        while ((output = br.readLine()) != null) {
            all += output;
        }
        //log.info("--- all: " + all);
        conn.disconnect();

        JSONObject obj = new JSONObject(all);
        String data = obj.getJSONArray(section).toString();
        return data;
    }

}
