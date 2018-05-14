package com.example.dev.southbrmemes.View.Fragment


import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.dev.southbrmemes.Model.BussnesRule.ValidateComponent
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Data.UserDataDomain
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Delete.UserDeleteDomain
import com.example.dev.southbrmemes.Presenter.Rest.Domain.Update.UserUpdateDomain
import com.example.dev.southbrmemes.R
import kotlinx.android.synthetic.main.fragment_edit_user.*


/**
 * A simple [Fragment] subclass.
 */
class EditUserFragment : Fragment() {


    companion object {
        fun getInstance(): EditUserFragment {
            return EditUserFragment();
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.setTitle("Editar Conta")
        (activity as AppCompatActivity).supportActionBar!!.setIcon(null)

        UserDataDomain(activity!!).data(name = editTextEditName,login = editTextEditLogin)

        btnEditAccount.setOnClickListener { v ->
            if (ValidateComponent.validadeUpdateUser(editTextEditName, editTextEditLogin, editTextEditPassword, editTextEditNewPassword))
                UserUpdateDomain(activity!!).update(editTextEditName.text.toString(), editTextEditLogin.text.toString(), editTextEditPassword.text.toString(),editTextEditNewPassword.text.toString())
        }

        btnDeleteAccount.setOnClickListener { v ->
            AlertDialog.Builder(activity!!)
                    .setTitle("Deletar conta")
                    .setMessage("Tem certeza que deseja deletar esta conta ?")
                    .setPositiveButton("Sim",
                            DialogInterface.OnClickListener { dialogInterface, i ->
                                UserDeleteDomain(activity!!).delete()
                            })
                    .setNegativeButton("Não", null)
                    .show()
        }

    }

}// Required empty public constructor
