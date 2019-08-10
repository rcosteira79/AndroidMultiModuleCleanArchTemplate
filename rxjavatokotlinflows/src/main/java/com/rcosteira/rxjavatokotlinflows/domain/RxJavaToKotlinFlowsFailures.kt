package com.rcosteira.rxjavatokotlinflows.domain

import com.rcosteira.core.exception.Failure.FeatureFailure

class RxJavaToKotlinFlowsFailures {
    class NoSearchMatch() : FeatureFailure()
}