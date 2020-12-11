package br.com.receitas.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.hungryapp.BuildConfig
import br.com.receitas.R
import br.com.receitas.adapters.ReceitaAdpater
import br.com.receitas.databinding.FragmentReceitasBinding
import br.com.receitas.viewModel.ReceitaViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings

@AndroidEntryPoint
@WithFragmentBindings
class ReceitasFragment : Fragment() {

    private lateinit var binding: FragmentReceitasBinding
    private val viewModel: ReceitaViewModel by viewModels()

    val BOT_CLASS_NAME = "br.com.atendimento.view.activities.MensagemBotActivity"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReceitasBinding.inflate(inflater, container, false)
        binding.fragment = this
        binding.lifecycleOwner = this
        setupMenu()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    private fun setupMenu() {
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.page_1 -> {
                    true
                }
                R.id.page_2 -> {
                    findNavController().navigate(R.id.action_receita_fragment_to_detalhe_fragment)
                    true
                }
                R.id.page_3 -> {
                    startActivity(Intent().setClassName(BuildConfig.APPLICATION_ID, BOT_CLASS_NAME))
                    true
                }
            }
        }
    }

    private fun setupRecycleView(){
        val recycleListView = binding.rvReceitas
        recycleListView.layoutManager = LinearLayoutManager(context)

        viewModel.resultRecipes.observe(viewLifecycleOwner, Observer { list ->
            recycleListView.adapter = ReceitaAdpater(list){
                var bundle = bundleOf("receitas" to it.id)
                findNavController().navigate(R.id.action_receita_fragment_to_detalhe_fragment, bundle)
            }
        })

        viewModel.listarReceitas()
    }
}
