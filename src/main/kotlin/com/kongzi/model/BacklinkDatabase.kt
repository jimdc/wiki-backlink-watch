package com.kongzi.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kongzi.model.Art
import com.kongzi.model.Zougou
import com.kongzi.model.ArtZougouJoin

@Database(version=1, entities=arrayOf(Art::class, Zougou::class, ArtZougouJoin::class))
/**
 * @todo: don't "allowMainThreadQueries()" or "fallbackToDestructiveMigration()"
 */
abstract class BacklinkDatabase : RoomDatabase() {
    abstract fun getArtDao(): ArtDao
    abstract fun getZougouDao(): ZougouDao
    abstract fun getArtZougouJoinDao(): ArtZougouJoinDao

    companion object {
        private var INSTANCE: BacklinkDatabase? = null

        public fun getInstance(context: Context): BacklinkDatabase? {
            if (INSTANCE == null) {
                synchronized(BacklinkDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            BacklinkDatabase::class.java, AZtableName)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        public fun destroyInstance() { INSTANCE = null }
    }
}