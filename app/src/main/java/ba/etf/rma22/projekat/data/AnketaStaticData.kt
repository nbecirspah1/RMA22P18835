package ba.etf.rma22.projekat.data

import java.util.*

fun datum(dan : Int, mjesec : Int, godina : Int) : Date {
    return Calendar.getInstance().run{
        set(godina, mjesec-1, dan)
        time
    }
}

fun sveAnkete(): List<Anketa>{
    return listOf(
        Anketa("Karijera ili ljubav", "Ljudska psihologija",
            datum(10, 5, 2022), datum(24, 5,2022),
            null, 14, "Grupa1", 0.33F),

        Anketa("Da li opraštate", "Ljudska psihologija",
            datum(15, 1, 2022), datum(28, 1, 2022),
            datum(13, 1, 2022), 13, "Grupa1", 0.55F),

        Anketa("Tjelesna aktivnost", "Zdravlje i način života",
            datum(17,12,2020), datum(17, 12, 2022),
            null, 15, "Grupa2", 0.65F),

        Anketa("Stavovi o izbjeglicama", "Trenutno stanje u svijetu",
            datum(10, 5, 2021), datum(10,6, 2021),
            datum(6, 6, 2021), 8, "Grupa3", 0.43F),

        Anketa("Protekli ispiti", "Studentski život",
            datum(10, 3, 2022), datum(14, 3, 2022),
            datum(11, 3, 2022), 14, "Grupa4", 0.78F),

        Anketa("Sankcije Rusiji", "Politika",
            datum(10, 3, 2022), datum(14, 4, 2022),
            null, 14, "Grupa5", 0.78F),



        Anketa("Zadovoljstvo zaposlenika", "Interna anketa",
            datum(10, 3, 2022), datum(10, 4, 2023),
            datum(16, 4, 2022), 14, "Grupa6", 0.78F),

        Anketa("Angažman zaposlenika", "Interna anketa",
            datum(10, 3, 2022), datum(11, 3, 2022),
            null, 14, "Grupa6", 0.78F)

    )
}
/*
fun sveMojeAnkete() : List<Anketa>{ return listOf(
    Anketa("Anketa1", "istrazivanje1",
        datum(10, 5, 2001), datum(10, 6,2001),
        datum(16,6, 2001), 14, "DNA", 0.33F),

    Anketa("Anketa2", "istrazivanje2",
        datum(10, 6, 2011), datum(15, 5, 2012),
        datum(16, 6, 2012), 13, "NBA", 0.55F),

    Anketa("Anketa3", "istrazivanje3",
        datum(17,12,2020), datum(11, 1, 2021),
        datum(13,2, 2021), 15, "NNHH", 0.65F),

    )
*/