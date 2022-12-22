package com.artemObrazumov.chemistryclass.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.artemObrazumov.chemistryclass.data.models.Reagent
import com.artemObrazumov.chemistryclass.data.repository.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MainRepository
) : ViewModel() {
    val reagents: MutableLiveData<Result<List<Reagent>>> = MutableLiveData()

    fun getReagents() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getReagents()
            if (response.isSuccessful) {
                reagents.postValue( Result.success(response.body()!!) )
            } else {
                reagents.postValue( Result.failure(Throwable("Error loading reagents")) )
            }
        }
    }
}