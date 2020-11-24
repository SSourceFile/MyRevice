package com.fire.myreivces

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

class Bea() : Parcelable {
  constructor(parcel: Parcel) : this() {
  }

  override fun describeContents(): Int {
    TODO("Not yet implemented")
  }

  override fun writeToParcel(dest: Parcel?, flags: Int) {
    TODO("Not yet implemented")
  }

  companion object CREATOR : Parcelable.Creator<Bea> {
    override fun createFromParcel(parcel: Parcel): Bea {
      return Bea(parcel)
    }

    override fun newArray(size: Int): Array<Bea?> {
      return arrayOfNulls(size)
    }
  }
}