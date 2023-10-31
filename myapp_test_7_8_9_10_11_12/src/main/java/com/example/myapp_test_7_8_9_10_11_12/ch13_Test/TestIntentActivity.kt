package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntentActivity : AppCompatActivity() {
    // 1번 화면
    lateinit var binding : ActivityTestIntentBinding
    var email : String = ""
    var password : String = ""
    var age : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        email = binding.emailEdit.text.toString()
        password = binding.passwordEdit.text.toString()
        age = binding.ageEdit.text.toString()

        // 인텐트에 기본 데이터 추가 및 가져오기 테스트.
        binding.testBtn2.setOnClickListener {
            // 데이터를 추가해서 + 화면 이동. : 내부 앱끼리 연동
            val intent = Intent(this@TestIntentActivity,
                TestIntent2DetailActivity::class.java)
            // 데이터 확인
            Log.d("lhs","데이터 확인 1번 화면 이메일: ${email}, "+
            "패스워드: ${password}, 나이: ${age}")
            // 데이터 추가 해보기.
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            intent.putExtra("age",age)
            startActivity(intent)

        }






    }
}