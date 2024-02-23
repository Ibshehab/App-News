package com.example.appnews.model.soursesResponce.NewsResponse

import com.example.appnews.model.soursesResponce.Source

data class ArticlesItem(
	val publishedAt: String? = null,
	val author: String? = null,
	val urlToImage: String? = null,
	val description: String? = null,
	val source: Source? = null,
	val title: String? = null,
	val url: String? = null,
	val content: String? = null
)
