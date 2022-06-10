package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.PitanjeAnketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PitanjeAnketaViewModel {

    fun getPitanja (idAnkete: Int,
        onSuccess: (pitanja: List<Pitanje>) -> Unit,
        onError: () -> Unit
    ) {
        GlobalScope.launch {
            val pitanja = PitanjeAnketaRepository.getPitanja(idAnkete)
            when (pitanja) {
                is List<Pitanje> -> onSuccess?.invoke(pitanja)
                else -> onError?.invoke()
            }
        }
    }

    fun getIndeks(){
        return
    }
}