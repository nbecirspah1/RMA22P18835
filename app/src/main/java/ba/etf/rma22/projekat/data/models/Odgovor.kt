package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Odgovor(
    @SerializedName("id") val id : Int,
     val pitanjeID: Int,
    @SerializedName("odgovoreno") val odgovoreno: Int


    )
