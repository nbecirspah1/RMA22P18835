package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Istrazivanje

fun svaIstrazivanja() : List<Istrazivanje> {
    return listOf(
        Istrazivanje("Ljudska psihologija", 1),
        Istrazivanje("Zdravlje i način života", 2),
        //Istrazivanje("Trenutno stanje u svijetu", 3),
        //Istrazivanje("Studentski život", 5),
        Istrazivanje("Politika", 3),
        Istrazivanje("Interna anketa", 4)
    )
}