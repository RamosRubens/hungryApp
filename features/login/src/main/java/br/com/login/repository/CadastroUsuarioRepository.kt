package br.com.login.repository

import android.content.Context
import br.com.hungryapp.AppResult
import com.google.firebase.auth.FirebaseAuth

class CadastroUsuarioRepository(val context: Context)   {

    fun criarNovoUsuairo(email: String, password: String, callback: (result: AppResult<Boolean>) -> Unit) {
        val mAuth = FirebaseAuth.getInstance()

        val task = mAuth.createUserWithEmailAndPassword(email,password)

        task.addOnCompleteListener {result ->
            if(result.isSuccessful){
                callback(AppResult.Success(result.isSuccessful))
            }else{
                callback(AppResult.Error(result.exception))
            }
        }
    }
}