package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.TakeAnketaRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AnketaViewModel {
    private lateinit var korisnikovaAnketa: Anketa;


    fun getSveAnkete(offset:Int=0,
        onSuccess: (ankete: List<Anketa>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch {
            val ankete = AnketaRepository.getAll()
            when (ankete) {
                is List<Anketa> -> onSuccess?.invoke(ankete)
                else -> onError?.invoke()
            }
        }
    }

    /*fun getStatus(anketa : Anketa): String{
        return AnketaRepository.dajStatus(anketa)
    }*/

    fun getSveMojeAnkete(
        onSuccess: (ankete: List<Anketa>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch {
            val ankete = AnketaRepository.getUpisane().sortedBy { it.datumPocetak }
            when (ankete) {
                is List<Anketa> -> onSuccess?.invoke(ankete)
                else -> onError?.invoke()
            }
        }
    }


    fun getSveBuduceAnkete(
        onSuccess: (ankete: List<Anketa>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch {
            val ankete = AnketaRepository.getFuture().sortedBy { it.datumPocetak }
            when (ankete) {
                is List<Anketa> -> onSuccess?.invoke(ankete)
                else -> onError?.invoke()
            }
        }
    }


    fun getSveUradjeneAnkete(
        onSuccess: (ankete: List<Anketa>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch {
            val ankete = AnketaRepository.getDone()?.sortedBy { it.datumPocetak }
            when (ankete) {
                is List<Anketa> -> onSuccess?.invoke(ankete)
                else -> onError?.invoke()
            }
        }
    }

    fun getSveNeuradjeneAnkete(
        onSuccess: (ankete: List<Anketa>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch {
            val ankete = AnketaRepository.getNotTaken().sortedBy { it.datumPocetak }
            when (ankete) {
                is List<Anketa> -> onSuccess?.invoke(ankete)
                else -> onError?.invoke()
            }
        }
    }

    fun getPoceteAnkete(onSuccess: (quizzes: List<AnketaTaken>) -> Unit,
                         onError: () -> Unit){
        GlobalScope.launch{
            val ankete = TakeAnketaRepository.getPoceteAnkete()
            when(ankete){
                is List<AnketaTaken> -> onSuccess?.invoke(ankete)
                else -> onError?.invoke()
            }
        }
    }

/*    fun getPoceteAnketeApp(anketa: Anketa, onSuccess: (rezultat: Boolean, anketa: Anketa) -> Unit,
                            onError: () -> Unit){
        GlobalScope.launch{
            val rezultat = TakeAnketaRepository.getPoceteAnketeApp()
            when(rezultat){
                is Boolean-> onSuccess?.invoke(rezultat, anketa)
                else -> onError?.invoke()
            }
        }
    }*/

}
