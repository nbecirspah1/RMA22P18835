package ba.etf.rma22.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Istrazivanje (
    @PrimaryKey @SerializedName("id")  public var id : Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") var naziv : String,
    @ColumnInfo(name = "godina") @SerializedName("godina") var godina : Int //(od 1 do 5)
)