package com.example.xml.views.components

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xml.R

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val productImg: ImageView
    val title: TextView
    val description : TextView
    val wikiBtn : Button
    val detailBtn : Button

    init {
        title = view.findViewById(R.id.cardProductTitle)
        productImg = view.findViewById(R.id.productImg)
        description = view.findViewById(R.id.cardProductDescription)
        wikiBtn = view.findViewById(R.id.wikiBtn)
        detailBtn = view.findViewById(R.id.detailBtn)
    }
}