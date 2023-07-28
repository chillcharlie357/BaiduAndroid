package com.example.myapplication.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.myapplication.databinding.FragmentSearchBinding
import java.net.URLEncoder

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val args: SearchFragmentArgs by navArgs()
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val webView = binding.searchWebview
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true

        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        val query = args.query
        val encodedQuery = URLEncoder.encode(query, "utf-8")
        val url = "https://www.baidu.com/s?wd=$encodedQuery"
        webView.loadUrl(url)
        return binding.root
    }
}