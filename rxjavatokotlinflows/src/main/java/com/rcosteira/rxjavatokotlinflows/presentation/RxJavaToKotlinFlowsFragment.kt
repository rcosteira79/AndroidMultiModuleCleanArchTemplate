package com.rcosteira.rxjavatokotlinflows.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.rcosteira.core.extensions.observe
import com.rcosteira.core.ui.BaseFragment
import com.rcosteira.rxjavatokotlinflows.R
import com.rcosteira.rxjavatokotlinflows.presentation.entities.DisplayedDetailedUser
import kotlinx.android.synthetic.main.fragment_rx_java_to_kotlin_flows.*

class RxJavaToKotlinFlowsFragment : BaseFragment() {

    private lateinit var viewModel: RxJavaToKotlinFlowsViewModel
    private lateinit var adapter: DetailedUsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rx_java_to_kotlin_flows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        viewModel = createViewModel(this) {
            observe(viewLifecycleOwner, viewState, ::renderViewState)
        }
    }

    private fun setupRecyclerView() {
        prepareAdapter()
        val numberOfColumns = 2
        recyclerViewDetailedUsers.layoutManager = GridLayoutManager(this.context, numberOfColumns)
        recyclerViewDetailedUsers.adapter = adapter
        recyclerViewDetailedUsers.setHasFixedSize(true)
    }

    private fun prepareAdapter() {
        adapter = DetailedUsersAdapter()
    }

    private fun renderViewState(state: RxJavaToKotlinFlowsViewState) {
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
