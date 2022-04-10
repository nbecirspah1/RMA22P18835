package ba.etf.rma22.projekat.viewmodel

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository


class IstrazivanjeViewModel {
    fun IstrazivanjaPoGodini(godina : Int) : List<Istrazivanje>{
        return IstrazivanjeRepository.getIstrazivanjeByGodina(godina)
    }

    fun setKorisnikovoIstrazivanje(nazivIstrazivanja : String, godina : Int){
        IstrazivanjeRepository.setKorisnikovoIstrazivanje(nazivIstrazivanja, godina)

    }

    fun getAll() : List<Istrazivanje>{
        return IstrazivanjeRepository.getAll()
    }

    fun getUpisani() : List<Istrazivanje>{
        return IstrazivanjeRepository.getUpisani()
    }
    companion object{
        var godina: Int = 0
    }

    fun setGodina (god :Int){
        godina=god
    }
    fun getGodina() : Int{
        return godina
    }
}