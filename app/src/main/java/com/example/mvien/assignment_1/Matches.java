package com.example.mvien.assignment_1;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mvien.assignment_1.models.MatchesModel;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import static android.graphics.Color.*;


public class Matches extends Fragment {
    private ArrayList<MatchesModel> people = new ArrayList<MatchesModel>();
    private OnListFragmentInteractionListener mListener;
    static Location myLocation = new Location("");

    public Matches() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        Bundle b = this.getArguments();
      //  myLocation.setLatitude(47.6062);
       // myLocation.setLongitude(122.3321);
        if(b.containsKey("myLat")){
            myLocation.setLatitude(b.getDouble("myLat"));
        }
        if (b.containsKey("myLong")){
            myLocation.setLongitude(b.getDouble("myLong"));
        }
        if (b.containsKey("matches")){
            this.people = b.getParcelableArrayList("matches");

           // Log.i("mymatches name at getParcelableArrayList() index 0", " " + this.people.get(0).name.toString());
            ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView id;
        public TextView card_text;
        public ImageButton btn;
        public MatchesModel person;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, final Context myContext) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            btn = (ImageButton)itemView.findViewById(R.id.favorite_button);
            card_text = (TextView)itemView.findViewById(R.id.card_text);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        //reverse the value
                        if(person.name.trim() != "No Matches Found!") {
                            person.liked = !person.liked;
                            //toast you like them
                            if (person.liked) {
                                Toast.makeText(myContext, "You like " + name.getText() + "!", Toast.LENGTH_LONG).show();
                            }
                            //toast you dont like them
                            else {
                                Toast.makeText(myContext, "You no longer like " + name.getText() + "!", Toast.LENGTH_LONG).show();
                            }
                            mListener.onListFragmentInteraction(person);
                        }
                    }
                }
            });

        }
    }

    /**
     * Adapter to display recycler view.
     */
    public class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private int LENGTH;

        //constructor
        public ContentAdapter(Context context) {
            ArrayList<MatchesModel> temp = new ArrayList<MatchesModel>();
            for (MatchesModel m : people) {
                Location match = new Location("");
                match.setLongitude(Double.parseDouble(m.longitude));
                match.setLatitude(Double.parseDouble(m.lat));
                float distance = myLocation.distanceTo(match);
                if (distance < 16093.4) {
               // if (distance < 48093.4) { // i dont live in seattle increasing radius for testing on my phone
                    temp.add(m);
                }

            }
            //set up dummy matchesmodel if no matches are found
            if(temp.size() == 0) {
                MatchesModel none = new MatchesModel();
                none.name = getString(R.string.no_match);
                none.imageUrl = "";
                none.liked  = false;
                none.lat = "0.0";
                none.longitude = "0.0";
                temp.add(none);
            }
            people = temp;
            LENGTH = people.size();
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, parent.getContext());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
                holder.person = people.get(position);
                String url = people.get(position).imageUrl;
                if(url.trim().length() !=0) {
                    Picasso.get().load(url).into(holder.picture);
                }
                holder.name.setText(people.get(position).name);
                if(people.get(position).name.trim() == getString(R.string.no_match));
                {
                    holder.card_text.setText(people.get(position).name);
                }
                Boolean liked = people.get(position).liked;
                if (liked) {
                    holder.btn.setColorFilter(RED);
                } else {
                    holder.btn.setColorFilter(LTGRAY);
                }


        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(MatchesModel item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnListFragmentInteractionListener){
            mListener = (OnListFragmentInteractionListener) context;
        } else{
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
