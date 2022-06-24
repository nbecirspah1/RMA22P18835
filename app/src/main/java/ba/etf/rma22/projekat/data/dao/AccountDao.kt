package ba.etf.rma22.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ba.etf.rma22.projekat.data.models.Account


@Dao
interface AccountDao {
    @Query("SELECT * FROM Account")
    suspend fun getAcc(): Account

    @Query("DELETE FROM Account")
    suspend fun obrisiAcc()

    @Insert
    suspend fun insertAcc(vararg acc: Account)

    @Query("SELECT acHash from Account")
    suspend fun getHash() : String
}