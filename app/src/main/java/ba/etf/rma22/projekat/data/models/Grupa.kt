package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Grupa (

    @SerializedName("id") val id : Int,
    @SerializedName("istrazivanjeId") val istrazivanjeId: Int,
    @SerializedName("naziv") val naziv: String,
    val nazivIstrazivanja : String?
)