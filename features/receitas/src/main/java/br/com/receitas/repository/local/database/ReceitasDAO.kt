package br.com.receitas.repository.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import br.com.receitas.repository.local.entity.IngredienteEntity
import br.com.receitas.repository.local.entity.ReceitaEntity
import br.com.receitas.repository.local.entity.ReceitaWithIngredientes
import io.reactivex.Single

@Dao
interface ReceitasDAO {

    @Transaction
    @Query("Select * from receitas")
    fun getReceitas(): Single<Array<ReceitaWithIngredientes>>

    @Insert
    fun addReceitas(receita: ReceitaEntity)

    @Insert
    fun addIngredientes(ingrediente: IngredienteEntity)
}