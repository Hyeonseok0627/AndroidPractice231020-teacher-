package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntentActivity : AppCompatActivity() {
    // 1번 화면
    lateinit var binding : ActivityTestIntentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 인텐트에 기본 데이터 추가 및 가져오기 테스트.
        binding.testBtn2.setOnClickListener {
            // 데이터를 추가해서 + 화면 이동. : 내부 앱끼리 연동
            val intent = Intent(this@TestIntentActivity,
                TestIntent2DetailActivity::class.java)

            // 위에서 lateinit var 선언을 하면, 화면이 최초 그려질 때,
            // 입력이 안된 상태로 할당이 되어 버림.
            // 그래서, 입력 란에 입력 후, 그 시점에 있는 값을
            // 지금 , 버튼을 눌려서 전달을 하기.
            val email = binding.emailEdit.text.toString()
            val password = binding.passwordEdit.text.toString()
            val age = binding.ageEdit.text.toString()

            // 데이터 추가 해보기.
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            intent.putExtra("age",age)
            startActivity(intent)

        }

        // 후처리, 1번 화면에서 -> 2번 화면으로 이동후 -> 특정 액션
        // -> 다시 1번 화면으로 데이터 가져오기
        // 예) 1번화면 -> 2번화면(갤러리) -> 2번에서 사진 선택 후 -> 1번으로 가져오기.

        binding.testBtn.setOnClickListener {
            val intent : Intent = Intent(this@TestIntentActivity,
                TestIntent2DetailActivity::class.java)
            // 데이터 보내기 했음.
//            intent.putExtra("email","lsy")
//            intent.putExtra("password","1234")

            // 후처리 하기.
            // 10 : 요청 코드 , 식별하기위한 식별자.
            startActivityForResult(intent,10)
            // 2번화면으로 넘어감.
        }


        // 후처리 함수 정의하기.
        // ActivityResultContracts  -> StartActivityForResult, 시스템에서 정의해둔 함수
        // 2번 화면에서 데이터를 가져왔을 때, 처리하는 함수.
        val requestLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val result2 = it.data?.getStringExtra("result")
            binding.resultData1.text = "결과 result2 : $result2"
        }

        //후처리 데이터 가져오기 방법2
        binding.testBtn3.setOnClickListener {
            val intent : Intent = Intent(this@TestIntentActivity,
                TestIntent2DetailActivity::class.java)

            //
            requestLauncher.launch(intent)
            // 2번화면으로 넘어감.
        }

        // 인텐트 필터를 이용해서 외부 앱에 접근하기.
        // 인텐트 : 시스템에 전달하는 메세지
        // 의 속성에 intent-filter : 무엇을 하는지를 나타내는 라벨링.
        // 시스템이 해당 필터의 요소를 보고서 해당 외부 앱을 연결 시켜줌.
        // 예) 좌표값이다. 위도 경도 -> 지도 앱(외부 앱)
        // 예2) http 주소다. -> 웹 브라우저 앱 (외부 앱)
        binding.testBtn4.setOnClickListener {
            // 지동 맵 열기 테스트.
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:37.7749,127.4194"))
            // 현재, 지도 관련 앱이 다양하게 없어서, 기본 앱: 구글 맵이 나옴.
            // 만약, 지도 관련 앱이 여러 개가 있다면, 특정 앱을 선택할 수 있음.
            // 사용하는 앱의 패키지명을 정확히 입력함.
            intent.setPackage("com.google.android.apps.maps")
            startActivity(intent)
        }



        // onCreate 마지막
    }
    // onCreate 밖에서 , 재정의하기.
    // 2번 화면에서 넘어온 데이터를 받아서 , 처리하는 함수.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 자동완성 된 코드에 필요한 로직만 추가하기.
        // 2번화면에서, resultCode 를 설정하고,
        // 넘어온 데이터는 data 에 담아져 있다.
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("resultData")
            Log.d("lhs","넘어온 결과값 : $result")
        }
    }

}