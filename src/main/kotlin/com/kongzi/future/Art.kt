package com.kongzi.future

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Basically, Article but in the context of a
 * many-to-many relationship with [Zougou] (Backlink)
 */
@Entity
data class Art(
        @PrimaryKey val aPageid: Int,
        var title: String)