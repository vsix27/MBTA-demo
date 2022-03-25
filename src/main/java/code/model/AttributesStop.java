package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AttributesStop {
    @JsonProperty("address")
    private String address;

    @JsonProperty("at_street")
    private String at_street;

    @JsonProperty("description")
    private String description;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("location_type")
    private int location_type;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("municipality")
    private String municipality;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String on_street;

    @JsonProperty("platform_code")
    private String platform_code;

    @JsonProperty("platform_name")
    private String platform_name;

    @JsonProperty("vehicle_type")
    private String vehicle_type;

    @JsonProperty("wheelchair_boarding")
    private int wheelchair_boarding;

    //region get/set
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAt_street() {
        return at_street;
    }

    public void setAt_street(String at_street) {
        this.at_street = at_street;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getLocation_type() {
        return location_type;
    }

    public void setLocation_type(int location_type) {
        this.location_type = location_type;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOn_street() {
        return on_street;
    }

    public void setOn_street(String on_street) {
        this.on_street = on_street;
    }

    public String getPlatform_code() {
        return platform_code;
    }

    public void setPlatform_code(String platform_code) {
        this.platform_code = platform_code;
    }

    public String getPlatform_name() {
        return platform_name;
    }

    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public int getWheelchair_boarding() {
        return wheelchair_boarding;
    }

    public void setWheelchair_boarding(int wheelchair_boarding) {
        this.wheelchair_boarding = wheelchair_boarding;
    }
    //endregion

}
