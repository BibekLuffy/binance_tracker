package com.enfiny.binancetracker.ui.binancelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bibekluffy.binancetracker.databinding.FragmentAddPortfolioBinding
import com.enfiny.binancetracker.data.room.entity.MyPortfolio
import com.enfiny.binancetracker.network.Resource
import com.enfiny.binancetracker.ui.base.BaseDBFragment
import com.enfiny.binancetracker.utils.SnackBarColor
import com.enfiny.binancetracker.utils.hideKeyboard
import com.enfiny.binancetracker.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddPortfolioFragment : BaseDBFragment<MyPortfolioViewModel, FragmentAddPortfolioBinding>() {

    private val vM: MyPortfolioViewModel by viewModels()
    override fun getViewModel(): MyPortfolioViewModel = vM

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddPortfolioBinding = FragmentAddPortfolioBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            with(baseViewModel) {
                val isE = arguments?.getBoolean("isEdit") ?: false
                isEdit.value = isE
                if (isE) {
                    bS.value = arguments?.getString("symbolB") ?: ""
                    sS.value = arguments?.getString("symbolS") ?: ""
                    q.value = arguments?.getString("priceOld") ?: ""
                    pId.value = arguments?.getInt("id") ?: 0
                    val quant = arguments?.getString("quantity") ?: ""
                    val fee = arguments?.getString("fee") ?: ""
                    f.value = fee
                    quantity.value = String.format("%.8f", quant.toDouble().plus(fee.toDouble()))
                    cP.value = arguments?.getString("costP") ?: ""
                }
                menuButton.setOnClickListener {
                    activity?.onBackPressed()
                }
                deleteButton.setOnClickListener {
                    pId.value?.let { pI ->
                        delete(pI)
                        doneButton.showSnackBar("Deleted successfully", SnackBarColor.SUCCESS)
                        activity?.onBackPressed()
                    }
                }
                doneButton.setOnClickListener {
                    hideKeyboard()
                    when {
                        bS.value.isNullOrBlank() -> {
                            doneButton.showSnackBar("Bought symbol is empty")
                        }
                        sS.value.isNullOrBlank() -> {
                            doneButton.showSnackBar("Sold symbol is empty")
                        }
                        q.value.isNullOrBlank() -> {
                            doneButton.showSnackBar("Bought price is empty")
                        }
                        quantity.value.isNullOrBlank() -> {
                            doneButton.showSnackBar("Quantity is empty")
                        }
                        f.value.isNullOrBlank() -> {
                            doneButton.showSnackBar("Fee is empty")
                        }
                        cP.value.isNullOrBlank() -> {
                            doneButton.showSnackBar("Amount Spent is empty")
                        }
                        else -> {
                            onDone()
                        }
                    }
                }
                priceResponse.observe(viewLifecycleOwner, Observer {
                    when (it) {
                        is Resource.Success -> {
                            val qNN =
                                quantity.value?.toDouble()?.minus(f.value?.toDouble()!!) ?: 0.0
                            val qN = String.format("%.8f", qNN)
                            val sPN = qNN.times((it.value.price?.toDouble() ?: 0.0))
                            val sP = String.format("%.8f", sPN)
                            val fN = String.format("%.8f", f.value?.toDouble() ?: 0.0)
                            val isProfit =
                                (q.value?.toDouble() ?: 0.0) <= (it.value.price?.toDouble() ?: 0.0)
                            if (isEdit.value!!) {
                                update(
                                    MyPortfolio(
                                        bS.value.toString()
                                            .uppercase(Locale.getDefault()),
                                        sS.value.toString()
                                            .uppercase(Locale.getDefault()),
                                        String.format("%.8f", it.value.price?.toDouble() ?: 0.0),
                                        q.value,
                                        qN,
                                        cP.value,
                                        sP,
                                        fN,
                                        isProfit,
                                        pId.value
                                    )
                                )
                                doneButton.showSnackBar(
                                    "Updated successfully",
                                    SnackBarColor.SUCCESS
                                )
                            } else {
                                insert(
                                    MyPortfolio(
                                        bS.value.toString()
                                            .uppercase(Locale.getDefault()),
                                        sS.value.toString()
                                            .uppercase(Locale.getDefault()),
                                        String.format("%.8f", it.value.price?.toDouble() ?: 0.0),
                                        q.value,
                                        qN,
                                        cP.value,
                                        sP,
                                        fN,
                                        isProfit,
                                        null
                                    )
                                )
                                doneButton.showSnackBar("Added successfully", SnackBarColor.SUCCESS)
                            }
                            activity?.onBackPressed()
                        }
                        is Resource.Failure -> {
                            loading.value = false
                            when {
                                it.isNetworkError -> {
                                    doneButton.showSnackBar("Network error")
                                }
                                else -> {
                                    doneButton.showSnackBar(
                                        "Error with the request. Please check the correctness of symbol",
                                        SnackBarColor.DANGER
                                    )
                                }
                            }
                        }
                        else -> {
                        }
                    }
                })
            }
        }
    }
}