package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.fragment.FragmentAnkete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository.getIstrazivanjeByGodina
import ba.etf.rma22.projekat.data.sveAnkete
import java.util.*

object AnketaRepository {
        private  var mojeAnkete = mutableListOf<Anketa>()
        private var i :Int=0
   /* init{
        mojeAnkete.add(0, sveAnkete().get(1))
    }*/
    fun setProgres(progres : Float){
        getTrenutnaAnketa1().progres=progres
    }
   fun setUradjeneAnkete(anketa : Anketa){
       getDone().toMutableList().add(anketa)
   }
    fun getTrenutnaAnketa1() : Anketa{
        return getAll().filter{it.naziv== FragmentAnkete.naziv && it.nazivIstrazivanja== FragmentAnkete.nazivIstrazivanja }[0]
    }
    fun getMyAnkete() : List<Anketa>{
      if(i==0){
           i++;
           mojeAnkete.add(0, sveAnkete().get(1))
       }

        return mojeAnkete

    } //  sve ankete za istraživanja i grupe gdje je korisnik upisan

    fun getAll() : List<Anketa>{
        return sveAnkete()
    } // sve ankete u sistemu

    fun getDone() : List<Anketa>{
        return getMyAnkete().filter{it.datumRada != null }
    } // sve urađene ankete


    fun getFuture() : List<Anketa>{
        return getMyAnkete().filter{it.datumPocetak.after(
            Calendar.getInstance().time)}
    } // sve buduće ankete


    fun getNotTaken() : List<Anketa>{
        return getMyAnkete().filter{it.datumKraj.before(Calendar.getInstance().time)
                && it.datumRada==null}
    } // sve prošle ankete koje nisu urađene


    fun setKorisnikovaAnketa(nazivIstrazivanja : String,grupa :  String, godina : Int ){
      //  getIstrazivanjeByGodina(godina).filter{it.naziv==nazivIstrazivanja}.get(0).naziv

        var filtriranaAnketa : List<Anketa>;
        filtriranaAnketa=getAll().filter{it.nazivIstrazivanja==nazivIstrazivanja
                                            && it.nazivGrupe==grupa}
        for(i in filtriranaAnketa)
        mojeAnkete.add(i)
    }
}