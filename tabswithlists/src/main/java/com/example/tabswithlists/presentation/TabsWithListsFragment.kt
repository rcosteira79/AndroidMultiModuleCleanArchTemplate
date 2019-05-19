package com.example.tabswithlists.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tabswithlists.R
import com.rcosteira.core.ui.BaseFragment
import javax.inject.Inject

class TabsWithListsFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: TabsWithListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_tabs_with_lists, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = createViewModel(this) {
            // TODO add view model observers here
        }

        super.onViewCreated(view, savedInstanceState)
    }
}