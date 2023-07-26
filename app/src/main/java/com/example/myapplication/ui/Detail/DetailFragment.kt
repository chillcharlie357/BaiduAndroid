package com.example.myapplication.ui.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentDetailBinding
import com.example.myapplication.databinding.FragmentWeatherBinding
import com.example.myapplication.utils.tools.Companion.randomString

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        var root: View = binding.root


        viewModel.avatar_id.observe(viewLifecycleOwner) {
            binding.avatar.setImageResource(it)
        }
        viewModel.title.observe(viewLifecycleOwner) {
            binding.Title.text = it
        }
        viewModel.post.observe(viewLifecycleOwner) {
            binding.post.text = it
        }
        viewModel.author.observe(viewLifecycleOwner) {
            binding.author.text = it
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}