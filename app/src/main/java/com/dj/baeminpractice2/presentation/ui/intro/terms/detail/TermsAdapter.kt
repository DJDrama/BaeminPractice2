package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class TermsAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    private val itemCount = 2

    override fun getItemCount() = itemCount
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> BaeminServiceTermFragment()
            1 -> LocationServiceTermFragment()
            else -> throw IllegalArgumentException("Position should be at least 0 or 1")
        }
    }
}
