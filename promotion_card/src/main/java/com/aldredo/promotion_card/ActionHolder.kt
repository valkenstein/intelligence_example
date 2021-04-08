package com.aldredo.promotion_card

import android.animation.ObjectAnimator
import android.app.Activity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.animation.doOnEnd
import com.bumptech.glide.Glide

class ActionHolder(
    private val activity: Activity,
    view: View,
    private val currentList: List<ActionModel>,
    currentPosition: Int
) {
    private var position = currentPosition
        set(value) {
            field = if ((value < currentList.count()) && value >= 0) {
                value
            } else if (value < 0) {
                0
            } else {
                exit()
                position
            }
        }

    private var isExit = false
    private var isPause = false
    private val head = view.findViewById<TextView>(R.id.head)
    private val name = view.findViewById<TextView>(R.id.name)
    private val left = view.findViewById<View>(R.id.left_focus)
    private val right = view.findViewById<View>(R.id.right_focus)
    private val body = view.findViewById<ViewGroup>(R.id.body)
    private val cross = view.findViewById<ImageView>(R.id.cross)
    private val imageView = view.findViewById<ImageView>(R.id.imageView)
    private val description = view.findViewById<TextView>(R.id.description)
    private val groupElement: ArrayList<View> = arrayListOf()
    private val objectAnimators: ArrayList<ObjectAnimator> = arrayListOf()

    init {
        for (i in 0 until currentList.count())
            body.apply {
                val progress = LayoutInflater.from(context)
                    .inflate(R.layout.progress_bar_example, this, false)

                ObjectAnimator.ofInt(progress as ProgressBar, "progress", 0, 100).apply {
                    duration = 3500
                    objectAnimators.add(this)
                }
                addView(progress)
                if (position > i)
                    objectAnimators[i].end()

            }
        groupElement.add(name)
        groupElement.add(head)
        groupElement.add(body)
        groupElement.add(description)
        groupElement.add(cross)

        setListener()
        startAnimation()
    }

    private fun setListener() {
        cross.setOnClickListener {
            exit()
        }

        imageView.setOnClickListener {
            nextFocusSlide()
        }

        right.setOnClickListener {
            nextFocusSlide()
        }

        left.setOnClickListener {
            previousFocusSlide()
        }

        left.setOnTouchListener { _, motionEvent ->
            keyEvent(motionEvent.action)
        }

        right.setOnTouchListener { _, motionEvent ->
            keyEvent(motionEvent.action)
        }
    }

    private fun keyEvent(action: Int) =
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                for (item in groupElement)
                    item.animate()
                        .alpha(0f)
                        .duration = 1000

                objectAnimators[position].pause()
                false
            }
            MotionEvent.ACTION_UP -> {
                for (item in groupElement)
                    item.animate()
                        .alpha(1f)
                        .duration = 500

                objectAnimators[position].resume()
                false
            }
            else -> false
        }

    private fun startAnimation() {
        objectAnimators[position].start()
        objectAnimators[position].doOnEnd {
            position++
            bind()
            startAnimation()
        }
    }

    private fun previousFocusSlide() {
        objectAnimators[position].removeAllListeners()
        objectAnimators[position].start()
        objectAnimators[position].pause()
        position--
        bind()
        startAnimation()
    }

    private fun nextFocusSlide() {
        objectAnimators[position].removeAllListeners()
        objectAnimators[position].end()
        position++
        bind()
        startAnimation()
    }

    fun bind() {
        if (isExit || isPause) return
        name.text = currentList[position].create_date
        head.text = currentList[position].title
        description.text = currentList[position].title

        imageView.apply {
            Glide.with(this).load(currentList[position].image).into(this)
        }
    }

    fun pause() {
        isPause = true
        objectAnimators[position].pause()
    }

    fun resume() {
        isPause = false
        objectAnimators[position].resume()
    }

    private fun exit() {
        isExit = true
        for (item in objectAnimators) {
            item.cancel()
            item.removeAllListeners()
        }
        activity.finish()
    }
}