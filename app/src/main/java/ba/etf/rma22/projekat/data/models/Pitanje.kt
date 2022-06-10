package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class Pitanje (
    @SerializedName("id") val id : Int, //jedinstveni id pitanja

    @SerializedName("naziv") val naziv : String, //jedinstveni naziv pitanja u okviru ankete u kojoj se nalazi
    @SerializedName("tekstPitanja") val tekstPitanja : String, // tekst pitanja
    @SerializedName("opcije") val opcije : List<String>, //lista ponuđenih odgovora


)