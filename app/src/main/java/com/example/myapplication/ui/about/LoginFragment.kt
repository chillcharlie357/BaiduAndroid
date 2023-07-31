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
import com.example.myapplication.databinding.FragmentAboutLoginBinding
import com.example.myapplication.utils.Failure
import com.example.myapplication.utils.Success
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {
    private var _binding: FragmentAboutLoginBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutLoginBinding.inflate(inflater, container, false)

        //requireActivity()而不是this,因为viewModel共享
        val viewModel = ViewModelProvider(requireActivity())[AboutViewModel::class.java]

        binding.buttonLogin.setOnClickListener {
            val userId = binding.userId.text.toString()
            val passwd = binding.password.text.toString()
            lifecycleScope.launch {
                val result = viewModel.login(userId, passwd)
                when (result) {
                    is Success -> {
                        val action = LoginFragmentDirections.actionLoginFragmentToNavigationAbout(userId)
                        findNavController().navigate(action)
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT).show()
                    }

                    is Failure -> {
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT).show()
                    }
                }
                binding.userId.clearComposingText()
            }


            Log.d("userID", userId)
            Log.d("passwd", passwd)
            Log.d("isLogin", viewModel.isLogin.value.toString())
            Log.d(TAG, "nav to about")
        }

        binding.buttonSignUp.setOnClickListener {
            val userId = binding.userId.text.toString()
            val passwd = binding.password.text.toString()
            lifecycleScope.launch {
                val result = viewModel.signUp(userId, passwd)

                when (result) {
                    is Success -> {
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT).show()
                    }

                    is Failure -> {
                        Toast.makeText(requireContext(), result.msg, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}