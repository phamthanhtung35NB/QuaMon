package com.example.thaphuong.Admin;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.thaphuong.AdminActivity;
import com.example.thaphuong.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//import model.SqliteAccountHelper;

public class LoginActivity extends AppCompatActivity {


    private TextView textViewUsername;
    private EditText textPassword;
    Button buttonLogin;
    //mAuth là biến để xác thực người dùng
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        // chuyêển sang map activity
//        Intent intent1 = new Intent(LoginActivity.this, MapActivity.class);
//        startActivity(intent1);
//        finish();


        init();
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        addEvents();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    void init() {
        textViewUsername = findViewById(R.id.textViewUsername);
        textPassword = findViewById(R.id.textPassword2);
        mAuth = FirebaseAuth.getInstance();
        buttonLogin = findViewById(R.id.buttonLogin);
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        if (email.equals("") || password.equals("")) {
            return;
        }
        textViewUsername.setText(email);
        textPassword.setText(password);
    }


    void addEvents() {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }

        });
    }
    private void login() {
        String username = textViewUsername.getText().toString();
        String password = textPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            textViewUsername.setError("Vui lòng nhập tên đăng nhập");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            textPassword.setError("Vui lòng nhập mật khẩu");
            return;
        }
        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // kiểm tra user uid của người dùng thuộc collection client hay collection restaurant
                    String uid = mAuth.getCurrentUser().getUid();
                    //kiểm tra xem có sharedPreferences chưa nếu chưa thì tạo mới

                    if (uid != null&&uid.length()>0) {
                        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("email", textViewUsername.getText().toString());
                        editor.putString("password", textPassword.getText().toString());
                        editor.putString("uid", uid);
                        editor.apply();
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Vui lòng chọn loại tài khoản", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
//    onCreate(): Được gọi khi Activity được tạo. Đây là nơi bạn thường khởi tạo các thành phần của giao diện người dùng và các dữ liệu khác.
//    onStart(): Được gọi khi Activity trở nên hiển thị cho người dùng.
//    onResume(): Được gọi khi người dùng bắt đầu tương tác với Activity.
//    onPause(): Được gọi khi hệ thống chuẩn bị tiếp tục một Activity khác.
//    onStop(): Được gọi khi Activity không còn hiển thị cho người dùng.
//    onDestroy(): Được gọi trước khi Activity bị hủy.