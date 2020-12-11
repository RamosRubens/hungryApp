package br.com.receitas.viewModel

import android.app.Application
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.hungryapp.utils.Utils
import br.com.receitas.domain.Receita
import br.com.receitas.interector.ReceitaInterector
import io.reactivex.disposables.Disposable
import java.security.AccessController.getContext

class ReceitaViewModel @ViewModelInject constructor(
    val app: Application,
    val interactor: ReceitaInterector
): AndroidViewModel(app) {

    private var disposable: Disposable? = null

    val resultRecipes = MutableLiveData<List<Receita>>()
    private val mutableSelectedRecipe = MutableLiveData<Receita>()
    val selectedRecipe: LiveData<Receita> get() = mutableSelectedRecipe

    fun selectItem(item: Receita) {
        mutableSelectedRecipe.value = item
    }

    fun listarReceitas(){
        disposable = interactor.listarReceitas()
            .subscribe{ res, error ->
                if(error == null){
                    if (!res.isNullOrEmpty()){
                        resultRecipes.value = res
                    }else{
                        listarReceitaAPI()
                    }
                }else{
                    Log.d("Erro Requisição", error.message!!)
                }
            }
    }

    fun listarReceitaAPI() {
        disposable = interactor.listarReceitasAPI()
            .subscribe { res, error ->
                if(error == null){
                    if (!res.isNullOrEmpty()){
                        resultRecipes.value = res
                    }else{
                        listarReceitas()
                    }
                }else{
                    Log.d("Erro Requisição", error.message!!)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable?.dispose()
    }
}