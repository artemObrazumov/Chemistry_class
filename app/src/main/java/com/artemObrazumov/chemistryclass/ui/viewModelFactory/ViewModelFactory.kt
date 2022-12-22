package com.artemObrazumov.chemistryclass.ui.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.artemObrazumov.chemistryclass.data.repository.MainRepository
import com.artemObrazumov.chemistryclass.ui.dialogs.reagentDetail.ReagentDetailViewModel
import com.artemObrazumov.chemistryclass.ui.home.HomeViewModel

class ViewModelFactory constructor(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(this.repository) as T
        } else if (modelClass.isAssignableFrom(ReagentDetailViewModel::class.java)) {
            ReagentDetailViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}