package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Stop {

    @JsonProperty("attributes")
    private AttributesStop attributes;

    @JsonProperty("id")
    private String id;

    @JsonProperty("type")
    private String type;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("relationships")
    private Relationships relationships;

    //region get/set
    public AttributesStop getAttributes() {
        return attributes;
    }

    public void setAttributes(AttributesStop attributes) {
        this.attributes = attributes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public void setRelationships(Relationships relationships) {
        this.relationships = relationships;
    }
    //endregion

}
