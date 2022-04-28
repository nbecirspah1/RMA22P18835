package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.fragment.FragmentAnkete.Companion.naziv
import ba.etf.rma22.projekat.data.fragment.FragmentAnkete.Companion.nazivIstrazivanja
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository

class AnketaViewModel {
    private lateinit var korisnikovaAnketa : Anketa;

    fun setKorisnikovaAnketa(nazivIstrazivanja : String,grupa :  String, godina : Int ){
       AnketaRepository.setKorisnikovaAnketa(nazivIstrazivanja, grupa, godina)
    }
    fun getTrenutnaAnketa() : Anketa{
       return AnketaRepository.getTrenutnaAnketa1()
    }
    fun getSveAnkete(): List<Anketa>{
        return AnketaRepository.getAll().sortedBy{it.datumPocetak}
    }

    fun getSveMojeAnkete(): List<Anketa>{
        return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak }
    }

    fun getSveBuduceAnkete() : List<Anketa>{
        return AnketaRepository.getFuture().sortedBy { it.datumPocetak }
    }

    fun getSveUradjeneAnkete() : List<Anketa> {
        return AnketaRepository.getDone().sortedBy{it.datumPocetak}
    }
    fun setUradjeneAnkete(anketa : Anketa){
        AnketaRepository.setUradjeneAnkete(anketa)
    }
    fun getSveNeuradjeneAnkete() : List<Anketa> {
        return AnketaRepository.getNotTaken().sortedBy { it.datumPocetak }
    }
    fun setProgress(progres : Float){
        AnketaRepository.setProgres(progres)
    }
}