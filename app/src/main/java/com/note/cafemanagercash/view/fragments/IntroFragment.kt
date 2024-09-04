package com.note.cafemanagercash.view.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.FragmentIntroBinding

class IntroFragment : Fragment(R.layout.fragment_intro) {
    private var fragmentIntroBinding: FragmentIntroBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentIntroBinding.bind(view)
        fragmentIntroBinding = binding

        val button = view.findViewById<Button>(R.id.startbtn)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_logInFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentIntroBinding = null
    }
}
