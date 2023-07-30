package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding get() = _binding!!

    val args: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            val action = DetailFragmentDirections.actionDetailFragmentToNavigationHome()
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        var root: View = binding.root
        val actionBar = (activity as AppCompatActivity).supportActionBar!!

        actionBar.apply {
            setDisplayHomeAsUpEnabled(false)
            setTitle("文章详情")
            setHomeButtonEnabled(true)
        }

        viewModel.getNewsById(args.itemId)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val action = DetailFragmentDirections.actionDetailFragmentToNavigationHome()
            findNavController().navigate(action)
        }

        viewModel.avatarUri.observe(viewLifecycleOwner) {
            binding.avatar.setImageURI(it)
        }
        viewModel.title.observe(viewLifecycleOwner) {
            binding.title.text = it
        }
        viewModel.content.observe(viewLifecycleOwner) {
            binding.content.text = it
        }
        viewModel.author.observe(viewLifecycleOwner) {
            binding.author.text = it
        }


        return root
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }

            else -> super.onContextItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}