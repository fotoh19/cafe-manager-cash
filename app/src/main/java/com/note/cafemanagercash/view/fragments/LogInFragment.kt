package com.note.cafemanagercash.view.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.note.cafemanagercash.R
import com.note.cafemanagercash.databinding.FragmentLogInBinding

class LogInFragment : Fragment(R.layout.fragment_log_in) {
    private var fragmentLogInBinding: FragmentLogInBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentLogInBinding.bind(view)
        fragmentLogInBinding = binding

        val skipBtn :AppCompatButton = view.findViewById(R.id.skipBtn)
        skipBtn.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_mainFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentLogInBinding = null
    }
}

