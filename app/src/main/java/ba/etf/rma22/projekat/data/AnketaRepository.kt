package ba.etf.rma22.projekat.data

import java.util.*

object AnketaRepository {

    fun getMyAnkete() : List<Anketa>{
        return sveAnkete().filter{it.nazivGrupe=="Grupa1"}

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

}