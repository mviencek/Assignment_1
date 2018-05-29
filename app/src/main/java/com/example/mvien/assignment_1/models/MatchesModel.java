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
        public String lat;
        public String longitude;

        //empty constructor parcelable is smart enough
        public MatchesModel() {
        }


        public static final Creator<MatchesModel> CREATOR = new Creator<MatchesModel>() {
            @Override
            public MatchesModel createFromParcel(Parcel in) {

                return new MatchesModel();
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
            dest.writeString(lat);
            dest.writeString(longitude);
            dest.writeString(imageUrl);
            dest.writeString(name);
            dest.writeByte((byte) (liked ? 1 : 0));
        }
}
