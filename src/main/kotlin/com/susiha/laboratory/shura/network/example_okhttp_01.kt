package com.susiha.laboratory.shura.network

import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    var okHttpClient = OkHttpClient()
    var request  = Request.Builder()
        .url("ws:www.jd.com")
        .build()
    var call = okHttpClient.newCall(request)
    var response = call.execute()
    println("response --->${response.body()?.string()}")

}