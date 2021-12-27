package com.gk.recording.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.gk.recording.database.model.Recording;

@Dao
public interface RecordingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertRecording(Recording recording);

    @Update
    public void updateRecording(Recording recording);

    @Delete
    public void deleteRecording(Recording recording);

    @Query("SELECT * FROM recording")
    public Recording[] loadAllRecordings();
}
