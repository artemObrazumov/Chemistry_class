package com.artemObrazumov.chemistryclass.data.api

import com.artemObrazumov.chemistryclass.data.models.Reagent
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("getReagentsList.php?reversed=1")
    suspend fun getReagents(): Response<List<Reagent>>
    @GET("getReagentInfo.php")
    suspend fun getReagentData(@Query("ID") id: Int): Response<List<Reagent>>
}