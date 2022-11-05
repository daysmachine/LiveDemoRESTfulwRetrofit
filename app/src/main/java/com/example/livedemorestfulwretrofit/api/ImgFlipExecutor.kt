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

    fun captionImage(
        template_id: String,
        caption1: String,
        caption2: String
    )
    {
        val responseLiveData: MutableLiveData<ImgFlipCaptionImageResponseData> = MutableLiveData()

        val imgFlipRequest : Call<ImgFlipCaptionImageResponse> = this.api.captionImage(
            template_id = template_id,
            caption1 = caption1,
            caption2 = caption2,
            username = HorribleIdea_AuthCredentials.HORRIBLE_IDEA_USERNAME,
            password = HorribleIdea_AuthCredentials.HORRIBLE_IDEA_PASSWORD
        )

        Log.d(TAG, "Enqueuing a request to caption meme #$template_id with $caption1 / $caption2")

        imgFlipRequest.enqueue(object: Callback<ImgFlipCaptionImageResponse>{

            override fun onFailure(call: Call<ImgFlipCaptionImageResponse>, t: Throwable) {

                Log.e(TAG, "Failed to caption ImgFlip meme template!")
            }

            override fun onResponse(
                call: Call<ImgFlipCaptionImageResponse>,
                response: Response<ImgFlipCaptionImageResponse>
            ) {
                Log.d(TAG, "Response received from ImgFlip caption_image endpoint")

                val imgFlipCaptionImageResponse: ImgFlipCaptionImageResponse? = response.body()

                if (imgFlipCaptionImageResponse?.success == true) {

                    val imgFlipCaptionImageResponseData: ImgFlipCaptionImageResponseData = imgFlipCaptionImageResponse.data
                    responseLiveData.value = imgFlipCaptionImageResponseData
                    Log.d(TAG, "Get new meme url: ${imgFlipCaptionImageResponseData.url}")

                }
                else {
                    Log.e(
                        TAG,
                        "Request  to caption image has failed ${imgFlipCaptionImageResponse?.error_message}"
                    )
                }
            }



        })


        return responseLiveData
    }
}