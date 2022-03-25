package code.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Line {
    @JsonProperty("data")
    private Data data;

    //region get/set
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    //endregion

    public class Data {
        @JsonProperty("id")
        private String id;
        @JsonProperty("type")
        private String type;

        //region get/set
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
        //endregion
    }
}
