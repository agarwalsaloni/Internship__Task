
package com.example.internshiptask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    lateinit var viewModel: MainViewModel
    lateinit var recyclerview : RecyclerView
    private val retrofitService = RetrofitService.getInstance()
    var adapter = UserAdapter(listOf())
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(this)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        viewModel.getAllUsers()

        viewModel.userList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter = UserAdapter(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        recyclerview.adapter = adapter
        recyclerview.layoutManager = layoutManager
    }
}