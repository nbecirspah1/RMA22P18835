package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import org.hamcrest.Matchers.hasItem
import org.hamcrest.Matchers.hasProperty
import org.junit.Test

import org.junit.Assert.*
import org.hamcrest.CoreMatchers.`is` as Is
/*
class GrupaRepositoryTest {
    @Test
    fun testGetGroupsByIstrazivanje() {
        var grupe=GrupaRepository.getGroupsByIstrazivanje("Ljudska psihologija")
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("Grupa1"))))

        grupe=GrupaRepository.getGroupsByIstrazivanje("Zdravlje i način života")
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("Grupa3"))))

        grupe=GrupaRepository.getGroupsByIstrazivanje("Politika")
        assertThat(grupe, hasItem<Grupa>(hasProperty("naziv", Is("Grupa5"))))

    }
}*/