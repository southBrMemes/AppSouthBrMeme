package com.example.dev.southbrmemes.View.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Message
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.IUserRepository
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.UserRepository
import com.example.dev.southbrmemes.Model.Session.SessionManager
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.View.Fragment.LoginFragment
import com.example.dev.southbrmemes.View.Fragment.TermFragment
import com.example.dev.southbrmemes.View.Fragment.TimeLineMemeFragment
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.app_bar_index.*
import kotlinx.android.synthetic.main.content_index.*

class IndexActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var timeLineMemeFragment : TimeLineMemeFragment
    private lateinit var loginFragment: LoginFragment
    private lateinit var termFragment: TermFragment
    private lateinit var _IUserRepository: IUserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)
        setSupportActionBar(toolbar)

        _IUserRepository = UserRepository(this)

        timeLineMemeFragment  = TimeLineMemeFragment.getInstance()
        loginFragment = LoginFragment.getInstance()
        termFragment = TermFragment.getInstance()

        if(_IUserRepository.buscarToken() != null)
            ChangesActivity.changeActivity(LoggedActivity::class.java,this)

        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flIndex, timeLineMemeFragment , null)
                    .commit()

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.index, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_home -> {
                chengefragment(timeLineMemeFragment , flIndex.id)
            }
            R.id.nav_login -> {
                chengefragment(loginFragment, flIndex.id)
            }
            R.id.nav_share -> {
                share()
            }
            R.id.nav_term -> {
                chengefragment(termFragment, flIndex.id)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun chengefragment(`object`: android.support.v4.app.Fragment, id: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(id, `object`, null)
                .addToBackStack("Fragment")
                .commit()
    }

    fun share() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "faça parte do snacktime torne seu negocio mais amplo https://play.google.com/store/apps/details?id=com.wolfdeveloper.snacktimevendedor")
        startActivity(intent)
    }
}
