package com.note.cafemanagercash.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.FragmentCartBinding
import com.note.cafemanagercash.model.service.ChangeNumberItemsListener
import com.note.cafemanagercash.model.service.ManagmentCart
import com.note.cafemanagercash.view.adapter.CartAdapter
import kotlin.math.roundToInt


class CartFragment : Fragment(R.layout.fragment_cart) {
    private var fragmentCartBinding: FragmentCartBinding? = null
    private lateinit var managment: ManagmentCart
    private var tax: Double = 0.0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCartBinding.bind(view)
        fragmentCartBinding = binding

        managment = ManagmentCart(requireContext())
        calculateCart()
        initCartList()
    }

    private fun initCartList() {
        fragmentCartBinding?.let { binding ->
            binding.cartView.layoutManager = LinearLayoutManager(requireContext())
            binding.cartView.adapter = CartAdapter(requireContext(), object : ChangeNumberItemsListener {
                override fun onChanged() {
                    calculateCart()
                }
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 15.0
        tax = ((managment.getTotalFee() * percentTax) * 100).roundToInt() / 100.0
        val total = ((managment.getTotalFee() + tax + delivery) * 100).roundToInt() / 100
        val itemTotal = (managment.getTotalFee() * 100).roundToInt() / 100

        fragmentCartBinding?.let { binding ->
            binding.totalFeeTxt.text = "$$itemTotal"
            binding.taxTxt.text = "$$tax"
            binding.deliverTxt.text = "$$delivery"
            binding.totalTxt.text = "$$total"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentCartBinding = null
    }
}
