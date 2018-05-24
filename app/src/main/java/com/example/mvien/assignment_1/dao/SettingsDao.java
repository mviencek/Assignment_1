package com.example.mvien.assignment_1.dao;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import com.example.mvien.assignment_1.entity.UserSettings;
import java.util.List;

@Dao
public interface SettingsDao {

    @Query("SELECT * FROM UserSettings WHERE email = :Email")
    public List<UserSettings> getUserSettings(String Email);

    //inserts but overwrites if primary key is the same
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSettings(UserSettings Settings);

}
