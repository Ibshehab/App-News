package com.example.appnews.model.soursesResponce.NewsResponse

import com.google.gson.annotations.SerializedName

data class NewsResponse(
	val totalResults: Int? = null,
	val articles: List<ArticlesItem?>? = null,
	val status: String? = null,
	@field:SerializedName("code")
	val code:String?=null,
	@field:SerializedName("message")
	val message:String?=null,





	)
