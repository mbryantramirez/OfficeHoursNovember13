package nyc.aisleone.officehoursnov13.models;


import com.squareup.moshi.Json;

public class PuppyItem {
    @Json(name = "status")
    private String status;

    @Json(name = "message")
    private String message;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
