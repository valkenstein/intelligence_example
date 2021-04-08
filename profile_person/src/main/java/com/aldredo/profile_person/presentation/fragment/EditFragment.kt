package com.aldredo.profile_person.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aldredo.profile_person.R

class EditFragment : Fragment(R.layout.activity_edit) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.save).setOnClickListener {
            Toast.makeText(this.context, "апи не этот экран", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        @JvmStatic
        fun startActivity(activity: Context, bundle: Bundle) {
            val intent = Intent(activity, EditFragment::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}