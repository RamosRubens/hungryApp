package br.com.login.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.hungryapp.AppResult
import br.com.login.interactor.LoginInteractor
import br.com.login.repository.network.dto.Usuario

class LoginViewModel(val app: Application) : AndroidViewModel(app) {
    private val interactor = LoginInteractor(app.applicationContext)

    val password = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val result = MutableLiveData<AppResult<Usuario>>()

    fun login() {
        interactor.login(email.value!!, password.value!!) {
            result.value = it
        }
    }
}