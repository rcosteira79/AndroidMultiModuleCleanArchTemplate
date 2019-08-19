package com.rcosteira.rxjavatocoroutines.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rcosteira.core.domain.Id
import com.rcosteira.core.extensions.load
import com.rcosteira.rxjavatocoroutines.R
import com.rcosteira.rxjavatocoroutines.presentation.DetailedUsersAdapter.DisplayedDetailedUserViewHolder
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.rx_coroutines_recycler_view_item.*

class DetailedUsersAdapter constructor(
    private val deleteButtonListener: CardButtonClickListener<Id>
) : ListAdapter<DisplayedDetailedUser, DisplayedDetailedUserViewHolder>(
    DiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayedDetailedUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return DisplayedDetailedUserViewHolder(
            inflater.inflate(
                R.layout.rx_coroutines_recycler_view_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DisplayedDetailedUserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, deleteButtonListener)
    }

    class DisplayedDetailedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(
            item: DisplayedDetailedUser,
            deleteButtonClickListener: CardButtonClickListener<Id>
        ) {
            textViewName.text = item.name.value
            textViewUsername.text = item.username.value
            textViewBlog.text = item.blog.value
            textViewLocation.text = item.location.value
            imageViewAvatar
                .load(item.avatar.value)

            setDeleteButtonClickListener(item, deleteButtonClickListener)
        }

        private fun setDeleteButtonClickListener(
            user: DisplayedDetailedUser,
            deleteButtonClickListener: CardButtonClickListener<Id>
        ) {
            buttonDelete.setOnClickListener {
                deleteButtonClickListener.onButtonClicked(user.id)
            }
        }
    }

    // This is pretty simple, so it shouldn't be a problem if it runs in the main thread
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
