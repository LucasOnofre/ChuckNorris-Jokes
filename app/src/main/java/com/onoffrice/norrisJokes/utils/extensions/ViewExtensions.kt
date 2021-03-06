package com.onoffrice.norrisJokes.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.onoffrice.norrisJokes.R


fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
    visibility = when {
        visible -> View.VISIBLE
        useInvisible -> View.INVISIBLE
        else -> View.GONE
    }
}

fun ImageView.loadImage(url: String?): Boolean {
    try {
        val optionsToApply = RequestOptions()
                .fitCenter()

        Glide.with(context)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.placeholder_photo)
            .apply(optionsToApply)
            .into(this)

    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return true
}

/** Animates List item views
 * firstPositionDelay is necessary cause  first position also needs a delay, but it's 0 **/
fun View.fadeUpItemListAnimation(position: Int, animationDelay: Long) {
    val firstPositionDelay = 3
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_up_item)
    animation.startOffset = (position + firstPositionDelay) * animationDelay

    this.animation = animation
}

/** Animates List item views
 * firstPositionDelay is necessary cause first position also needs a delay, but it's 0 **/
fun View.fadeInItemListAnimation(position: Int, animationDelay: Long) {
    val firstPositionDelay = 0
    val animation = AnimationUtils.loadAnimation(context, R.anim.fade_in_item)
    animation.startOffset = (position + firstPositionDelay) * animationDelay

    this.animation = animation
}

fun EditText.afterTextChanged(onTextChanged: ((String) -> Unit)) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onTextChanged(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {

        }
    })
}

