package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntent2DetailBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestIntentBinding

class TestIntent2DetailActivity : AppCompatActivity() {

    lateinit var binding : ActivityTestIntent2DetailBinding
    // 2번 화면
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestIntent2DetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1번 화면에서 넘어온 데이터 받아서, 결과 뷰에 붙이기 작업.
        // 임시 저장소에 담기
        val email = intent.getStringExtra("email")
        val password = intent.getStringExtra("password")
        val age = intent.getIntExtra("age", 31)

        // 데이터 확인
        Log.d("lhs","데이터 확인 2번 화면 이메일: ${email}, "+
                "패스워드: ${password}, 나이: ${age}")

        binding.resulNametView.text = "이메일 : $email"
        binding.resulEmailtView.text = "패스워드 : $password"
        binding.resulAgetView.text = "나이 : $age"

    }
}