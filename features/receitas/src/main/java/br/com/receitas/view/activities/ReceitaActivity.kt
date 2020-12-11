package br.com.receitas.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.receitas.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReceitaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receita)
    }
}