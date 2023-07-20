package com.div.additonapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.div.additonapp.databinding.RowLayoutBinding
import com.div.additonapp.model.Post

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private var myList = emptyList<Post>()


    inner class MyViewHolder(val binding: RowLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding = RowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            userIdTxt.text = myList[position].userId.toString()
            idTxt.text = myList[position].id.toString()
            titleTxt.text = myList[position].title
            bodyTxt.text = myList[position].body

        }


    }

    fun setData(newList: List<Post>) {
        myList = newList
        notifyDataSetChanged()
    }
}