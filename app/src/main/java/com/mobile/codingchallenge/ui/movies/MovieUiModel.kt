package com.mobile.codingchallenge.ui.movies

import android.os.Parcel
import android.os.Parcelable

data class MovieUiModel(
    val id: Int,
    val title: String,
    val overview: String,
    val imageUrl: String,
    val rating: String,
    val releaseDate: String
) : Parcelable {

    private constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(imageUrl)
        parcel.writeString(rating)
        parcel.writeString(releaseDate)
    }

    override fun describeContents() = 0

    companion object CREATOR : Parcelable.Creator<MovieUiModel> {
        override fun createFromParcel(parcel: Parcel): MovieUiModel {
            return MovieUiModel(parcel)
        }

        override fun newArray(size: Int): Array<MovieUiModel?> {
            return arrayOfNulls(size)
        }
    }
}