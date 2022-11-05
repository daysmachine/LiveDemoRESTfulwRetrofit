package com.example.livedemorestfulwretrofit

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.squareup.picasso.Picasso

private const val TAG = "RenderedMemeFragment"

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


        this.captionButton.setOnClickListener {

            Log.v(TAG, "Caption button clicked!")

            val text1 = this.captionInput1.text
            val text2 = this.captionInput2.text

            val memeTemplate = this.memerViewModel.getCurrentMemeTemplate()

            if( text1 == null || text2 == null){
                Log.e(TAG, "cannot caption meme because one of the caption inputs is null!")
            }
            else if (memeTemplate == null){
                Log.e(TAG, "Cannot caption meme now because there is no selected template!")
            }
            else{
                Log.v(TAG, "Asking the MemerViewModel to caption a meme: $text1 / $text2 / $memeTemplate")

                val captionResponseData = this.memerViewModel.captionImage(
                    template_id = memeTemplate.id,
                    caption1 = text1.toString(),
                    caption2 = text2.toString()
                )
                captionResponseData.observe(
                    this.viewLifecycleOwner,
                    Observer { responseData ->
                        Log.d(TAG, "Fragment has noticed a meme has been created: $responseData")

                        Picasso.get()
                            .load(responseData.url)
                            .into(this.captionedMemeImage)
                    }
                )
            }


        }
        return view
    }
}