package com.example.coinchart.view.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coinchart.R
import com.example.coinchart.dataModel.CurrentPriceResult

class SelectRVAdapter(val context: Context, val coinPriceList: List<CurrentPriceResult>) :
    RecyclerView.Adapter<SelectRVAdapter.ViewHolder>() {

    val selectedCoinList = ArrayList<String>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coinName: TextView = view.findViewById(R.id.coinName)
        val coinPriceUpDown: TextView = view.findViewById(R.id.coinPriceUpDown)
        val likeImage: ImageView = view.findViewById(R.id.likeBtn)
    }

    override fun getItemCount(): Int {
        return coinPriceList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.intro_coin_item, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.coinName.text = coinPriceList[position].coinName

        val fluctate = coinPriceList[position].coinInfo.fluctate_24H

        if (fluctate.contains("-")) {
            holder.coinPriceUpDown.text = "가격 하락"
            holder.coinPriceUpDown.setTextColor(Color.parseColor("#114fed"))
        } else if (fluctate.equals("0")) {
            holder.coinPriceUpDown.text = "가격 변동 없음"
            holder.coinPriceUpDown.setTextColor(Color.parseColor("#000000"))
        } else {
            holder.coinPriceUpDown.text = "가격 상승"
            holder.coinPriceUpDown.setTextColor(Color.parseColor("#ed2e11"))
        }

        val likeImage = holder.likeImage
        val currentCoin = coinPriceList[position].coinName

        //view를 그려줄 때
        if(selectedCoinList.contains(currentCoin)){
            likeImage.setImageResource(R.drawable.like_red)
        }else{
            likeImage.setImageResource(R.drawable.like_grey)
        }

        likeImage.setOnClickListener {

            if (selectedCoinList.contains(currentCoin)) {
                likeImage.setImageResource(R.drawable.like_grey)
                selectedCoinList.remove(currentCoin)

            } else {
                likeImage.setImageResource(R.drawable.like_red)
                selectedCoinList.add(currentCoin)
            }

        }
    }


}