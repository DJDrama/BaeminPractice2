package com.dj.baeminpractice2.presentation.ui

import android.Manifest
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import com.dj.baeminpractice2.R
import com.dj.baeminpractice2.databinding.DialogFragmentCheckPermissionsBinding
import com.dj.baeminpractice2.presentation.utils.REQUEST_KEY_PERMISSION_CHECK_READ_EXTERNAL_STORAGE

class CheckPermissionsDialogFragment : DialogFragment(R.layout.dialog_fragment_check_permissions) {
    private val neededPermission = Manifest.permission.READ_EXTERNAL_STORAGE
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            setFragmentResultAndPopBackStack()
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DialogFragmentCheckPermissionsBinding.bind(view)
        initViews(binding = binding)
    }

    private fun initViews(binding: DialogFragmentCheckPermissionsBinding) {
        binding.btnConfirm.setOnClickListener {
            requestPermissionLauncher.launch(neededPermission)
        }
    }

    private fun setFragmentResultAndPopBackStack() {
        findNavController().popBackStack()
        setFragmentResult(REQUEST_KEY_PERMISSION_CHECK_READ_EXTERNAL_STORAGE, bundleOf())
    }

}