package com.example.myapplication.ui.about

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentAboutBinding
import com.example.myapplication.utils.Failure
import com.example.myapplication.utils.Success
import kotlinx.coroutines.launch

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val args: AboutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(requireActivity())[AboutViewModel::class.java]

        _binding = FragmentAboutBinding.inflate(inflater, container, false)


        binding.buttonSignOut.setOnClickListener {
            viewModel.signOut()
            val action = AboutFragmentDirections.actionNavigationAboutToLoginFragment()
            findNavController().navigate(action)

            Log.d("button sign out", viewModel.isLogin.value.toString())
        }

        lifecycleScope.launch {
            val userEntity = viewModel.getUserInfo(args.userId)
            binding.UIDText.text = userEntity.user_id
            binding.userNameText.text = userEntity.user_name
        }

        binding.buttonChangeName.setOnClickListener {
            val newName = binding.newNameText.text.toString()

            lifecycleScope.launch {
                val result = viewModel.updateUserName(args.userId,newName)
                when(result){
                    is Success -> {
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT).show()
                    }
                    is Failure -> {
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        viewModel.userName.observe(viewLifecycleOwner){
            binding.userNameText.text = it
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}