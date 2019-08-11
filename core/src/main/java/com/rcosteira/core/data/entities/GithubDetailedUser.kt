package com.rcosteira.core.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

/**
 *  Model class for both network and database. I know, I know, it _might_ get messy in the future, if we keep adding
 *  stuff. But let future me worry about that =]
 */

@Entity(tableName = "Users")
data class GithubDetailedUser(

    @PrimaryKey
    @field:Json(name = "id")
    val id: Long = -1,

    @ColumnInfo(name = "username")
    @field:Json(name = "login")
    val login: String = "",

    @ColumnInfo(name = "avatar_url")
    @field:Json(name = "avatar_url")
    val avatarUrl: String = "",

    @ColumnInfo(name = "name")
    @field:Json(name = "name")
    val name: String = "",

    @ColumnInfo(name = "blog")
    @field:Json(name = "blog")
    val blog: String? = "",

    @ColumnInfo(name = "location")
    @field:Json(name = "location")
    val location: String? = ""
)