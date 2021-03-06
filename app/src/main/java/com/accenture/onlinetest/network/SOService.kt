package com.accenture.onlinetest.network

import com.accenture.onlinetest.models.Base
import retrofit2.http.GET
import retrofit2.http.Headers
import io.reactivex.Observable

interface SOService {
    @Headers("Content-Type: application/json")
    @GET("/s/2iodh4vg0eortkl/facts.json")
    fun getFacts(): Observable<Base>
}
