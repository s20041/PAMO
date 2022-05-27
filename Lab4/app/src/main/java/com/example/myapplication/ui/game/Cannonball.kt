// Cannonball.java
// Represents the Cannonball that the Cannon fires
package com.example.myapplication.ui.game

import android.graphics.Canvas
import android.graphics.Rect

class Cannonball     // constructor
    (
    view: CannonView, color: Int, soundId: Int, x: Int,
    y: Int, radius: Int, private var velocityX: Float, velocityY: Float
) : GameElement(
    view, color, soundId, x, y,
    2 * radius, 2 * radius, velocityY
) {
    // returns true if this Cannonball is on the screen
    var isOnScreen = true
        private set

    // get Cannonball's radius
    private val radius: Int
        private get() = (shape.right - shape.left) / 2

    // test whether Cannonball collides with the given GameElement
    fun collidesWith(element: GameElement?): Boolean {
        return Rect.intersects(shape, element!!.shape) && velocityX > 0
    }

    // reverses the Cannonball's horizontal velocity
    fun reverseVelocityX() {
        velocityX *= -1f
    }

    // updates the Cannonball's position
    override fun update(interval: Double) {
        super.update(interval) // updates Cannonball's vertical position

        // update horizontal position
        shape.offset((velocityX * interval).toInt(), 0)

        // if Cannonball goes off the screen
        if (shape.top < 0 || shape.left < 0 || shape.bottom > view.screenHeight || shape.right > view.screenWidth) isOnScreen =
            false // set it to be removed
    }

    // draws the Cannonball on the given canvas
    override fun draw(canvas: Canvas?) {
        canvas!!.drawCircle(
            (shape.left + radius).toFloat(), (
                    shape.top + radius).toFloat(), radius.toFloat(), paint
        )
    }


}