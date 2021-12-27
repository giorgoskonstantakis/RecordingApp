package com.gk.recording.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gk.recording.database.dao.RecordingsDao;
import com.gk.recording.database.model.Recording;

@Database(entities = {Recording.class}, version = 1)
public abstract class RecordingAppDatabase extends RoomDatabase {
    public abstract RecordingsDao recordingsDao();
}
