package com.div.additonapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.div.additonapp.adapter.MyAdapter
import com.div.additonapp.databinding.ActivityMainBinding
import com.div.additonapp.repository.Repository

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy { MyAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setupRecyclerview()

        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let {
                    myAdapter.setData(it)
                }
            } else {
                Toast.makeText(this, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun setupRecyclerview() {
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

}