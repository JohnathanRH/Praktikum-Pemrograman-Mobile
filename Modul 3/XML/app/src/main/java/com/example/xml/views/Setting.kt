package com.example.xml.views

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.example.xml.LanguageManager
import com.example.xml.MainActivity
import com.example.xml.R

class Setting : Fragment(R.layout.setting) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val englishBtn : Button = view.findViewById(R.id.englishBtn)
        val spanishBtn : Button = view.findViewById(R.id.spansihBtn)

        englishBtn.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags("en")
            )
        }
        spanishBtn.setOnClickListener {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags("sp")
            )
        }

    }
}