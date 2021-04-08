package com.aldredo.promotion_card

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.google.gson.Gson

class CardPromotionActivity : AppCompatActivity() {
    lateinit var holder: ActionHolder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val contentView = LayoutInflater.from(this).inflate(R.layout.activity_card_promotion, null)
        setContentView(contentView)
        val newsListJson = intent.getStringExtra(ARG_ACTIONS) ?: ""
        val position = intent.getIntExtra(ARG_POSITION, 0)

        Gson().fromJson(newsListJson, Array<ActionModel>::class.java).toList().apply {
            holder = ActionHolder(this@CardPromotionActivity, contentView, this, position).apply {
                bind()
            }
        }
    }

    override fun onPause() {
        holder.pause()
        super.onPause()
    }

    override fun onResume() {
        holder.resume()
        super.onResume()
    }

    companion object {
        const val ARG_ACTIONS = "actionAll"
        const val ARG_POSITION = "position"

        @JvmStatic
        fun startActivity(activity: Context, bundle: Bundle) {
            val intent = Intent(activity, CardPromotionActivity::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}