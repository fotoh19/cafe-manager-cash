package com.note.cafemanagercash.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.FragmentDetailBinding
import com.note.cafemanagercash.model.ItemsModel
import com.note.cafemanagercash.model.service.ManagmentCart
import com.note.cafemanagercash.view.adapter.SizeAdapter

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private var fragmentDetailBinding: FragmentDetailBinding? = null
    private lateinit var item: ItemsModel
    private lateinit var managementCart: ManagmentCart

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)
        fragmentDetailBinding = binding

        setupBundleData()

        val backBtn: ImageButton = view.findViewById(R.id.backBtn)
        backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
        }
    }

    private fun setupBundleData() {
        arguments?.let { bundle ->
            item = bundle.getParcelable("object")!!

            fragmentDetailBinding?.apply {
                titleTxt.text = item.title
                descriptionTxt.text = item.description
                priceTxt.text = "$${item.price}"
                ratingBar.rating = item.rating.toFloat()

                initSizeList()  // استدعاء initSizeList بعد تهيئة item

                addToCartBtn.setOnClickListener {
                    item.numberInCart = numberItemTxt.text.toString().toInt()
                     managementCart.insertItems(item)
                }

                pluscart.setOnClickListener {
                    item.numberInCart++
                    numberItemTxt.text = item.numberInCart.toString()
                }

                minusCart.setOnClickListener {
                    if (item.numberInCart > 0) {
                        item.numberInCart--
                        numberItemTxt.text = item.numberInCart.toString()
                    }
                }
            }
        }
    }

    private fun initSizeList() {
        val sizeList = arrayListOf("1", "2", "3", "4")
        fragmentDetailBinding?.apply {
            fragmentDetailBinding!!.sizeList.adapter = SizeAdapter(sizeList)
            fragmentDetailBinding!!.sizeList.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                false
            )

            val colorList = item.picUrl
            Glide.with(requireContext())
                .load(colorList[0])
                .apply(RequestOptions.bitmapTransform(RoundedCorners(100)))
                .into(picMain)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentDetailBinding = null
    }
}
