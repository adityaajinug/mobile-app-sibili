package com.adit.sibili.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Service {

    private fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost/sibili/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getInstance() : ApiEndPoint {
        return getClient().create(ApiEndPoint::class.java)
    }
}