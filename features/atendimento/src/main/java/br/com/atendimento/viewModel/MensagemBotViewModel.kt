package br.com.atendimento.viewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import br.com.atendimento.interactor.MensagemBotInteractor
import br.com.atendimento.repository.network.dto.DialogflowResult
import io.reactivex.disposables.Disposable

class MensagemBotViewModel(val app: Application): AndroidViewModel(app) {
    private val mensagemBotInteractor = MensagemBotInteractor();
    private var disposable: Disposable? = null
    val resultBot = MutableLiveData<Pair<DialogflowResult?, Boolean>>();

    fun enviarMensagemBot(context: Context, text: String) {
        disposable = mensagemBotInteractor.enviarMensagemBot(context, text)
            .subscribe { res, error ->
                if (error != null) {
                    resultBot.value = Pair(null, true)
                }
                resultBot.value = Pair(res, false)
            }
    }
}