package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Grupa

fun sveGrupe() : List<Grupa> {
    return listOf(
        Grupa ("Grupa1", "Ljudska psihologija"),
        Grupa ("Grupa2", "Ljudska psihologija"),
        Grupa ("Grupa3", "Zdravlje i način života"),
        Grupa ("Grupa4", "Zdravlje i način života"),
       // Grupa ("Grupa3", "Trenutno stanje u svijetu"),
        //Grupa("Grupa4", "Studentski život"),
        Grupa("Grupa5", "Politika"),
        Grupa("Grupa6", "Politika"),

        Grupa ("Grupa6", "Interna anketa"),
        Grupa ("Grupa7", "Interna anketa")
    )
}