package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Anketa(
    @PrimaryKey @SerializedName("id") var id : Int=-1,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") var naziv: String="",
    // var datumPocetakDb: String="",
     //var datumKrajDb: String="",
    @ColumnInfo(name = "trajanje") @SerializedName("trajanje") var trajanje : Int=0,
   //  var datumRadaDb: String="",
    @ColumnInfo(name = "nazivIstrazivanja") var nazivIstrazivanja : String?=null,
    @ColumnInfo(name = "datumPocetak") @SerializedName("datumPocetak") var datumPocetak : Date= Date(),
    @ColumnInfo(name = "datumKraj") @SerializedName("datumKraj") var datumKraj : Date?=Date(),
    @ColumnInfo(name = "datumRada")  @SerializedName("datumRada") var datumRada : Date?=null,
    @ColumnInfo(name = "nazivGrupe") var nazivGrupe : String?=null,
    @ColumnInfo(name = "progres") var progres: Float?=null,
    @ColumnInfo(name="upisaneAnkete") var upisaneAnkete : Boolean? = false



) {


   override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        val anketa=other as Anketa
        if(anketa.id==this.id) return true
        return false
    }


}