package com.example.myapp_test_7_8_9_10_11_12.ch11_Test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.ActivityTest11ToolBarBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.FragmentOneBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OneFragment : Fragment() {
    // 베이스 액티비티 위에 출력이 되는 조각(프래그먼트)
    // 기본1 (binding이라는 인스턴스 생성)
    lateinit var binding : FragmentOneBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lhs","생명주기,onCreate ")
    }

    override fun onStart() {
        super.onStart()
        Log.d("lhs","생명주기,onStart ")
    }
    override fun onResume() {
        super.onResume()
        Log.d("lhs","생명주기,onResume ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("lhs","생명주기,onPause ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("lhs","생명주기,onStop ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("lhs","생명주기,onDestroy ")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 기본2 (binding 초기화)
        binding = FragmentOneBinding.inflate(layoutInflater, container, false)
//        return inflater.inflate(R.layout.fragment_one, container, false)
        // 기본3 (binding 출력)
        return binding.root
    }


}