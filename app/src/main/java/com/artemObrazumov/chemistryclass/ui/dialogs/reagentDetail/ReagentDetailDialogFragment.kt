package com.artemObrazumov.chemistryclass.ui.dialogs.reagentDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager.LayoutParams
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.artemObrazumov.chemistryclass.data.models.Reagent
import com.artemObrazumov.chemistryclass.data.repository.MainRepository
import com.artemObrazumov.chemistryclass.databinding.ReagentDetailDialogFragmentBinding
import com.artemObrazumov.chemistryclass.ui.viewModelFactory.ViewModelFactory

class ReagentDetailDialogFragment: DialogFragment() {
    companion object {
        const val TAG = "ReagentDetailDialogFragment"
    }
    private lateinit var binding: ReagentDetailDialogFragmentBinding
    private lateinit var viewModel: ReagentDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReagentDetailDialogFragmentBinding.inflate(inflater)
        dialog!!.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        subscribeToModel()
        return binding.root
    }

    private fun subscribeToModel() {
        viewModel = ViewModelProvider(this,
            ViewModelFactory(MainRepository())
        )[ReagentDetailViewModel::class.java]
        viewModel.reagentData.observe(this) { result ->
            binding.progress.visibility = View.GONE
            if (result.isSuccess) {
                val reagent = result.getOrNull()
                binding.title.visibility = View.VISIBLE
                binding.note.visibility = View.VISIBLE
                displayReagentData(reagent!!)
            } else {
                binding.errorDownloading.visibility = View.VISIBLE
            }
        }
        viewModel.loadReagent(requireArguments().getInt("ID"))
    }

    // TODO: Proper use of string resources
    @SuppressLint("SetTextI18n")
    private fun displayReagentData(reagent: Reagent) {
        binding.title.text = reagent.name
        binding.note.text = "Примечание: ${reagent.note}"
    }

    override fun onResume() {
        super.onResume()
        val params = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params as LayoutParams
    }
}