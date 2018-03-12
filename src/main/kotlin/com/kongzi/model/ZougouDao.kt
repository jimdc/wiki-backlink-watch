package com.kongzi.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

/**
 * Not sure if I should be using [AZtableName] here.
 * The example below used a different table for "repository":
 * https://android.jlelse.eu/android-architecture-components-room-relationships-bf473510c14a
 */

@Dao
interface ZougouDao {

    @get:Query("SELECT * FROM ${AZtableName}")
    val allZougous: List<Zougou>

    @Insert
    fun insert(zougou: Zougou)

    @Update
    fun update(vararg zougou: Zougou)

    @Delete
    fun delete(vararg zougou: Zougou)

    @Query("SELECT * FROM ${AZtableName} WHERE aPageid=:aPageid")
    fun findZougousForArt(aPageid: Int): List<Zougou>
}