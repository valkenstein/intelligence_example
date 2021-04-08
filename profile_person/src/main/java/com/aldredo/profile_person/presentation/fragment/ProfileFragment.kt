package com.aldredo.profile_person.presentation.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aldredo.core.base.navigation.mediator.IProfilePersonMediator
import com.aldredo.core.base.util.IManagerToken
import com.aldredo.profile_person.R
import com.aldredo.profile_person.di.ProfileComponent
import javax.inject.Inject


class ProfileFragment : Fragment(R.layout.activity_profile) {
    @Inject
    lateinit var mediator: IProfilePersonMediator

    @Inject
    lateinit var manager: IManagerToken

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProfileComponent.create(this).inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id: Int = (requireView().parent as ViewGroup).id

        view.findViewById<TextView>(R.id.edit_data).setOnClickListener {
            mediator.openEditPerson(this, Bundle())
        }

        view.findViewById<TextView>(R.id.history).setOnClickListener {
            mediator.openOrderHistory(this, Bundle())
        }

        view.findViewById<TextView>(R.id.support).setOnClickListener {
            Toast.makeText(this.context, "нет апи и макета", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<TextView>(R.id.exit).setOnClickListener {
            //requireActivity().onBackPressed()
            manager.removeToken()
            mediator.openAuthorization(this, Bundle())
        }
    }

    companion object {
        @JvmStatic
        fun startActivity(activity: Context, bundle: Bundle) {
            val intent = Intent(activity, ProfileFragment::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}
