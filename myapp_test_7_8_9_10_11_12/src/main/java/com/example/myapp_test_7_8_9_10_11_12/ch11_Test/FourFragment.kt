package com.example.myapp_test_7_8_9_10_11_12.ch11_Test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapp_test_7_8_9_10_11_12.R
import com.example.myapp_test_7_8_9_10_11_12.databinding.FragmentFourBinding
import com.example.myapp_test_7_8_9_10_11_12.databinding.FragmentThreeBinding


class FourFragment : Fragment() {

    lateinit var binding: FragmentFourBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}