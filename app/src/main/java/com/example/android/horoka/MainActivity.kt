package com.example.android.horoka

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import timber.log.Timber

class MainActivity : AppCompatActivity() {

//    Initiate nav_host

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.who_loves -> {
//                TODO: Implement Dialog with preference
                Timber.v("Menu item clicked")
                findNavController(this,R.id.nav_host_fragment).navigate(R.id.dialogFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
