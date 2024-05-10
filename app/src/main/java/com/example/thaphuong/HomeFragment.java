package com.example.thaphuong;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.thaphuong.Adapter.UniversityAdapter;
import com.example.thaphuong.Class.Content;
import com.example.thaphuong.DataUniversity.Data;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class HomeFragment extends Fragment {
    Spinner spinner_university;
    UniversityAdapter universityAdapter;
    EditText textContent;
    EditText textName;
    Button buttonKhan;
    Boolean isClicked = true;
    SimpleDateFormat timeHienTai;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        addEvents(view);
        return view;
    }
    void init(View view) {
        spinner_university = view.findViewById(R.id.spinner_university);
        textName = view.findViewById(R.id.textName);
        textContent = view.findViewById(R.id.textContent);
        textContent.setError("Không được để trống");
        buttonKhan = view.findViewById(R.id.buttonKhan);
        universityAdapter = new UniversityAdapter(getContext(), Data.getListUniversity());
        spinner_university.setAdapter(universityAdapter);
    }
    void addEvents(View view) {
    buttonKhan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isClicked==false) {
                showDialog();
                return;
            }

            String content = textContent.getText().toString();
            int position = spinner_university.getSelectedItemPosition();
            String university = spinner_university.getSelectedItemPosition() == 0 ? "Trường Khác" : Data.getListUniversity().get(position).getName();
            String name = textName.getText().toString();
            if (content.isEmpty()) {
                textContent.setError("Nhập nội dung");
                return;
            }
            if (name.length()>20) {
                textName.setError("Tên Thí Chủ dưới 20 ký tự");
                return;
            }
            if (name.length()==0) {
                name = "Ẩn Danh";
            }
            // Write
            addItemToFirebase(content, university, name);

            // Disable the button
            isClicked = false;

            // Enable the button after 60 seconds
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isClicked = true;
                }
            }, 60000);  // 60000 milliseconds = 60 seconds
        }
    });
}
    private void showDialog() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_center_show_spam);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);
        if (Gravity.BOTTOM == Gravity.BOTTOM) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }
        TextView tvContent = dialog.findViewById(R.id.tvContent);
        tvContent.setText("Bạn vừa dâng hương rồi hãy chờ 60s mới có thể quay lại nhé");
        Button btnClose = dialog.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(v -> {
            System.out.println("Đóng dialog");
            dialog.dismiss();
        });

        dialog.show();
    }
    private void addItemToFirebase(String content, String uneversity,String name) {
    Locale vietnam = new Locale("vi", "VN");


    SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss", vietnam);
    String currentDateTimeString = sdf.format(new Date());

    DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

        // Lấy thời điểm hiện tại
        Date now = new Date();

// Lấy số mili giây từ thời điểm hiện tại
        long millis = now.getTime();

// In số mili giây ra màn hình
        System.out.println(millis);

    Content itemPush = new Content(content, uneversity, name, currentDateTimeString);
    System.out.println(itemPush.getContent());
    System.out.println(currentDateTimeString);
    dbRef.child(currentDateTimeString+""+millis).setValue(itemPush);
}
}