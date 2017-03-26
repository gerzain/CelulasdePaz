package com.dev.celulaspaz.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.dev.celulaspaz.fragments.DashDetail;
import com.dev.celulaspaz.fragments.NoticiasFragment;
import com.dev.celulaspaz.fragments.Perfil;
import com.dev.celulaspaz.fragments.Notificaciones;
import com.dev.celulaspaz.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragmentManager=getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    int id=item.getItemId();

                    switch (id)
                    {
                        case R.id.navigation_home:
                            fragment=new NoticiasFragment();

                            break;

                        case R.id.navigation_dashboard:
                            fragment=new DashDetail();

                            break;

                        case R.id.navigation_notifications:
                            fragment=new Notificaciones();

                            break;
                        case R.id.navigation_recompensas:
                            fragment=new Perfil();
                    }
                final FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.content, fragment).commit();


                return true;
            }
        });
    }

}
