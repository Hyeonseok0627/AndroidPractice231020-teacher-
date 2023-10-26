package com.example.myapp_test_7_8_9_10_11_12.ch11_Test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.FragmentThreeBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.FragmentTwoBinding


class ThreeFragment : Fragment() {

    lateinit var binding: FragmentThreeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentThreeBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

}