package com.mangnaik.yogesh.enthusia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yogesh on 9/17/2017.
 */

public class ContactDetailsFragment  extends android.support.v4.app.Fragment  {

    public ContactDetailsFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String name = getArguments().getString("type");
        if(name.equals("commitee")){
            return inflater.inflate(R.layout.fragment_commitee, container, false);
        }
        else{
            return inflater.inflate(R.layout.fragment_event_contact, container, false);
        }
    }
}
