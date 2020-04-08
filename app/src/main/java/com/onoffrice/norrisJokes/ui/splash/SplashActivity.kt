package com.onoffrice.norrisJokes.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.onoffrice.norrisJokes.R
import com.onoffrice.norrisJokes.ui.categories.createCategoriesIntent
import com.onoffrice.norrisJokes.utils.extensions.startActivitySlideTransition
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setLogoAnimation()
        setDelayForActivity()
    }

    private fun setDelayForActivity() {
        val handle = Handler()
        handle.postDelayed({
            startActivitySlideTransition(createCategoriesIntent())
            finish()
            }, 4000)
    }

    private fun setLogoAnimation() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.logo_transition)
        animation.apply {
            this.repeatCount    = 1
            this.duration       = 2000
            this.fillAfter      = true
            this.repeatMode     = Animation.REVERSE
        }.also { splash_logo.startAnimation(animation) }
    }
}
