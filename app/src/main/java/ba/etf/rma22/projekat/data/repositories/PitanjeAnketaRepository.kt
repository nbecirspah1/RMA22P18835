package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.AppDatabase
import ba.etf.rma22.projekat.data.models.Pitanje


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object PitanjeAnketaRepository {
  /*  fun getPitanja(nazivAnkete : String, nazivIstrazivanja : String) : List<Pitanje>{
        sveAnkete().filter{nazivAnkete==it.naziv && nazivIstrazivanja==it.nazivIstrazivanja}
        svaPitanjaAnkete().filter{it.anketa==nazivAnkete}
        var pitanjaAnkete = mutableListOf<PitanjeAnketa>()
        pitanjaAnkete.addAll(svaPitanjaAnkete().filter{it.anketa==nazivAnkete})
       var pitanja = mutableListOf<Pitanje>()
        var m : PitanjeAnketa
        for( m in pitanjaAnkete){
           pitanja.addAll(svaPitanja().filter{m.naziv==it.naziv})
        }
        return pitanja
    }*/
  private lateinit var context: Context
    fun setContext(con: Context) {
        context = con
    }
    suspend fun getPitanja(idAnkete : Int): List<Pitanje>{
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(AccountRepository.getContext())
            val rez=ApiAdapter.retrofit.getPitanja(idAnkete)
            var rez1= mutableListOf<Pitanje>()
               for(pitanje in rez) {
                   var result = ""
                   pitanje.opcije.forEach { result = result.plus(it).plus(" ") }
                   pitanje.opcijeDB=result
                   rez1.add(pitanje)
               }



            db.pitanjeDao().insert(rez1)
            return@withContext rez1
        }
    }


}