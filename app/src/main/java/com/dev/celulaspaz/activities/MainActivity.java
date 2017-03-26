package com.dev.celulaspaz.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.celulaspaz.fragments.FragmentRecompensas;
import com.dev.celulaspaz.fragments.NoticiasFragment;
import com.dev.celulaspaz.fragments.Perfil;
import com.dev.celulaspaz.fragments.Notificaciones;
import com.dev.celulaspaz.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Fragment fragment;
    private FragmentManager fragmentManager;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.perfil_menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

            switch (item.getItemId())
            {
                case R.id.action_profile:
                   // Toast.makeText(getApplicationContext(),"PerfilMenu",Toast.LENGTH_SHORT).show();
                    fragment=new Perfil();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.content,fragment).commit();

                    return true;

                default:
                    return super.onOptionsItemSelected(item);
            }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.fragmentManager=getSupportFragmentManager();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        agregarToolbar();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                    int id=item.getItemId();

                    switch (id)
                    {
                        case R.id.navigation_noticias:
                            fragment=new NoticiasFragment();

                            break;

                        case R.id.navigation_descubre:
                            fragment=new FragmentRecompensas();

                            break;

                        case R.id.navigation_hacer:
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
    private void agregarToolbar()
    {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab=getSupportActionBar();
        if(ab!=null)
        {
            ab.setDefaultDisplayHomeAsUpEnabled(true);
            ab.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        }
  }




}
