package com.accenture.onlinetest.screens.landing

import com.accenture.onlinetest.models.Base

interface LandingView {
    fun init()
    fun onSuccess(factList: Base)
    fun onError(error: String)
}