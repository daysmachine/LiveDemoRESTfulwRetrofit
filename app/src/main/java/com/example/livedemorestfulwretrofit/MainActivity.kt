package com.example.livedemorestfulwretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup the meme template fragment
        val memeTemplateFragment = this.supportFragmentManager.findFragmentById(
            R.id.meme_template_fragment_container
        )
        if ( memeTemplateFragment == null ) {
            val frag = MemeTemplatesFragment()
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.meme_template_fragment_container, frag)
                .commit()
        }

        // Setup the captioned meme fragment
        val captionedMemeFragment = this.supportFragmentManager.findFragmentById(
            R.id.rendered_meme_fragment_container
        )
        if ( captionedMemeFragment == null ) {
            val frag = RenderedMemeFragment()
            this.supportFragmentManager
                .beginTransaction()
                .add(R.id.rendered_meme_fragment_container, frag)
                .commit()
        }
    }
}