package com.example.myapp_test_7_8_9_10_11_12.ch13_Test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTestCoroutineBinding
import kotlin.system.measureTimeMillis

class TestCoroutineActivity : AppCompatActivity() {
    lateinit var binding : ActivityTestCoroutineBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestCoroutineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testCoBtn.setOnClickListener {
            //오래걸리는 작업 추가. -> 화면에서 처리하기보다는 -> 비동기식으로 처리를 하겠다.
            //비동기식으로 처리하는 방법이 기존에 스레드(핸들러작업) -> 코루틴으로 작업하는 부분 소개.
            var sum = 0L
            var time = measureTimeMillis {
                for(i in 1..9_000_000_000){
                    sum += i
                }
            }
            Log.d("lhs","time : $time")
            binding.resultCoView.text = "time: $time, sum : $sum"
        }




    }
}