package com.note.cafemanagercash.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.FragmentMainBinding
import com.note.cafemanagercash.view.adapter.CategoryAdapter
import com.note.cafemanagercash.view.adapter.PopularAdapter
import com.note.cafemanagercash.viewModel.MainViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment(R.layout.fragment_main) {

    private var fragmentMainBinding: FragmentMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMainBinding.bind(view)
        fragmentMainBinding = binding

        initCategory()
        initPopular()

        val cartBtn:FloatingActionButton = view.findViewById(R.id.cart_btn)
        cartBtn.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_cartFragment)
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")

    private fun initPopular() {
        fragmentMainBinding!!.progressBarPopular.visibility = View.VISIBLE

        val popularAdapter = PopularAdapter()
        fragmentMainBinding!!.recyclerviewPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        fragmentMainBinding!!.recyclerviewPopular.adapter = popularAdapter

        viewModel.popular.observe(viewLifecycleOwner) { popularList ->
            popularAdapter.submitList(popularList)
            fragmentMainBinding!!.progressBarPopular.visibility = View.GONE
        }

        viewModel.loadPopular()
    }


    private fun initCategory() {
        fragmentMainBinding!!.progressBarCategory.visibility = View.VISIBLE

        categoryAdapter = CategoryAdapter()
        fragmentMainBinding!!.recyclerViewCategory.layoutManager =
            LinearLayoutManager(
                this@MainFragment.context, LinearLayoutManager.HORIZONTAL,
                false
            )
        fragmentMainBinding!!.recyclerViewCategory.adapter = categoryAdapter

        viewModel.category.observe(viewLifecycleOwner) { categoryList ->
            categoryAdapter.submitList(categoryList)
            fragmentMainBinding!!.progressBarCategory.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentMainBinding = null
    }
}
