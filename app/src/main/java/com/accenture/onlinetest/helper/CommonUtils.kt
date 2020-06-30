package com.accenture.onlinetest.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.accenture.onlinetest.network.RestClient
import com.accenture.onlinetest.network.SOService

object CommonUtils {
    val BASE_URL = "https://dl.dropboxusercontent.com"
    fun getSOService(): SOService {
        return RestClient.getClient(BASE_URL)!!.create(SOService::class.java)
    }

    fun showToast(context: Context, message: String) {
        Toast.makeText(
            context,
            message, Toast.LENGTH_SHORT
        ).show()
    }
    //checking the internet connection using System services
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) {
                for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        }
        return false
    }
}