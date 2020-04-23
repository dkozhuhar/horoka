package com.example.android.horoka

import android.net.ConnectivityManager
import android.net.Network

object NetworkCallbackImp : ConnectivityManager.NetworkCallback() {
    private var mIsConnected = false
    val isConnected: Boolean
    get() = mIsConnected

    override fun onAvailable(network: Network) {
        mIsConnected = true
    }

    override fun onLost(network: Network) {
        mIsConnected = false
    }
}