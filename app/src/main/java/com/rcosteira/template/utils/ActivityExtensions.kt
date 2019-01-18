package com.rcosteira.template.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity

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