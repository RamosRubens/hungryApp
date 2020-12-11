package br.com.login.view.fragmets

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
import br.com.login.R
import br.com.login.databinding.FragmentCadastroUsuarioBinding
import br.com.login.viewModel.CadasatroUsuarioViewModel

class CadastroUsuarioFragment : Fragment() {

    private lateinit var binding: FragmentCadastroUsuarioBinding

    private val viewModel: CadasatroUsuarioViewModel by lazy {
        ViewModelProviders.of(this@CadastroUsuarioFragment).get(CadasatroUsuarioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroUsuarioBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is AppResult.Success -> {
                    Toast.makeText(this.context,
                        R.string.mensagem_envio_email,
                        Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_cadasatroUsuarioFragment_to_LoginFragment)
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

    fun possuiCadastro(){
        findNavController().navigate(R.id.action_cadasatroUsuarioFragment_to_LoginFragment)
    }

}