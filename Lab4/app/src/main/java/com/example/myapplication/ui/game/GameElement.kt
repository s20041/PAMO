// GameElement.java
// Represents a rectangle-bounded game element
package com.example.myapplication.ui.game


import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import android.widget.TextView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import com.example.myapplication.ui.bmi.BmiViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.ui.bmi.BmiFragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.example.myapplication.ui.game.CannonView
import com.example.myapplication.ui.game.GameElement
import com.example.myapplication.ui.game.Cannonball
import android.view.SurfaceView
import android.view.SurfaceHolder
import android.app.Activity
import com.example.myapplication.ui.game.Cannon
import android.media.SoundPool
import android.util.SparseIntArray
import androidx.annotation.RequiresApi
import android.os.Build
import com.example.myapplication.R
import android.view.MotionEvent
import android.content.DialogInterface
import android.media.AudioAttributes
import android.graphics.drawable.VectorDrawable
import android.graphics.drawable.Drawable
import android.media.AudioManager
import com.example.myapplication.ui.home.HomeViewModel
import android.widget.RadioButton
import com.example.myapplication.ui.notifications.KcalFragment
import com.example.myapplication.ui.quiz.QuizViewModel
import com.example.myapplication.ui.notifications.KcalViewModel
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.NavController
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import com.example.myapplication.MainActivity

open class GameElement constructor(// the view that contains this GameElement
    protected var view: CannonView, color: Int, soundId: Int, x: Int,
    y: Int, width: Int, length: Int, velocityY: Float
) {
    protected var paint: Paint = Paint() // Paint to draw this GameElement
    var shape // the GameElement's rectangular bounds
            : Rect
    protected var vector: VectorDrawable? = null
    private var velocityY // the vertical velocity of this GameElement
            : Float
    private val soundId // the sound associated with this GameElement
            : Int
    private val drawable: Drawable

    // update GameElement position and check for wall collisions
    open fun update(interval: Double) {
        // update vertical position
        shape.offset(0, (velocityY * interval).toInt())
        drawable.setBounds(shape)

        // if this GameElement collides with the wall, reverse direction
        if (shape.top < 0 && velocityY < 0 ||
            shape.bottom > view.getScreenHeight() && velocityY > 0
        ) velocityY *= -1f // reverse this GameElement's velocity
    }

    // draws this GameElement on the given Canvas
    open fun draw(canvas: Canvas?) {
        drawable.draw((canvas)!!)
        canvas!!.drawRect(shape, paint)
    }

    // plays the sound that corresponds to this type of GameElement
    fun playSound() {
        view.playSound(soundId)
    }

    // public constructor
    init {
        paint.setColor(color)
        drawable = view.getResources().getDrawable(R.drawable.badburger)
        shape = Rect(x, y, x + width, y + length) // set bounds
        drawable.setBounds(shape)
        this.soundId = soundId
        this.velocityY = velocityY
    }
}