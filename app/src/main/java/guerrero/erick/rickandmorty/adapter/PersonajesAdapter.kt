package guerrero.erick.rickandmorty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import guerrero.erick.rickandmorty.R
import guerrero.erick.rickandmorty.model.Personaje

class PersonajesAdapter(ctx:Context,listaPersonajes:List<Personaje>): RecyclerView.Adapter<PersonajesAdapter.PersonajesVH>() {
    lateinit var ctx:Context
    lateinit var personajesList:List<Personaje>

init {
    this.ctx = ctx
    this.personajesList = listaPersonajes
}



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonajesVH {
        val vistaPersonaje = LayoutInflater.from(ctx).inflate(R.layout.card_personaje,parent,false)
        val viewHolderPersonaje = PersonajesVH(vistaPersonaje)
        vistaPersonaje.tag = viewHolderPersonaje
        return viewHolderPersonaje
    }

    override fun onBindViewHolder(holder: PersonajesVH, position: Int) {
        holder.tvNombre.text = personajesList[position].nombre
        holder.tvLocacion.text = personajesList[position].ultimaLocacion
        Picasso.get()
            .load(personajesList[position].imagen)
            .into(holder.iVFoto);
    }

    override fun getItemCount(): Int {
        return this.personajesList!!.size
    }

    class PersonajesVH(vistaIndividual: View):RecyclerView.ViewHolder(vistaIndividual){
        lateinit var iVFoto:ImageView
        lateinit var tvNombre:TextView
        lateinit var tvLocacion:TextView

        init {
            iVFoto = vistaIndividual.findViewById(R.id.ivImagen)
            tvNombre = vistaIndividual.findViewById(R.id.tvNombre)
            tvLocacion = vistaIndividual.findViewById(R.id.tvLocacion)
        }
    }
}