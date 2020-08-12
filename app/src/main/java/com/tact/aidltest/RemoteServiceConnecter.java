package com.tact.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;

import androidx.core.util.Predicate;

public class RemoteServiceConnecter {
    private final String TAG = "RemoteServiceConnecter";
    private static RemoteServiceConnecter mInstance = null;
    private IMyAidlInterface mRemoteServiceInterface = null;
    private Runnable mConnectCompleteCallback = null;

    private ServiceConnection mConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            Log.d(TAG, "onService Connected");
            mRemoteServiceInterface = IMyAidlInterface.Stub.asInterface(boundService);

            mConnectCompleteCallback.run();
        }

        public void onServiceDisconnected(ComponentName name) {
        }
    };

    private Context mApplicationContext;
    private RemoteServiceConnecter(Context applicationContext)
    {
        mApplicationContext = applicationContext;
    }

    public static RemoteServiceConnecter GetInstance(Context applicationContext)
    {
        if(mInstance == null){
            mInstance = new RemoteServiceConnecter(applicationContext);
        }
        return mInstance;
    }

    public boolean Connect(Runnable connectCompleteHandler)
    {
        Log.d(TAG, "Connect Start");
        mConnectCompleteCallback = connectCompleteHandler;

        Intent intent = new Intent();
        intent.setClassName(mApplicationContext, RemoteService.class.getName());
        mApplicationContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        return true;
    }

    public IMyAidlInterface getInterface()
    {
        return mRemoteServiceInterface;
    }
}
