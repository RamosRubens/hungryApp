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
import br.com.login.databinding.FragmentEsqueceuSenhaBinding
import br.com.login.viewModel.EsqueceuSenhaViewModel

class EsqueceuSenhaFragment : Fragment() {

    private lateinit var binding: FragmentEsqueceuSenhaBinding

    private val viewModel: EsqueceuSenhaViewModel by lazy {
        ViewModelProviders.of(this@EsqueceuSenhaFragment).get(EsqueceuSenhaViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentEsqueceuSenhaBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is AppResult.Success -> {
                    Toast.makeText(this.context,
                        R.string.mensagem_envio_email,
                        Toast.LENGTH_LONG).show()

                    findNavController().navigate(R.id.action_esqueceuFragment_to_LoginFragment)
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

}