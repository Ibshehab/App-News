package com.example.appnews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.appnews.databinding.ActivityMainBinding
import com.example.appnews.nwesFragments.newsSourcesFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,newsSourcesFragment()).commit()


    }
}