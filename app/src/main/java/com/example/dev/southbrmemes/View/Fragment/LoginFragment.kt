package com.example.dev.southbrmemes.View.Fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.Model.BussnesRule.ValidateComponent
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Auth.UserLoginDomain
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.View.Activity.LoggedActivity
import kotlinx.android.synthetic.main.fragment_login.*


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : Fragment() {

    companion object {
        fun getInstance(): LoginFragment {
            return LoginFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar!!.setTitle("South BR Memes")
        (activity as AppCompatActivity).supportActionBar!!.setIcon(null)


        btnLogin.setOnClickListener { v ->
            if(ValidateComponent.validadeLogin(login = editTextLogin,password = editTextPassword))
            UserLoginDomain(activity!!).login(editTextLogin.text.toString(),editTextPassword.text.toString())
        }

        btnRegister.setOnClickListener { v ->
            chengefragment(RegisterUserFragment.getInstance(), R.id.flIndex)
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
