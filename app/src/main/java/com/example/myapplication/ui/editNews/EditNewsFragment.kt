package com.example.myapplication.ui.editNews

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.media3.common.util.Log
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.data.application.MyApplication
import com.example.myapplication.data.entity.NewsEntity
import com.example.myapplication.databinding.FragmentEditNewsBinding

class EditNewsFragment : Fragment() {
    private var _binding: FragmentEditNewsBinding? = null
    val binding get() = _binding!!

    private var headImageUri: Uri? = null
    private var thumbnailImageUri: Uri? = null

    private val viewModel: EditViewModel by viewModels {
        EditViewModelFactory((requireContext().applicationContext as MyApplication).repository)
    }

    private val pickThumbnailImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                Log.d("PhotoPicker", "Selected URI: $it")
                thumbnailImageUri = it
                binding.thumbnailImageButton.setImageURI(it)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }
    private val pickHeadImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                Log.d("PhotoPicker", "Selected URI: $it")
                headImageUri = it
                binding.headImageButton.setImageURI(it)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNewsBinding.inflate(inflater, container, false)

        binding.thumbnailImageButton.setOnClickListener {
            pickThumbnailImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.headImageButton.setOnClickListener {
            pickHeadImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        binding.submitButton.setOnClickListener {
            val newsEntity = NewsEntity(
                title = binding.newsTitleTextView.text.toString(),
                author = binding.authorText.text.toString(),
                content = binding.contentText.text.toString(),
                headImageUri = headImageUri.toString(),
                thumbnailImageUri = thumbnailImageUri.toString(),
                avatarUri = "android.resource://com.example.myapplication/${R.drawable.icons8_avatar_48}"
            )
            viewModel.insertNews(newsEntity)

            val action = EditNewsFragmentDirections.actionEditNewsFragmentToNavigationHome()
            findNavController().navigate(action)
        }
        return binding.root
    }
}
