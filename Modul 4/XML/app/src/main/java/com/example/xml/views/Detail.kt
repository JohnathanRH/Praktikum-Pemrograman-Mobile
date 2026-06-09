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

        binding.wikiUrl = arguments?.getString("wikiUri") ?: ""

        val context = requireContext()
        binding.productImg.setImageDrawable(context.getDrawable(arguments?.getInt("imgResource") ?: 0))
        binding.titleTextView.text = getString(arguments?.getInt("titleResource") ?: 0)
        binding.descTextView.text = getString(arguments?.getInt("descResource") ?: 0)

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