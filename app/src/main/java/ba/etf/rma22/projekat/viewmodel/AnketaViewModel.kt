package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.Anketa
import ba.etf.rma22.projekat.data.AnketaRepository

class AnketaViewModel {
    fun getSveAnkete(): List<Anketa>{
        return AnketaRepository.getAll().sortedBy{it.datumPocetak}
    }

    fun getSveMojeAnkete(): List<Anketa>{
        return AnketaRepository.getMyAnkete().sortedBy { it.datumPocetak }
    }

    fun getSveBuduceAnkete() : List<Anketa>{
        return AnketaRepository.getFuture().sortedBy { it.datumPocetak }
    }

    fun getSveUradjeneAnkete() : List<Anketa> {
        return AnketaRepository.getDone().sortedBy{it.datumPocetak}
    }

    fun getSveNeuradjeneAnkete() : List<Anketa> {
        return AnketaRepository.getNotTaken().sortedBy { it.datumPocetak }
    }
}