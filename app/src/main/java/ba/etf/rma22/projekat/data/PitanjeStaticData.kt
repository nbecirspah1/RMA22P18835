package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.Pitanje

fun svaPitanja() : List<Pitanje> {
    return listOf(
        Pitanje("Pitanje1", "TesktPitanja1",
                    listOf("a", "b", "c")),
        Pitanje("Pitanje2", "TesktPitanja2",
            listOf("a", "b", "c", "d")),

        Pitanje("Pitanje3", "TesktPitanja3",
            listOf("a", "b")),
        Pitanje("Pitanje4", "TesktPitanja4",
            listOf("a", "b", "c", "d")),
        Pitanje("Pitanje5", "TesktPitanja5",
            listOf("a", "b", "c", "d", "e")),
        Pitanje("Pitanje6", "TesktPitanja6",
            listOf("a", "b", "c", "d", "e")),
        Pitanje("Pitanje7", "TesktPitanja7",
            listOf("a", "b", "c", "d", "e"))
    )

}