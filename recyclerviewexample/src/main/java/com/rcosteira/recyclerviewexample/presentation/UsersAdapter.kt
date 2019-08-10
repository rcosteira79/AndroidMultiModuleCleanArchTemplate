package com.rcosteira.recyclerviewexample.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rcosteira.recyclerviewexample.R
import com.rcosteira.recyclerviewexample.presentation.entities.DisplayedUser
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_view_item.*

class UsersAdapter constructor(
    private val rowClickListener: RecyclerViewRowClickListener<DisplayedUser>
) : ListAdapter<DisplayedUser, UsersAdapter.DisplayedUserViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisplayedUserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return DisplayedUserViewHolder(
            inflater.inflate(
                R.layout.recycler_view_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DisplayedUserViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, rowClickListener)
    }

    class DisplayedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(displayedUser: DisplayedUser, clickListener: RecyclerViewRowClickListener<DisplayedUser>) {
            textViewUserName.text = displayedUser.name
            checkboxUserSelected.isChecked = displayedUser.isChecked

            setCheckBoxOnClickListener(displayedUser, clickListener)
            setRowOnClickListener(displayedUser, clickListener)
        }

        private fun setCheckBoxOnClickListener(
            displayedUser: DisplayedUser,
            clickListener: RecyclerViewRowClickListener<DisplayedUser>
        ) {
            checkboxUserSelected.setOnClickListener {
                onUserClicked(displayedUser, clickListener)
            }
        }

        private fun setRowOnClickListener(
            displayedUser: DisplayedUser,
            clickListener: RecyclerViewRowClickListener<DisplayedUser>
        ) {
            itemView.setOnClickListener {
                onUserClicked(displayedUser, clickListener)
            }
        }

        private fun onUserClicked(
            displayedUser: DisplayedUser,
            clickListener: RecyclerViewRowClickListener<DisplayedUser>
        ) {
            changeCheckbox(displayedUser)
            clickListener.onRowClicked(displayedUser, adapterPosition)
        }

        private fun changeCheckbox(displayedUser: DisplayedUser) {
            val checked = displayedUser.isChecked
            displayedUser.isChecked = !checked
            checkboxUserSelected.isChecked = !checked
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DisplayedUser>() {
        override fun areItemsTheSame(
            oldItem: DisplayedUser,
            newItem: DisplayedUser
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: DisplayedUser,
            newItem: DisplayedUser
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.isChecked == newItem.isChecked
        }
    }
}
