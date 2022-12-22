package com.artemObrazumov.chemistryclass.ui.dialogs.reagentDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemObrazumov.chemistryclass.data.models.Reagent
import com.artemObrazumov.chemistryclass.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ReagentDetailViewModel(
    private val repository: MainRepository
): ViewModel() {
    val reagentData: MutableLiveData<Result<Reagent>> = MutableLiveData()

    fun loadReagent(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getReagentData(id)
            if (response.isSuccessful) {
                val reagent = response.body()!!
                reagentData.postValue( Result.success(reagent.first()) )
            } else {
                reagentData.postValue( Result.failure(Throwable("Error loading reagent data")) )
            }
        }
    }
}