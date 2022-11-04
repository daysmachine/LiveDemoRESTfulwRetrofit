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

class RenderedMemeFragment : Fragment()
{
    private lateinit var memerViewModel: MemerViewModel

    private lateinit var captionInput1: TextView
    private lateinit var captionInput2: TextView

    private lateinit var captionButton: Button
    private lateinit var captionedMemeImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.memerViewModel = ViewModelProviders.of(this.requireActivity()).get(MemerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rendered_meme, container, false)

        this.captionInput1 = view.findViewById(R.id.caption_input1)
        this.captionInput2 = view.findViewById(R.id.caption_input2)

        this.captionButton = view.findViewById(R.id.caption_button)
        this.captionedMemeImage = view.findViewById(R.id.captioned_meme)

        return view
    }
}