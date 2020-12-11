package br.com.receitas.repository.network.dto

data class RecipesRequest(val id: String)

data class RecipesResult(
    var id: Int? = null,
    var nome: String? = null,
    var qntCurtidas: Int? = null,
    var serve: Int?= null,
    var tempoPreparo: Int?=null,
    var dificuldade: Int?=null,
    var ingredienteResults: List<IngredienteResult>? = null,
    var passoAPasso: List<String>? = null,
    var descricao: String? = null)