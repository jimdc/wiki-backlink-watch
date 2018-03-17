package com.kongzi.model

import android.arch.persistence.room.*
import io.reactivex.Flowable
import com.kongzi.model.WikiApiService.BacklinkModel.Backlink

const val blTable = "blTable1"

@Dao
interface BacklinkDao {

    @Query("SELECT * FROM $blTable")
    fun getAllBacklinks(): Flowable<List<Backlink>>

    @Query("SELECT * FROM $blTable WHERE pageid = :id")
    fun getBacklinkById(id: Int): Backlink

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(backlink: Backlink): Int

    @Delete
    fun delete(backlink: Backlink): Int

    @Query("DELETE FROM $blTable")
    fun deleteAllBacklinks()
}