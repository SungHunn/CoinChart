package com.example.coinchart.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinchart.R
import com.example.coinchart.databinding.FragmentPriceChangeBinding
import com.example.coinchart.view.adapter.PriceListUpDownRVAdapter
import timber.log.Timber


class PriceChangeFragment : Fragment() {

    private val viewModel : MainViewModel by activityViewModels()

    private var _binding: FragmentPriceChangeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentPriceChangeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllSelectedCoinData()


        viewModel.arr15min.observe(viewLifecycleOwner){
            Timber.tag("15").d(it.toString())

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext() , it)
            binding.price15M.adapter = priceListUpDownRVAdapter
            binding.price15M.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.arr30min.observe(viewLifecycleOwner){
            Timber.tag("30").d(it.toString())

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext() , it)
            binding.price30M.adapter = priceListUpDownRVAdapter
            binding.price30M.layoutManager = LinearLayoutManager(requireContext())
        }

        viewModel.arr45min.observe(viewLifecycleOwner){
            Timber.tag("45").d(it.toString())

            val priceListUpDownRVAdapter = PriceListUpDownRVAdapter(requireContext() , it)
            binding.price45M.adapter = priceListUpDownRVAdapter
            binding.price45M.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}