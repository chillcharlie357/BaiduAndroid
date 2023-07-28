package com.example.myapplication.ui.EditNews

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentEditNewsBinding
import com.example.myapplication.utils.requets_codes

class EditNewsFragment : Fragment() {
    private var _binding: FragmentEditNewsBinding? = null
    val binding get() = _binding!!

    private lateinit var imagePickerLaunchers: Array<ActivityResultLauncher<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        imagePickerLaunchers = Array(2) { index ->
            registerForActivityResult(
                ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                uri?.let {
                    if (index == 0) {
                        Glide.with(this)
                            .load(uri)
                            .into(binding.imageButton)
                    } else if (index == 1) {
                        Glide.with(this)
                            .load(uri)
                            .into(binding.imageButton2)
                    }
                }
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditNewsBinding.inflate(inflater, container, false)

        binding.imageButton.setOnClickListener {
           imagePickerLaunchers[0].launch("image/*")
        }
        binding.imageButton2.setOnClickListener {
            imagePickerLaunchers[1].launch("image/*")
        }
        return binding.root
    }
}
