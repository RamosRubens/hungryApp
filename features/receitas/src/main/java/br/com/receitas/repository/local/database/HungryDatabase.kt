package br.com.receitas.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.receitas.repository.local.entity.IngredienteEntity
import br.com.receitas.repository.local.entity.ReceitaEntity

@Database(entities = [ReceitaEntity::class, IngredienteEntity::class], version = 1, exportSchema = false)
abstract class HungryDatabase : RoomDatabase() {
    abstract fun ReceitasDAO(): ReceitasDAO
}