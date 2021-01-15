package com.rma.marvelchallenge.core.platform

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.rma.marvelchallenge.R

class IconHelper(private val context: Context) {

    fun draw(url: String = "", iv: ImageView){

        var params = "?apikey=${Connection.API_PUBLIC_KEY}"
        params += "&hash=${Connection.API_HASH}"
        params += "&ts=${Connection.API_TS}"
        val imageUrl : String = url + params

        Glide.with(context)
            .load(
                GlideUrl(imageUrl, LazyHeaders.Builder()
                .build())
            )
            .placeholder(R.drawable.ic_launcher_background)
            .into(iv)
    }
}