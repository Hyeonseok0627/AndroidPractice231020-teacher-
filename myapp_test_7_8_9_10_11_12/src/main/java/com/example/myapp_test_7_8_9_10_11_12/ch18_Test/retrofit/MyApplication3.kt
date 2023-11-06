package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 자주 사용이 될 네트워크 인터페이스를 , 미리 시스템에 등록.
// 메니페스트에 등록해서,
// 앱이 실행이 되면, 해당 MyApplication 의 기능이
// 메모리 등록이 되어서 사용하기 편함.
class MyApplication3 : Application(){


    val BASE_URL = "https://apis.data.go.kr/6260000/"


    //add....................................
    var networkService: INetworkService3
    val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    init {
        networkService = retrofit.create(INetworkService3::class.java)
    }


}