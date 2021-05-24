package com.enfiny.binancetracker.ui.binancelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bibekluffy.binancetracker.databinding.FragmentMyPortfolioBinding
import com.enfiny.binancetracker.data.room.entity.MyPortfolio
import com.enfiny.binancetracker.ui.base.BaseVBFragment
import com.enfiny.binancetracker.ui.binancelist.adapter.MyPortfolioAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPortfolioFragment : BaseVBFragment<MyPortfolioViewModel, FragmentMyPortfolioBinding>() {

    private val vM: MyPortfolioViewModel by viewModels()
    private lateinit var myPortfolioAdapter: MyPortfolioAdapter
    override fun getViewModel(): MyPortfolioViewModel = vM

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMyPortfolioBinding = FragmentMyPortfolioBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            initRecyclerView()
            baseViewModel.getMyPortfolioData.observe(viewLifecycleOwner, Observer {
                myPortfolioAdapter.setData(it as ArrayList<MyPortfolio>)
            })
        }
    }

    private fun initRecyclerView() {
        myPortfolioAdapter = MyPortfolioAdapter(ArrayList())
        binding.myPortfolioRecycler.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = myPortfolioAdapter
        }
    }

}