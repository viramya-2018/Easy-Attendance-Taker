package com.example.madt_innovative;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 12-Mar-18.
 */

public class frag_home extends Fragment {

    private TextView frag_home_trialTextView;
    private expandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_home, container, false);
        frag_home_trialTextView = (TextView)v.findViewById(R.id.frag_home_trialTextView);

        expListView = (ExpandableListView)v.findViewById(R.id.frag_home_expandableLV);
        prepareListData();
        listAdapter = new expandableListAdapter(getContext(), listDataHeader, listDataChild);
        expListView.setAdapter(listAdapter);

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                Toast.makeText(getContext(), "" + listDataHeader.get(groupPosition) + " clicked!", Toast.LENGTH_LONG).show();
                return false;
            }
        });
        return v;
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Class A");
        listDataHeader.add("Class B");
        listDataHeader.add("Class C");
        listDataHeader.add("Class D");
        listDataHeader.add("Class E");
        listDataHeader.add("Class F");
        listDataHeader.add("Class G");
        listDataHeader.add("Class H");
        listDataHeader.add("Class I");

        // Adding child data
        List<String> classA = new ArrayList<String>();
        classA.add("Section");
        classA.add("Total students");

        List<String> classB = new ArrayList<String>();
        classB.add("Section");
        classB.add("Total students");

        List<String> classC = new ArrayList<String>();
        classC.add("Section");
        classC.add("Total students");

        List<String> classD = new ArrayList<String>();
        classD.add("Section");
        classD.add("Total students");

        List<String> classE = new ArrayList<String>();
        classE.add("Section");
        classE.add("Total students");

        List<String> classF = new ArrayList<String>();
        classF.add("Section");
        classF.add("Total students");

        List<String> classG = new ArrayList<String>();
        classG.add("Section");
        classG.add("Total students");

        List<String> classH = new ArrayList<String>();
        classH.add("Section");
        classH.add("Total students");

        List<String> classI = new ArrayList<String>();
        classI.add("Section");
        classI.add("Total students");

        listDataChild.put(listDataHeader.get(0), classA);
        listDataChild.put(listDataHeader.get(1), classB);
        listDataChild.put(listDataHeader.get(2), classC);
        listDataChild.put(listDataHeader.get(3), classD);
        listDataChild.put(listDataHeader.get(4), classE);
        listDataChild.put(listDataHeader.get(5), classF);
        listDataChild.put(listDataHeader.get(6), classG);
        listDataChild.put(listDataHeader.get(7), classH);
        listDataChild.put(listDataHeader.get(8), classI);
    }
}
