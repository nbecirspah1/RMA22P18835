package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName

data class PovratniOdgovor(

    @SerializedName("odgovoreno") var odgovoreno: Int?,
    @SerializedName("KvizTakenId") var kvizTakenId: Int?,
    @SerializedName("PitanjeId") var pitanjeId: Int?
)
