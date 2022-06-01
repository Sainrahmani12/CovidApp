package com.example.covidapp.api

import com.example.covidapp.model.ResponseItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("indonesia")
    fun getIndonesia(): Call<ArrayList<ResponseItem>>

}