package br.com.login.repository

import android.content.Context
import br.com.hungryapp.AppResult
import com.google.firebase.auth.FirebaseAuth

class EsqueceuSenhaRepository (val context: Context)  {

    fun recuperarSenha(user: String, callback: (result: AppResult<Boolean>) -> Unit) {
        val mAuth = FirebaseAuth.getInstance()

        val task = mAuth.sendPasswordResetEmail(user)

        task.addOnCompleteListener {result ->
            if(result.isSuccessful){
                callback(AppResult.Success(result.isSuccessful))
            }else{
                callback(AppResult.Error(result.exception))
            }
        }
    }
}