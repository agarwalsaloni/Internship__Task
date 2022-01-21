package com.example.internshiptask

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val userList = MutableLiveData<List<User>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUsers() {

        val response = repository.getAllUsers()
        response.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val uL = arrayListOf<User>()
                if(response.isSuccessful && response.body()!=null) {

                    val data = response.body()
                    if (data != null) {
                        for (element in data) {
                            val userlist = User(element.firstName,
                                element.lastName,
                                element.email,
                                element.mobile,
                                element.age,
                                element.imageUrl)
                            uL.add(userlist)
                        }
                        userList.postValue(uL)
                    }
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}