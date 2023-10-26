package com.example.myapp_test_7_8_9_10_11_12.ch11_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest11ActionBarBinding

class Test11_ActionBarActivity : AppCompatActivity() {
    // activityTest11ActionBarBinding 인스턴스 생성(여기에 관련 뷰들을 넣어서 꺼내서 쓰기 편하게 묶어두는 것)
    lateinit var activityTest11ActionBarBinding: ActivityTest11ActionBarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityTest11ActionBarBinding = ActivityTest11ActionBarBinding.inflate(layoutInflater)
        setContentView(activityTest11ActionBarBinding.root)

        // 액션바에 업버튼 붙이기
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        }

    // 액션바 클릭 이벤트 처리.
    override fun onSupportNavigateUp(): Boolean {
        Log.d("lhs","test")
        onBackPressed() // 뒤로가기(이전화면으로 넘어가기)
        return super.onSupportNavigateUp()

    }
}