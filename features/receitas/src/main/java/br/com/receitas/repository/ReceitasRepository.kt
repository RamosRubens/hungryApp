package br.com.receitas.repository

import br.com.receitas.domain.Ingrediente
import br.com.receitas.domain.Receita
import br.com.receitas.repository.local.database.ReceitasDAO
import br.com.receitas.repository.local.entity.IngredienteEntity
import br.com.receitas.repository.local.entity.ReceitaEntity
import br.com.receitas.repository.network.service.ReceitaService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReceitasRepository @Inject constructor(
    private val retrofitService: ReceitaService,
    private val dao: ReceitasDAO
) {

    fun getRecipes(): Single<List<Receita>> {
        return getLocalRecipes()
    }

    private fun getLocalRecipes(): Single<List<Receita>> {
        return dao.getReceitas()
            .map { entidades ->
                val arr = mutableListOf<Receita>()
                entidades.map { entity ->
                    val ingredientes = mutableListOf<Ingrediente>()
                    entity.ingredientes.forEach { item ->
                        ingredientes.add(
                            Ingrediente(
                                entity.receitaEntity.id,
                                item.nome,
                                item.latitude,
                                item.longitude
                            )
                        )
                    }
                    arr.add(
                        Receita(
                            entity.receitaEntity.id,
                            entity.receitaEntity.nome,
                            entity.receitaEntity.qntCurtidas,
                            entity.receitaEntity.dificuldade,
                            entity.receitaEntity.serve,
                            entity.receitaEntity.tempoPreparo,
//                            ingredientes,
                            entity.receitaEntity.descricao
                        )
                    )
                }
                arr.toList()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getRecipesFromAPI(): Single<List<Receita>> {
        return retrofitService.getRecipes()
            .map {
                val arr = mutableListOf<Receita>()
                it.forEach {dto ->
                    val receita =
                        ReceitaEntity(
                            dto.id,
                            dto.nome,
                            dto.qntCurtidas,
                            dto.serve,
                            dto.tempoPreparo,
                            dto.dificuldade,
                            dto.descricao
                        )
                    dao.addReceitas(receita)

//                    val ingredientes = mutableListOf<IngredienteEntity>()
//                    dto.ingredienteResults.forEach { item ->
//                        val ingrediente =
//                            IngredienteEntity(
//                                dto.id,
//                                item.quantidade,
//                                item.nome,
//                                item.latitude,
//                                item.longitude
//                            )
//                        dao.addIngredientes(ingrediente)
//                    }

                    arr.add(
                        Receita(
                            dto.id,
                            dto.nome,
                            dto.qntCurtidas,
                            dto.serve,
                            dto.dificuldade,
                            dto.tempoPreparo,
//                            dto.ingredienteResults,
                            dto.descricao
                        )
                    )
                }
                arr.toList()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}