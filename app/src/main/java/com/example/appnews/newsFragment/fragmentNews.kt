package com.example.appnews.newsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.appnews.apis.ApiManger
import com.example.appnews.databinding.FragmentNewsBinding
import com.example.appnews.model.soursesResponce.NewsResponse.ArticlesItem
import com.example.appnews.model.soursesResponce.NewsResponse.NewsResponse
import com.example.appnews.model.soursesResponce.Source
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fragmentNews:Fragment() {
lateinit var binding: FragmentNewsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentNewsBinding.inflate(inflater,container,false)
        return binding.root

     }
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      viewinit()


    }

    private fun viewinit() {
binding.RecyclerView.adapter=adapter    }

    var source:Source?=null
fun ChangeSource(source: Source){
    this.source=source
    LoadNews()

}

    private fun LoadNews() {
ChangeLoadingVisability(true)
        source?.id?.let {sourceId->
            ApiManger
                .getServices()
                .getNews(sources = sourceId)
                .enqueue(object :Callback<NewsResponse>{
                    override fun onResponse(
                        call: Call<NewsResponse>,
                        response: Response<NewsResponse>
                    ) {
                        ChangeLoadingVisability(false)
                    if (response.isSuccessful){
                            showNewsList(response.body()?.articles)
                                        return
                    }
                        val ResponseJson=response.errorBody()?.string()
                        val errorResponse=Gson().fromJson(ResponseJson,NewsResponse::class.java)
                        errorResponse.message?.let { showError(it) }



                    }

                    override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    ChangeLoadingVisability(false)
                        t.message?.let { showError(it) }
                    }
                } ) }

    }

//define new s adapter
    val adapter=newsAdapter(null)
    private fun showNewsList(articles: List<ArticlesItem?>?) {
            adapter.changeData(articles)


    }

    private fun ChangeLoadingVisability(isLoadingVisibility:Boolean){
        binding.progressBar.isVisible=isLoadingVisibility

    }

    private fun showError(message:String) {

        binding.errorView.isVisible=true
        binding
            .errorMessage.text=message

    }

}

