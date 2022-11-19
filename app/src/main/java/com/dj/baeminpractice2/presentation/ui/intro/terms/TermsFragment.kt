package com.dj.baeminpractice2.presentation.ui.intro.terms

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dj.baeminpractice2.R
import com.dj.baeminpractice2.databinding.FragmentTermsBinding
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch

class TermsFragment : Fragment(R.layout.fragment_terms) {
    private var _binding: FragmentTermsBinding? = null
    private val binding: FragmentTermsBinding
        get() = _binding!!

    private val viewModel: TermsViewModel by viewModels()

    private lateinit var tabTexts: Array<String>
    private lateinit var termsAdapter: TermsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentTermsBinding.bind(view)
        initViews()
        collectFlows()
    }

    private fun initViews() {
        binding.layoutToolbar.ivBack.setOnClickListener { findNavController().navigateUp() }
        binding.layoutToolbar.tvTitle.text = getString(R.string.text_term)
        tabTexts = arrayOf(
            getString(R.string.text_baemin_term),
            getString(R.string.text_location_term)
        )
        termsAdapter = TermsAdapter(childFragmentManager, viewLifecycleOwner.lifecycle)
        binding.viewPager.adapter = termsAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTexts[position]
        }.attach()
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect {
                binding.viewPager.setCurrentItem(it, false)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}