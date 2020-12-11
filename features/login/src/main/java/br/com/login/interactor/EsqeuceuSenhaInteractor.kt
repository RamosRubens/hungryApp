package br.com.login.interactor

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import br.com.hungryapp.AppResult
import br.com.login.repository.EsqueceuSenhaRepository

class EsqeuceuSenhaInteractor (context: Context) {

    private val esqueceuSenhaRepository = EsqueceuSenhaRepository(context)

    fun recuperaSenha(user: String, callback: (result: AppResult<Boolean>) -> Unit) {

        if(TextUtils.isEmpty(user)){
            callback(AppResult.Error(Exception("Email deve ser informado")))
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            callback(AppResult.Error(Exception("Email Inválido")))
            return
        }

        esqueceuSenhaRepository.recuperarSenha(user, callback)
    }

}