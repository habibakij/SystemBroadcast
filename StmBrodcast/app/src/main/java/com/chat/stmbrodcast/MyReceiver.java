package com.chat.stmbrodcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String state= intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String number= intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        /*if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            boolean noconnection= intent.getBooleanExtra(
                    ConnectivityManager.EXTRA_NO_CONNECTIVITY, false
            );
            if (noconnection){
                Toast.makeText(context, "DisConnected", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
            }
        }*/

        if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
            DbHelper dbHelper= new DbHelper(context);
            SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
            dbHelper.SaveNumber(number, sqLiteDatabase);
            Toast.makeText(context, "the number is: "+number, Toast.LENGTH_SHORT).show();
            dbHelper.close();
        }
        Intent action= new Intent(DbContact.UPDATE_FILTER);
        context.sendBroadcast(action);
    }
}
