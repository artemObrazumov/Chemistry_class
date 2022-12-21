package com.artemObrazumov.chemistryclass.data.repository

import com.artemObrazumov.chemistryclass.App

class MainRepository {
    suspend fun getReagents() = App.apiService.getReagents()
}