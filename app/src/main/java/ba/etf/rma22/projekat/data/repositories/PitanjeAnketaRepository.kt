package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.svaPitanja
import ba.etf.rma22.projekat.data.svaPitanjaAnkete
import ba.etf.rma22.projekat.data.sveAnkete

object PitanjeAnketaRepository {
    fun getPitanja(nazivAnkete : String, nazivIstrazivanja : String) : List<Pitanje>{
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
    }
}