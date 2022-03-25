package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonApi {
    @JsonProperty("version")
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
