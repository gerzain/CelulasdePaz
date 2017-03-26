package com.dev.celulaspaz.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.celulaspaz.R;
import com.dev.celulaspaz.activities.LoginActivity;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Irving on 25/03/2017.
 */

public class Perfil extends Fragment
{

    @BindView(R.id.iv_profile_pic)
    CircleImageView profile_pic;
    @BindView(R.id.btnLogout)
    Button logout;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.content_detail,container,false);
        TextView facebok_name = (TextView) view.findViewById(R.id.facebook_name);
        //Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //  ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

       // setHasOptionsMenu(true);
        ButterKnife.bind(this,view);
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        if(extras!=null)
        {
            String name = extras.getString("name");
            String url=extras.getString("pic");
            String id=extras.getString("id");
            facebok_name.setText(name);
            String imageurl = "https://graph.facebook.com/" + id + "/picture?type=large";
            Picasso.with(getContext()).
                    load(imageurl)
                    .into(profile_pic);

        }
        return view;

    }
    public void Logout()
    {
        LoginManager.getInstance().logOut();
        IniciarSesion();
    }
    @OnClick(R.id.btnLogout)
    public void cerrarsesion()
    {
        Logout();
    }
    private void IniciarSesion()
    {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id=item.getItemId();

        switch (id)
        {
            case R.id.profile:
                return  true;
        }
        return  false;
    }



}
