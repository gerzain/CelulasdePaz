package com.dev.celulaspaz.activities;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.util.Log;

import com.dev.celulaspaz.R;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by Irving on 25/03/2017.
 */

public class LoginActivity extends AppCompatActivity
{

    private String TAG=LoginActivity.class.getSimpleName();
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private AccessToken accessToken;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton=(LoginButton)findViewById(R.id.fblogin);
        loginButton.setReadPermissions("public_profile");
        callbackManager=CallbackManager.Factory.create();
        accessToken=AccessToken.getCurrentAccessToken();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult)
            {
                accessToken=loginResult.getAccessToken();
                Log.d(TAG,"OnSucces");
                get_userInfo();
            }

            @Override
            public void onCancel()
            {
                Log.d(TAG,"OnCancel");
                Snackbar.make(findViewById(R.id.rootview), "CancelFacebookLogin", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error)
            {
                Log.d(TAG,error.getMessage());
                Snackbar.make(findViewById(R.id.rootview),"Error",Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void get_userInfo()
    {
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback()
                {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response)
                    {

                        try
                        {
                            String name = object.optString("name");
                            String id = object.optString("id");
                            String profilePicUrl= "";
                            if (object.has("picture"))
                            {
                                profilePicUrl= object.getJSONObject("picture").getJSONObject("data").getString("url");
                            }
                           // Log.d("nom",name +"id" +id);
                            //Log.d("picture",profilePicUrl);
                            Bundle extras=new Bundle();
                            Intent main = new Intent(getApplicationContext(),MainActivity.class);
                            extras.putString("name",name);
                            extras.putString("pic",profilePicUrl);
                            extras.putString("id",id);
                            main.putExtras(extras);
                            main.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(main);

                        } catch (JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "name,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
