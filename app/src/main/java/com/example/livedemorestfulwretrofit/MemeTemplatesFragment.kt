package com.example.livedemorestfulwretrofit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

class MemeTemplatesFragment : Fragment()
{
    private lateinit var memerViewModel: MemerViewModel

    private lateinit var memeTemplateImage: ImageView
    private lateinit var prevButton: Button
    private lateinit var nextButton: Button
    private lateinit var memeTemplateIndexLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)

        this.memerViewModel = ViewModelProviders.of(this).get(MemerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meme_template, container, false)

        this.memeTemplateImage = view.findViewById(R.id.meme_template_image)
        this.prevButton = view.findViewById(R.id.prev_button)
        this.nextButton = view.findViewById(R.id.next_button)
        this.memeTemplateIndexLabel = view.findViewById(R.id.meme_template_index_label)

        return view
    }
}