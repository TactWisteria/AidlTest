package com.tact.aidltest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

public class RemoteService extends Service {

    private IMyAidlInterface.Stub binder = new IMyAidlInterface.Stub() {
        @Override
        public int getVal() throws RemoteException {
            return 0;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

}
