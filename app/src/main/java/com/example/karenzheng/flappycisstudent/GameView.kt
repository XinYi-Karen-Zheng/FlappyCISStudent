package com.example.karenzheng.flappycisstudent

import android.content.Context
import android.view.View
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.view.MotionEvent
import java.util.*
import java.lang.Math
import android.graphics.Bitmap



class GameView(context: Context?, w: Float, h: Float) : View(context) {
    var timer: Timer
    var timehandler: Handler

    var score = 0

    var bird = BitmapFactory.decodeResource(getResources(), R.drawable.bird)
    var resized = Bitmap.createScaledBitmap(bird,(bird.getWidth()*0.3).toInt(), (bird.getHeight()*0.3).toInt(), true);

    val paint = Paint()

    val wide = w
    val height = h

    var playerX = 10.toFloat()
    var playerY = 3*height/6
    var x = resized.getWidth()*0.3
    var y = resized.getHeight()*0.3

    var input = 0.toFloat()

    var wall1X = wide
    var wall1Y = 5*height/6
    var wallSpeed = 5

    var r = 100

    var diff = 0

    init {
        timer = Timer()
        timehandler = Handler()
        val timetask = object : TimerTask() {
            override fun run() {
                timehandler.post(Runnable {
                    invalidate()

                    if (checkCollision()) {
                        timer.cancel()
                        val i = Intent(context, ScoreBoardActivity::class.java)
                        i.putExtra("score", score.toString())
                        context!!.startActivity(i)
                    }
                })
            }
        }
        startTimer(timetask)
    }

    fun startTimer(task: TimerTask) {
        timer = Timer()
        timer.schedule(task, 1, 10)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.setColor(Color.parseColor("#000000"));

        canvas?.drawBitmap(resized, (playerX-x/2).toFloat(), (playerY-y/2).toFloat(), paint)
        paint.setColor(Color.parseColor("#f9a602"))
        //canvas?.drawBitmap(resizedDown, wall1X, wall1Y, paint)
        canvas?.drawRect(wall1X-75, wall1Y - r, wall1X + 75, wall1Y + 40, paint)

        val top = wall1Y - r - 450
        if (top < 0){
            input = 0.toFloat()
        } else{
            input = top
        }

        canvas?.drawRect(wall1X-75, 0.toFloat(), wall1X+75, input, paint)
        //canvas?.drawRect(quesadillaX-40, quesadillaY-40, quesadillaX+40, quesadillaY+40, paint)

        //the wall moving
        wall1X -= wallSpeed

        //the player moving
        if(playerY <= height) {
            playerY -= diff
            diff -= 1
        }

        //after a wall reaching the end
        if (wall1X < 0) {
            wall1X = wide
            r = randomY()
            score ++
        }

    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {}
            MotionEvent.ACTION_UP -> {jump()}
            MotionEvent.ACTION_CANCEL -> {}
        }
        return true
    }

    fun jump() {
        diff = 20

    }

    fun checkCollision(): Boolean {
        if ((playerY + y/2 >= wall1Y - r)
            && (playerX + x/2 >= wall1X - 75)){
            return true
        } else if ((playerX + x/2 >= wall1X - 75)
            && (playerY - y/2 <= input)){
            return true
        } else if (playerY > height){
            return true
        } else if (playerY < 0){
            return true
        }
        return false
    }

    fun randomY(): Int{
        var min = 50
        var max = (height - 700).toInt()
        var randomY = Math.random() * max + min
        return randomY.toInt()
    }

}