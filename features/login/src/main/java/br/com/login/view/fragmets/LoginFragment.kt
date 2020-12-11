package br.com.login.view.fragmets

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import br.com.hungryapp.AppResult
import br.com.hungryapp.BuildConfig
import br.com.login.R
import br.com.login.databinding.FragmentLoginBinding
import br.com.login.viewModel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    val RECEITA_CLASS_NAME = "br.com.receitas.view.activities.ReceitaActivity"

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this@LoginFragment).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is AppResult.Success -> {
                    startActivity(Intent().setClassName(BuildConfig.APPLICATION_ID, RECEITA_CLASS_NAME ))
                    activity?.finish()
                }
                is AppResult.Error -> {
                    if (it.error != null) {
                        Toast.makeText(
                            this.context,
                            it.error!!.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        val genericError = this.context?.getString(R.string.generic_error)
                        Toast.makeText(
                            this.context,
                            genericError,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        return binding.root
    }

    fun recuperarSenha() {
        findNavController().navigate(R.id.action_loginFragment_to_esqueceu_senha_fragment)
    }


    fun cadastrarNovoUsuario(){
        findNavController().navigate(R.id.action_loginFragment_to_cadastro_usuario_fragment)
    }
}