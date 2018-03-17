package com.kongzi.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ArtZougouJoinDao {
    @Insert
    fun insert(artZougouJoin: ArtZougouJoin)

    @Query("""SELECT * FROM user INNER JOIN $azTableName ON
            art.aPageId=$azTableName.aPageId WHERE
            $azTableName.zPageId=:zPageId""")
    fun getArtsForZougous (zPageid: Int): List<Art>

    @Query("""SELECT * FROM repo INNER JOIN $azTableName ON
            zougou.zPageId=$azTableName.zPageId WHERE
            $azTableName.aPageId=:aPageId""")
    fun getZougousForArts(aPageid: Int): List<Zougou>
}