package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Attributes {
    @JsonProperty("type")
    private int type;

    @JsonProperty("test_color")
    private String test_color;

    @JsonProperty("sort_order")
    private int sort_order;

    @JsonProperty("short_name")
    private String short_name;

    @JsonProperty("long_name")
    private String long_name;

    @JsonProperty("fare_class")
    private String fare_class;

    @JsonProperty("description")
    private String description;

    @JsonProperty("direction_names")
    private String[] direction_names;

    @JsonProperty("direction_destinations")
    private String[] direction_destinations;

    @JsonProperty("color")
    private String color;

    //region get/set
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTestColor() {
        return test_color;
    }

    public void setTestColor(String test_color) {
        this.test_color = test_color;
    }

    public int getSortOrder() {
        return sort_order;
    }

    public void setSortOrder(int sort_order) {
        this.sort_order = sort_order;
    }

    public String getShortName() {
        return short_name;
    }

    public void setShortName(String short_name) {
        this.short_name = short_name;
    }

    public String getLongName() {
        return long_name;
    }

    public void setLongName(String long_name) {
        this.long_name = long_name;
    }

    public String getFareClass() {
        return fare_class;
    }

    public void setFareClass(String fare_class) {
        this.fare_class = fare_class;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getDirection_names() {
        return direction_names;
    }

    public void setDirection_names(String[] direction_names) {
        this.direction_names = direction_names;
    }

    public String[] getDirectionDestinations() {
        return direction_destinations;
    }

    public void setDirectionDestinations(String[] direction_destinations) {
        this.direction_destinations = direction_destinations;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    //endregion

}
