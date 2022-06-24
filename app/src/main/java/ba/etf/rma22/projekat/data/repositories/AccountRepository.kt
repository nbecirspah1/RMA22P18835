package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.AppDatabase
import ba.etf.rma22.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class AccountRepository {
    companion object {

        var acHash = "39590664-6275-430a-ab51-a784d4546f62"
        private lateinit var  context : Context

        fun setContext(con : Context){
            context=con
        }
        fun getContext() : Context {
            return context
        }
        suspend fun postaviHash(hash:String):Boolean{
            //acHash=hash
            return withContext(Dispatchers.IO){
                try{
                    val db=AppDatabase.getInstance(context)

                    try {
                        if(hash!=acHash) {
                            acHash = hash
                            val acc = ApiAdapter.retrofit.getAcc(hash)

                            db.accountDao().obrisiAcc()
                            db.anketaDao().obrisiDb()
                            db.istrazivanjeDao().obrisiDb()
                            db.grupaDao().obrisiDb()
                            db.pitanjeDao().obrisiDb()
                            db.odgovorDao().obrisiDb()

                            db!!.accountDao().insertAcc(acc)
                    }
                    }catch(e1 : java.lang.Exception){
                        return@withContext  true
                    }
                    return@withContext  true
                }catch(e: Exception){
                    return@withContext  false
                }
            }
        }
        fun getHash():String{
            return acHash
        }

      /*  @SuppressLint("SimpleDateFormat")
        private fun getDateFormat(date: Date): String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
            return format.format(date)
        }
        suspend fun getAcc(): Account? {
            return withContext(Dispatchers.IO) {
                try {
                    val db = AppDatabase.getInstance(context)
                    val rez = db.accountDao().getAcc()
                    return@withContext rez
                } catch (e: Exception) {
                    return@withContext null
                }
            }

        }*/
    }



}