
package com.rma.marvelchallenge.data.response.character;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("offset")
    @Expose
    public Integer offset;
    @SerializedName("limit")
    @Expose
    public Integer limit;
    @SerializedName("total")
    @Expose
    public Integer total;
    @SerializedName("count")
    @Expose
    public Integer count;
    @SerializedName("results")
    @Expose
    public List<Result> results = null;

}
