package com.example.internshiptask

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllUsers() = retrofitService.getAllUsers()
}