package ba.etf.rma22.projekat.data

import ba.etf.rma22.projekat.data.models.PitanjeAnketa

fun svaPitanjaAnkete() : List<PitanjeAnketa> {
    return listOf(
        PitanjeAnketa("Pitanje1", "Karijera ili ljubav"),
        PitanjeAnketa("Pitanje2", "Karijera ili ljubav"),
        PitanjeAnketa("Pitanje3", "Ljudska psihologija"),
        PitanjeAnketa("Pitanje3", "Tjelesna aktivnost"),
        PitanjeAnketa("Pitanje4", "Tjelesna aktivnost"),
        PitanjeAnketa("Pitanje5", "Tjelesna aktivnost"),

        PitanjeAnketa("Pitanje6", "Tjelesna aktivnost"),
        PitanjeAnketa("Pitanje7", "Tjelesna aktivnost"),
        PitanjeAnketa("Pitanje6", "Sankcije Rusiji"),
        PitanjeAnketa("Pitanje7", "Sankcije Rusiji"),
        PitanjeAnketa("Pitanje1", "Zadovoljstvo zaposlenika2"),
        PitanjeAnketa("Pitanje2", "Zadovoljstvo zaposlenika2"),
        PitanjeAnketa("Pitanje3", "Zadovoljstvo zaposlenika1"),
        PitanjeAnketa("Pitanje4", "Zadovoljstvo zaposlenika1"),
        PitanjeAnketa("Pitanje2", "Zadovoljstvo zaposlenika1")
    )
}