package com.example.livedemorestfulwretrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.livedemorestfulwretrofit.api.ImgFlipExecutor
import com.example.livedemorestfulwretrofit.api.MemeTemplateItem

class MemerViewModel : ViewModel()
{
    private var templateIndex: Int = 0


    fun setTemplateIndex(index: Int) {
        this.templateIndex = index
    }
    fun getTemplateIndex(): Int {
        return this.templateIndex
    }

    fun increaseTemplateIndex(): Int {
        this.setTemplateIndex(this.templateIndex + 1)
        return  this.templateIndex
    }

    fun decreaseTemplateIndex(): Int {
        this.setTemplateIndex(this.templateIndex - 1)
        return  this.templateIndex
    }

    private fun keepTemplateIndexInBounds(){

        if (this.templateIndex >= (this.memeTemplatesLiveData.value?.size ?: 0) ) {
            this.templateIndex = 0
            }
            else if ( this.templateIndex < 0 && (this.memeTemplatesLiveData.value?.size ?: 0) > 0 ) {
                this.templateIndex = this.memeTemplatesLiveData.value!!.size - 1
            }
    }

    //slide 60 need to fix
    fun getCurrentMemeTemplate(): MemeTemplateItem? {
        if (this.memeTemplatesLiveData.value != null ){

            this.keepTemplateIndexInBounds()

            if (this.templateIndex >= 0 && this.templateIndex <= this.memeTemplatesLiveData.value!!.size){
               return this.memeTemplatesLiveData.value!![this.templateIndex]
            }

        }

        return null
    }

    val memeTemplatesLiveData: LiveData<List<MemeTemplateItem>>
    init {
        this.memeTemplatesLiveData = ImgFlipExecutor().fetchTemplates()
    }
}