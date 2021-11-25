package guerrero.erick.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import guerrero.erick.rickandmorty.adapter.PersonajesAdapter
import guerrero.erick.rickandmorty.model.Personaje
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var rvPersonajes:RecyclerView
    var listaPersonajes:MutableList<Personaje> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvPersonajes = findViewById(R.id.recyclerPersonajes)
        rvPersonajes.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val adaptador = PersonajesAdapter(this,listaPersonajes)
        rvPersonajes.adapter = adaptador


        //https://rickandmortyapi.com/api/character

        val pila = Volley.newRequestQueue(this)
        val url = "https://rickandmortyapi.com/api/character"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null,Response.Listener() { response ->
            val resultados = response.getJSONArray("results")
            val tamRespuesta = resultados.length()
            Log.d("respuesta","el tamaño es ${tamRespuesta}")
                for ( i in 0..tamRespuesta-1){
                    val personajeJson = resultados.getJSONObject(i)
                    val personaje = Personaje(
                        personajeJson.getString("name"),
                        personajeJson.getJSONObject("location").getString("name"),
                        personajeJson.getString("image"))
                    listaPersonajes.add(personaje)
                    Log.d("respuesta","${personaje.nombre}")
                }
            adaptador.notifyDataSetChanged()

        },
        Response.ErrorListener(){

            Log.d("Respuesta", "no funcionó")
        })

        pila.add(jsonObjectRequest)
    }


}