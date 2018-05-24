package com.example.mvien.assignment_1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import com.example.mvien.assignment_1.entity.UserSettings;
import com.example.mvien.assignment_1.dao.SettingsDao;



@Database(entities = {UserSettings.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SettingsDao userSettings();
}
