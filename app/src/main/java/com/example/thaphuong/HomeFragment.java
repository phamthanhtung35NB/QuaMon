package com.example.thaphuong;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


public class HomeFragment extends Fragment {
    Spinner spinner_university;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        addEvents(view);
        return view;
    }
    void init(View view) {
        spinner_university = view.findViewById(R.id.spinner_university);
    }
    void addEvents(View view) {
    }
}