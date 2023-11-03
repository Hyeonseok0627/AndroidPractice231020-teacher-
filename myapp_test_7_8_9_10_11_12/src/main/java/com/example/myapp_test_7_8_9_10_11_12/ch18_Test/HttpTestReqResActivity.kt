package com.example.myapp_test_7_8_9_10_11_12.ch18_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.adapter.MyAdapterRetrofit
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.model.UserListModel
import com.example.myapp_test_7_8_9_10_11_12.ch18_Test.retrofit.MyApplication
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityHttpTestReqResBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpTestReqResActivity : AppCompatActivity() {
    lateinit var binding : ActivityHttpTestReqResBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpTestReqResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // reqres.in : 외국에서 무료 REST 테스트 서버를 제공해주는 곳.
        // 데이터를 받아서, 리사이클러 뷰에 출력 해보기.
        // 리사이클러 뷰 준비물 1) 어댑터 2) 뷰홀더 3) 목록 요소의 뷰 4) 데이터 (제공받는 데이터가 달라지면, 3)과 4)만 바뀔 예정)


        // http 통신 준비물
        // 1) MyApplication 설정 파일 2) 인터페이스 3) 모델 또는 모델이 담겨진 리스트가 필요.

        // 준비작업 1) 모델 준비하기.
        // 2) 모델을 요소로 하는 리스트로 준비하기.
        // 주의사항.

//        https://reqres.in/api/users?page=2
//
//        {
//            "page": 2,
//            "per_page": 6,
//            "total": 12,
//            "total_pages": 2,
//            //이 부분이 중요한 포인터, 나중에, 공공데이터 특정 api를 받을 때,
//            // 키 : data, 값 : 배열 [모델1,모델2,모델3,...]
//            "data": [
//            { // 모델: DTO, VO
//                "id": 7,
//                "email": "michael.lawson@reqres.in",
//                "first_name": "Michael",
//                "last_name": "Lawson",
//                "avatar": "https://reqres.in/img/faces/7-image.jpg"
//            },
//            {
//                "id": 8,
//                "email": "lindsay.ferguson@reqres.in",
//                "first_name": "Lindsay",
//                "last_name": "Ferguson",
//                "avatar": "https://reqres.in/img/faces/8-image.jpg"
//            },

        // 작업 순서2 : 네트워크 인터페이스 정의하기.

        // 작업 순서3 : MyApplication, baseurl 등록, 우리가 만든 인터페이스 연결.
        // 매니페스트에 등록해서, 앱이 실행 시, 선언 및 초기화 자동으로 되고,
        // 바로 사용만 하면 됨.

        // 작업 순서4 : 일단, retrofit 통신 이용해서 데이터가 전달하는지의 유무 확인.
        // 데이터 가져오기
        // 1)
        // applicationContext 안에 우리가 등록한 설정이 있고,
        // as MyApplication -> 형 변환
        // 형변환 된 인스턴스 내부에 networkService를 사용하기.
        val networkService = (applicationContext as MyApplication).networkService

        // 2) 호출하는 함수 콜 만들기. 우리가 만든 인터페이스에 등록된
        // 추상 함수를 이용함. 인자값은 페이지 번호 정의를 문자열 타입으로 했음.
        val userListCall = networkService.doGetUserList("2")
        //doGetUserList 이것을 못 불러오는 것은 
        // val networkService = (applicationContext as MyApplication).networkService 여기서
        // .networkService 붙여줘야 함
        
        
        // 실제 통신이 시작이 되는 부분, 이 함수를 통해서 데이터를 받아옴.
        userListCall.enqueue(object : Callback<UserListModel> {
            // 익명 클래스가, Callback, 레트로핏2에서 제공하는 인터페이스를 구현했고,
            // 반드시 재정의해야하는 함수들이 있음.(object를 눌러서 implement를 눌러서 두개 선택 후 ok하면 됨)
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
//                데이터를 성공적으로 받았을 때 수행되는 함수
                val userList = response.body()
                Log.d("lhs", "userList의 값 : ${userList?.data}") // 데이터 못받는 null상태가 될수도 있어서 ?.사용

                // 데이터를 성공적으로 받았다면, 여기서 리사이클러 뷰 어댑터에 연결하면 됨
                    // 리사이클러뷰의 레이아웃을 정하는 부분, 기본인 LinearLayoutManager를 이용했고,
                val layoutManager = LinearLayoutManager(
                    this@HttpTestReqResActivity)
                // 리사이클러뷰에 어댑터 연결
                // 인자값은 : 현재 context : this@HttpTestReqResActivity
                // 2번째 인자값은 : 데이터, 네트워크, 레트로핏2 통신으로 받아온 데이터 리스트
                binding.retrofitRecyclerView.layoutManager = layoutManager
                binding.retrofitRecyclerView.adapter = MyAdapterRetrofit(
                    this@HttpTestReqResActivity,userList?.data)



            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {
                // 데이터를 못 받았을 때 수행되는 함수
                call.cancel()
            }
        })


        // 작업 순서 5 : 리사이클러뷰에 넣는 작업, 재사용.

    }
    // OnCreate
}