package com.example.internshiptask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(var users : List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
//    var users = mutableListOf<User>()
//
//    @SuppressLint("NotifyDataSetChanged")
//    fun setUserList(users: List<User>) {
//        this.users = users.toMutableList()
//        notifyDataSetChanged()
//    }

    class UserViewHolder(binding : View) : RecyclerView.ViewHolder(binding){
        val fullname : TextView = binding.findViewById(R.id.fullNametxt)
        val email : TextView = binding.findViewById(R.id.mailTxt)
        val pic : ImageView = binding.findViewById(R.id.userImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.user,parent,false)
        return UserViewHolder(root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.fullname.text = "${user.firstName} ${user.lastName}"
        holder.email.text = user.email
        Glide.with(holder.itemView).load(user.imageUrl).into(holder.pic)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}