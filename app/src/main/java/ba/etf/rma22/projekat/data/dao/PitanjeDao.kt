package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Pitanje

@Dao
interface PitanjeDao {
    @Query("DELETE FROM pitanje")
    suspend fun obrisiDb()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviDb(pitanja: List<Pitanje>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pitanja: List<Pitanje>)

}