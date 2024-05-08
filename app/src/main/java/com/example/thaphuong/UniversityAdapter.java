package com.example.thaphuong;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.thaphuong.DataUniversity.University;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UniversityAdapter extends BaseAdapter {
    private Context context;
    private List<University> universityList;
    public UniversityAdapter(Context context, List<University> universityList) {
        this.context = context;
        this.universityList = universityList;
    }
    @Override
    public int getCount() {
        return universityList!=null?universityList.size():0;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = LayoutInflater.from(context)
                .inflate(R.layout.item_university, parent, false);

        TextView txtName = rootView.findViewById(R.id.txtName);
        CircleImageView universityImage = rootView.findViewById(R.id.universityImage);

        txtName.setText(universityList.get(position).getName());
        universityImage.setImageResource(universityList.get(position).getImage());

        return rootView;
    }
}
