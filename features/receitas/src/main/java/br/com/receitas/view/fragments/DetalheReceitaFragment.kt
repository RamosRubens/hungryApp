package br.com.receitas.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.hungryapp.BuildConfig
import br.com.receitas.R
import br.com.receitas.databinding.FragmentDetalheReceitaBinding
import br.com.receitas.viewModel.ReceitaViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class DetalheReceitaFragment : Fragment() {

    private lateinit var binding: FragmentDetalheReceitaBinding
    private val viewModel: ReceitaViewModel by viewModels()

    val BOT_CLASS_NAME = "br.com.atendimento.view.activities.MensagemBotActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetalheReceitaBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        setupMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listarReceitas()

        var id = arguments?.getInt("receitas")
        viewModel.resultRecipes.observe(viewLifecycleOwner, Observer {
           for (item in it){
                when(item.id){
                    id -> binding.receita = item
                }
            }
        })

        binding.encontrarIngrediente.setOnClickListener {
            findNavController().navigate(R.id.action_detalhe_fragment_to_mapa_activity)
        }
    }

    fun close(view: View){
        findNavController().navigateUp()
    }

    private fun setupMenu() {
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.page_1 -> {
                    findNavController().navigate(R.id.action_detalhe_fragment_to_receita_fragment)
                    true
                }
                R.id.page_2 -> {
                    true
                }
                R.id.page_3 -> {
                    startActivity(Intent().setClassName(BuildConfig.APPLICATION_ID, BOT_CLASS_NAME))
                    true
                }
            }
        }
    }

}