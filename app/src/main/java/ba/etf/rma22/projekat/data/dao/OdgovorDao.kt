package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Odgovor

@Dao
interface OdgovorDao {
    @Query("DELETE FROM odgovor")
    suspend fun obrisiDb()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviDb(odgovori: List<Odgovor>)

    @Insert()
    suspend fun insert(odgovori: List<Odgovor>)

    @Insert()
    suspend fun insertOdgovor(odgovor : Odgovor)

    @Query("SELECT * FROM anketa")
    suspend fun dajOdgovore(): List<Anketa>


}