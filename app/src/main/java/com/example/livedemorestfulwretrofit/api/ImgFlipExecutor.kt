package com.example.livedemorestfulwretrofit.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ImgFlipExecutor"
private const val BASE_URL = "https://api.imgflip.com/"

class ImgFlipExecutor
{
    private val api: ImgFlipApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        this.api = retrofit.create(ImgFlipApi::class.java)
    }

    fun fetchTemplates(): LiveData<List<MemeTemplateItem>> {
        val responseLiveData: MutableLiveData<List<MemeTemplateItem>> = MutableLiveData()
        val imgFlipRequest: Call<ImgFlipGetMemesResponse> = this.api.fetchTemplates()

        imgFlipRequest.enqueue(object: Callback<ImgFlipGetMemesResponse>{

            override fun onFailure(call: Call<ImgFlipGetMemesResponse>, t: Throwable) {
                Log.v(TAG, "Failed to fetch ImgFlip meme template")
            }

            override fun onResponse(
                call: Call<ImgFlipGetMemesResponse>,
                response: Response<ImgFlipGetMemesResponse>
            ) {
                Log.v(TAG, "Response received from ImgFlip templates endpoint")

                val imgFlipGetMemesResponse: ImgFlipGetMemesResponse? = response.body()
                val imgFlipGetMemesResponseData: ImgFlipGetMemesResponseData? = imgFlipGetMemesResponse?.data

                var memeTemplates: List<MemeTemplateItem> =
                    imgFlipGetMemesResponseData?.templates ?: mutableListOf()

                memeTemplates = memeTemplates.filterNot {
                    it.url.isBlank()
                }

                //Log.v(TAG, "ImgFlip templates: $memeTemplates")

                responseLiveData.value = memeTemplates
            }

        })

        return responseLiveData
    }
}