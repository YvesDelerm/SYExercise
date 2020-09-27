package fr.ydelerm.sherpanyves.model

import androidx.room.ColumnInfo
import java.io.Serializable

data class Company(
    @ColumnInfo(name = "company_name") val name: String,
    val catchPhrase: String,
    val bs: String
) : Serializable