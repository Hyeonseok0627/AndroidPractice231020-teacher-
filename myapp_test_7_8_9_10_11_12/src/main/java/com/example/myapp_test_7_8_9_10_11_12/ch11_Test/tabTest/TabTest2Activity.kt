package com.example.myapp_test_7_8_9_10_11_12.ch11_Test.tabTest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTabTest2Binding
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTabTestBinding

class TabTest2Activity : AppCompatActivity() {
    lateinit var binding: ActivityTabTest2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTabTest2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 탭 레이아웃과 뷰페이저2 연동하기.
    }
}