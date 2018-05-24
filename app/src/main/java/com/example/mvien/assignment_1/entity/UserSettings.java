package com.example.mvien.assignment_1.entity;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class UserSettings {

    @PrimaryKey
    @NonNull
    private String email = "";

    @ColumnInfo(name = "min_age")
    private int minAge;

    @ColumnInfo(name = "max_age")
    private int maxAge;

    @ColumnInfo(name = "is_private")
    private boolean isPrivate;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "max_distance")
    private int distance;

    @ColumnInfo(name = "reminder_time")
    private String reminder;

    @NonNull
    public String getEmail()
    {

        return this.email;
    }

    public void setEmail(@NonNull String Email)
    {
        this.email = Email;
    }

    public int getMinAge()
    {
        return this.minAge;
    }

    public void setMinAge(int MinAge)
    {
        this.minAge = MinAge;
    }

    public int getMaxAge()
    {
        return this.maxAge;
    }

    public void setMaxAge(int MaxAge)
    {
        this.maxAge = MaxAge;
    }

    public int getDistance()
    {
        return this.distance;
    }

    public void setDistance(int Distance)
    {
        this.distance = Distance;
    }

    public String getReminder() {
        return this.reminder;
    }

    public void setReminder(String Reminder) {
        this.reminder = Reminder;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public void setPrivate(boolean IsPrivate) {
        isPrivate = IsPrivate;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String Gender) {
        this.gender = Gender;
    }
}
