package br.com.login.repository.network.dto

data class Usuario(
    var id: String? = null,
    var email: String? = null,
    var password: String? = null,
    var name: String? = null
)