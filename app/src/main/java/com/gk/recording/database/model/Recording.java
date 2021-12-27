package com.gk.recording.database.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recording {

    @PrimaryKey
    public int id;

    @ColumnInfo(name="name")
    public String name;

    @ColumnInfo(name="time_duration")
    public Long timeDuration;

    @ColumnInfo(name="type")
    public String type;
}
