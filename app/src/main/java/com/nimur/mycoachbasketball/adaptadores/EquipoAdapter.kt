package com.nimur.mycoachbasketball.adaptadores

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nimur.mycoachbasketball.R
import com.nimur.mycoachbasketball.config.Constantes
import com.nimur.mycoachbasketball.databinding.ItemListBinding
import com.nimur.mycoachbasketball.model.entidades.Equipo
import com.nimur.mycoachbasketball.ui.AddTeamActivity
import com.nimur.mycoachbasketball.view.JugadoresActivity


class EquipoAdapter(private val dataSet: List<Equipo>?) :
    RecyclerView.Adapter<EquipoAdapter.ViewHolder>() {



    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item: Equipo? = dataSet?.get(position)
        viewHolder.enlazarItem(item!!)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet!!.size

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ItemListBinding.bind(view)
        var contexto = view.context
        fun enlazarItem(e: Equipo) {
            //todo enlazar elementos
            binding.tvNombreEquipo.text = "${e.nombreEquipo}"
            binding.tvCatergoriaEquipo.text = "${e.categoriaEquipo}"

            binding.root.setOnClickListener {
                val intent = Intent(contexto,JugadoresActivity::class.java)
                contexto.startActivity(intent)
            }

            binding.editIcon.setOnClickListener {
                val intent = Intent(contexto,AddTeamActivity::class.java)
                intent.putExtra(Constantes.OPERACION_KEY, Constantes.OPERACION_EDITAR)
                intent.putExtra(Constantes.ID_EQUIPO_KEY, e.idEquipo)
                contexto.startActivity(intent)
            }


        }

    }

}
