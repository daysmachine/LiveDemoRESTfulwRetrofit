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

    val memeTemplatesLiveData: LiveData<List<MemeTemplateItem>>
    init {
        this.memeTemplatesLiveData = ImgFlipExecutor().fetchTemplates()
    }
}