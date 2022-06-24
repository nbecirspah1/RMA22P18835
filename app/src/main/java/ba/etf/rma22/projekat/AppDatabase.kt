package ba.etf.rma22.projekat

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.etf.rma22.projekat.data.Converters
import ba.etf.rma22.projekat.data.dao.*
import ba.etf.rma22.projekat.data.models.*

@Database(entities = arrayOf(Account::class, Grupa::class, Istrazivanje::class, Anketa::class, Pitanje::class, Odgovor::class), version = 1013)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun accountDao(): AccountDao
    abstract fun grupaDao(): GrupaDao
    abstract fun istrazivanjeDao(): IstrazivanjeDao
    abstract fun anketaDao(): AnketaDao
    abstract fun pitanjeDao(): PitanjeDao
    abstract fun odgovorDao(): OdgovorDao

    companion object{
        private var INSTANCE: AppDatabase? = null

        fun setInstance(appdb:AppDatabase):Unit{
            INSTANCE=appdb
        }

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = buildRoomDB(context)
                }
            }
            return INSTANCE!!
        }

        private fun buildRoomDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "RMA22DB"
            ).fallbackToDestructiveMigration().build()
    }
}