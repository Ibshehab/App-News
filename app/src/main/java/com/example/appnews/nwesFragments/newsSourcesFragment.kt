package com.example.appnews.nwesFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.appnews.R
import com.example.appnews.apis.ApiManger
import com.example.appnews.databinding.FragmentSourcesNewsBinding
import com.example.appnews.model.soursesResponce.Source
import com.example.appnews.model.soursesResponce.SourcesResponse
import com.example.appnews.newsFragment.fragmentNews
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class newsSourcesFragment: Fragment() {

    lateinit var binding:FragmentSourcesNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSourcesNewsBinding.inflate(inflater,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getNewsSourses()

        initViews()


    }

val newsFragment=fragmentNews()
    private fun initViews() {
        childFragmentManager.beginTransaction().replace(R.id.fragment_container,newsFragment).commit()
        binding.tryAgain.setOnClickListener{
            binding.errorView.isVisible=false
            binding.progressBar.isVisible=true
            getNewsSourses()
        }
    }

    private fun ChangeLoadingVisability(isLoadingVisibility:Boolean){
    binding.progressBar.isVisible=isLoadingVisibility

}
    private fun getNewsSourses() {
         ApiManger.getServices().getNewsRequest( ).enqueue(object : Callback<SourcesResponse>{
            override fun onResponse(
                call: Call<SourcesResponse>,
                response: Response<SourcesResponse>
            ) {
                ChangeLoadingVisability(false)
                if (response.isSuccessful){


                    ShowNewsSource(response.body()?.sources)
                    return

                }

               val responseJson= response.errorBody()?.string()
                val errorResponse=Gson().fromJson(responseJson,SourcesResponse::class.java)
                errorResponse.message?.let { showError(it) }

            }

            override fun onFailure(call: Call<SourcesResponse>, t: Throwable) {
                ChangeLoadingVisability(false)
                t.message?.let { showError(it) }
            }


        })
    }
//if all things is done
    private fun ShowNewsSource(sources: List<Source?>?) {
        binding.errorView.isVisible=false
    binding.progressBar.isVisible=false

    sources?.forEach {source->
        val tab =binding.tabLayout.newTab()
        tab.text=source?.name
        tab.tag=source
         }
    binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
        override fun onTabSelected(tab: TabLayout.Tab?) {
            val source= tab?.tag as Source
            newsFragment.ChangeSource(source)        }

        override fun onTabUnselected(tab: TabLayout.Tab?) {
            TODO("Not yet implemented")
        }

        override fun onTabReselected(tab: TabLayout.Tab?) {
            val source=tab?.tag as Source
            newsFragment.ChangeSource(source)

         }
    })
binding.tabLayout.getTabAt(0)?.select()

    }

    private fun showError(message:String) {

        binding.errorView.isVisible=true
         binding
             .errorMessage.text=message

    }


}