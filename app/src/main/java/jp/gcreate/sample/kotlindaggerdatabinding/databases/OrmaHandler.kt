package jp.gcreate.sample.kotlindaggerdatabinding.databases

import android.content.Context

/**
 * Copyright 2016 G-CREATE
 *
 */
class OrmaHandler {
    val db: OrmaDatabase

    constructor(context: Context) {
        db = OrmaDatabase.builder(context).build()
    }
}