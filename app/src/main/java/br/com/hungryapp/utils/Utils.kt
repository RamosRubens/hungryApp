package br.com.hungryapp.utils

import android.content.Context
import android.widget.Toast
import java.security.AccessControlContext

class Utils {
    companion object{
        fun isNumerosSequenciais(senha: String): Boolean {

            var referencia = "0123456789"

            if(referencia.contains(senha) || referencia.reversed().contains(senha)){
                return true
            }

            return false
        }

        fun isNumerosIguais(senha: String): Boolean {
            return senha.all {
                it == senha.get(0)
            }
        }

        fun apresentaMessagem(context: Context, msg: String?){
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }
    }
}