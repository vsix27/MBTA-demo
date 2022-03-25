package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route {
    /**
     * {
     *   "links": { "self": "string", ..."first": "string" },
     *   "data": [ ]
     * }
     */
    @JsonProperty("attributes")
    private Attributes attributes;

    @JsonProperty("id")
    private String id;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("relationships")
    private Relationships relationships;

    @JsonProperty("type")
    private String type;

    //region get/set
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Relationships getRelationships() {
        return relationships;
    }

    public void setRelationships(Relationships relationships) {
        this.relationships = relationships;
    }




    //endregion
}
