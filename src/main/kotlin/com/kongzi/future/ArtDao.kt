package com.kongzi.future

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Update
import com.kongzi.future.Art

@Dao
interface ArtDao {

    @Insert
    fun insert(vararg art: Art)

    @Update
    fun update(vararg art: Art)

    @Delete
    fun delete(vararg art: Art)
}