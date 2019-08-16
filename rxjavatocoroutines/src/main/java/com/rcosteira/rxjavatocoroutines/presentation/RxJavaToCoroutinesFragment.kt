package com.rcosteira.rxjavatocoroutines.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.rcosteira.core.domain.Id
import com.rcosteira.core.extensions.observe
import com.rcosteira.core.ui.BaseFragment
import com.rcosteira.rxjavatocoroutines.R
import com.rcosteira.rxjavatocoroutines.presentation.RxJavaToCoroutinesViewEvents.DeleteUser
import com.rcosteira.rxjavatocoroutines.presentation.entities.DisplayedDetailedUser
import kotlinx.android.synthetic.main.fragment_rx_java_to_coroutines.*

class RxJavaToCoroutinesFragment : BaseFragment(), CardButtonClickListener<Id> {
    private lateinit var viewModel: RxJavaToCoroutinesViewModel
    private lateinit var adapter: DetailedUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rx_java_to_coroutines, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        viewModel = createViewModel(this) {
            observe(viewLifecycleOwner, viewState, ::renderViewState)
        }
    }

    // Method that is called when we click a delete button on a card
    override fun onButtonClicked(item: Id) {
        viewModel.processEvents(DeleteUser(item))
    }

    private fun setupRecyclerView() {
        prepareAdapter()
        val numberOfColumns = 2
        recyclerViewDetailedUsers.layoutManager = GridLayoutManager(this.context, numberOfColumns)
        recyclerViewDetailedUsers.adapter = adapter
        recyclerViewDetailedUsers.setHasFixedSize(true)
    }

    private fun prepareAdapter() {
        adapter = DetailedUsersAdapter(this)
    }

    private fun renderViewState(state: RxJavaToCoroutinesViewState) {
        renderProgressBar(state.isLoading)
        renderDetailedUserList(state.detailedUsers)
    }

    private fun renderProgressBar(loading: Boolean) {
        if (loading) {
            progressBarLoading.show()
        } else {
            progressBarLoading.hide()
        }
    }

    private fun renderDetailedUserList(detailedUsers: List<DisplayedDetailedUser>) {
        adapter.submitList(detailedUsers)
    }
}
