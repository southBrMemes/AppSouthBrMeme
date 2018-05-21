package com.example.dev.southbrmemes.View.Activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.IUserRepository
import com.example.dev.southbrmemes.Model.Infra.Repository.UserRepository.UserRepository
import com.example.dev.southbrmemes.Presenter.ChangesScreen.ChangesActivity
import com.example.dev.southbrmemes.R
import com.example.dev.southbrmemes.View.Fragment.LoginFragment
import com.example.dev.southbrmemes.View.Fragment.TermFragment
import com.example.dev.southbrmemes.View.Fragment.TimeLineMemeFragment
import com.example.dev.southbrmemes.View.PopUp.PopUpRegisterMeme
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_index.*
import kotlinx.android.synthetic.main.content_index.*


class IndexActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var timeLineMemeFragment: TimeLineMemeFragment
    private lateinit var loginFragment: LoginFragment
    private lateinit var termFragment: TermFragment
    private lateinit var _IUserRepository: IUserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        var toolbar = findViewById<View>(R.id.toolbar) as android.support.v7.widget.Toolbar
        setSupportActionBar(toolbar)

        MobileAds.initialize(this, "ca-app-pub-6530429566946986~2014950797")

        toolbar?.setOnClickListener {
            PopUpRegisterMeme(activity = this).creatPopUpMenu()
        }

        _IUserRepository = UserRepository(this)

        if (_IUserRepository.buscarToken() != null)
            ChangesActivity.changeActivity(LoggedActivity::class.java, this)


        timeLineMemeFragment = TimeLineMemeFragment.getInstance()
        loginFragment = LoginFragment.getInstance()
        termFragment = TermFragment.getInstance()


        if (savedInstanceState == null)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.flIndex, timeLineMemeFragment, null)
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
                chengefragment(timeLineMemeFragment, flIndex.id)
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
            R.id.nav_app -> {
                myApps("https://play.google.com/store/apps/developer?id=SnackTime")
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
        val appPackageName = packageName
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        try {
            intent.putExtra(Intent.EXTRA_TEXT, "conheça o south br memes https://play.google.com/store/apps/details?id=$appPackageName")
            startActivity(intent)
        } catch (e: Exception) {
        }
    }


    fun myApps(url: String) {
        var i: Intent = Intent(android.content.Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i)
    }

    override fun onStart() {
        super.onStart()
        aksCheckedPermissions()
    }

    public fun aksCheckedPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            askForPermissions()

        }
    }

    private fun askForPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE),
                7)
    }
}
