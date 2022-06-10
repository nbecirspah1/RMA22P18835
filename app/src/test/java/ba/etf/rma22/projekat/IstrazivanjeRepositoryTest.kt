package ba.etf.rma22.projekat
/*
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
//import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import org.hamcrest.Matchers.*
import org.junit.Test
import org.junit.Assert.*
import org.junit.FixMethodOrder
import org.junit.runners.MethodSorters
import org.hamcrest.CoreMatchers.`is` as Is
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

class IstrazivanjeRepositoryTest {

    @Test
    fun test1GetIstrazivanjeByGodina(){
        var istrazivanja=IstrazivanjeRepository.getIstrazivanjeByGodina(2)
        assertEquals(istrazivanja.size, 1)
       // assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Politika"))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Zdravlje i način života"))))

        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(2))))
        //assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(2))))

        istrazivanja=IstrazivanjeRepository.getIstrazivanjeByGodina(5)
        assertEquals(istrazivanja.size, 0)
       // assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Studentski život"))))

    }

    @Test
    fun test2GetAll(){
        var istrazivanja=IstrazivanjeRepository.getAll()
        assertEquals(istrazivanja.size, 4)

        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Politika"))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Zdravlje i način života"))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(2))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(3))))
        assertThat(istrazivanja, not(hasItem<Anketa>(hasProperty("naziv", Is("Politttika")))))
        assertThat(istrazivanja, not(hasItem<Anketa>(hasProperty("godina", Is(6)))))
    }

    @Test
    fun test3GetUpisani() {
        var istrazivanja=IstrazivanjeRepository.getUpisani()
        assertEquals(istrazivanja.size, 1)
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Ljudska psihologija"))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(1))))

        IstrazivanjeRepository.setKorisnikovoIstrazivanje("Politika", 2)
        IstrazivanjeRepository.setKorisnikovoIstrazivanje("Studentski život", 5)
        IstrazivanjeRepository.setKorisnikovoIstrazivanje("Zdravlje i način života", 2)

        assertEquals(istrazivanja.size, 4)
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Ljudska psihologija"))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(1))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Politika"))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("godina", Is(5))))
        assertThat(istrazivanja, hasItem<Anketa>(hasProperty("naziv", Is("Zdravlje i način života"))))




    }

}*/