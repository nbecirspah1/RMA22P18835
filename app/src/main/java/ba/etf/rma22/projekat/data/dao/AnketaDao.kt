package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken

@Dao
interface AnketaDao {
    @Query("DELETE FROM anketa")
    suspend fun obrisiDb()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviDb(ankete: List<Anketa>)




    @Query("SELECT * FROM anketa")
    suspend fun dajSveAnketeDb(): List<Anketa>


}