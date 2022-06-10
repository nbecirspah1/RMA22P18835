package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Grupa
//import ba.etf.rma22.projekat.data.sveGrupe

object GrupaRepository {
    fun getGroupsByIstrazivanje(nazivIstrazivanja:String) : List<Grupa> {
        return emptyList()
        //sveGrupe().filter{it.nazivIstrazivanja==nazivIstrazivanja}
    }
    //- lista grupa za
    //istraživanje

}