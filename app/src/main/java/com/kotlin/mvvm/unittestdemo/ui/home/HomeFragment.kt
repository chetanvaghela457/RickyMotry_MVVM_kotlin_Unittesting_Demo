package com.kotlin.mvvm.unittestdemo.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.mvvm.unittestdemo.databinding.FragmentHomeBinding
import com.kotlin.mvvm.unittestdemo.ui.MainActivity
import com.kotlin.mvvm.unittestdemo.ui.home.adapter.CharacterAdapter
import com.kotlin.mvvm.unittestdemo.ui.home.adapter.LocationAdapter

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel> ()

    private lateinit var binding: FragmentHomeBinding

    private val adapter = LocationAdapter{
        locationHomeUiData -> adapterOnClickLocation(locationHomeUiData)
    }

    private val characterAdapter = CharacterAdapter{
        characterHomeUiData -> adapterOnClickCharacter(characterHomeUiData)
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    var pageNumber = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater).apply {
            locationListRecyclerView.adapter = adapter
            locationListRecyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
            locationListRecyclerView.addOnScrollListener(this@HomeFragment.scrollListener)
            characterListRecyclerView.adapter = characterAdapter
            characterListRecyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        }
        (activity as MainActivity).homeFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLocationUiState()
        observeCharacterUiState()
    }


    private fun observeLocationUiState() {
        viewModel.getLocations()
        viewModel.locationHomeUiState.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.Error->{
                    isLoading = false
                    hideLocationProgressBar()
                }
                is HomeUiState.Loading->{
                    isLoading = true
                    showLocationProgressBar()
                }
                is HomeUiState.Success->{
                    isLoading = false
                    hideLocationProgressBar()
                    handleSuccessUiState(it.data)
                }
            }
        }
    }

    private fun observeCharacterUiState() {
        viewModel.characterHomeUiState.observe(viewLifecycleOwner){
            when(it){
                is HomeUiState.Error->{
                    Toast.makeText(requireContext(),getString(it.message),Toast.LENGTH_SHORT).show()
                    hideCharacterProgressBar()
                }
                is HomeUiState.Loading->{
                    showCharacterProgressBar()
                }
                is HomeUiState.Success->{
                    handleSuccessCharacterUiState(it.data)
                    hideCharacterProgressBar()
                }
            }
        }
    }

    private fun handleSuccessCharacterUiState(data: List<CharacterHomeUiData>) {
        characterAdapter.updateItems(data)
    }

    private fun handleSuccessUiState(data: List<LocationHomeUiData>) {
        adapter.updateItems(data)
    }

    private fun adapterOnClickLocation(locationHomeUiData: LocationHomeUiData) {
        viewModel.getCharactersById(locationHomeUiData.residents)
    }
    private fun adapterOnClickCharacter(characterHomeUiData: CharacterHomeUiData) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(characterHomeUiData)
        findNavController().navigate(action)
    }
    private fun hideLocationProgressBar(){
        binding.locationProgressBar.visibility = View.INVISIBLE
    }
    private fun showLocationProgressBar(){
        binding.locationProgressBar.visibility = View.VISIBLE
    }
    private fun hideCharacterProgressBar(){
        binding.characterProgressBar.visibility = View.INVISIBLE
    }
    private fun showCharacterProgressBar(){
        binding.characterProgressBar.visibility = View.VISIBLE
    }

    private val scrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= 20 //Total Response Item
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning &&
                    isTotalMoreThanVisible && isScrolling
            if(shouldPaginate && !recyclerView.canScrollHorizontally(1)){
                isScrolling= false
                if (pageNumber!=7){
                    hideLocationProgressBar()
                    viewModel.getLocations()
                    pageNumber++
                }
            }
        }
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }
    }

}