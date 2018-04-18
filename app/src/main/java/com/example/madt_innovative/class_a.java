package com.example.madt_innovative;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class class_a extends AppCompatActivity{

    private ArrayList<String> arrayList;
    CustomAdapter_StudentRoll customAdapter_studentRoll;
    private TextView classA_tv;
    private RecyclerView recyclerView;
    private Button generateFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_a);

        Bundle bundle = getIntent().getExtras();

        String[] rolls = bundle.getString("student_data").split(",");
        String cn = bundle.getString("className");

        generateFile = findViewById(R.id.generateFile);
        classA_tv = (TextView)findViewById(R.id.classA_tv);
        recyclerView = (RecyclerView)findViewById(R.id.classA_rv);
        final ArrayList<String> arrayListRoll = new ArrayList<String>(Arrays.asList(rolls));
        classA_tv.setText(cn);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        customAdapter_studentRoll = new CustomAdapter_StudentRoll(this, arrayListRoll);
        recyclerView.setAdapter(customAdapter_studentRoll);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final String removeName = arrayListRoll.get(position);

                if(direction == ItemTouchHelper.LEFT || direction == ItemTouchHelper.RIGHT){
                    arrayListRoll.remove(position);
                    customAdapter_studentRoll.notifyItemRemoved(position);
                }

                Toast.makeText(getApplicationContext(),"Swipped to right",Toast.LENGTH_SHORT).show();

                Snackbar snackbar = Snackbar
                        .make((LinearLayout)findViewById(R.id.classA_layout), removeName + " removed from today's attendance!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // undo is selected, restore the deleted item
                        Toast.makeText(class_a.this, "undo clicked", Toast.LENGTH_SHORT).show();
                        customAdapter_studentRoll.restoreItem(removeName, position);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();

            }


        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        generateFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Code goes here
                Toast.makeText(class_a.this, "File generated and stored to internal storage", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
