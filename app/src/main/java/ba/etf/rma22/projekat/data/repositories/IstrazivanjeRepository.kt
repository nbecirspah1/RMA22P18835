package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Istrazivanje
//import ba.etf.rma22.projekat.data.svaIstrazivanja

object IstrazivanjeRepository {
    private var mojaIstrazivanja = mutableListOf<Istrazivanje>()
    private var i : Int=0;
    init{
       // mojaIstrazivanja.add(0, svaIstrazivanja().get(0))
    }
    fun getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje> {
        return emptyList()//svaIstrazivanja().filter{it.godina==godina}
    } //- lista istraživanja na godini
    fun getAll() : List<Istrazivanje> {
        return emptyList()//svaIstrazivanja()
    }//- lista svih istraživanja

    fun getUpisani() : List<Istrazivanje>{
       // mojaIstrazivanja.add(svaIstrazivanja().get(0))
       /* if(i==0){
            i++;
            mojaIstrazivanja.add(0, svaIstrazivanja().get(0))
        }*/
        return mojaIstrazivanja
    } //- lista istraživanja na kojima je korisnik upisan

   /* fun setKorisnikovoIstrazivanje(nazivIstrazivanja : String, godina : Int){
        mojaIstrazivanja.add(Istrazivanje(nazivIstrazivanja, godina))



    }*/
}