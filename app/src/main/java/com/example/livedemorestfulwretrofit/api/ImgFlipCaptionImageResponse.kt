package com.example.livedemorestfulwretrofit.api

import com.google.gson.annotations.SerializedName

class ImgFlipCaptionImageResponse
{
    @SerializedName("success")
    var success: Boolean = false

    @SerializedName("data")
    lateinit var data: ImgFlipCaptionImageResponse

    @SerializedName("error_message")
    lateinit var error_message: String
}