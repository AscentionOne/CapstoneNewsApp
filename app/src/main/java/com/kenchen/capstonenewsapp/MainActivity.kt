package com.kenchen.capstonenewsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kenchen.capstonenewsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // using view binding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}