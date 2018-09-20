package by.overpass.poms23.data.model.pojo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Question(
        @SerializedName("text")
        var text: String?,
        @SerializedName("options")
        var options: List<String>?,
        @SerializedName("answer")
        var answer: Int?,
        @PrimaryKey
        @SerializedName("ordinalNumber")
        var ordinalNumber: Int
) : Parcelable {
    @Ignore
    constructor() : this(null, null, null, 0)
}