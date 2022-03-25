package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {
    /**
     *   "data": [
     *     {
     *       "type": "string",
     *       "relationships": {},
     *       "links": {},
     *       "id": "string",
     *       "attributes": {...}
     *     }
     *   ]
     */
    @JsonProperty("type")
    private String type;

    @JsonProperty("relationships")
    private Relationships relationships;

    @JsonProperty("links")
    private Links links;

    @JsonProperty("id")
    private String id;

    @JsonProperty("attributes")
    private Attributes attributes;

    //region get/set
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

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

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
    //endregion
}
