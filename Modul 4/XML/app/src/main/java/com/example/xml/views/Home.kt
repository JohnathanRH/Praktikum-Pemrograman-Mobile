package com.example.xml.views

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.xml.CarouselAdapter
import com.example.xml.MainActivity
import com.example.xml.R
import com.example.xml.RecyclerViewAdapter
import com.example.xml.databinding.HomeBinding
import com.example.xml.viewmodels.GameViewModel
import com.example.xml.viewmodels.GameViewModelFactory
import com.example.xml.viewmodels.NavigationEvent
import kotlinx.coroutines.launch
import kotlin.math.abs

class Home : Fragment(R.layout.home) {

    // Scoped to activity so other fragments can share this pipeline
    private val mainViewModel: GameViewModel by activityViewModels {
        GameViewModelFactory(username = "Admin")
    }
    private lateinit var binding: HomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeBinding.bind(view)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val images = listOf(R.drawable.ss, R.drawable.ss2, R.drawable.ss3)
        binding.viewPager.adapter = CarouselAdapter(images)
        val transformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer { page, position -> page.scaleY = 0.85f + (1 - abs(position)) * 0.15f }
        }
        binding.viewPager.setPageTransformer(transformer)

        val recyclerAdapter = RecyclerViewAdapter(games = emptyList())
        binding.gamesList.adapter = recyclerAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect { state ->
                    recyclerAdapter.updateGames(state.games)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.navigationEvent.collect { event ->
                    when (event) {
                        is NavigationEvent.NavigateToSetting -> {
                            (activity as? MainActivity)?.supportFragmentManager?.beginTransaction()?.apply {
                                replace(R.id.fragmentContainerView, Setting())
                                addToBackStack(null)
                                commit()
                            }
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}