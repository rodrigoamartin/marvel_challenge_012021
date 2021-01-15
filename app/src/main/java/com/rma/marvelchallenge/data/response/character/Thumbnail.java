
package com.rma.marvelchallenge.data.response.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnail {

    @SerializedName("path")
    @Expose
    public String path;
    @SerializedName("extension")
    @Expose
    public String extension;

}
