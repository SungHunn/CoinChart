package com.example.coinchart.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinchart.R
import com.example.coinchart.databinding.FragmentCoinListBinding
import com.example.coinchart.db.entity.InterestCoinEntity
import com.example.coinchart.view.adapter.CoinListRVAdpater
import timber.log.Timber


class CoinListFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private val selectedList = ArrayList<InterestCoinEntity>()
    private val unSelectedList = ArrayList<InterestCoinEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllInterestCoinData()
        viewModel.selectedCoinList.observe(viewLifecycleOwner) {

            selectedList.clear()
            unSelectedList.clear()

            for (item in it){
                if(item.selected){
                    selectedList.add(item)
                }else{
                    unSelectedList.add(item)
                }
            }

            setSelectedListRV()

        }
    }

    private fun setSelectedListRV(){

        val selectedRVAdapter = CoinListRVAdpater(requireContext() , selectedList)
        binding.selectedCoinRV.adapter = selectedRVAdapter
        binding.selectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        selectedRVAdapter.itemClick = object  : CoinListRVAdpater.ItemClick{
            override fun onClick(view: View, position: Int) {
                viewModel.updateInterestCoinData(selectedList[position])
            }

        }


        val unSelectedRVAdapter = CoinListRVAdpater(requireContext() , unSelectedList)
        binding.unselectedCoinRV.adapter = unSelectedRVAdapter
        binding.unselectedCoinRV.layoutManager = LinearLayoutManager(requireContext())

        unSelectedRVAdapter.itemClick = object : CoinListRVAdpater.ItemClick{
            override fun onClick(view: View, position: Int) {
                viewModel.updateInterestCoinData(unSelectedList[position])
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}