package br.com.atendimento.repository.network.dto

data class DialogflowRequest(val text: String, val email: String, val sessionId: String)

data class DialogflowResult(
    var message: String? = null,
    var name: String? = null,
    var lifespanCount: String? = null,
    var endConversation: Boolean? = null)