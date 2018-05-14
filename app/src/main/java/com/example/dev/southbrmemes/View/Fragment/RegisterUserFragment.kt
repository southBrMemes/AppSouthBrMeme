package com.example.dev.southbrmemes.View.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.Model.BussnesRule.ValidateComponent
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Insert.UserInsertDomain
import com.example.dev.southbrmemes.R
import kotlinx.android.synthetic.main.fragment_register.*


/**
 * A simple [Fragment] subclass.
 */
class RegisterUserFragment : Fragment() {

    companion object {
        fun getInstance(): RegisterUserFragment {
            return RegisterUserFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.setTitle("Cadastro")
        (activity as AppCompatActivity).supportActionBar!!.setIcon(null)

        textViewTerm.setOnClickListener { v ->
            chengefragment(TermFragment.getInstance(), R.id.flIndex)
        }

        btnRegisterAccount.setOnClickListener { v ->
            if (ValidateComponent.validadeRegisterUser(editTextRegisterName, editTextRegisterLogin, editTextRegisterPassword, editTextRegisterConfirmPassword, checkboxTerm))
                UserInsertDomain(activity!!).insert(editTextRegisterName.text.toString(), editTextRegisterLogin.text.toString(), editTextRegisterPassword.text.toString())
        }

        btnReturnLogin.setOnClickListener { v ->
            chengefragment(LoginFragment.getInstance(), R.id.flIndex)
        }
    }

    fun chengefragment(`object`: Fragment, id: Int) {
        fragmentManager!!
                .beginTransaction()
                .replace(id, `object`, null)
                .addToBackStack("Fragment")
                .commit()
    }

}// Required empty public constructor
