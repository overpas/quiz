package by.overpass.poms23.data.model.pojo

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class User(
        @PrimaryKey
        @SerializedName("name") var name: String,
        @SerializedName("bestScore") var bestScore: Int?
) : Parcelable {
    @Ignore
    constructor() : this("username", null)
}