package com.example.giphyapi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.giphyapi.Repository.GiphyRepository
import com.example.giphyapi.adapters.GiphyAdapter
import com.example.giphyapi.data.remote.GiphyManager
import com.example.giphyapi.databinding.FragmentSecondBinding
import com.example.giphyapi.ui.GiphyViewModel
import com.example.giphyapi.ui.GiphyViewModelFactory
import com.example.giphyapi.utils.Constants.Companion.API_KEY
import com.example.giphyapi.utils.Constants.Companion.SEARCH_NEWS_TIME_DELAY
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SecondFragment : Fragment(R.layout.fragment_second) {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GiphyViewModel
    private lateinit var giphyAdapter: GiphyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(
            this,
            GiphyViewModelFactory(GiphyRepository(GiphyManager()))
        ).get(GiphyViewModel::class.java)

        setupRecyclerView()

        giphyAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("data", it)
            }
            findNavController().navigate(R.id.action_secondFragment_to_thirdFragment, bundle)
        }

        var job: Job? = null
        binding.etSearch.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(SEARCH_NEWS_TIME_DELAY)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchGiphy(it.toString(), "40", API_KEY)
                    }
                }
            }
        }

        viewModel.searchNewsData.observe(viewLifecycleOwner, Observer {
            giphyAdapter.differ.submitList(it.data?.data)
        })
    }

    fun setupRecyclerView() =
        binding.rvSecondFragment.apply {
            giphyAdapter = GiphyAdapter()
            adapter = giphyAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
}
