package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {
   suspend fun  zapocniAnketu(idAnkete:Int): AnketaTaken? {
        return withContext(Dispatchers.IO){
            val account= AccountRepository()
            try {
                val rez=ApiAdapter.retrofit.zapocniAnketu(idAnkete, account.getHash())
               return@withContext rez
            }catch(e :Exception){return@withContext null}
        }
    }
    // kreira pokušaj za anketu, vraća kreirani pokušaj ili null u slučaju greške
    suspend fun getPoceteAnkete():List<AnketaTaken>? {
        return withContext(Dispatchers.IO){
            val account= AccountRepository()
            try {
                var pokusaji = ApiAdapter.retrofit.getPoceteAnkete(account.getHash())
                if (pokusaji.isEmpty())
                    return@withContext null
                var i : Int=0
             /*   for(pokusaj in pokusaji){
                    pokusaji[i].anketumId=pokusaj.id
                    i++
                }*/
                return@withContext pokusaji
            }catch(e :Exception){return@withContext null}
        }
    }



}