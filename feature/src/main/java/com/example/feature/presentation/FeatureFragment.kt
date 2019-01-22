package com.example.feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.feature.R
import com.rcosteira.core.ui.BaseFragment
import javax.inject.Inject

class FeatureFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: FeatureViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_feature, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = createViewModel(this) {
            // TODO add view model observers here
        }

        super.onViewCreated(view, savedInstanceState)
    }
}