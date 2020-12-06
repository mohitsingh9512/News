package com.example.tnews.utlis

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.tnews.R
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: T){
    if(recyclerView.adapter is com.example.tnews.utlis.BindingAdapter<*>){
        @Suppress("UNCHECKED_CAST")
        (recyclerView.adapter as com.example.tnews.utlis.BindingAdapter<T>).setData(data)
    }
}

@BindingAdapter("statusVisibility")
fun setVisibility(view: View, s : String?) {
    if (s.isNullOrBlank() ) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter("imageUrl", "sourceUrl", requireAll = false)
fun loadImage(imageView: ImageView, imageUrl: String?, sourceUrl: Boolean?) {
    // Load Fav icon image for sources placed on root of sites.
    var url = imageUrl
    if(sourceUrl == true){
        url += "/favicon.ico"
    }
    if (url != null) {
        if (imageView.getTag(R.id.image_url) == null || imageView.getTag(R.id.image_url) != url) {
            imageView.apply {
                setImageBitmap(null)
                setTag(R.id.image_url, url)
                val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
                val options: RequestOptions = RequestOptions()
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .priority(Priority.HIGH)
                        .dontAnimate()
                        .dontTransform()
                Glide.with(this)
                        .load(url)
                        .apply(options)
                        .transition(DrawableTransitionOptions.withCrossFade(factory))
                        .into(this)
            }
        }
    } else {
        imageView.apply {
            setTag(R.id.image_url, null)
            setImageBitmap(null)
        }
    }
}

fun getNormalTime(utcTimeStamp: Timestamp): String {
    var timeElapsedInSeconds = (System.currentTimeMillis() - utcTimeStamp.time) / 1000
    return when {
        timeElapsedInSeconds < 60 -> {
            "less than 1m ago"
        }
        timeElapsedInSeconds < 3600 -> {
            timeElapsedInSeconds /= 60
            timeElapsedInSeconds.toString() + "m ago"
        }
        timeElapsedInSeconds < 86400 -> {
            timeElapsedInSeconds /= 3600
            timeElapsedInSeconds.toString() + "h ago"
        }
        else -> {
            timeElapsedInSeconds /= 86400
            timeElapsedInSeconds.toString() + "d ago"
        }
    }
}