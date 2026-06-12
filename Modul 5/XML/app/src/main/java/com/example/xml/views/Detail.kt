package com.example.xml.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.xml.MainActivity
import com.example.xml.R
import com.example.xml.databinding.DetailBinding
import com.example.xml.viewmodels.GameViewModel
import com.example.xml.viewmodels.NavigationEvent
import kotlinx.coroutines.launch

class Detail : Fragment(R.layout.detail) {
    private val mainViewModel: GameViewModel by activityViewModels()
    private lateinit var binding: DetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = DetailBinding.bind(view)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val movieTitle = arguments?.getString("title") ?: "No Title"
        val movieOverview = arguments?.getString("overview") ?: "No Description"
        val moviePosterUrl = arguments?.getString("posterPath") ?: ""

        binding.wikiUrl = "https://www.themoviedb.org/search?query=${movieTitle.replace(" ", "+")}"

        binding.titleTextView.text = movieTitle
        binding.descTextView.text = movieOverview

        binding.productImg.load(moviePosterUrl) {
            crossfade(true)
            placeholder(R.drawable.ss)
            error(R.drawable.ss)
        }

        val context = requireContext()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.navigationEvent.collect { event ->
                    when (event) {
                        is NavigationEvent.OpenWiki -> {
                            val intent = Intent(Intent.ACTION_VIEW, event.url.toUri())
                            context.startActivity(intent)
                        }
                        is NavigationEvent.NavigateToHome -> {
                            (activity as? MainActivity)?.supportFragmentManager?.popBackStack()
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}