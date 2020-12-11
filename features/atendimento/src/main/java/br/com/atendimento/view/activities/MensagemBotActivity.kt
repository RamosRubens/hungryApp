package br.com.atendimento.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.atendimento.R
import br.com.atendimento.viewModel.MensagemBotViewModel
import kotlinx.android.synthetic.main.activity_mensagem_bot.*

class MensagemBotActivity : AppCompatActivity() {

    private val viewModel: MensagemBotViewModel by lazy {
        ViewModelProvider(this).get(MensagemBotViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensagem_bot)

        controlaProgressbar(false);

        tvMensagemBot.text = resources.getString(R.string.inicioConversa)

        observerResultMensagemBot();

        controlaVisibilidadeInicioConversa();

        btnComecarConversaBot.setOnClickListener{
            controlaProgressbar(true);
            viewModel.enviarMensagemBot(this, tvPergunta.text.toString())
        }

        btnFinalizarConversaBot.setOnClickListener{
            controlaProgressbar(true);
            viewModel.enviarMensagemBot(this, "Finalizar")
        }
    }

    fun controlaVisibilidadeInicioConversa() {
        tfPergunta.visibility = View.INVISIBLE;
        tvPergunta.visibility = View.INVISIBLE;
        btnComecarConversaBot.text = resources.getString(R.string.comecarConversa)
        btnFinalizarConversaBot.visibility = View.INVISIBLE;
    }

    fun controlaVisibilidadeMeioConversa() {
        tfPergunta.visibility = View.VISIBLE;
        tvPergunta.visibility = View.VISIBLE;
        btnComecarConversaBot.text = resources.getString(R.string.enviarMensagem)
        btnFinalizarConversaBot.visibility = View.VISIBLE;
    }

    fun controlaProgressbar(opcao: Boolean) {
        if (opcao) {
            llProgressBar.visibility = View.VISIBLE
            btnComecarConversaBot.isClickable = false
            btnFinalizarConversaBot.isClickable = false
            btnComecarConversaBot.background.alpha = 50
            btnFinalizarConversaBot.background.alpha = 50
        } else {
            llProgressBar.visibility = View.INVISIBLE
            btnComecarConversaBot.isClickable = true
            btnFinalizarConversaBot.isClickable = true
            btnComecarConversaBot.background.alpha = 255
            btnFinalizarConversaBot.background.alpha = 255
        }
    }

    fun observerResultMensagemBot() {
        viewModel.resultBot.observe(this, Observer {
            if (it.second) {
                Toast.makeText(this, R.string.dialogFlow_server_error, Toast.LENGTH_LONG).show()
                controlaVisibilidadeInicioConversa();
            } else {
                tvMensagemBot.text = it.first?.message
                if (it.first?.endConversation == false) {
                    controlaVisibilidadeMeioConversa()
                } else {
                    controlaVisibilidadeInicioConversa()
                    tvPergunta.setText("")
                }
            }
            controlaProgressbar(false);
        })
    }

}