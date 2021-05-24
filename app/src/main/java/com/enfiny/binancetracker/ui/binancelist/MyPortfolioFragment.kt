package com.enfiny.binancetracker.ui.binancelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bibekluffy.binancetracker.R
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
            menuButton.setOnClickListener {
                (activity as BinanceListActivity).openDrawer()
            }
            initRecyclerView()
            baseViewModel.getMyPortfolioData.observe(viewLifecycleOwner, Observer {
                addButton.visibility = when {
                    it.isEmpty() -> View.VISIBLE
                    else -> View.GONE
                }
                myPortfolioAdapter.setData(it as ArrayList<MyPortfolio>)
            })
            addButton.setOnClickListener {
                findNavController().navigate(R.id.action_myPortfolioFragment_to_addPortfolioFragment)
            }
        }
    }

    private fun initRecyclerView() {
        myPortfolioAdapter = MyPortfolioAdapter(ArrayList()) {
            println(it)
            val bundle = bundleOf(
                "symbolB" to it.symbolB,
                "symbolS" to it.symbolS,
                "price" to it.price,
                "priceOld" to it.priceOld,
                "quantity" to it.quantity,
                "costP" to it.costP,
                "sellP" to it.sellP,
                "fee" to it.fee,
                "id" to it.id,
                "isEdit" to true
            )
            findNavController().navigate(
                R.id.action_myPortfolioFragment_to_addPortfolioFragment,
                bundle
            )
        }
        binding.myPortfolioRecycler.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = myPortfolioAdapter
        }
    }

}