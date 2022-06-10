package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.Api
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {
    suspend fun getUpisaneGrupe():List<Grupa>{
        val account = AccountRepository()
        return withContext(Dispatchers.IO){
            val grupe = ApiAdapter.retrofit.getUpisaneGrupe(account.getHash())
            return@withContext grupe
        }
    }
    //- vraća grupe u kojima je student upisan

    suspend fun getGrupe():List<Grupa>{
        return withContext(Dispatchers.IO){
            val svegrupe=ApiAdapter.retrofit.getGrupe()
            for(grupa in svegrupe){

            }
            return@withContext svegrupe
        }
    }
    //- vraća sve grupe

    suspend fun getIstrazivanjaZaGrupu(idGrupe : Int):List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            val istrazivanja=ApiAdapter.retrofit.getIstrazivanjaZaGrupu(idGrupe)
            return@withContext istrazivanja
        }
    }
   suspend fun getGrupeZaIstrazivanje(idIstrazivanja:Int):List<Grupa>{
       return withContext(Dispatchers.IO){
           val svegrupe= getGrupe()
           val rezultat= mutableListOf<Grupa>()
           for(grupa in svegrupe){
               val istrazivanja= getIstrazivanjaZaGrupu(grupa.id)
               for(istr in istrazivanja){
                   if(istr.id==idIstrazivanja) rezultat.add(grupa)
               }
           }
           return@withContext rezultat
       }
   }
    //- vraća grupe na istraživanju sa id-em idIstrazivanja


   suspend fun upisiUGrupu(idGrupa:Int):Boolean {
       val account=AccountRepository()
       return withContext(Dispatchers.IO){
           val rez1= getUpisaneGrupe()
           for(r in rez1){
               if(r.id==idGrupa) return@withContext true
           }

           val string= ApiAdapter.retrofit.upisiUGrupu(idGrupa, account.getHash()).poruka
            if(string.equals("Grupa not found.")) return@withContext false
           else if(string.equals("Ne postoji account gdje je hash=" + account.getHash())) return@withContext false
          return@withContext true
       }
   }
//- upisuje studenta u grupu sa id-em idGrupa i vraća true ili vraća
// false ako nije moguće upisati studenta


    suspend fun getIstrazivanja(offset:Int =0) : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
             var istrazivanja= mutableListOf<Istrazivanje>()
            if(offset==0){
                var i : Int=0

                while(true){
                    i++
                    val istr=ApiAdapter.retrofit.getIstrazivanja(i)

                     istrazivanja.addAll(istr)
                    if(istr.size<5) break


                }

            }
            else{
                istrazivanja.addAll(ApiAdapter.retrofit.getIstrazivanja(offset))
            }
            return@withContext istrazivanja
        }
    }
//- vraća sva istraživanja ili ako je zadan offset odgovarajući page u rezultatima

}