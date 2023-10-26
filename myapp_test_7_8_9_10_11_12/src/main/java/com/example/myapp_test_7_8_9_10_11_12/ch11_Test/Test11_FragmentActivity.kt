package com.example.myapp_test_7_8_9_10_11_12.ch11_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest11FragmentBinding

class Test11_FragmentActivity : AppCompatActivity() {
    // 프래그먼트를 출력하기 위한 베이스로 사용.
    lateinit var binding : ActivityTest11FragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTest11FragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 프래그먼트 출력하기. 연결(바인딩)
        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()

        // 첫번째 프래그먼트
        val oneFragment = OneFragment()

        // 두번째 프래그먼트
        val twoFragment = TwoFragment()

        // 세번째 프래그먼트
        val threeFragment = ThreeFragment()

        // 네번째 프래그먼트
        val fourFragment = FourFragment()

        // 첫번째 프래그먼트 붙이기
        transaction.add(R.id.fragment1,oneFragment)

        // 두번째 프래그먼트 붙이기
        transaction.add(R.id.fragment2,twoFragment)

        // 세번째 프래그먼트 붙이기
        transaction.add(R.id.fragment3,threeFragment)

        // 네번째 프래그먼트 붙이기
        transaction.add(R.id.fragment4, fourFragment)


        // 벡스택, 화면을 출력 시, Task라는 공간을 사용해서, (메모리 사용을 함.)
        // 출력하고, 화면 전환이 발생할 경우, 매번 프래그먼트를 소멸시키고, 또 생성하고 이 작업이 반복이 되면
        // 자원 사용이 비효율적임. 그래서, 잠시, keep해서 가지고 있다가, 다시 그려주기(소멸 시키지않고,)
        // 액티비티에서도, 기존의 액티비티를 최대한 활용하는 방안으로 singleTop을 소개시켜 드릴게요.

        // 뷰 페이저 등을 이용해서, 프래그먼트 전환할 때, 다시 재확인하기.
        transaction.addToBackStack(null)


        //화면에 출력하기.
        transaction.commit()


    }
}