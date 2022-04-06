package ba.etf.rma22.projekat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.view.AnketaAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    companion object{
        var godina: Int = 0
    }
    private lateinit var ankete: RecyclerView
    private lateinit var odabirAnketa: Spinner
    private lateinit var anketaAdapter: AnketaAdapter
    private var anketaViewModel = AnketaViewModel()
   // private lateinit var upisDugme: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*upisDugme=findViewById(R.id.upisDugme)
        upisDugme.setOnClickListener {
            val intent = Intent(this, UpisIstrazivanjeActivity::class.java)
            startActivity(intent)
        }*/

        ankete=findViewById(R.id.listaAnketa)
        ankete.layoutManager= GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        anketaAdapter= AnketaAdapter(arrayListOf())
        ankete.adapter=anketaAdapter
        anketaAdapter.updateAnkete(anketaViewModel.getSveAnkete())

        odabirAnketa = findViewById(R.id.filterAnketa)
/*
        val opcijeSpinnera = listOf("Sve ankete", "Sve moje ankete", "Urađene ankete", "Buduće ankete")
        odabirAnketa.adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1, opcijeSpinnera)
*/

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_opcije,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            odabirAnketa.adapter = adapter
        }
        odabirAnketa.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                getAnkete()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                return
            }
        }


    }
    private fun getAnkete(): Unit{
        when {
            odabirAnketa.selectedItem.toString() == "Sve ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveAnkete())
            odabirAnketa.selectedItem.toString() == "Sve moje ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveMojeAnkete())
            odabirAnketa.selectedItem.toString() == "Urađene ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveUradjeneAnkete())
            odabirAnketa.selectedItem.toString() == "Buduće ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveBuduceAnkete())
            odabirAnketa.selectedItem.toString() == "Prošle ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveNeuradjeneAnkete())
        }
    }

    //override fun onResume() {
    //  super.onResume()

    //}
}