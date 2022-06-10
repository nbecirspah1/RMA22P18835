package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class PovratniPodaci(
    @SerializedName("odgovor") val odgovor : Int,
    @SerializedName("pitanje") val pitanje : Int,
    @SerializedName("progres") val progres : Int
)
