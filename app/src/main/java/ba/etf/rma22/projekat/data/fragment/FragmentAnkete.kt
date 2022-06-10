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
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.MainActivity

import ba.etf.rma22.projekat.R
//import ba.etf.rma22.projekat.data.fragment.FragmentPredaj.Companion.progres
//import ba.etf.rma22.projekat.data.fragment.FragmentPredaj.Companion.progres1
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.view.AnketaAdapter
import ba.etf.rma22.projekat.viewmodel.AnketaViewModel
import ba.etf.rma22.projekat.viewmodel.PitanjeAnketaViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*


class FragmentAnkete : Fragment() {
    private lateinit var ankete: RecyclerView
    private lateinit var odabirAnketa: Spinner
    private lateinit var anketaAdapter: AnketaAdapter
    private var anketaViewModel = AnketaViewModel()
    private var pitanjeViewModel = PitanjeAnketaViewModel()
    private var ankete1 = mutableListOf<Anketa>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view=inflater.inflate(R.layout.fragment_ankete, container, false)

            ankete=view.findViewById(R.id.listaAnketa)
            ankete.layoutManager= GridLayoutManager(view.context, 2, GridLayoutManager.VERTICAL, false)
        //ankete.layoutManager= GridLayoutManager(activity, 2)

        anketaAdapter= AnketaAdapter(arrayListOf())
            ankete.adapter=anketaAdapter
            //anketaAdapter.updateAnkete(anketaViewModel.getSveAnkete(onSuccess = ::onSuccess,
              //  onError = ::onError))
            anketaViewModel.getSveAnkete(onSuccess = ::onSuccess,
                  onError = ::onError)


         /*   anketaAdapter.setOnItemClickListener(object : AnketaAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                     //naziv=getAnkete()[position].naziv
                     //nazivIstrazivanja=getAnkete()[position].nazivIstrazivanja
                    //progres=0
                    //progres1=0.0
                    val idAnkete=ankete1[position].id
                    val pitanja = mutableListOf<Pitanje>()

                    pitanjeViewModel.getPitanja(idAnkete, onSuccess = ::onSuccess1,
                                                                  onError = ::onError )


                    Handler(Looper.getMainLooper()).postDelayed({
                        MainActivity.viewPagerAdapter.add(
                            pitanja.size,
                         //   FragmentPredaj()
                        FragmentAnkete())
                    }, 50)

                }
            })*/
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
            MainActivity.viewPagerAdapter.refreshFragment(1,   FragmentAnkete())}, 50)

    }
    /*private fun showAnketa(anketa: Anketa) {
        if(odabirAnketa.selectedItem.toString() != "Sve ankete" &&
          //  anketaViewModel.getStatus(anketa) != "zuta") {

            // SACUVAO KVIZ RADI PITANJA
            AnketaRepository.pokrenutaAnketa = anketa

            // NAPRAVITI KVIZTAKEN ILI NE AKO VEC POSTOJI TJ OTVORIO JE KVIZ KOJI NIJE ZAVRSIO
            //anketaViewModel.getPoceteAnketeApp(anketa, onSuccess = ::onSuccess, onError = ::onError)
            // SACUVATI TAJ KVIZTAKEN U NEKI REPO

        }
    }

    private fun getAnkete() {
        when {
            odabirAnketa.selectedItem.toString() == "Sve ankete" -> anketaViewModel.getSveAnkete(onSuccess = ::onSuccess,
               onError = ::onError)
            odabirAnketa.selectedItem.toString() == "Sve moje ankete" -> anketaViewModel.getSveMojeAnkete(onSuccess = ::onSuccess,
                onError = ::onError)
            odabirAnketa.selectedItem.toString() == "Urađene ankete" -> anketaViewModel.getSveUradjeneAnkete(onSuccess = ::onSuccess,
                onError = ::onError)
            odabirAnketa.selectedItem.toString() == "Buduće ankete" -> anketaViewModel.getSveBuduceAnkete(onSuccess = ::onSuccess,
                onError = ::onError)
            odabirAnketa.selectedItem.toString() == "Prošle ankete" -> anketaViewModel.getSveNeuradjeneAnkete(onSuccess = ::onSuccess,
                onError = ::onError)
        }
    }
*/
    companion object {
        fun newInstance(): FragmentAnkete = FragmentAnkete()
        var velicina : Int
        init{velicina=0}
        var naziv =""
        var nazivIstrazivanja=""
        var indeks : Int
        init{indeks=-1}

    }

    fun onSuccess1(pitanja:List<Pitanje>){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                //ankete1= pitanja as MutableList<Pitanje>
                var i : Int=0
                for(pitanje in pitanja){

                    if(i<2){
                        Handler(Looper.getMainLooper()).postDelayed({
                            MainActivity.viewPagerAdapter.refreshFragment(
                                0,
                                 FragmentPitanje.newInstance(pitanja[0]))
                                //FragmentAnkete())
                        }, 50)
                        Handler(Looper.getMainLooper()).postDelayed({
                            MainActivity.viewPagerAdapter.refreshFragment(
                                1,
                                FragmentPitanje.newInstance(pitanja[1]))
                                //FragmentAnkete())
                        }, 50)


                    }else {

                        Handler(Looper.getMainLooper()).postDelayed({
                            MainActivity.viewPagerAdapter.add(
                                1,
                                //FragmentAnkete()
                                        FragmentPitanje.newInstance(pitanje)
                            )
                        }, 50)

                    }
                    i += 1
                }
            }
        }
    }

    fun onSuccess(ankete:List<Anketa>){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                ankete1= ankete as MutableList<Anketa>
               anketaAdapter.updateAnkete(ankete)
            }
        }
        }
    fun onError() {
        GlobalScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {

                val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
                toast.show()
            }
        }
            }
    fun onSuccess(rezultat: Boolean, anketa: Anketa){
        GlobalScope.launch(Dispatchers.IO){
            withContext(Dispatchers.Main){
                if(rezultat)
                    pitanjeViewModel.getPitanja(anketa.id, onSuccess = ::onSuccess1, onError = ::onError)
            }
        }
    }

}


