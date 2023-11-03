package com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit

import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserListModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

// 통신 라이브러리 : retrofit2 이용해서,
// 인터페이스, 추상 메서드를 만들어서,
// 레트로핏한테 전달 : 인터페이스 통으로 전달하면,
// 여기에 정의된 함수를 이용해서, 통신을 함. crud 개념.

interface INetworkService2 {
    @GET("api/users")
    // baseuri : https://reqres.in/
    // get : 가져올 주소가 정확히 이것
    // https://reqres.in/api/users?page=2
    // : Call<UserListModel> : 반환 타입 지정 표현
    // 예, 함수 호출 -> doGetUserList("3")
    // https://reqres.in/api/users?page=3 이런식으로 서버로 보내게 되는 것
    // 반환 타입은 Call, 담겨진 데이터는 리스트의 요소가 (UserModel)담겨진 내용을 전달받음.
    fun doGetUserList(@Query("page") page: String): Call<UserListModel> // import 중 retrofit2으로 해주면 오류 사라짐

    // 프로필 이미지를 받기 위한, 추상 함수(선언만 하고 본문 내용없는 것).
    //    @Url
//    기본 baseUrl 이 있지만, 다른 url 를 호출 할 때 사용.
    @GET
    fun getAvatarImage(@Url url: String): Call<ResponseBody>

}