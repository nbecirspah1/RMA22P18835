package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.AccountRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class GrupaViewModel {
    fun getGroupsByIstrazivanje(istrazivanje : String) : List<Grupa>{
        return GrupaRepository.getGroupsByIstrazivanje(istrazivanje)
    }

    fun promijeniHash(hash: String,
                      onSuccess: () -> Unit,
                      onError: () -> Unit){
        GlobalScope.launch {
            val upisaniAcc = AccountRepository.postaviHash(hash)
            when (upisaniAcc) {
                is Boolean -> onSuccess?.invoke()
                else -> onError?.invoke()
            }
        }
    }
}