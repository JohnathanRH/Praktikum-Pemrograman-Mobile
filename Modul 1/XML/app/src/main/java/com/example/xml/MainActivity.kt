package com.example.xml

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn : Button = findViewById(R.id.rollButton)

        btn.setOnClickListener {
            roll()
        }
    }

    fun roll()
    {
        val imgView : ImageView = findViewById(R.id.diceImage)
        val imgView2 : ImageView = findViewById(R.id.diceImage2)
        val result = (1..6).random()
        val result2 = (1..6).random()

        val imageResource = getDiceDrawable(result)
        val imageResource2 = getDiceDrawable(result2)

        imgView.setImageResource(imageResource)
        imgView2.setImageResource(imageResource2)
        message(result == result2)
    }

    fun message(isDouble : Boolean)
    {
        val rootLayout : LinearLayout = findViewById(R.id.rootLayout)
        if (isDouble)
        {
            Snackbar.make(rootLayout, "Selamat, anda dapat dadu double!", Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            Snackbar.make(rootLayout, "Anda belum beruntung!", Snackbar.LENGTH_SHORT).show()
        }
    }

    fun getDiceDrawable(result : Int) : Int
    {
        var resource = when(result)
        {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }

        return resource
    }
}