package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Pitanje (
    @PrimaryKey @SerializedName("id") var id : Int=-1, //jedinstveni id pitanja
    @ColumnInfo(name = "naziv") @SerializedName("naziv") var naziv : String="", //jedinstveni naziv pitanja u okviru ankete u kojoj se nalazi
    @ColumnInfo(name = "tekstPitanja") @SerializedName("tekstPitanja") var tekstPitanja : String="", // tekst pitanja
    @ColumnInfo(name = "opcije") var opcijeDB : String="",
    @Ignore @SerializedName("opcije") var opcije : List<String> = emptyList()//lista ponuđenih odgovora


)