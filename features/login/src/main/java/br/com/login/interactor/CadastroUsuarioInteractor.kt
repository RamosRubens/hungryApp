package br.com.login.interactor

import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import br.com.hungryapp.AppResult
import br.com.hungryapp.utils.Utils
import br.com.login.repository.CadastroUsuarioRepository

class CadastroUsuarioInterector(context: Context) {

    private val cadastroUsuarioRepository =
        CadastroUsuarioRepository(
            context
        )

    fun criarUsuario(email: String, password: String, confirmPassword: String, callback: (result: AppResult<Boolean>) -> Unit) {

        if(TextUtils.isEmpty(email)){
            callback(AppResult.Error(Exception("Email deve ser informado")))
            return
        }

        if(TextUtils.isEmpty(password)){
            callback(AppResult.Error(Exception("Senha deve ser informada")))
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            callback(AppResult.Error(Exception("Email Inválido")))
            return
        }

        if(Utils.isNumerosSequenciais(password) || Utils.isNumerosIguais(password)){
            callback(AppResult.Error(Exception("Senha Inválida")))
            return
        }

        if(!password.equals(confirmPassword)){
            callback(AppResult.Error(Exception("Senhas não conferem")))
            return
        }

        cadastroUsuarioRepository.criarNovoUsuairo(email,password,callback)
    }
}
