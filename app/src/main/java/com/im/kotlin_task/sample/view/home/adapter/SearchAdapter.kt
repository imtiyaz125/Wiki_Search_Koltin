package com.im.kotlin_task.sample.view.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.im.kotlin_task.sample.databinding.RowSearchBinding
import com.im.kotlin_task.sample.model.bean.responses.Page

class SearchAdapter(private val context: Context, private val arrList: ArrayList<Page>,private val iClickListener: iClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val layoutInflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(RowSearchBinding.inflate(layoutInflater, parent, false))
    }

    override fun getItemCount(): Int {
        return arrList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).bind(arrList[position])
    }


    /** View Holders */
    inner class MyViewHolder(private val binding: RowSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Page) {
            binding.search = item
            binding.root.setOnClickListener {
                iClickListener.onItemClicked(adapterPosition)
            }
            Glide.with(context).load(item.thumbnail?.source).into(binding.ivAvtar)
        }
    }
}
interface iClickListener{
    fun onItemClicked(pos:Int)
}