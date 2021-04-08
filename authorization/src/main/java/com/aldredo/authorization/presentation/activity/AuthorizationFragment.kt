package com.aldredo.authorization.presentation.activity

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.aldredo.authorization.R
import com.aldredo.authorization.di.AuthoComponent
import com.aldredo.authorization.navigation.Router
import com.aldredo.core.base.util.IManagerToken
import com.redmadrobot.inputmask.MaskedTextChangedListener
import com.redmadrobot.inputmask.helper.AffinityCalculationStrategy
import javax.inject.Inject

class AuthorizationFragment : Fragment(R.layout.authorization_main) {
    private val affineFormats: MutableList<String> = ArrayList()

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var managerToken: IManagerToken
    private lateinit var inputNumber: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthoComponent.create(this)
        router.attachContext(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        setListener()
    }

    private fun init() {
        inputNumber = requireView().findViewById(R.id.input_number)
        val listener = MaskedTextChangedListener.installOn(
            inputNumber,
            "+7 ([000]) [000] - [00] - [00]",
            affineFormats.apply { add("+7 ([000]) [000]-[00]-[00]") },
            AffinityCalculationStrategy.PREFIX,
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String
                ) {
                }
            }
        )
        inputNumber.hint = listener.placeholder()
    }

    override fun onResume() {
        super.onResume()
        router.attachContext(this)
        checkAuthorization()
    }

    private fun checkAuthorization() {
        if (managerToken.checkStateAuthorized()) {
            //parentFragmentManager.beginTransaction().remove(this)
           // childFragmentManager.popBackStack(this.id, 1)
               //router.finish()
        }
    }

    override fun onStop() {
        super.onStop()
        router.detachContext()
    }

    private fun setListener() {
        requireView().findViewById<Button>(R.id.button).setOnClickListener {
            router.openCodeVerification()

//            val number = cleanNumber(inputNumber.text)
//            if (number.count() > 9) {
//                AuthorizationCodeActivity.startActivity(requireActivity(), Bundle().apply {
//                    putString(AuthorizationCodeActivity.ARG_NUMBER, number)
//                })
//            } else Toast.makeText(requireContext(), "Неверный номер", Toast.LENGTH_LONG)
        }
    }

    private fun cleanNumber(text: Editable): String {
        var cleanText = ""
        for (i in 0 until text.count()) {
            val numberOrNull = text[i].toString().toIntOrNull()
            if (numberOrNull != null)
                cleanText += numberOrNull
        }
        return cleanText
    }
}
