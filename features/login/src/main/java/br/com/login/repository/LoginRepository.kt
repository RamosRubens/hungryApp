package br.com.login.repository

import android.content.Context
import br.com.hungryapp.AppResult
import br.com.login.repository.network.dto.Usuario
import com.google.firebase.auth.FirebaseAuth

class LoginRepository(val context: Context) {

    fun login(user: String, password: String, callback: (result: AppResult<Usuario>) -> Unit) {
        val mAuth = FirebaseAuth.getInstance()

        val task = mAuth.signInWithEmailAndPassword(user,password)

        task.addOnCompleteListener {result ->
            if(result.isSuccessful){
                val firebaseUser = task.result?.user
                val usuario = Usuario(
                    id = firebaseUser?.uid,
                    name = firebaseUser?.displayName,
                    email = firebaseUser?.email
                )
                callback(AppResult.Success(usuario))
            }else{
                callback(AppResult.Error(task.exception))
            }
        }
    }
}