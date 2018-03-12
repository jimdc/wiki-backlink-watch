package com.kongzi.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.kongzi.model.Art
import com.kongzi.model.Zougou
import com.kongzi.model.ArtZougouJoin

@Dao
public interface ArtZougouJoinDao {
    @Insert
    fun insert(artZougouJoin: ArtZougouJoin)

    @Query("""SELECT * FROM user INNER JOIN ${AZtableName} ON
            art.aPageid=${AZtableName}.aPageid WHERE
            ${AZtableName}.zPageid=:zPageid""")
    fun getArtsForZougous (zPageid: Int): List<Art>

    @Query("""SELECT * FROM repo INNER JOIN ${AZtableName} ON
            zougou.zPageid=${AZtableName}.zPageid WHERE
            ${AZtableName}.aPageid=:aPageid""")
    fun getZougousForArts(aPageid: Int): List<Zougou>
}