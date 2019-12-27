package com.chat.stmbrodcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerNumberView;
    private NumberAdapter numberAdapter;
    private ArrayList<IncomingNum> arrayList= new ArrayList<>();
    private BroadcastReceiver broadcastReceiver;
    private MyReceiver myReceiver= new MyReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerNumberView= findViewById(R.id.reclerNumberView);
        recyclerNumberView.setLayoutManager(new LinearLayoutManager(this));
        recyclerNumberView.setHasFixedSize(true);

        numberAdapter= new NumberAdapter(arrayList);
        recyclerNumberView.setAdapter(numberAdapter);
        ReadDate();

        broadcastReceiver= new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                ReadDate();
            }
        };
        /*Intent intent= new Intent(DbContact.UPDATE_FILTER);
        sendBroadcast(intent);
        IntentFilter intentFilter= new IntentFilter();
        intentFilter.addAction(DbContact.UPDATE_FILTER);

        registerReceiver(myReceiver,intentFilter);

       MyReceiver myReceiver= new MyReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent!= null){
                    getNumber.add(intent.getStringExtra("number"));
                    //Toast.makeText(context, ""+intent.getStringExtra("number"), Toast.LENGTH_SHORT).show();
                    ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, getNumber);
                    listView.setAdapter(arrayAdapter);
                }
            }
        };
        IntentFilter intentFilter= new IntentFilter();
        registerReceiver(myReceiver, intentFilter);*/
    }

    public void ReadDate(){
        arrayList.clear();
        DbHelper dbHelper= new DbHelper(this);
        //SQLiteDatabase sqLiteDatabase= dbHelper.getWritableDatabase();
        Cursor cursor= dbHelper.Display_Data();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "data set error", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                String number;
                int id;
                number= cursor.getString(cursor.getColumnIndex(DbContact.INCOMING_NUMBER));
                id= cursor.getInt(cursor.getColumnIndex("id"));
                arrayList.add(new IncomingNum(id, number));
            }
        }
        cursor.close();
        dbHelper.close();
        numberAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(DbContact.UPDATE_FILTER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    /*@Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter= new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
    }*/
}
