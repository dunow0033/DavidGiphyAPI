package com.example.giphyapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.giphyapi.databinding.FragmentThirdBinding

class ThirdFragment : Fragment() {
    val args: ThirdFragmentArgs by navArgs()

    private var _binding: FragmentThirdBinding? = null
    private val binding: FragmentThirdBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val giphy = args.data
        binding.webview.apply {
            webViewClient = WebViewClient()
            loadUrl(giphy.images.original.url)
        }

    }


}
