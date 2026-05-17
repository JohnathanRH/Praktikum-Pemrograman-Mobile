package com.example.xml.views

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.compose.DummyData
import com.example.xml.CarouselAdapter
import com.example.xml.MainActivity
import com.example.xml.R
import com.example.xml.RecyclerViewAdapter
import kotlin.math.abs

class Home : Fragment(R.layout.home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val settingBtn : ImageButton = view.findViewById(R.id.setting_btn)
        settingBtn.setOnClickListener {
            val activity = context as MainActivity
            val settingFragment = Setting()

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainerView, settingFragment)
                addToBackStack(null)
                commit()
            }
        }

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)

        val images = listOf(
            R.drawable.ss,
            R.drawable.ss2,
            R.drawable.ss3
        )

        viewPager.adapter = CarouselAdapter(images)

        // vfx
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        viewPager.setPageTransformer(transformer)

        //--------------

        val recyclerAdapter = RecyclerViewAdapter(games = DummyData.Card.items)
        val recyclerView: RecyclerView = view.findViewById(R.id.gamesList)

        recyclerView.adapter = recyclerAdapter
    }
}