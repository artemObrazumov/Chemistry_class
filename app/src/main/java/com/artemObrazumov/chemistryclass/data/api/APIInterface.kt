package com.artemObrazumov.chemistryclass.data.api

import com.artemObrazumov.chemistryclass.data.models.Reagent
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("getReagentsList.php?reversed=1")
    suspend fun getReagents(): Response<List<Reagent>>
}