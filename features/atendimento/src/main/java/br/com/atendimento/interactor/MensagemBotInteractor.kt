package br.com.atendimento.interactor

import android.content.Context
import android.text.TextUtils
import br.com.atendimento.repository.network.DialogFlowRepository
import br.com.atendimento.repository.network.dto.DialogflowResult
import io.reactivex.Single

class MensagemBotInteractor {

    fun enviarMensagemBot(context: Context, text: String): Single<DialogflowResult> {
        val repoMenssagemBot =
            DialogFlowRepository(
                context
            );

        val textParaBot: String = if(TextUtils.isEmpty(text)){
            "Ola"
        } else {
            text
        }

        return repoMenssagemBot.sentTextMessage(textParaBot, "teste@teste.com", "123456")
    }
}