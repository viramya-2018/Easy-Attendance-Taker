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
import android.widget.Toast;

/**
 * Created by admin on 17-Apr-18.
 */

public class frag_add_student extends Fragment {

    DBAdapter_student dbAdapter_student;
    private EditText className, rollNumber;
    private Button save;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_add_student, container, false);
        className = v.findViewById(R.id.frag_add_student_classname);
        rollNumber = v.findViewById(R.id.frag_add_student_roll_number);
        save = v.findViewById(R.id.frag_add_student_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAdapter_student = new DBAdapter_student(getContext());
                dbAdapter_student.open();

                String cn = className.getText().toString();
                String rolls = rollNumber.getText().toString();

                dbAdapter_student.insertFunction(cn, rolls);
                dbAdapter_student.close();

                Toast.makeText(getContext(), "Saved to DB", Toast.LENGTH_SHORT).show();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                if(getFragmentManager().findFragmentById(R.id.fragment_container) != null)
                    fragmentTransaction.remove(getFragmentManager().findFragmentById(R.id.fragment_container));
                fragmentTransaction.replace(R.id.fragment_container, new frag_home());
                fragmentTransaction.commit();
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
