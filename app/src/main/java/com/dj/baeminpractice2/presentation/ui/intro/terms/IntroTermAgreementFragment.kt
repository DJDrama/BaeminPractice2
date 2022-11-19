package com.dj.baeminpractice2.presentation.ui.intro.terms

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dj.baeminpractice2.R
import com.dj.baeminpractice2.databinding.FragmentIntroTermAgreementBinding
import com.dj.baeminpractice2.presentation.utils.BUNDLE_KEY_VIEWPAGER_POSITION
import kotlinx.coroutines.launch

class IntroTermAgreementFragment : Fragment(R.layout.fragment_intro_term_agreement),
    View.OnClickListener {
    private var _binding: FragmentIntroTermAgreementBinding? = null
    private val binding: FragmentIntroTermAgreementBinding
        get() = _binding!!

    private val positionLocationTerm = 1

    private val viewModel: IntroTermAgreementViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentIntroTermAgreementBinding.bind(view)
        initViews()
        collectFlows()
    }

    private fun initViews() {
        binding.flAgreeAll.setOnClickListener(this)
        binding.flAgreeLocation.setOnClickListener(this)
        binding.flAgreeMarketingPush.setOnClickListener(this)
        binding.ivSeeLocationTerm.setOnClickListener(this)
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.STARTED
            ).collect {
                handleAgreementStates(it.isLocationTermAgreed, it.isMarketingPushTermAgreed)
            }
        }
    }

    private fun handleAgreementStates(
        isLocationTermAgreed: Boolean,
        isMarketingPushTermAgreed: Boolean
    ) {
        binding.cbAgreeAll.isChecked = isLocationTermAgreed && isMarketingPushTermAgreed
        binding.cbAgreeLocation.isChecked = isLocationTermAgreed
        binding.cbAgreeMarketingPush.isChecked = isMarketingPushTermAgreed
        binding.btnStart.isEnabled = isLocationTermAgreed
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it) {
                binding.flAgreeAll -> viewModel.handleIntent(intent = IntroTermAgreementIntent.AgreeAllTerms)
                binding.flAgreeLocation -> viewModel.handleIntent(intent = IntroTermAgreementIntent.AgreeLocationTerm)
                binding.flAgreeMarketingPush -> viewModel.handleIntent(intent = IntroTermAgreementIntent.AgreeMarketingPushTerm)
                binding.ivSeeLocationTerm -> findNavController().navigate(
                    resId = R.id.action_introTermAgreementFragment_to_termsFragment,
                    bundleOf(BUNDLE_KEY_VIEWPAGER_POSITION to positionLocationTerm)
                )
            }
        }
    }
}