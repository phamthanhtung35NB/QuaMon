package com.example.thaphuong;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private FrameLayout fragmentContainer;
    CircleImageView imageLogoAvatar;
    TextView textUsername;
    TextView textEmail;
    String profilePic = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        init(savedInstanceState);
        addEvent();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    void init(Bundle savedInstanceState) {
        drawerLayout = findViewById(R.id.main);
        fragmentContainer = findViewById(R.id.fragment_container);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setBackground(null);
        navigationView = findViewById(R.id.navigationView);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.navHome);
        }
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //thanh bên
        NavigationView navigationView = findViewById(R.id.navigationView);
        View headerView = navigationView.getHeaderView(0);
        imageLogoAvatar = headerView.findViewById(R.id.imageLogoAvatar);
        textUsername = headerView.findViewById(R.id.textUsername);
        textEmail = headerView.findViewById(R.id.textEmail);
        if (!profilePic.isEmpty()&&profilePic!=null&&profilePic.length()>0){
            Picasso.get().load(profilePic).into(imageLogoAvatar);
        }


        replaceFragment(new HomeFragment(), true);
        Toast.makeText(MainActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
    }
    void addEvent() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NotNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navHome){
                    replaceFragment(new HomeFragment(), false);
                    Toast.makeText(MainActivity.this, "navHome", Toast.LENGTH_SHORT).show();
                }else if(itemId==R.id.navLichSu){

                }
                return true;
            }
        });
        // Xử lý sự kiện khi click vào một item trong NavigationView thanh bên
        navigationView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_header_home) {
                replaceFragment(new HomeFragment(), false);
            } else if (itemId == R.id.nav_header_settings) {

            } else if (itemId == R.id.nav_header_share) {
                Toast.makeText(MainActivity.this, "Share", Toast.LENGTH_SHORT).show();

            } else if (itemId == R.id.nav_header_feedback) {
                //mở link liên kết github
                Intent intent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://github.com/phamthanhtung35NB"));
                startActivity(intent);
            }
            return true;
        });
    }
    void replaceFragment(Fragment fragment, boolean isAppInit){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (!isAppInit){
            fragmentTransaction.replace(R.id.fragment_container, fragment);
        }else {
            fragmentTransaction.add(R.id.fragment_container, fragment);
        }

        fragmentTransaction.commit();
    }
}