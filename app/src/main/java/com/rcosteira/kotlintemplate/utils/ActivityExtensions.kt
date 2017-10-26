package com.rcosteira.kotlintemplate.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment,
                                      frameId: Int,
                                      animationIn: Int = android.R.anim.slide_in_left,
                                      animationOut: Int = android.R.anim.fade_out) {
    supportFragmentManager.inTransaction {
        setCustomAnimations(animationIn, animationOut)
        replace(frameId, fragment)
    }
}