package ba.etf.rma22.projekat.data.fragment


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.MainActivity

import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.fragment.FragmentPredaj.Companion.progres
import ba.etf.rma22.projekat.data.fragment.FragmentPredaj.Companion.progres1
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.sveAnkete
import ba.etf.rma22.projekat.view.AnketaAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import java.util.*


class FragmentAnkete : Fragment() {
    private lateinit var ankete: RecyclerView
    private lateinit var odabirAnketa: Spinner
    private lateinit var anketaAdapter: AnketaAdapter
    private var anketaViewModel = AnketaViewModel()
    private var pitanjeViewModel = PitanjeAnketaViewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_ankete, container, false)

            ankete=view.findViewById(R.id.listaAnketa)
            ankete.layoutManager= GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        //ankete.layoutManager= GridLayoutManager(activity, 2)

        anketaAdapter= AnketaAdapter(arrayListOf())
            ankete.adapter=anketaAdapter
            anketaAdapter.updateAnkete(anketaViewModel.getSveAnkete())

            anketaAdapter.setOnItemClickListener(object : AnketaAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                     naziv=anketaViewModel.getSveAnkete()[position].naziv
                     nazivIstrazivanja=anketaViewModel.getSveAnkete()[position].nazivIstrazivanja
                    progres=0
                    progres1=0.0
                    val pitanja = mutableListOf<Pitanje>()
                    var i : Int=0
                    pitanja.addAll(pitanjeViewModel.getPitanja(naziv, nazivIstrazivanja))
                    velicina=pitanja.size
                     for(pitanje in pitanja){

                         if(i<2){
                             Handler(Looper.getMainLooper()).postDelayed({
                                 MainActivity.viewPagerAdapter.refreshFragment(
                                     0,
                                     FragmentPitanje.newInstance(pitanja[0])
                                 )
                             }, 50)
                             Handler(Looper.getMainLooper()).postDelayed({
                                 MainActivity.viewPagerAdapter.refreshFragment(
                                     1,
                                     FragmentPitanje.newInstance(pitanja[1])
                                 )
                             }, 50)


                         }else {

                                    Handler(Looper.getMainLooper()).postDelayed({
                                        MainActivity.viewPagerAdapter.add(
                                            1,
                                            FragmentPitanje.newInstance(pitanje)
                                        )
                                    }, 50)

                         }
                         i += 1
                       }
                    Handler(Looper.getMainLooper()).postDelayed({
                        MainActivity.viewPagerAdapter.add(
                            pitanja.size,
                            FragmentPredaj()
                        )
                    }, 50)

                }
            })
            odabirAnketa = view.findViewById(R.id.filterAnketa)


            ArrayAdapter.createFromResource(
                view.context,
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




            return view
    }

    override fun onResume() {
        super.onResume()

        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.viewPagerAdapter.refreshFragment(1,   FragmentIstrazivanje())}, 50)

    }

    private fun getAnkete() {
        when {
            odabirAnketa.selectedItem.toString() == "Sve ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveAnkete())
            odabirAnketa.selectedItem.toString() == "Sve moje ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveMojeAnkete())
            odabirAnketa.selectedItem.toString() == "Urađene ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveUradjeneAnkete())
            odabirAnketa.selectedItem.toString() == "Buduće ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveBuduceAnkete())
            odabirAnketa.selectedItem.toString() == "Prošle ankete" -> anketaAdapter.updateAnkete(anketaViewModel.getSveNeuradjeneAnkete())
        }
    }

    companion object {
        fun newInstance(): FragmentAnkete = FragmentAnkete()
        var velicina : Int
        init{velicina=0}
        var naziv =""
        var nazivIstrazivanja=""
        var indeks : Int
        init{indeks=-1}

    }
}


