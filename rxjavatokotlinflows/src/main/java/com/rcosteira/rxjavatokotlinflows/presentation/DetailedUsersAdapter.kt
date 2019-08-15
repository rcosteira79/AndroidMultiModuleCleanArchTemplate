package com.rcosteira.rxjavatokotlinflows.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rcosteira.core.extensions.load
import com.rcosteira.rxjavatokotlinflows.R
import com.rcosteira.rxjavatokotlinflows.presentation.DetailedUsersAdapter.DisplayedDetailedUserViewHolder
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rx_flows_recycler_view_item.*

class DetailedUsersAdapter(
) : ListAdapter<DisplayedDetailedUser, DisplayedDetailedUserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayedDetailedUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return DisplayedDetailedUserViewHolder(
            inflater.inflate(
                R.layout.rx_flows_recycler_view_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DisplayedDetailedUserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DisplayedDetailedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(item: DisplayedDetailedUser) {
            textViewName.text = item.name.value
            textViewUsername.text = item.username.value
            textViewBlog.text = item.blog.value
            textViewLocation.text = item.location.value
            imageViewAvatar
                .load(item.avatar.value)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DisplayedDetailedUser>() {
        override fun areItemsTheSame(
            oldItem: DisplayedDetailedUser,
            newItem: DisplayedDetailedUser
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DisplayedDetailedUser,
            newItem: DisplayedDetailedUser
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.username == newItem.username &&
                    oldItem.blog == newItem.blog &&
                    oldItem.location == newItem.location &&
                    oldItem.avatar == newItem.avatar
        }
    }
}
