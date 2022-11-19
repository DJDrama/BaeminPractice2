package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dj.baeminpractice2.R
import com.dj.baeminpractice2.databinding.BottomSheetDialogFragmentTermsListBinding
import com.dj.baeminpractice2.presentation.model.TermType
import com.dj.baeminpractice2.presentation.utils.BUNDLE_KEY_SELECTED_TERM
import com.dj.baeminpractice2.presentation.utils.REQUEST_KEY_SELECTED_TERM_FOR_BAEMIN_SERVICE
import com.dj.baeminpractice2.presentation.utils.REQUEST_KEY_SELECTED_TERM_FOR_LOCATION_SERVICE
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

class TermsListBottomSheetDialogFragment :
    BottomSheetDialogFragment(R.layout.bottom_sheet_dialog_fragment_terms_list) {
    private val viewModel: TermsListViewModel by viewModels()
    private lateinit var termsTitleAdapter: TermsTitleAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = BottomSheetDialogFragmentTermsListBinding.bind(view)
        initViews(binding = binding)
        collectFlows()
    }

    private fun initViews(binding: BottomSheetDialogFragmentTermsListBinding) {
        termsTitleAdapter = TermsTitleAdapter {
            viewModel.handleIntent(
                intent = TermsListIntent.OnChooseTermItem(
                    termPresentationModel = it
                )
            )
        }
        binding.rvTerms.adapter = termsTitleAdapter
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiState.collect {
                        termsTitleAdapter.submitList(it.terms)
                    }
                }
                launch {
                    viewModel.uiEvent.collect {
                        when (it) {
                            is TermsListUiEvent.ChosenTermItem -> {
                                findNavController().popBackStack()
                                setFragmentResult(
                                    requestKey = if (it.termType == TermType.BAEMIN_SERVICE_TYPE.ordinal)
                                        REQUEST_KEY_SELECTED_TERM_FOR_BAEMIN_SERVICE
                                    else
                                        REQUEST_KEY_SELECTED_TERM_FOR_LOCATION_SERVICE,
                                    result = bundleOf(
                                        BUNDLE_KEY_SELECTED_TERM to it.termPresentationModel
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}