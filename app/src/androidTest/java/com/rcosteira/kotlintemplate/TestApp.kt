package com.rcosteira.kotlintemplate

import com.rcosteira.kotlintemplate.di.AppInjector

class TestApp : App() {
    override fun injectDaggerComponents() {
        AppInjector.testInject(this)
    }
}