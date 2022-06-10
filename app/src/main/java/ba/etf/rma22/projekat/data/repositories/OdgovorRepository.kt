package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.PovratniPodaci
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object OdgovorRepository {
    suspend fun getOdgovoriAnketa(idAnkete:Int):List<Odgovor> {
        return withContext(Dispatchers.IO) {
            val account = AccountRepository()

                val pokrenuteAnk = ApiAdapter.retrofit.getPoceteAnkete(account.getHash())
                var k : Int =0
            for(pAnk in pokrenuteAnk){
                if(pAnk.AnketumId==idAnkete){
                    k=pAnk.id
                    break
                }
            }

                val rez=ApiAdapter.retrofit.getOdgovoriAnketa(k, account.getHash())
            return@withContext rez

        }
    }
// vraća listu odgovora za anketu, praznu listu ako student nije
// odgovarao ili nije upisan na zadanu anketu

   suspend fun postaviOdgovorAnketa(idAnketaTaken:Int,idPitanje:Int,odgovor:Int):Int  {
        return withContext(Dispatchers.IO){
            val account=AccountRepository()
           // try{
            var idAnkete : Int=0
               val pocete=ApiAdapter.retrofit.getPoceteAnkete(account.getHash())
                var poc : Int=-1
                for(poceta in pocete){
                    if(poceta.id==idAnketaTaken){
                      idAnkete=poceta.AnketumId
                        break
                    }
                }
            val brojOdgovora=ApiAdapter.retrofit.getOdgovoriAnketa(idAnkete, account.getHash())!!.size+1
            val brojPitanja=ApiAdapter.retrofit.getPitanja(idAnkete)!!.size
            val progres=noviProgres(brojOdgovora*1.0F/brojPitanja)
            ApiAdapter.retrofit.postaviOdgovorAnketa( idAnketaTaken, account.getHash(), PovratniPodaci(odgovor, idPitanje, progres))

            return@withContext  progres
           // }catch(e : Exception){return@withContext -1}
        }
   }

   private  fun noviProgres(progres : Float) : Int{
        if(progres>=0.1F && progres<0.3F){
            return 20
        }
        else if(progres>=0.3F && progres<0.5F){
            return 40
        }
        else if(progres>=0.5F && progres<0.7F){
            return 60
        }
        else if(progres>=0.7F && progres<0.9F){
            return 80
        }
        else if(progres>=0.9) return 100
        return 0

    }

    }
//funkcija postavlja odgovor sa indeksom odgovor na pitanje sa id-em idPitanje
// u okviru pokušaja sa id-em idAnketaTaken. Funkcija vraća progres (0-100) na anketi
// nakon odgovora ili -1 ukoliko ima neka greška u zahtjevu

