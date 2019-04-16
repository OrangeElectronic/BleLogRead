package com.example.android.bluetoothlegatt.ScanModel;

import android.bluetooth.BluetoothGattCharacteristic;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.bluetoothlegatt.ConnectModel.BluetoothLeService;
import com.example.android.bluetoothlegatt.R;

public class SetCharastic extends AppCompatActivity {
    public static BluetoothLeService mBluetoothLeService;
    public static BluetoothGattCharacteristic mNotifyCharacteristic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_charastic);
    }
}
