package com.example.appnews.newsFragment

import android.view.LayoutInflater
 import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

import com.example.appnews.databinding.ItemNewsBinding
import com.example.appnews.model.soursesResponce.NewsResponse.ArticlesItem

class newsAdapter(var NewsList:List<ArticlesItem?>?): RecyclerView.Adapter<newsAdapter.NewsViewHolder>() {

    class NewsViewHolder(val binding: ItemNewsBinding) : ViewHolder(binding.root) {
        // Bind data to views
        fun bind(article: ArticlesItem?) {
            binding.title.text = article?.title
            // Bind other views here if needed
            binding.author.text=article?.author
            binding.date.text=article?.publishedAt

            Glide. with(itemView)
                .load(article?.urlToImage)
                .into(binding.newsImage)


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
       var binding=ItemNewsBinding.inflate(LayoutInflater.from(parent.context),
           parent,false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int= NewsList?.size?:0

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(NewsList?.get(position))
    }

    fun changeData(articles: List<ArticlesItem?>?) {
        NewsList=articles
        notifyDataSetChanged()
    }


}