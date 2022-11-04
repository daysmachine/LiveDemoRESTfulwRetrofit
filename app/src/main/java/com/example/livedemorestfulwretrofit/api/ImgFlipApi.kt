package com.example.livedemorestfulwretrofit.api

import retrofit2.Call
import retrofit2.http.GET

interface ImgFlipApi
{
    // get_memes
    @GET("get_memes")
    fun fetchTemplates(): Call<ImgFlipGetMemesResponse>
}