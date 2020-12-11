package br.com.atendimento.repository.network

import android.content.Context
import br.com.atendimento.repository.network.dto.DialogflowRequest
import br.com.atendimento.repository.network.dto.DialogflowResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

class DialogFlowRepository(context: Context) : DialogFlowRetrofit(context)  {
    private val service = retrofit.create(DialogflowService::class.java)

    interface DialogflowService {
        @POST("message/text/send")
        @Headers("Content-Type: application/json")
        fun sendTextMessage(@Body request: DialogflowRequest): Single<DialogflowResult>
    }

    fun sentTextMessage(text: String, email: String, sessionId: String): Single<DialogflowResult> {
        val request = DialogflowRequest(
            text,
            email,
            sessionId
        )
        return service.sendTextMessage(request)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}