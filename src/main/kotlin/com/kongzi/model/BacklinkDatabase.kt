package com.kongzi.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(version=1, entities= [(Art::class), (Zougou::class), (ArtZougouJoin::class)])
/**
 * @todo: don't "allowMainThreadQueries()" or "fallbackToDestructiveMigration()"
 */
abstract class BacklinkDatabase : RoomDatabase() {
    abstract fun getArtDao(): ArtDao
    abstract fun getZougouDao(): ZougouDao
    abstract fun getArtZougouJoinDao(): ArtZougouJoinDao

    companion object {
        private var INSTANCE: BacklinkDatabase? = null

        fun getInstance(context: Context): BacklinkDatabase? {
            if (INSTANCE == null) {
                synchronized(BacklinkDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            BacklinkDatabase::class.java, azTableName)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() { INSTANCE = null }
    }
}