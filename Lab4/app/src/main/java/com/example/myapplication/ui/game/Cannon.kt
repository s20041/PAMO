// Cannon.java
// Represents Cannon and fires the Cannonball
package com.example.myapplication.ui.game

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point

class Cannon(// view containing the Cannon
    private val view: CannonView, // Cannon base's radius
    private val baseRadius: Int, // Cannon barrel's length
    private val barrelLength: Int,
    barrelWidth: Int
) {
    private val barrelEnd = Point() // endpoint of Cannon's barrel
    private var barrelAngle // angle of the Cannon's barrel
            = 0.0

    // returns the Cannonball that this Cannon fired
    var cannonball // the Cannon's Cannonball
            : Cannonball? = null
        private set
    private val paint = Paint() // Paint used to draw the cannon

    // aligns the Cannon's barrel to the given angle
    fun align(barrelAngle: Double) {
        this.barrelAngle = barrelAngle
        barrelEnd.x = (barrelLength * Math.sin(barrelAngle)).toInt()
        barrelEnd.y = (-barrelLength * Math.cos(barrelAngle)).toInt() +
                view.screenHeight / 2
    }

    @JvmName("getCannonball1")
    fun getCannonball(): Cannonball? {
        return cannonball
    }

    // creates and fires Cannonball in the direction Cannon points
    fun fireCannonball() {
        // calculate the Cannonball velocity's x component
        val velocityX = (CannonView.Companion.CANNONBALL_SPEED_PERCENT *
                view.screenWidth * Math.sin(barrelAngle)) as Int

        // calculate the Cannonball velocity's y component
        val velocityY = (CannonView.Companion.CANNONBALL_SPEED_PERCENT *
                view.screenWidth * -Math.cos(barrelAngle)) as Int

        // calculate the Cannonball's radius
        val radius = (view.screenHeight *
                CannonView.Companion.CANNONBALL_RADIUS_PERCENT) as Int

        // construct Cannonball and position it in the Cannon
        cannonball = Cannonball(
            view, Color.BLACK,
            CannonView.Companion.CANNON_SOUND_ID, -radius,
            view.screenHeight / 2 - radius, radius, velocityX.toFloat(),
            velocityY.toFloat()
        )
        cannonball!!.playSound() // play fire Cannonball sound
    }

    // draws the Cannon on the Canvas
    fun draw(canvas: Canvas?) {
        // draw cannon barrel
        canvas!!.drawLine(
            0f, (view.screenHeight / 2).toFloat(), barrelEnd.x.toFloat(),
            barrelEnd.y.toFloat(), paint
        )

        // draw cannon base
        canvas.drawCircle(
            0f, (view.screenHeight / 2).toFloat(),
            baseRadius as Float, paint
        )
    }

    // removes the Cannonball from the game
    fun removeCannonball() {
        cannonball = null
    }

    // constructor
    init {
        paint.strokeWidth = barrelWidth.toFloat() // set width of barrel
        paint.color = Color.BLACK // Cannon's color is Black
        align(Math.PI / 2) // Cannon barrel facing straight right
    }
}