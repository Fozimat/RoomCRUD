package com.fozimat.roomcrud.fragment.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fozimat.roomcrud.data.User
import com.fozimat.roomcrud.databinding.ItemRowUserBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    fun setData(user: List<User>) {
        this.userList = user
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemRowUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            with(binding) {
                tvId.text = user.id.toString()
                tvFirstName.text = user.firstName
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRowUserBinding.inflate(LayoutInflater.from(parent.context), parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = userList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = userList.size
}