package br.com.receitas.domain

class Receita (
    var id: Int,
    var nome: String,
    var qntCurtidas: Int,
    var serve: Int,
    var tempoPreparo: Int,
    var dificuldade: Int,
//    var ingredienteResults: List<Ingrediente>,
    var descricao: String
)