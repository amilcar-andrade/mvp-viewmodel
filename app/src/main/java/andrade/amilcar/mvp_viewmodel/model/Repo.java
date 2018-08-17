package andrade.amilcar.mvp_viewmodel.model;

import com.google.gson.annotations.SerializedName;

public class Repo {

    private int id;

    private String url;

    @SerializedName("full_name")
    private String name;

    private String description;
}
