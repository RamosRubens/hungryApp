package br.com.hungryapp.view.activities.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import br.com.hungryapp.BuildConfig
import br.com.hungryapp.R

class SplashActivity : AppCompatActivity() {

    val LOGIN_CLASS_NAME = "br.com.login.view.activities.LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        login()
    }

    private fun login() {
        Handler().postDelayed({
            startActivity(Intent().setClassName(BuildConfig.APPLICATION_ID, LOGIN_CLASS_NAME ))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)

            finish()
        }, 1500)
    }
}