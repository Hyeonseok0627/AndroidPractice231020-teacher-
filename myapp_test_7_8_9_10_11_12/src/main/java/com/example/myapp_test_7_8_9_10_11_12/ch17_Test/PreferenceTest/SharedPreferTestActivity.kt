package com.example.myapp_test_7_8_9_10_11_12.ch17_Test.PreferenceTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivitySharedPreferTestBinding

class SharedPreferTestActivity : AppCompatActivity() {
    // 1번 화면
    // val test = 공유프리퍼런스 파일에 데이터 담기
    // 공유프리퍼런스 파일 => 모든 액티비티 화면에서 접근이 가능함.
    // EditText 뷰에서,
    // 라디오 뷰에서,
    // 각 뷰에서, 데이터를 가져와서, (마치 회원가입 하듯이)
    // test에 저장.

    lateinit var binding : ActivitySharedPreferTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferTestBinding.inflate(layoutInflater) // 인스턴스를 메모리에 올려주는 코드(할당) (그래야 해당 인스턴스를 다른데에서 가져와서 쓰면서 거기 내부 함수를 사용가능)
        setContentView(binding.root) // binding 중에서 root만 꺼내서 보여주는 코드

        // 이벤트 처리할 예정.
        // 회원가입에서 사용했던 뷰를 재사용.
    }
}