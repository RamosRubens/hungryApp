package br.com.receitas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.receitas.R
import br.com.receitas.databinding.ItemReceitaBinding
import br.com.receitas.domain.Receita

class ReceitaAdpater(
    private val receitas: List<Receita>,
    private val onItemClick: ((Receita) -> Unit)
):RecyclerView.Adapter<ReceitaAdpater.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_receita, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = holder.binding
        binding?.receita = receitas[position]
        binding?.executePendingBindings()
    }

    override fun getItemCount(): Int = receitas.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemReceitaBinding? = ItemReceitaBinding.bind(view)

        init {
            view.setOnClickListener {
                onItemClick.invoke(receitas[adapterPosition])
            }
        }
    }
}