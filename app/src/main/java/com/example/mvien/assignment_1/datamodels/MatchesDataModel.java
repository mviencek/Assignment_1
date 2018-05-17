package com.example.mvien.assignment_1.datamodels;
import com.example.mvien.assignment_1.models.MatchesModel;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.function.Consumer;

public class MatchesDataModel {
        private DatabaseReference mDatabase;
        private HashMap<DatabaseReference, ValueEventListener> listeners;

        //constructor
        public MatchesDataModel() {
            mDatabase = FirebaseDatabase.getInstance().getReference();
            listeners = new HashMap<>();

        }

        //gets matches from the database
        public void getMatchedItems(Consumer<DataSnapshot> dataChangedCallback, Consumer<DatabaseError> dataErrorCallback) {
            DatabaseReference matchesItemsRef = mDatabase.child("matches");
            //listener that looks for a data change
            ValueEventListener matchesItemsListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataChangedCallback.accept(dataSnapshot);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    dataErrorCallback.accept(databaseError);

                }
            };
            matchesItemsRef.addValueEventListener(matchesItemsListener);
            listeners.put(matchesItemsRef, matchesItemsListener);
        }

    //updates person by id
    public void updateMatchesItemById(MatchesModel person) {
        DatabaseReference matchesItemsRef = mDatabase.child("matches");
        matchesItemsRef.child(person.uid).setValue(person);
    }

    //clears the listeners
    public void clear() {
        listeners.forEach(Query::removeEventListener);
    }

}
