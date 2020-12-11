package br.com.receitas.repository.network.service

import br.com.receitas.domain.Receita
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ReceitaService {
    @GET("recipes")
    @Headers("Content-Type: application/json")
    fun getRecipes(): Single<List<Receita>>
}