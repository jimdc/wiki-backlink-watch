package com.kongzi.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.ForeignKey.CASCADE

/**
 * Basically the same as [WikiNamespace]'s Backlink,
 * but in the context of a many-to-many relationship w/ [Art]
 */

@Entity
data class Zougou (
        @PrimaryKey val zPageid: Int,
        val ns: Int,
        val title: String,
        val aPageid: Int) {
}