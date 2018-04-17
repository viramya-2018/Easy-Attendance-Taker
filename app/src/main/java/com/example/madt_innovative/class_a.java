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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class class_a extends AppCompatActivity{ //} implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private String[] str = {"a","b","c","d"};
    private ArrayList<String> arrayList;
    CustomAdapter customAdapter;
    private TextView classA_tv;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_a);

        Bundle bundle = getIntent().getExtras();

        String[] rolls = bundle.getString("student_data").split(",");
        String cn = bundle.getString("className");

        //String[] a = bundle.getStringArray("student_data");
        //arrayList = new ArrayList<String>(Arrays.asList(a));

        classA_tv = (TextView)findViewById(R.id.classA_tv);
        recyclerView = (RecyclerView)findViewById(R.id.classA_rv);

        classA_tv.setText(cn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        customAdapter = new CustomAdapter(this, new ArrayList<String>(Arrays.asList(str)));
        recyclerView.setAdapter(customAdapter);

        //ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, this);
        //new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final String removeName = arrayList.get(position);

                if(direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT){
                    arrayList.remove(position);
                    customAdapter.notifyItemRemoved(position);
                }

                Toast.makeText(getApplicationContext(),"Swipped to right",Toast.LENGTH_SHORT).show();

                Snackbar snackbar = Snackbar
                        .make((LinearLayout)findViewById(R.id.classA_layout), removeName + " removed from cart!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // undo is selected, restore the deleted item
                        Toast.makeText(class_a.this, "undo clicked", Toast.LENGTH_SHORT).show();
                        //customAdapter.restoreItem(removeName, position);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }


        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }
}
