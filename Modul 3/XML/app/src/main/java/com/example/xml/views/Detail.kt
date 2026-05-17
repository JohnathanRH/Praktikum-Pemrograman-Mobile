package com.example.xml.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.xml.MainActivity
import com.example.xml.R
import com.google.android.material.imageview.ShapeableImageView

class Detail : Fragment(R.layout.detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title: TextView = view.findViewById(R.id.titleTextView)
        val productImg: ShapeableImageView = view.findViewById(R.id.productImg)
        val desc: TextView = view.findViewById(R.id.descTextView)
        val wikiBtn: Button = view.findViewById(R.id.wikiBtn)
        val homeBtn: Button = view.findViewById(R.id.homeBtn)

        val imgDrawable = view.context.getDrawable(arguments?.getInt("imgResource") ?: 0)
        val wikiUri: Uri = arguments?.getString("wikiUri")?.toUri() ?: "".toUri()

        productImg.setImageDrawable(imgDrawable)
        title.text = getString(arguments?.getInt("titleResource") ?: 0)
        desc.text = getString(arguments?.getInt("descResource") ?: 0)

        wikiBtn.setOnClickListener {
            val intent : Intent = Intent(
                Intent.ACTION_VIEW,
                wikiUri
            )
            view.context.startActivity(intent)
        }

        homeBtn.setOnClickListener {
            val activity = context as? MainActivity
            val homeFragment = Home()

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainerView, homeFragment)
                addToBackStack(null)
                commit()
            }
        }

    }
}