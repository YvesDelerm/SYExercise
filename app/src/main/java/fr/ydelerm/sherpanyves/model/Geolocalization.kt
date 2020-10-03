package fr.ydelerm.sherpanyves.model

import android.os.Parcel
import android.os.Parcelable

data class Geolocalization(
    val lat: String,
    val lng: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(lat)
        parcel.writeString(lng)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Geolocalization> {
        override fun createFromParcel(parcel: Parcel): Geolocalization {
            return Geolocalization(parcel)
        }

        override fun newArray(size: Int): Array<Geolocalization?> {
            return arrayOfNulls(size)
        }
    }
}