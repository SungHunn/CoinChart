package com.example.coinchart.view.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinchart.databinding.FragmentIntro2Binding
import com.example.coinchart.view.SelectActivity


class IntroFragment2 : Fragment() {

    var binding: FragmentIntro2Binding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIntro2Binding.inflate(inflater, container , false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding!!.nextButton.setOnClickListener {
            val intent = Intent(requireContext(), SelectActivity::class.java)
            startActivity(intent)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}