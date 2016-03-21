package jp.gcreate.sample.kotlindaggerdatabinding.databases

import com.github.gfx.android.orma.annotation.Column
import com.github.gfx.android.orma.annotation.PrimaryKey
import com.github.gfx.android.orma.annotation.Setter
import com.github.gfx.android.orma.annotation.Table

/**
 * Copyright 2016 G-CREATE
 *
 */
@Table
class TestData {
    @PrimaryKey(autoincrement = true)
    val id: Long

    @Column
    val name: String

    constructor(@Setter("id") id: Long, @Setter("name") name: String){
        this.id = id
        this.name = name
    }
}