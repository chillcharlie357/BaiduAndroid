package com.example.myapplication.ui.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentWeatherBinding

class DetailFragment : Fragment() {
    private var _binding : FragmentDetailBinding? = null
    val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater,container,false)
        var root: View = binding.root

        val textView = binding.textView
        textView.text = "detail"
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}