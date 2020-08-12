package com.tact.aidltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RemoteServiceConnecter connecter = RemoteServiceConnecter.GetInstance(getApplicationContext());
        IMyAidlInterface anInterface = connecter.getInterface();
        if(anInterface != null){
            try {
                int val = anInterface.getVal();
                Log.d(TAG, "val = " + val);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
