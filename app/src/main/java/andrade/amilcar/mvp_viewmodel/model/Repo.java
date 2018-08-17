package andrade.amilcar.mvp_viewmodel.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    private int id;

    private String url;

    @SerializedName("name")
    private String name;

    private String description;

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
