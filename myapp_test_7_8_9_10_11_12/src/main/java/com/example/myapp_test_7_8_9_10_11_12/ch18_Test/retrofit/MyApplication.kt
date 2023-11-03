package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


// 자주 사용이 될 네트워크 인터페이스를, 미리 시스템에 등록.
// 메니페스트에 등록해서,
// 앱이 실행이 되면, 해당 MyApplication의 기능이
// 메모리 등록이 되어서 사용하기 편함.
class MyApplication : Application(){

    // 1) 통신에 필요한 인스턴스를 선언 및 초기화
    // 아직 초기화 안해서 오류가 뜸.
    val networkService : INetworkService
    // 2_ 통신할 서버의 URL 주소를 등록함.
    val retrofit : Retrofit
        get() = Retrofit.Builder() // 레스트 통신할 서버(Builder써주면 뒤에 '.'을 써서 할당해줄 수 있음)
            .baseUrl("https://reqres.in/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // 초기화 할당하는 부분.
    init {
        networkService = retrofit.create(INetworkService::class.java)
    }

}