package jp.gcreate.sample.kotlindaggerdatabinding.databases;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Setter;
import com.github.gfx.android.orma.annotation.Table;

/**
 * Copyright 2016 G-CREATE
 *
 */
@Table
public class TestData {
    @PrimaryKey(autoincrement = true)
    public final long id;

    @Column
    public final String name;

    public TestData(@Setter("id") long id, @Setter("name") String name){
        this.id = id;
        this.name = name;
    }
}