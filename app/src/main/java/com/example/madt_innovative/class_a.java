package com.example.madt_innovative;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class class_a extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private String[] str = {"a","b","c","d","e","f","g","h","i","j","a","b","c","d","e","f","g","h","i","j"};
    private ArrayList<String> arrayList;
    CustomAdapter customAdapter;
    private TextView classA_tv;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_a);
        arrayList = new ArrayList<String>(Arrays.asList(str));
        classA_tv = (TextView)findViewById(R.id.classA_tv);

        recyclerView = (RecyclerView)findViewById(R.id.classA_rv);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        customAdapter = new CustomAdapter(this, new ArrayList<String>(Arrays.asList(str)));
        recyclerView.setAdapter(customAdapter);

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CustomAdapter.myViewHolder) {
            // get the removed item name to display it in snack bar
            String name = new ArrayList<String>(Arrays.asList(str)).get(viewHolder.getAdapterPosition());

            // backup of removed item for undo purpose
            final String deletedItem = new ArrayList<String>(Arrays.asList(str)).get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view
            customAdapter.remove(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar
                    .make((LinearLayout)findViewById(R.id.classA_layout), name + " removed from cart!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    customAdapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}
