package br.com.receitas.repository.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "receitas")
data class ReceitaEntity(

    @PrimaryKey
    val id: Int = 0,
    val nome: String,
    val qntCurtidas: Int,
    val serve: Int,
    val tempoPreparo: Int,
    val dificuldade: Int,
    val descricao: String
)

data class ReceitaWithIngredientes(
    @Embedded val receitaEntity: ReceitaEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "receitaId"
    )

    val ingredientes: List<IngredienteEntity>
)