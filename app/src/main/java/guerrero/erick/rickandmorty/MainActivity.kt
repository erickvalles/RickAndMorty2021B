package guerrero.erick.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //https://rickandmortyapi.com/api/character

        val pila = Volley.newRequestQueue(this)
        val url = "http://www.google.com"
        val stringRequest = StringRequest(Request.Method.GET,url,Response.Listener { response ->
            Log.d("Respuesta",response)
        },
        Response.ErrorListener {
            Log.d("Respuesta", "no funcionó")
        })

        pila.add(stringRequest)
    }
}