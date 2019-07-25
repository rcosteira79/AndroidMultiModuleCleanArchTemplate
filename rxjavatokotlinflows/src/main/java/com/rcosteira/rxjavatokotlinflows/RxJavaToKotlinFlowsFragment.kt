package com.rcosteira.rxjavatokotlinflows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rcosteira.core.ui.BaseFragment


class RxJavaToKotlinFlowsFragment : BaseFragment() {

    private lateinit var viewModel: RxJavaToKotlinFlowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rx_java_to_kotlin_flows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = createViewModel(this) {
            // observe stuff
        }
    }

}
