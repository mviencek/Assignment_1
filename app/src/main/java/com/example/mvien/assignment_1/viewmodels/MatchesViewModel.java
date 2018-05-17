package com.example.mvien.assignment_1.viewmodels;
import com.example.mvien.assignment_1.datamodels.MatchesDataModel;
import com.example.mvien.assignment_1.models.MatchesModel;
import java.util.ArrayList;
import java.util.function.Consumer;
import com.google.firebase.database.DataSnapshot;

    public class MatchesViewModel {

        private MatchesDataModel matchesDataModel;

        //constructor.  view model instantiates a data model
        public MatchesViewModel() {
            matchesDataModel = new MatchesDataModel();
        }

        //grabs matched items from the data model and places the result in a matchesmodel arraylist
        public void getMatchedItems(Consumer<ArrayList<MatchesModel>> responseCallback) {
            matchesDataModel.getMatchedItems(
                    (DataSnapshot dataSnapshot) -> {
                        ArrayList<MatchesModel> matches = new ArrayList<>();
                        for (DataSnapshot matchesSnapshot : dataSnapshot.getChildren()) {
                            MatchesModel person = matchesSnapshot.getValue(MatchesModel.class);
                            assert person != null;
                            person.uid = matchesSnapshot.getKey();
                            matches.add(person);
                        }
                        responseCallback.accept(matches);
                    },
                    (databaseError -> System.out.println("Error reading Matches: " + databaseError))
            );
        }

        //clears the data model
        public void clear() {
            matchesDataModel.clear();
        }

        //updates the item in the database
        public void updateMatchesItem(MatchesModel person) {
            matchesDataModel.updateMatchesItemById(person);
        }

}


