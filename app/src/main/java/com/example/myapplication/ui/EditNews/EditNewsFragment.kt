package com.example.myapplication.ui.EditNews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentEditNewsBinding

class EditNewsFragment:Fragment() {
    private var _binding: FragmentEditNewsBinding? = null
    val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditNewsBinding.inflate(inflater,container,false)

        return binding.root
    }
}