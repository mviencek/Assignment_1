package com.example.mvien.assignment_1.models;
import android.os.Parcelable;
import com.google.firebase.database.IgnoreExtraProperties;
import android.os.Parcel;

@IgnoreExtraProperties
public class MatchesModel implements Parcelable {
        public String uid;
        public String name;
        public String imageUrl;
        public boolean liked;

        //empty constructor
        public MatchesModel() {
        }

        //constructor
        public MatchesModel(Parcel in) {
            name = in.readString();
            imageUrl = in.readString();
            liked = in.readByte() != 0;
        }

        public static final Creator<MatchesModel> CREATOR = new Creator<MatchesModel>() {
            @Override
            public MatchesModel createFromParcel(Parcel in) {

                return new MatchesModel(in);
            }

            @Override
            public MatchesModel[] newArray(int size) {

                return new MatchesModel[size];
            }
        };

        @Override
        public int describeContents() {

            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(imageUrl);
            dest.writeString(name);
            dest.writeByte((byte) (liked ? 1 : 0));
        }
}
