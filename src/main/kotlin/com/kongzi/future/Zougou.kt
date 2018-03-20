package com.kongzi.future

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Basically the same as [WikiNamespace]'s Backlink,
 * but in the context of a many-to-many relationship w/ [Art]
 */

@Entity
data class Zougou (
        @PrimaryKey val zPageId: Int,
        val ns: Int,
        val title: String,
        val aPageId: Int)