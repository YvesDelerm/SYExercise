package fr.ydelerm.sherpanyves.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Embedded
import java.io.Serializable

data class PostAndUser(
    @Embedded val user: User,
    @Embedded val post: Post
) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(User::class.java.classLoader)!!,
        parcel.readParcelable(Post::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(user, flags)
        parcel.writeParcelable(post, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PostAndUser> {
        override fun createFromParcel(parcel: Parcel): PostAndUser {
            return PostAndUser(parcel)
        }

        override fun newArray(size: Int): Array<PostAndUser?> {
            return arrayOfNulls(size)
        }
    }
}
