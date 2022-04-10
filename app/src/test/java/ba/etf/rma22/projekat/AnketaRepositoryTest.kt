package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import org.hamcrest.Matchers.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import org.hamcrest.CoreMatchers.`is` as Is
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//@RunWith(AndroidJUnit4::class)
class AnketaRepositoryTest {

    @Test
    fun test1getAll() {
        val ankete= AnketaRepository.getAll()
        assertEquals(ankete.size, 8)

        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Tjelesna aktivnost"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("naziv", Is("Nema naziva")))))

        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Zdravlje i način života"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("ZdravljeG i način života")))))

        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa1"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa2"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa3"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivGrupe", Is("Grupa16")))))

        assertThat(ankete, not(hasItem<Anketa>(hasProperty("progres", Is(16)))))



    }

    @Test
    fun test2getMyAnkete() {
        var ankete=AnketaRepository.getMyAnkete()
        assertEquals(ankete.size, 1)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Karijera ili ljubav"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("naziv", Is("Tjelesna aktivnost")))))

        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Ljudska psihologija"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Zdravlje i način života")))))

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Zdravlje i način života", "Grupa4", 2)
        ankete=AnketaRepository.getMyAnkete()
        assertEquals(ankete.size, 2)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Tjelesna aktivnost"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Zdravlje i način života"))))


    }

    @Test
    fun test3getDone() {
        var ankete=AnketaRepository.getDone()

        assertEquals(ankete.size, 1)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Karijera ili ljubav"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("naziv", Is("Tjelesna aktivnost")))))

        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Ljudska psihologija"))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Zdravlje i način života")))))

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Zdravlje i način života", "Grupa4", 2)
        ankete=AnketaRepository.getDone()
        assertEquals(ankete.size, 1)
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("naziv", Is("Tjelesna aktivnost")))))
        assertThat(ankete, not(hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Zdravlje i način života")))))

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Interna anketa", "Grupa6", 4)
        ankete=AnketaRepository.getDone()
        assertEquals(ankete.size, 2)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Zadovoljstvo zaposlenika1"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Interna anketa"))))



    }

    @Test
    fun test4getFuture(){
        var ankete=AnketaRepository.getFuture()

        assertEquals(ankete.size, 0)

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Zdravlje i način života", "Grupa2", 2)
        ankete=AnketaRepository.getFuture()
        assertEquals(ankete.size, 0)

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Ljudska psihologija", "Grupa1", 1)
        ankete=AnketaRepository.getFuture()
        assertEquals(ankete.size, 1)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Karijera ili ljubav"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Ljudska psihologija"))))
    }

    @Test
    fun test5getNotTaken(){
        var ankete=AnketaRepository.getNotTaken()

        assertEquals(ankete.size, 0)

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Zdravlje i način života", "Grupa4", 2)
        ankete=AnketaRepository.getNotTaken()
        assertEquals(ankete.size, 0)

        //dodajemo novu Anketu u korisnikove ankete
        AnketaRepository.setKorisnikovaAnketa("Interna anketa", "Grupa7", 4)
        ankete=AnketaRepository.getNotTaken()
        assertEquals(ankete.size, 1)
        assertThat(ankete, hasItem<Anketa>(hasProperty("naziv", Is("Zadovoljstvo zaposlenika2"))))
        assertThat(ankete, hasItem<Anketa>(hasProperty("nazivIstrazivanja", Is("Interna anketa"))))
    }
    }
