package ba.etf.rma22.projekat

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel

class UpisIstrazivanjeActivity : AppCompatActivity() {
    private lateinit var izborGodine: Spinner
    private lateinit var izborIstrazivanja: Spinner
    private lateinit var button : Button
    private lateinit var izborGrupe : Spinner
    private var anketaViewModel = AnketaViewModel()
    private var istrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel = GrupaViewModel()


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_istrazivanje)

        button=findViewById(R.id.dodajIstrazivanjeDugme)
        button.isEnabled=false
        izborGodine = findViewById(R.id.odabirGodina)
        izborIstrazivanja = findViewById(R.id.odabirIstrazivanja)
        izborGrupe=findViewById(R.id.odabirGrupa)


        ArrayAdapter.createFromResource(
            this,
            R.array.godine_spinner_opcije,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            izborGodine.adapter = adapter
        }


        izborGodine.setSelection(istrazivanjeViewModel.getGodina())
        izborGodine.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (izborGodine.selectedItem.toString() != "") {
                    izborIstrazivanja.isEnabled = true
                    istrazivanjeViewModel.setGodina(izborGodine.selectedItemPosition)


                opcijeIstrazivanja()
            }
            else
            {
                izborIstrazivanja.isEnabled = false
                izborGrupe.isEnabled=false
            }
        }


        override fun onNothingSelected(parent: AdapterView<*>?) {
            opcijeIstrazivanja()
        }
    }



        izborIstrazivanja.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (izborIstrazivanja.selectedItem.toString() != "") {
                    izborGrupe.isEnabled = true
                    opcijeGrupa()

                }
                else
                {

                    izborGrupe.isEnabled=false
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                opcijeGrupa()
            }
        }


        izborGrupe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (izborGrupe.selectedItem.toString() != "" && izborIstrazivanja.selectedItem.toString() != "" && izborGodine.selectedItem.toString() != "") {
                    button.isEnabled = true

                } else {
                    button.isEnabled = false


                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                button.isEnabled=false
            }
        }


        button.setOnClickListener {
            anketaViewModel.setKorisnikovaAnketa(izborIstrazivanja.selectedItem.toString(),
            izborGrupe.selectedItem.toString(), izborGodine.selectedItem.toString().toInt())
            istrazivanjeViewModel.setKorisnikovoIstrazivanje(izborIstrazivanja.selectedItem.toString(),izborGodine.selectedItem.toString().toInt())

            this.finish()

        }
    }





    fun opcijeIstrazivanja() {
        val opcijeIstrazivanja =
            istrazivanjeViewModel.IstrazivanjaPoGodini(izborGodine.selectedItem.toString().toInt())



        var istrazivanja1 : MutableList<String>
            istrazivanja1= mutableListOf("")
        var ne : Boolean
        for(i in opcijeIstrazivanja){
            ne=false
            for(j in istrazivanjeViewModel.getUpisani()){
                if(i==j) {
                ne=true
                    break
                }
                }
            if(!ne){
                istrazivanja1.add(i.naziv)
            }

            }


        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            istrazivanja1
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        izborIstrazivanja.adapter = adapter

    }

    fun opcijeGrupa(){
        val opcijeGrupe=grupaViewModel.getGroupsByIstrazivanje(izborIstrazivanja.selectedItem.toString())
        var grupe = mutableListOf("")

        for(g in opcijeGrupe){
            grupe.add(g.naziv)
        }

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            grupe
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        izborGrupe.adapter = adapter

    }


    }
