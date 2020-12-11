package br.com.login.interactor

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import br.com.hungryapp.AppResult
import br.com.hungryapp.utils.Utils
import br.com.login.repository.LoginRepository
import br.com.login.repository.network.dto.Usuario

class LoginInteractor(context: Context) {

    private val loginRepository =
        LoginRepository(context)

    fun login(user: String, password: String, callback: (result: AppResult<Usuario>) -> Unit) {

        if(TextUtils.isEmpty(user)){
            callback(AppResult.Error(Exception("Email deve ser informado")))
            return
        }

        if(TextUtils.isEmpty(password)){
            callback(AppResult.Error(Exception("Senha deve ser informada")))
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            callback(AppResult.Error(Exception("Email Inválido")))
            return
        }

        if(Utils.isNumerosSequenciais(password) || Utils.isNumerosIguais(password)){
            callback(AppResult.Error(Exception("Senha Inválida")))
            return
        }

        loginRepository.login(user, password, callback)
    }
}