package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.PreferenceTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivitySharedPreferDetailBinding


class SharedPrefDetailActivity : AppCompatActivity() {
    //2번 화면
    // 공유프리퍼런스 파일에 데이터 가져오기

    lateinit var binding : ActivitySharedPreferDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 조회 버튼 누르면 공유된 프리퍼런스 파일에 저장된 값 가져오기
        binding.getSharedPreferBtnTest.setOnClickListener {


            // getter, setter 개념

            // 값 가져오기
            // 저장된 파일명 : userInfo
            // key, value 형태로 저장됨.
            // key 로 해당 값을 불러오기.
            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val email = pref.getString("email", "Default EMAIL")
            val password = pref.getString("password", "Default PASSWORD")

            // 결과 뷰에 데이터 넣기.
            binding.resultEmailSP.text = email
            binding.resultPasswordSP.text = password

            // 삭제 테스트1 - 부분 삭제
            binding.deleteSharedPreferBtnTest.setOnClickListener {
                val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = pref.edit()
                editor.remove("email")
                editor.remove("password")
                editor.commit()
            }

        }
    }
}