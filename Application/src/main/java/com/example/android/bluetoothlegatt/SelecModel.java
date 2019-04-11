package com.example.android.bluetoothlegatt;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.android.bluetoothlegatt.ConnectModel.DeviceScanActivity;
import com.example.android.bluetoothlegatt.ScanModel.ScanActivity;

public class SelecModel extends AppCompatActivity {
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selec_model);
    }
    public void scan(View view){
        startActivity(new Intent(this, ScanActivity.class));
    }
    public void connect(View view){
        startActivity(new Intent(this,DeviceScanActivity.class));
    }

}
