package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.User

class ListUserAdapter(private val listUser: ArrayList<User>) : RecyclerView.Adapter<ListUserAdapter.ListUserViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var ivProfileImage: ImageView = itemView.findViewById(R.id.iv_profile_image)
        var tvUsername: TextView = itemView.findViewById(R.id.tv_username)
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListUserViewHolder {
        val view : View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false)
        return ListUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUserViewHolder, position: Int) {
        val (username, name, _, _, _, _, _, avatar) = listUser[position]

        Glide.with(holder.itemView.context)
                .load(holder.itemView.context.resources.getIdentifier(avatar,"drawable",holder.itemView.context.packageName))
                .circleCrop()
                .into(holder.ivProfileImage)
            holder.tvUsername.text = username
            holder.tvName.text = name
            holder.itemView.setOnClickListener {
                onItemClickCallback.onItemClicked(listUser[holder.adapterPosition])
            }

    }

    override fun getItemCount(): Int = listUser.size

    interface OnItemClickCallback {
        fun onItemClicked(data: User)
    }
}