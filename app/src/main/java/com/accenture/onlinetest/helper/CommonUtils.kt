package com.accenture.onlinetest.helper

import android.content.Context
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
}