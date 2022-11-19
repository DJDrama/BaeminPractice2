package com.dj.baeminpractice2.presentation.ui.intro.terms.detail

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.dj.baeminpractice2.R
import com.dj.baeminpractice2.data.api.BaeminApiServiceImpl
import com.dj.baeminpractice2.data.datasource.remote.implementation.TermDataSourceImpl
import com.dj.baeminpractice2.data.repository.remote.TermsRepositoryImpl
import com.dj.baeminpractice2.databinding.FragmentLocationTermBinding
import com.dj.baeminpractice2.databinding.LayoutTermDetailBinding
import com.dj.baeminpractice2.domain.usecase.GetLocationServiceTermsUseCase
import com.dj.baeminpractice2.presentation.model.TermPresentationModel
import com.dj.baeminpractice2.presentation.model.TermType
import com.dj.baeminpractice2.presentation.utils.*
import kotlinx.coroutines.launch

class LocationServiceTermFragment : Fragment(R.layout.fragment_location_term),
    View.OnClickListener {

    private val viewModel by viewModels<LocationServiceTermViewModel> {
        LocationServiceTermViewModelFactory(
            getLocationServiceTermsUseCase = GetLocationServiceTermsUseCase(
                repository = TermsRepositoryImpl(
                    TermDataSourceImpl(baeminApiService = BaeminApiServiceImpl())
                )
            )
        )
    }

    private var _binding: FragmentLocationTermBinding? = null
    private val binding: FragmentLocationTermBinding
        get() = _binding!!

    private var _mergedBinding: LayoutTermDetailBinding? = null
    private val mergedBinding: LayoutTermDetailBinding
        get() = _mergedBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentLocationTermBinding.bind(view)
        _mergedBinding = LayoutTermDetailBinding.bind(binding.root)
        initViews()
        subscribeToFragmentResultListeners()
        collectFlows()
    }

    private fun initViews() {
        mergedBinding.flChooseTermByDate.setOnClickListener(this)
    }

    @Suppress("DEPRECATION")
    private fun subscribeToFragmentResultListeners() {
        parentFragment?.parentFragmentManager?.setFragmentResultListener(
            REQUEST_KEY_SELECTED_TERM_FOR_LOCATION_SERVICE,
            viewLifecycleOwner
        ) { _, bundle ->
            viewModel.handleIntent(
                intent = LocationServiceTermIntent.ShowChosenTermContent(
                    termPresentationModel =
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        bundle.getParcelable(
                            BUNDLE_KEY_SELECTED_TERM,
                            TermPresentationModel::class.java
                        )
                    } else {
                        bundle.getParcelable(BUNDLE_KEY_SELECTED_TERM)
                    }
                )
            )
        }
    }

    private fun collectFlows() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(state = Lifecycle.State.STARTED) {
                launch {
                    viewModel.uiEvent.collect {
                        when (it) {
                            is LocationServiceTermUiEvent.ListAllTerms -> showAllTerms(
                                terms = it.terms,
                                currentTermId = it.currentTermId
                            )
                        }
                    }
                }
                launch {
                    viewModel.uiState.collect {
                        showCurrentTerm(it.dateTitle, it.content)
                    }
                }
            }
        }
    }

    private fun showCurrentTerm(dateTitle: String, content: String) {
        mergedBinding.tvTermDateTitle.text = dateTitle
        mergedBinding.tvTermContent.text = content
    }

    private fun showAllTerms(terms: List<TermPresentationModel>, currentTermId: Int) {
        findNavController().navigate(
            resId = R.id.action_termsFragment_to_termsListBottomSheetDialogFragment,
            args = bundleOf(
                BUNDLE_KEY_TERMS to terms,
                BUNDLE_KEY_TERM_ID to currentTermId,
                BUNDLE_KEY_TERM_TYPE to TermType.LOCATION_SERVICE_TYPE.ordinal
            )
        )
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it) {
                mergedBinding.flChooseTermByDate -> viewModel.handleIntent(intent = LocationServiceTermIntent.OnClickChooseTerms)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mergedBinding = null
        _binding = null
    }

}