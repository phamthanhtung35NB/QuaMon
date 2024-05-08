package com.example.thaphuong;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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
        view.findViewById(R.id.buttonKhan).setOnClickListener(v -> {
            String content = ((Spinner) view.findViewById(R.id.spinner_university)).getSelectedItem().toString();
            String uneversity = "Đại học Kinh tế";
            addItemToFirebase(content, uneversity);
        });
    }
    private void addItemToFirebase(String content, String uneversity) {
        Locale vietnam = new Locale("vi", "VN");

        SimpleDateFormat day = new SimpleDateFormat("ddMMyyyy", vietnam);
        String dayFormat = day.format(new Date());

        SimpleDateFormat time = new SimpleDateFormat("HHmmss", vietnam);
        String timeFormat = time.format(new Date());

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss", vietnam);
        String currentDateTimeString = sdf.format(new Date());

        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        Content itemPush = new Content(content, uneversity, dayFormat, timeFormat);
        dbRef.child(currentDateTimeString).setValue(itemPush);
    }
}