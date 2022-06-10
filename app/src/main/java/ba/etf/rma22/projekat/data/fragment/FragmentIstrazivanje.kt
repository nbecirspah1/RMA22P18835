package ba.etf.rma22.projekat.data.fragment

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.MainActivity
import ba.etf.rma22.projekat.R


import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.GrupaViewModel
import ba.etf.rma22.projekat.viewmodel.IstrazivanjeViewModel


class FragmentIstrazivanje : Fragment() {
    private lateinit var izborGodine: Spinner
    private lateinit var izborIstrazivanja: Spinner
    private lateinit var button : Button
    private lateinit var izborGrupe : Spinner
    private var anketaViewModel = AnketaViewModel()
    private var istrazivanjeViewModel = IstrazivanjeViewModel()
    private var grupaViewModel = GrupaViewModel()
   private lateinit var poruka : FragmentPoruka


    private lateinit var view1: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       view1 = inflater.inflate(R.layout.fragment_istrazivanje, container, false)

          //  super.onCreate(savedInstanceState)
           // setContentView(R.layout.activity_upis_istrazivanje)

            button=view1.findViewById(R.id.dodajIstrazivanjeDugme)
            button.isEnabled=false
            izborGodine = view1.findViewById(R.id.odabirGodina)
            izborIstrazivanja = view1.findViewById(R.id.odabirIstrazivanja)
            izborGrupe=view1.findViewById(R.id.odabirGrupa)


            ArrayAdapter.createFromResource(
                view1.context,
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
           //     anketaViewModel.setKorisnikovaAnketa(izborIstrazivanja.selectedItem.toString(),
              //      izborGrupe.selectedItem.toString(), izborGodine.selectedItem.toString().toInt())
               // istrazivanjeViewModel.setKorisnikovoIstrazivanje(izborIstrazivanja.selectedItem.toString(),izborGodine.selectedItem.toString().toInt())

                var s :String
                s="Uspješno ste upisani u grupu "+  izborGrupe.selectedItem.toString()+" istraživanja "+ izborIstrazivanja.selectedItem.toString()+"!"
                Handler(Looper.getMainLooper()).postDelayed({
                    MainActivity.Companion.viewPagerAdapter.refreshFragment(1, FragmentPoruka.Companion.newInstance(s))
                }, 50)

            }
        return view1
        }
    companion object {
        fun newInstance(): FragmentIstrazivanje = FragmentIstrazivanje()
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
            view1.context,
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
          view1.context,
            android.R.layout.simple_spinner_item,
            grupe
        )

        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)

        izborGrupe.adapter = adapter

    }


}
