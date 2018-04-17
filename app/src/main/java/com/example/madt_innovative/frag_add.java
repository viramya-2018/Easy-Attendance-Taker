package com.example.madt_innovative;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by admin on 12-Mar-18.
 */

public class frag_add extends Fragment implements View.OnClickListener{

    DBAdapter dbAdapter;
    private TextView frag_add_trialTextView;
    private EditText frag_add_className, frag_add_section, frag_add_numberOfStudents;
    private Button frag_add_butAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_add, container, false);
        frag_add_trialTextView = (TextView)v.findViewById(R.id.frag_add_trailTextView);
        frag_add_className = (EditText)v.findViewById(R.id.frag_add_className);
        frag_add_section = (EditText)v.findViewById(R.id.frag_add_section);
        frag_add_numberOfStudents = (EditText)v.findViewById(R.id.frag_add_numberOfStudents);
        frag_add_butAdd = (Button)v.findViewById(R.id.frag_add_butAdd);

        frag_add_butAdd.setOnClickListener(this);

        return  v;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.frag_add_butAdd:
                frag_home frag_home = new frag_home();
                String className = frag_add_className.getText().toString();
                String sectionName = frag_add_section.getText().toString();
                String numOfStudents = frag_add_numberOfStudents.getText().toString();

                dbAdapter = new DBAdapter(getContext());
                dbAdapter.open();
                dbAdapter.insertFunction(className, sectionName, numOfStudents);
                dbAdapter.close();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                if(getFragmentManager().findFragmentById(R.id.fragment_container) != null)
                    fragmentTransaction.remove(getFragmentManager().findFragmentById(R.id.fragment_container));
                fragmentTransaction.replace(R.id.fragment_container, new frag_home());
                fragmentTransaction.commit();

                break;
        }
    }


}
