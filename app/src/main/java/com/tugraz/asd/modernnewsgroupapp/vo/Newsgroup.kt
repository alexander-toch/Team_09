package com.tugraz.asd.modernnewsgroupapp.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlin.text.count as count

@Entity
data class Newsgroup (
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "alias") var alias: String? = null,
        @ColumnInfo(name = "newsgroup_server_id") var newsgroupServerId: Int,
        @ColumnInfo(name = "parent") var parent: String? = null,
        @ColumnInfo(name = "hierarchy_level") var hierarchyLevel: Int? = null,
        @ColumnInfo(name = "subscribed") var subscribed: Boolean = false,
        @ColumnInfo(name = "first_article") var firstArticle: Long = 0,
        @ColumnInfo(name = "last_article") var lastArticle: Long = 0
) {
    // if newsgroup has at least a dot in its name -> indicates subgroup
    fun isSubgroup(): Boolean {
        return name.filter { it == '.' }.count() >= 1
    }

    fun setParentNewsgroup() {
        parent = name.substringBeforeLast(".")
    }

    fun setHierarchyLevel() {
        hierarchyLevel = name.filter { it == '.' }.count()
    }
}