package com.example.xml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.commit
import androidx.recyclerview.widget.RecyclerView
import com.example.xml.models.Game
import com.example.xml.views.components.CardViewHolder
import com.example.xml.views.Detail

class RecyclerViewAdapter(private val games: List<Game>) :
    RecyclerView.Adapter<CardViewHolder>(){

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card, viewGroup, false)

        return CardViewHolder(view)
    }

    override fun onBindViewHolder(
        viewHolder: CardViewHolder, position: Int
    ) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val game = games[position]

        setupCardContents(viewHolder, game)
    }

    override fun getItemCount() = games.size

    fun setupCardContents(viewHolder: CardViewHolder, game : Game){
        val context = viewHolder.itemView.context

        viewHolder.title.text = context.getString(game.titleResource)
        viewHolder.description.text = context.getString(game.descResource)
        viewHolder.productImg.setImageDrawable(context.getDrawable(game.imgResource))

        viewHolder.wikiBtn.setOnClickListener {
            val intent : Intent = Intent(
                Intent.ACTION_VIEW,
                game.wikiUri.toUri()
                )
            context.startActivity(intent)
        }

        viewHolder.detailBtn.setOnClickListener {
            val activity = context as? MainActivity
            val detailFragment = Detail()

            val bundle : Bundle = Bundle()
            bundle.putInt("titleResource", game.titleResource)
            bundle.putInt("descResource", game.descResource)
            bundle.putInt("imgResource", game.imgResource)
            bundle.putString("wikiUri", game.wikiUri)
            detailFragment.arguments = bundle

            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.fragmentContainerView, detailFragment)
                addToBackStack(null)
                commit()
            }
        }
    }
}