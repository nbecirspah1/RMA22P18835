package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.AppDatabase
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("StaticFieldLeak")
object TakeAnketaRepository {

    private lateinit var context: Context
    fun setContext(con: Context) {
       context = con
    }
   suspend fun  zapocniAnketu(idAnkete:Int): AnketaTaken? {
        return withContext(Dispatchers.IO){

            val db = AppDatabase.getInstance(AccountRepository.getContext())
            try {
                val rez=ApiAdapter.retrofit.zapocniAnketu(idAnkete, AccountRepository.getHash())
                var anketa=ApiAdapter.retrofit.getAnketaById(idAnkete)
                //anketa.datumKrajDb= getDateFormat(anketa.datumKraj!!)
                //anketa.datumPocetakDb= getDateFormat(anketa.datumPocetak!!)
                //anketa.datumRadaDb= getDateFormat(anketa.datumRada!!)
                var ankete= mutableListOf<Anketa>()
                ankete.add(anketa)
                db.anketaDao().napraviDb(ankete)
               return@withContext rez
            }catch(e :Exception){return@withContext null}
        }
    }
    // kreira pokušaj za anketu, vraća kreirani pokušaj ili null u slučaju greške
    suspend fun getPoceteAnkete():List<AnketaTaken>? {
        return withContext(Dispatchers.IO) {
           /* val db = AppDatabase.getInstance(AccountRepository.getContext())

            try {
                var pokusaji = ApiAdapter.retrofit.getPoceteAnkete(AccountRepository.getHash())

                db.anketaDao().napraviDb(emptyList())

                if (pokusaji.isEmpty())
                    return@withContext null
                var i: Int = 0
                /*   for(pokusaj in pokusaji){
                    pokusaji[i].anketumId=pokusaj.id
                    i++
                }*/
                var anketeDB = mutableListOf<Anketa>()
                if (!pokusaji.isEmpty()) {
                    for (pokusaj in pokusaji) {
                        var id = pokusaj.AnketumId
                        var anketa = ApiAdapter.retrofit.getAnketaById(id)
                            anketa.upisaneAnkete=true
                        //anketa.datumRadaDb = getDateFormat(anketa.datumRada!!)
                        //anketa.datumKrajDb = getDateFormat(anketa.datumKraj!!)
                        //anketa.datumPocetakDb = getDateFormat(anketa.datumPocetak!!)
                        anketeDB.add(anketa)

                    }
                    db.anketaDao().napraviDb(anketeDB)
                }
                return@withContext pokusaji
            } catch (e: Exception) {
                return@withContext null
       */
            try{
                val rezultat =ApiAdapter.retrofit.getPoceteAnkete(AccountRepository.getHash())
                if(rezultat.isEmpty())
                    return@withContext null
                return@withContext rezultat
            } catch(e : Exception){
                return@withContext  null

        }
        }

    }
        @SuppressLint("SimpleDateFormat")
        private fun getDateFormat(date: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
            return format.format(date)
        }






}