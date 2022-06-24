package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.AppDatabase
import ba.etf.rma22.projekat.data.fragment.FragmentAnkete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken

//import ba.etf.rma22.projekat.data.sveAnkete
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("StaticFieldLeak")
object AnketaRepository {
    private var mojeAnkete = mutableListOf<Anketa>()
    private var i: Int = 0

    lateinit var pokrenutaAnketa: Anketa
    var radjeniKviz: AnketaTaken? = null

    /* init{
        mojeAnkete.add(0, sveAnkete().get(1))
    }*/
    /*fun setProgres(progres : Float){
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
*/
    /*fun getAll() : List<Anketa>{
        return sveAnkete()
    } // sve ankete u sistemu
*/
    private lateinit var context: Context
    fun setContext(con: Context) {
        context = con
    }

    suspend fun getDone(): List<Anketa>? {
        return withContext(Dispatchers.IO) {
            val account = AccountRepository()
            try {
                val poceteAnkete = ApiAdapter.retrofit.getPoceteAnkete(AccountRepository.getHash())
                val rez = mutableListOf<Anketa>()
                for (anketa in poceteAnkete) {
                    if (anketa.progres.toInt() == 1) {
                        var ank: Anketa
                        ank = ApiAdapter.retrofit.getById(anketa.id)
                        ank.progres = 1.0F
                        ank.datumRada = anketa.datumRada
                        //ank.datumRadaDb= getDateFormat(ank.datumRada!!)

                        rez.add(ank)
                    }
                }
                return@withContext rez
            } catch (e: Exception) {
                return@withContext null
            }

        } // sve urađene ankete
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateFormat(date: Date): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        return format.format(date)
    }

    suspend fun getFuture(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val account = AccountRepository()
            try {
                val ankete = mutableListOf<Anketa>()
                val grupeStudenta = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.getHash())
                for (i in grupeStudenta) {
                    ankete.addAll(ApiAdapter.retrofit.getAnketeZaGrupu(i.id))
                }
                return@withContext ankete.filter {
                    it.datumPocetak.after(
                        Calendar.getInstance().time
                    )
                }
            } catch (e: Exception) {
                return@withContext emptyList()
            }
        }
    }
    // sve buduće ankete


    suspend fun getNotTaken(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            val account = AccountRepository()
            try {
                val poceteAnkete = ApiAdapter.retrofit.getPoceteAnkete(AccountRepository.getHash())
                val rez = mutableListOf<Anketa>()
                for (anketa in poceteAnkete) {
                    if (anketa.progres.toInt() == 0) {
                        var ank: Anketa
                        ank = ApiAdapter.retrofit.getById(anketa.id)
                        ank.progres = 1.0F
                        ank.datumRada = anketa.datumRada
                        //ank.datumRadaDb= getDateFormat(ank.datumRada!!)

                        rez.add(ank)
                    }
                }
                return@withContext rez
            } catch (e: Exception) {
                return@withContext emptyList<Anketa>()
            }

        }
    } // sve prošle ankete koje nisu urađene


    suspend fun getAll(offset: Int = 0): List<Anketa> {
        return withContext(Dispatchers.IO) {
            try {
                val db = AppDatabase.getInstance(AccountRepository.getContext())
                var offset1 = 1
                var konacno = mutableListOf<Anketa>()
                var ankete = mutableListOf<Anketa>()

                if (offset == 0) {
                    while (true) {
                        var ankete1 = ApiAdapter.retrofit.getAll(offset1)
                        ankete.addAll(ankete1)
                        if (ankete1.size < 5) break
                        offset1++
                    }
                } else {
                    ankete.addAll(ApiAdapter.retrofit.getAll(offset))
                }
                //val responseBody = ankete.body()
                var i: Int = 0
                for (a in ankete) {
                    val pom1 = ApiAdapter.retrofit.getGrupeZaAnketu(a.id)
                    val anketaZaUbaciti = a
                    var listaNaziva = mutableListOf<String>()
                    for (a1 in pom1) {
                        val naziv = ApiAdapter.retrofit.getIstrazivanjeId(a1.istrazivanjeId).naziv
                        if (!listaNaziva.contains(naziv)) {
                            if (anketaZaUbaciti.nazivIstrazivanja == null)
                                anketaZaUbaciti.nazivIstrazivanja =
                                    naziv
                            else anketaZaUbaciti.nazivIstrazivanja += "," + naziv
                            listaNaziva.add(naziv)
                        }
                    }
                    konacno.add(anketaZaUbaciti)

                }

                db.anketaDao().napraviDb(konacno)
                return@withContext konacno

            } catch (e: Exception) {
                val db = AppDatabase.getInstance(AccountRepository.getContext())
                val konacno = db.anketaDao().dajSveAnketeDb()
                return@withContext konacno
            }
        }
    }
//vraća listu svih anketa ili ako je zadan offset odgovarajući page
// u rezultatima (npr ako je pozvana metoda bez parametra vraćaju se sve ankete,
// a ako je offset 1 vraća se samo prvih 5)


    suspend fun getById(id: Int): Anketa? {
        return withContext(Dispatchers.IO) {
            try {
                val anketa = ApiAdapter.retrofit.getById(id)
                return@withContext anketa
            } catch (e: Exception) {
                return@withContext null
            }
        }
// vraća jednu anketu koja ima zadani id ili null ako anketa ne postoji
    }
    /*  suspend fun getUpisane():List<Anketa>{
          return withContext(Dispatchers.IO){

              val acc = AccountRepository()
              val db = AppDatabase.getInstance(AccountRepository.getContext())
              var grupe = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.getHash())
              var rezultat = mutableListOf<Anketa>()
              for(grupa in grupe){
                  var pom = ApiAdapter.retrofit.getAnketeZaGrupu(grupa.id)
                  val nazivIstrazivanja = ApiAdapter.retrofit.getIstrazivanjeId(grupa.istrazivanjeId).naziv
                  pom.stream().forEach { x -> x.nazivGrupe = grupa.naziv;
                      if(x.nazivIstrazivanja == null)
                          x.nazivIstrazivanja = nazivIstrazivanja
                      else x.nazivIstrazivanja+= nazivIstrazivanja
                  }
                  rezultat.addAll(pom)
              }
              var izbacuj = mutableListOf<Int>()
              var i = 0
              var j = 0
              while(i < rezultat.size){
                  j = i + 1
                  while(j < rezultat.size){
                      if(rezultat[i].id == rezultat[j].id){
                          rezultat[i].nazivIstrazivanja+= "," + rezultat[j].nazivIstrazivanja
                          rezultat.removeAt(j)
                          izbacuj.add(j)
                      }
                      j++
                  }
                  i++
              }
              for(izbaci in izbacuj)
                  rezultat.removeAt(izbaci)
              val anketaTakenZaDatum = TakeAnketaRepository.getPoceteAnkete()
              if (anketaTakenZaDatum != null) {
                  for(kviz in anketaTakenZaDatum){
                      rezultat.stream().forEach { x ->
                          if(x.id == kviz.AnketumId)
                              x.datumRada = kviz.datumRada
                              //x.datumRadaDb= getDateFormat(x.datumRada!!)
                      }
                  }
              }
              if(rezultat==null) return@withContext emptyList()
            /*  for(rez in rezultat){
                  rez.datumPocetakDb= getDateFormat(rez.datumPocetak!!)
                  rez.datumKrajDb= getDateFormat(rez.datumKraj!!)
              }*/

              var sveAnketeSaBaze= db.anketaDao().dajSveAnketeDb()
              var brojac : Int=0
              for(anketabaza in sveAnketeSaBaze){
                  for(anketaAPI in rezultat){
                      if(anketabaza.id==anketaAPI.id){
                          sveAnketeSaBaze.get(brojac).upisaneAnkete=true
                      }

                  }
                  i++
              }
              db.anketaDao().napraviDb(sveAnketeSaBaze)
              return@withContext rezultat
          }
      }*/


    suspend fun getUpisane(): List<Anketa> {
        return withContext(Dispatchers.IO) {
            try {
                var grupe = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.getHash())
                var rezultat = mutableListOf<Anketa>()
                for (grupa in grupe) {
                    var pom = ApiAdapter.retrofit.getAnketeZaGrupu(grupa.id)
                    val nazivPredmeta =
                        ApiAdapter.retrofit.getIstrazivanjeId(grupa.istrazivanjeId).naziv
                    pom.stream().forEach { x ->
                        x.nazivGrupe = grupa.naziv;
                        if (x.nazivIstrazivanja == null)
                            x.nazivIstrazivanja = nazivPredmeta
                        else x.nazivIstrazivanja += nazivPredmeta
                    }
                    rezultat.addAll(pom)
                }
                var izbacuj = mutableListOf<Int>()
                var i = 0
                var j = 0
                while (i < rezultat.size) {
                    j = i + 1
                    while (j < rezultat.size) {
                        if (rezultat[i].id == rezultat[j].id) {
                            rezultat[i].nazivIstrazivanja += "," + rezultat[j].nazivIstrazivanja
                            rezultat.removeAt(j)
                            izbacuj.add(j)
                        }
                        j++
                    }
                    i++
                }
                for (izbaci in izbacuj)
                    rezultat.removeAt(izbaci)
                val anketaTakenZaDatum = TakeAnketaRepository.getPoceteAnkete()
                if (anketaTakenZaDatum != null) {
                    for (anketa in anketaTakenZaDatum) {
                        rezultat.stream().forEach { x ->
                            if (x.id == anketa.AnketumId)
                                x.datumRada = anketa.datumRada
                        }
                    }
                }
                val db = AppDatabase.getInstance(AccountRepository.getContext())
                val sveAnkete = db.anketaDao().dajSveAnketeDb()
                var index: Int = 0
                for (rez in rezultat) {
                    for (ank in sveAnkete) {
                        if (ank.id == rez.id) {
                            sveAnkete.get(index).upisaneAnkete = true
                        }
                    }
                    index++
                }
                return@withContext rezultat


        } catch (e : Exception)
        {
            val db = AppDatabase.getInstance(AccountRepository.getContext())
            val sveAnkete = db.anketaDao().dajSveAnketeDb()
            var upisane = mutableListOf<Anketa>()
            for (ank in sveAnkete) {
                if (ank.upisaneAnkete == true) upisane.add(ank)
            }
            return@withContext upisane
        }
        }
    }
}
   // vraća listu svih anketa za grupe na kojima je student upisan

