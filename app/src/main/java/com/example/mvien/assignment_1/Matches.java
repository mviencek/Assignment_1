package com.example.mvien.assignment_1;
import android.content.Context;
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

    public Matches() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        Bundle b = this.getArguments();
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
        public ImageButton btn;
        public MatchesModel person;
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, final Context myContext) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            btn = (ImageButton)itemView.findViewById(R.id.favorite_button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        //reverse the value
                        person.liked = !person.liked;
                        //toast you like them
                        if(person.liked){
                            Toast.makeText(myContext, "You like " + name.getText() + "!", Toast.LENGTH_LONG).show();
                        }
                        //toast you dont like them
                        else
                        {
                            Toast.makeText(myContext, "You no longer like " + name.getText() + "!", Toast.LENGTH_LONG).show();
                        }
                        mListener.onListFragmentInteraction(person);
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
        private int LENGTH = people.size();

        //empty constructor
        public ContentAdapter(Context context) {
            // Log.i("mymatches name at zero index in contentadapter", " " + people.get(0).name.toString());
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, parent.getContext());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.person = people.get(position);
            String url = people.get(position).imageUrl;
            Picasso.get().load(url).into(holder.picture);
            holder.name.setText(people.get(position).name);
            Boolean liked = people.get(position).liked;
            if(liked){
                    holder.btn.setColorFilter(RED);
            }
            else{
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
