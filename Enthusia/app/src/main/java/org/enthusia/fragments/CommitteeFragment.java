package org.enthusia.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ramotion.foldingcell.FoldingCell;

import org.enthusia.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Yogesh on 11/4/2017.
 */

public class CommitteeFragment extends Fragment{
    public CommitteeFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commitee, container, false);
        LinearLayout main_container = (LinearLayout) view.findViewById(R.id.container);
        try {
            InputStream inputStream = getActivity().getAssets().open("texts/committee.txt");
            Scanner scan = new Scanner(inputStream);
            String string = "";
            while(scan.hasNextLine()){
                string += scan.nextLine();
            }
            System.out.println(string);
            String[] parts = string.split("%");
            for(int i=0; i<parts.length; i++){
                View v = inflater.inflate(R.layout.committee_item, container, false);
                if(i==0){
                    View cell = v.findViewById(R.id.cell);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)v.getLayoutParams();
                    layoutParams.setMargins(0,0,0,4);
                    cell.setLayoutParams(layoutParams);
                }
                LinearLayout textContainer = (LinearLayout)v.findViewById(R.id.text_container);
                TextView name = (TextView) v.findViewById(R.id.inner_name);
                String n = parts[i].split("#")[0].trim();
                name.setText(n);
                name = (TextView) v.findViewById(R.id.outer_name);
                name.setText(n);
                String[] people = parts[i].split("#")[1].trim().split(",");
                for(int j=0; j<people.length; j++){
                    View v1 = inflater.inflate(R.layout.committee_text_item, container, false);
                    name = (TextView) v1.findViewById(R.id.name);
                    name.setText(people[j]);
                    textContainer.addView(v1);
                }
                main_container.addView(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
