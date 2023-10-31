package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntentActivity : AppCompatActivity() {
    // 1번 화면
    lateinit var binding : ActivityTestIntentBinding
    //    lateinit var email : String
//    lateinit var password : String
//    lateinit var age : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        email = binding.emailEdit.text.toString()
//        password = binding.passwordEdit.text.toString()
//        age = binding.ageEdit.text.toString()

        // 데이터 확인
//        Log.d("lsy","데이터 확인 버튼 클릭 전 0번 화면 이메일: ${email} , " +
//                "패스워드: ${password}, 나이: ${age}")

        // 인텐트에 기본 데이터 추가 및 가져오기 테스트.
        binding.testBtn2.setOnClickListener {
            // 데이터를 추가해서 + 화면 이동. : 내부 앱끼리 연동
            val intent = Intent(this@TestIntentActivity,
                TestIntent2DetailActivity::class.java)

            // 위에서 lateinit var 선언을 하면, 화면이 최초 그려질 때,
            // 입력이 안된 상태로 할당이 되어 버림.(onCreate 함수가 최초 실행될 때 한번만 동작하는 함수)
            // 그래서, 입력 란에 입력 후, 그 시점에 있는 값을
            // 지금 , 버튼을 눌려서 전달을 하기.
            val email = binding.emailEdit.text.toString()
            val password = binding.passwordEdit.text.toString()
            val age = binding.ageEdit.text.toString()

            // 데이터 확인
            Log.d("lhs","데이터 확인 1번 화면 이메일: ${email} , " +
                    "패스워드: ${password}, 나이: ${age}")
            // 데이터 추가 해보기.
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            intent.putExtra("age",age)
            startActivity(intent)

        }

        // 후처리, 1번 화면에서 -> 2번 화면으로 이동 후 -> 특정 액션
        // -> 다시 1번 화면으로 데이터 가져오기
        // 예) 1번 화면 -> 2번 화면(갤러리) -> 2번에서 사진 선택 후 -> 1번으로 가져오기.

        binding.testBtn.setOnClickListener {
            val intent : Intent = Intent(this@TestIntentActivity,
                TestIntent2DetailActivity::class.java)
            // 데이터 보내기 했음.
//            intent.putExtra("email","lhs")
//            intent.putExtra("password","1234")

            // 후처리 하기.
            // 10: 요청 코드, 식별하기위한 식별자.
            startActivityForResult(intent, 10)
            // 2번 화면으로 넘어감.
        }

        // onCreate 마지막
    }
    // onCreate 밖에서, 재정의하기.
    // 2번 화면에서 넘어온 데이터를 받아서, 처리하는 함수.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 자동완성 된 코드에 필요한 로직만 추가하기.
        // 2번 화면에서, resultCode를 설정하고,
        // 넘어온 데이터는 data에 담아져 있다.
        if(requestCode == 10 && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("resultData")
        }
    }
}