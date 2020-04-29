package com.example.android.horoka

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import timber.log.Timber

object NetworkCallbackImp : ConnectivityManager.NetworkCallback() {
    private var mIsConnected = false
    val isConnected: Boolean
    get() = mIsConnected

    override fun onCapabilitiesChanged(network: Network, networkCapabilities: NetworkCapabilities) {
        mIsConnected = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
        Timber.v(networkCapabilities.toString())
    }
}