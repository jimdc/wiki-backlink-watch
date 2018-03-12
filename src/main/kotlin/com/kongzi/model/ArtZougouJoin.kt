package com.kongzi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey

const val AZtableName = "art_zougou_join"

@Entity(tableName = "${AZtableName}",
        primaryKeys = arrayOf("aPageid", "zPageid"),
        foreignKeys = arrayOf(
                ForeignKey(entity = Art::class,
                        parentColumns = arrayOf("zPageid"),
                        childColumns = arrayOf("aPageid")),
                ForeignKey(entity = Zougou::class,
                        parentColumns = arrayOf("aPageid"),
                        childColumns = arrayOf("zPageid"))
        ))
class ArtZougouJoin(val aPageid: Int, val zPageid: Int)