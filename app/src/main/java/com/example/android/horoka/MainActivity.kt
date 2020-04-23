package com.example.android.horoka

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation.findNavController
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var connectivityManager : ConnectivityManager
    //    Initiate nav_host
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//    This is for tracking Network Status
        connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), NetworkCallbackImp)
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
                Timber.v("Menu item clicked")
                findNavController(this,R.id.nav_host_fragment).navigate(R.id.dialogFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
