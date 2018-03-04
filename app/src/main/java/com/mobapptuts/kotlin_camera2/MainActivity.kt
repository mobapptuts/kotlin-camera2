package com.mobapptuts.kotlin_camera2

import android.app.PictureInPictureParams
import android.arch.lifecycle.ViewModelProviders
import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Menu
import android.view.MenuItem
import com.mobapptuts.kotlin_camera2.Fragments.PreviewFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val drawerToogle by lazy {
        ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close)
    }

    val cameraViewModel by lazy {
        ViewModelProviders.of(this).get(CameraViewModel::class.java)
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        if (cameraViewModel.isVideoPlaying) {
            val params = PictureInPictureParams.Builder().apply {
                setAspectRatio(cameraViewModel.aspectRatio)
            }
            enterPictureInPictureMode(params.build())
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        navigationView.setNavigationItemSelectedListener {
            selectDrawerItem(it)
            true
        }
        drawerLayout.addDrawerListener(drawerToogle)

        val fragment = PreviewFragment.newInstance()
        addFragment(fragment)

        // The ViewPager will be implemented once it's fragments have
        // been implemented.
//        val pagerAdapter = CamFragmentPagerAdapter(supportFragmentManager)
//        viewPager.adapter = pagerAdapter
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        drawerToogle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        drawerToogle.onConfigurationChanged(newConfig)
    }

    private fun selectDrawerItem(item: MenuItem) {
        var fragment: Fragment? = null

//        val fragmentClass = TODO("Will be implemented later")
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (drawerToogle.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.fragment_menu, menu)
        return false
    }

    private fun addFragment(fragment: Fragment?) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer, fragment)
        fragmentTransaction.commit()
    }
}
