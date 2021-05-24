package com.enfiny.binancetracker.ui.binancelist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bibekluffy.binancetracker.R
import com.bibekluffy.binancetracker.databinding.MyPortfolioListBinding
import com.enfiny.binancetracker.data.room.entity.MyPortfolio

class MyPortfolioAdapter(
    private var myPortFolio: ArrayList<MyPortfolio>,
    private val onItemClicked: (MyPortfolio) -> Unit
) :
    RecyclerView.Adapter<MyPortfolioAdapter.ViewHolder>() {

    inner class ViewHolder(val myPortfolioListBinding: MyPortfolioListBinding) :
        RecyclerView.ViewHolder(myPortfolioListBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.my_portfolio_list, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.myPortfolioListBinding) {
            viewModel = myPortFolio[position]
            myPortfolioButton.setOnClickListener {
                onItemClicked(myPortFolio[position])
            }
        }
    }

    override fun getItemCount(): Int = myPortFolio.size

    fun setData(myPortFolioM: ArrayList<MyPortfolio>) {
        myPortFolio = myPortFolioM
        notifyDataSetChanged()
    }
}