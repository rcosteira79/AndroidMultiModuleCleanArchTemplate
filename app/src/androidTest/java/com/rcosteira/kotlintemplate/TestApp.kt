package com.rcosteira.kotlintemplate

import com.rcosteira.kotlintemplate.di.TestAppInjector

class TestApp : App() {
    override fun injectDaggerComponents() {
        TestAppInjector.testInject(this)
    }
}