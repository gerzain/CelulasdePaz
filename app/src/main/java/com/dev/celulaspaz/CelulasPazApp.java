package com.dev.celulaspaz;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Irving on 25/03/2017.
 */

public class CelulasPazApp extends Application
{


    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        //printKeyHash();
    }

    public void printKeyHash()
    {
        try {
            PackageInfo info=getPackageManager().getPackageInfo("com.dev.celulaspaz", PackageManager.GET_SIGNATURES);

            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.e("Hash Key", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }
        catch (PackageManager.NameNotFoundException | NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }

    }

}
