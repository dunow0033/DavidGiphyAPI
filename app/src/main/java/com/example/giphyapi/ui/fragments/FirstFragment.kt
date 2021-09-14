package com.example.giphyapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.giphyapi.R
import com.example.giphyapi.Repository.GiphyRepository
import com.example.giphyapi.adapters.GiphyAdapter
import com.example.giphyapi.data.remote.GiphyManager
import com.example.giphyapi.databinding.FragmentFirstBinding
import com.example.giphyapi.ui.GiphyViewModel
import com.example.giphyapi.ui.GiphyViewModelFactory

class FirstFragment : Fragment(R.layout.fragment_first) {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GiphyViewModel
    private lateinit var giphyAdapter: GiphyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = (activity as MainActivity).viewModel

        viewModel = ViewModelProvider(
            this,
            GiphyViewModelFactory(GiphyRepository(GiphyManager()))
        ).get(GiphyViewModel::class.java)

        setupRecyclerView()

        giphyAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("data", it)
            }
            findNavController().navigate(R.id.action_firstFragment_to_thirdFragment, bundle)
        }

        viewModel.giphyData.observe(viewLifecycleOwner, Observer {
            giphyAdapter.differ.submitList(it.data?.data)
        })
    }

    private fun setupRecyclerView() = binding.rvFirstFragment.apply {
        giphyAdapter = GiphyAdapter()
        adapter = giphyAdapter
        layoutManager = GridLayoutManager(context, 2)

    }


}