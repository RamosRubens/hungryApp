package br.com.login.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.hungryapp.AppResult
import br.com.login.interactor.EsqeuceuSenhaInteractor

class EsqueceuSenhaViewModel(val app: Application): AndroidViewModel(app) {

    private val interactor = EsqeuceuSenhaInteractor(app.applicationContext)     

    val email = MutableLiveData<String>()
    val result = MutableLiveData<AppResult<Boolean>>()

    fun recuperarSenha() {
        interactor.recuperaSenha(email.value!!) {
            result.value = it
        }
    }
}