package br.com.receitas.interector

import br.com.receitas.domain.Receita
import br.com.receitas.repository.ReceitasRepository
import io.reactivex.Single
import javax.inject.Inject


class ReceitaInterector @Inject constructor(
    private val repo: ReceitasRepository
){
    fun listarReceitas(): Single<List<Receita>> {
        return repo.getRecipes()
    }

    fun listarReceitasAPI(): Single<List<Receita>> {
        return repo.getRecipesFromAPI()
    }
}