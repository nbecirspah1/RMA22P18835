package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje

@Dao
interface GrupaDao {
    @Query("DELETE FROM grupa")
    suspend fun obrisiDb()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun napraviDb(grupe: List<Grupa>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll( grupa: Grupa)

    @Query("SELECT * FROM grupa")
    suspend fun vratiGrupe() : List<Grupa>

    @Insert()
    suspend fun ubaciGrupe(grupe: List<Grupa>)


    @Insert
    suspend fun insertAllUpisane(vararg grupa: Grupa)

}