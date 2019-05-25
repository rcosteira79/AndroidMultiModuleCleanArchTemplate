package com.rcosteira.recyclerviewexample.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rcosteira.core.exception.Failure
import com.rcosteira.core.extensions.observe
import com.rcosteira.core.ui.BaseFragment
import com.rcosteira.recyclerviewexample.R
import com.rcosteira.recyclerviewexample.presentation.entities.DisplayedUser
import kotlinx.android.synthetic.main.fragment_recycler_view_example.*
import javax.inject.Inject

class RecyclerViewExampleFragment : BaseFragment(), RecyclerViewRowClickListener<DisplayedUser> {

    @Inject
    lateinit var viewModel: RecyclerViewExampleViewModel

    lateinit var adapter: UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recycler_view_example, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()

        viewModel = createViewModel(this) {
            observe(viewLifecycleOwner, viewState, ::renderViewState)
        }
    }

    private fun setRecyclerView() {
        prepareAdapter()
        recyclerViewUsers.layoutManager = LinearLayoutManager(this.context)
        recyclerViewUsers.adapter = adapter
        recyclerViewUsers.setHasFixedSize(true)
    }

    private fun prepareAdapter() {
        adapter = UsersAdapter(this)
    }

    private fun renderViewState(state: RecyclerViewExampleViewState) {

        when (state) {
            is RecyclerViewExampleViewState.Loading -> renderLoadingState()
            is RecyclerViewExampleViewState.GotUsers -> renderUserList(state.users)
            is RecyclerViewExampleViewState.SelectedUsersChanged -> renderButton(state.selectedUsers)
            is RecyclerViewExampleViewState.PossibleFailure -> renderPossibleFailure(state.failure)
        }
    }

    private fun renderLoadingState() {
        progressBarLoading.show()
    }

    private fun renderUserList(users: List<DisplayedUser>) {
        adapter.submitList(users)
    }

    private fun renderButton(selectedUsers: String) {
        buttonDoStuff.text = getString(R.string.button_label_do_stuff_with_users, selectedUsers)
        buttonDoStuff.isEnabled = !selectedUsers.isBlank()
    }

    private fun renderPossibleFailure(failure: Failure) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onRowClicked(item: DisplayedUser, position: Int) {
        viewModel.updateNumberOfSelectedUsers(item.isChecked)
        viewModel.updateButtonLabel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerViewUsers.adapter = null
    }
}