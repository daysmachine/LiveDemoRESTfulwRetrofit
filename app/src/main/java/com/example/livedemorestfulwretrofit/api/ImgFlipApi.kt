package com.example.livedemorestfulwretrofit.api

import retrofit2.Call
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ImgFlipApi
{
    // get_memes
    @GET("get_memes")
    fun fetchTemplates(): Call<ImgFlipGetMemesResponse>

    @FormUrlEncoded
    @POST("caption_image")
    fun captionImage(
        //TODO
    ): Call<ImgFlipCaptionImageResponse>
}