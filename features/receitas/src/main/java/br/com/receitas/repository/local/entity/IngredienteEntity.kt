package br.com.receitas.repository.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredientes")
data class IngredienteEntity(
    @PrimaryKey
    val receitaId: Int = 0,
    val quantidade: Int,
    val nome: String,
    val latitude: String,
    val longitude: String
)