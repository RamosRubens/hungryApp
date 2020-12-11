package br.com.login.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.hungryapp.AppResult
import br.com.login.interactor.CadastroUsuarioInterector

class CadasatroUsuarioViewModel(val app: Application): AndroidViewModel(app) {

    private val interactor = CadastroUsuarioInterector(app.applicationContext)

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()
    val result = MutableLiveData<AppResult<Boolean>>()

    fun criarNovoUsuario() {
        interactor.criarUsuario(email.value!!,password.value!!,confirmPassword.value!!) {
            result.value = it
        }
    }
}