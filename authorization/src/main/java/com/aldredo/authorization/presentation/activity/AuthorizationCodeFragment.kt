package com.aldredo.authorization.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.aldredo.authorization.R
import com.aldredo.authorization.di.AuthoComponent
import com.aldredo.authorization.navigation.Router
import com.aldredo.authorization.presentation.Presenter
import javax.inject.Inject

class AuthorizationCodeFragment : Fragment(R.layout.authorization_code) {
    @Inject
    lateinit var presenter: Presenter

    @Inject
    lateinit var router: Router

    private val inputs: ArrayList<EditText> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthoComponent.create(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router.attachContext(this)
        init(view)
    }

    private fun init(view: View) {
        inputs.apply {
            add(view.findViewById(R.id.input_1))
            add(view.findViewById(R.id.input_2))
            add(view.findViewById(R.id.input_3))
            add(view.findViewById(R.id.input_4))
        }
        for (index in 0 until inputs.count())
            inputs[index].addTextChangedListener(CustomTextWatcher(index))
        requireView().findViewById<Button>(R.id.button).setOnClickListener {
            var code = ""
            for (item in inputs)
                code += item.text.toString()

            if (code.count() >= 4) {
                 presenter.putData("2434",
                    // intent.getStringExtra(ARG_NUMBER) ?: "",
                     code)
                //router.openPersonRoom()
            } else {
                //TODO сообщение об ошибке
            }
        }
    }

    override fun onStop() {
        router.detachContext()
        super.onStop()
    }

    inner class CustomTextWatcher(private val index: Int) : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, before: Int, p3: Int) {
            if (before == 1) {
                if (index > 0)
                    inputs[index - 1].requestFocus()
            } else {
                if (index < inputs.count() - 1)
                    inputs[index + 1].requestFocus()
            }
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {}
    }

    companion object {
        const val ARG_NUMBER = "number"

        @JvmStatic
        fun startActivity(activity: Context, bundle: Bundle) {
            val intent = Intent(activity, AuthorizationCodeFragment::class.java)
            intent.putExtras(bundle)
            activity.startActivity(intent)
        }
    }
}