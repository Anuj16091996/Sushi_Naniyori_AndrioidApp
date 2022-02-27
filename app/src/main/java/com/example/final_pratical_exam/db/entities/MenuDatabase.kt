package com.reza.roomapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class MenuDatabase(
    @PrimaryKey var id: Int ,
    @ColumnInfo var MenuTitle: String?,
    @ColumnInfo var Description: String?,
    @ColumnInfo var Price: Double?,
    @ColumnInfo val MenuImage: Int?,
    @ColumnInfo var Quantity: Int?=0,

) : Serializable {
}