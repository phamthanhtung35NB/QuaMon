package com.example.thaphuong;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thaphuong.Adapter.AdapterLS;
import com.example.thaphuong.Class.LS;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class LichSuFragment extends Fragment {
    private DatabaseReference dbRef;
    static private List<LS> listLS = new ArrayList<>();
    AdapterLS adapterLS = new AdapterLS(listLS, getActivity());
    RecyclerView postsRecyclerView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lich_su, container, false);
        init(view);
        addEvents(view);
        getLS();
        return view;
    }
    void init(View view) {
        postsRecyclerView = view.findViewById(R.id.postsRecyclerView);
        postsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        postsRecyclerView.setAdapter(adapterLS) ;
        //hiêểu thị thông báo cuối
        postsRecyclerView.scrollToPosition(listLS.size() - 1);
    }
    void addEvents(View view) {
    }
    private void getLS(){

        // Lấy một tham chiếu đến cơ sở dữ liệu
        dbRef = FirebaseDatabase.getInstance().getReference();

        // Lắng nghe sự kiện thay đổi dữ liệu
        System.out.println("getLS");
        dbRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listLS.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    // Lấy key (day1)
                    String key = postSnapshot.getKey();
                    String keyIn = postSnapshot.child("keyIN").getValue(String.class);

                    // Lấy dữ liệu từng biến một
//                    private String content;
//                    private String university;
//                    private String name;
//                    private String key;
                    String name = postSnapshot.child("name").getValue(String.class);
                    String content = postSnapshot.child("content").getValue(String.class);
                    String university = postSnapshot.child("university").getValue(String.class);


                    LS post = new LS(content, university, name, key,keyIn);
                    System.out.println("name: " + name + " content: " + content + " university: " + university + " key: " + key);
                    listLS.add(post);
                }
                System.out.println("listLS.size(): " + listLS.size());
                adapterLS.updateList(listLS);
                postsRecyclerView.setAdapter(adapterLS);
                //hiêểu thị thông báo cuối
                postsRecyclerView.scrollToPosition(listLS.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi
                System.out.println("Failed to read value: " + databaseError.toException());
            }
        });
    }
}