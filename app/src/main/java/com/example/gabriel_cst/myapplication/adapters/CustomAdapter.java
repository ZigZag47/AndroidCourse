package com.example.gabriel_cst.myapplication.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabriel_cst.myapplication.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPE_GREEN = 0;
    private final int TYPE_PINK = 1;

    //List of elements to load in your list
    private ArrayList<String> items;
    //The context where adapter is used
    private Context context;

    //Init the list of elements in constructor
    public CustomAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0){
            return TYPE_GREEN;
        }

        return TYPE_PINK;
    }

    // Inflate a layout from XML and returning the holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        switch (viewType) {

            case TYPE_GREEN:
                View greenView = inflater.inflate(R.layout.item_fragment_four, parent, false);

                // Return a new holder instance
                RecyclerView.ViewHolder viewHolderGreen = new CustomAdapter.ViewHolderGreen(greenView);
                return viewHolderGreen;

            case TYPE_PINK:
                View pinkView = inflater.inflate(R.layout.item_list_element, parent, false);

                // Return a new holder instance
                RecyclerView.ViewHolder viewHolderPink = new CustomAdapter.ViewHolderPink(pinkView);
                return viewHolderPink;

        }

        return null;
    }

    // Populate data into the item through holder
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        String item = items.get(position) + " " + position;

        // Set item views based on your views and data model

        switch (getItemViewType(position)) {
            case TYPE_GREEN:
                TextView textViewGreen = ((ViewHolderGreen)viewHolder).nameTextView;
                textViewGreen.setText(item);
                break;

            case TYPE_PINK:
                TextView textViewPink = ((ViewHolderPink)viewHolder).nameTextView;
                textViewPink.setText(item);
                break;
        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolderGreen extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolderGreen(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.tv_item);
        }
    }

    public class ViewHolderPink extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolderPink(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.tv_title);
        }
    }

}
