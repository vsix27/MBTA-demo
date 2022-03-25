package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Relationships {
    @JsonProperty("line")
    private Line line;

    //region get/set
    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }
    //endregion

}
