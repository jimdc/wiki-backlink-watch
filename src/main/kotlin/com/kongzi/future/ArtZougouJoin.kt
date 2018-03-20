package com.kongzi.future

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

const val azTableName = "art_zougou_join"

@Entity(tableName = azTableName,
        primaryKeys = ["aPageId", "zPageId"],
        foreignKeys = [(ForeignKey(entity = Art::class,
                parentColumns = arrayOf("zPageId"),
                childColumns = arrayOf("aPageId"))), (ForeignKey(entity = Zougou::class,
                parentColumns = arrayOf("aPageId"),
                childColumns = arrayOf("zPageId")))])
class ArtZougouJoin(val aPageId: Int, val zPageId: Int)