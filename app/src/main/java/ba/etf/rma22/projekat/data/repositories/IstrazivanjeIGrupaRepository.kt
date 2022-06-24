package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.AppDatabase
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")
object IstrazivanjeIGrupaRepository {

    private lateinit var context: Context
    fun setContext(con: Context) {
      context = con
    }
    suspend fun getUpisaneGrupe():List<Grupa> {

        return withContext(Dispatchers.IO) {
           try {
                val account = AccountRepository()
                val db = AppDatabase.getInstance(AccountRepository.getContext())
                val grupe = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.getHash())
                val sveGrupeBaza = db.grupaDao().vratiGrupe()
                val noveVrijednostiBaze = mutableListOf<Grupa>()
                for (grupa in sveGrupeBaza) {
                    for (grupaAPI in grupe) {
                        if (grupa.id == grupaAPI.id)
                            grupa.upisanaGrupa = true
                        noveVrijednostiBaze.add(grupa)
                    }
                }
                db.grupaDao().napraviDb(noveVrijednostiBaze)

                return@withContext grupe
            } catch (e: Exception) {
                val db = AppDatabase.getInstance(AccountRepository.getContext())
                val sveGrupe = db.grupaDao().vratiGrupe()
                var rezultat = mutableListOf<Grupa>()
                for (grupa in sveGrupe) {
                    if (grupa.upisanaGrupa == true) {
                        rezultat.add(grupa)
                    }
                }
                return@withContext rezultat

            }
        }
    }
    //- vraća grupe u kojima je student upisan

    suspend fun getGrupe():List<Grupa>?{
        return withContext(Dispatchers.IO){

            try {
                val db = AppDatabase.getInstance(AccountRepository.getContext())

                val svegrupe = ApiAdapter.retrofit.getGrupe()

              db.grupaDao().napraviDb(svegrupe)
              return@withContext svegrupe
          }
          catch(e : java.net.UnknownHostException){
              val db = AppDatabase.getInstance(AccountRepository.getContext())
              val svegrupe=db.grupaDao().vratiGrupe()
              return@withContext  svegrupe

          }
        }
    }
    //- vraća sve grupe

    suspend fun getIstrazivanjaZaGrupu(idGrupe : Int):List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            val istrazivanja=ApiAdapter.retrofit.getIstrazivanjaZaGrupu(idGrupe)
            return@withContext istrazivanja
        }
    }
   /*suspend fun getGrupeZaIstrazivanje(idIstrazivanja:Int):List<Grupa>{
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
   }*/
    //- vraća grupe na istraživanju sa id-em idIstrazivanja


   suspend fun upisiUGrupu(idGrupa:Int):Boolean {
       val db = AppDatabase.getInstance(AccountRepository.getContext())
       val account=AccountRepository()
       return withContext(Dispatchers.IO){
           val rez1= getUpisaneGrupe()
           for(r in rez1){
               if(r.id==idGrupa) return@withContext true
           }

           val string= ApiAdapter.retrofit.upisiUGrupu(idGrupa, AccountRepository.getHash()).poruka
            if(string.equals("Grupa not found.")) return@withContext false
           else if(string.equals("Ne postoji account gdje je hash=" + AccountRepository.getHash())) return@withContext false
           var grupa= ApiAdapter.retrofit.getGrupaById(idGrupa)
           var grupe=rez1
           var nemaInserta=false
           var sveGrupeSaBaze=db.grupaDao().vratiGrupe()
           for(gr in grupe){
               if(gr.id==grupa.id) nemaInserta=true
           }
            if(nemaInserta==false){
                var i : Int=0;
               for(gr in sveGrupeSaBaze){
                   if(gr.id==grupa.id){
                       sveGrupeSaBaze.get(i).upisanaGrupa=true
                   }
                }
            }
           db.grupaDao().napraviDb(sveGrupeSaBaze)

           return@withContext true
       }
   }
//- upisuje studenta u grupu sa id-em idGrupa i vraća true ili vraća
// false ako nije moguće upisati studenta


    suspend fun getIstrazivanja(offset:Int =0) : List<Istrazivanje>{
        return withContext(Dispatchers.IO){
            val db = AppDatabase.getInstance(AccountRepository.getContext())
            try{

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
            db.istrazivanjeDao().napraviDb(istrazivanja)
            return@withContext istrazivanja
                }catch( e : Exception){
                   var istrazivanja= db.istrazivanjeDao().vratiIzBaze()
                return@withContext istrazivanja

                }
        }
    }
//- vraća sva istraživanja ili ako je zadan offset odgovarajući page u rezultatima

}