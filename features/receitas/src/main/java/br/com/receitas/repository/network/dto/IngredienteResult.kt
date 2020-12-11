package br.com.receitas.repository.network.dto

data class IngredienteResult(
    var quantidade: Int? = null,
    var nome: String? = null,
    var latitude: String? = null,
    var longitude: String? = null
)