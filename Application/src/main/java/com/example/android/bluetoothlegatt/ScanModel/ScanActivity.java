package com.example.android.bluetoothlegatt.ScanModel;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.bluetoothlegatt.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ScanActivity extends AppCompatActivity {
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> dn = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning=false;
    public EditText et;
    scanad scanad = new scanad(title,dn,time);
    RecyclerView recyclerView;
    public String devicename="no";
    boolean iscroll=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initPermission();
        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        recyclerView = findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(scanad);
        et = new EditText(this);
        stop(null);
    }

    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String dd="";
                            try{dd=device.getName();}catch (Exception e){dd="";}
                            if(devicename.equals("no")||devicename.equals(dd)){
                                StringBuilder stringBuilder = new StringBuilder();
                                for (byte a : scanRecord) {
                                    stringBuilder.append(String.format("%02X", a));
                                }
                                Log.d("scanrecord", stringBuilder.toString());
                                try {
                                    Log.d("name", device.getName());
                                    dn.add(device.getName());
                                    title.add(stringBuilder.toString());
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
                                    Date date = new Date(System.currentTimeMillis());
                                    time.add(simpleDateFormat.format(date));
                                } catch (Exception e) {
                                    Log.d("name", "UNROWN");
                                    dn.add("UNKNOWN");
                                    title.add(stringBuilder.toString());
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss:SSS");
                                    Date date = new Date(System.currentTimeMillis());
                                    time.add(simpleDateFormat.format(date));

                                }
                                if(iscroll){ recyclerView.scrollToPosition(title.size()-1);}
                                scanad.notifyDataSetChanged();
                            }
                        }
                    });
                }
            };


    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
//            mHandler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mScanning = false;
//                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
//                    invalidateOptionsMenu();
//                }
//            }, 10000);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        invalidateOptionsMenu();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scanLeDevice(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Ensures Bluetooth is enabled on the device.  If Bluetooth is not currently enabled,
        // fire an intent to display a dialog asking the user to grant permission to enable it.
        if (!mBluetoothAdapter.isEnabled()) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }
public void device(View view){
    et = new EditText(this);
    new AlertDialog.Builder(this).setTitle("輸入no則搜索全部").setIcon(android.R.drawable.ic_dialog_info).setView(et).setPositiveButton("确定", new DialogInterface.OnClickListener() {	public void onClick(DialogInterface dialog, int which) {	String input = et.getText().toString();	if (input.equals("")) {		Toast.makeText(getApplicationContext(), "搜索内容不能为空！" + input, Toast.LENGTH_LONG).show();	}	else { devicename=et.getText().toString();}	}	}).setNegativeButton("取消", null).show();
}
public void scroll(View view){

    AlertDialog dialog = new AlertDialog.Builder(this).setTitle("").setIcon(android.R.drawable.ic_dialog_info)

            .setNegativeButton("滾動", new DialogInterface.OnClickListener() {



                @Override

                public void onClick(DialogInterface dialog, int which) {

                    iscroll=true;

                }

            }).setPositiveButton("不滾動", new DialogInterface.OnClickListener() {



                @Override

                public void onClick(DialogInterface dialog, int which) {

                 iscroll=false;

                }

            }).setNeutralButton("取消", null)

            .setMessage("是否滾動").create();

    dialog.show();

}
    public void start(View view){scanLeDevice(true);}
    public void stop(View view){scanLeDevice(false);}
    //////////////////////////////////////權限要求
    boolean flag = false;

    public void RequestPermission() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mBluetoothAdapter.setName("blueTestPhone");
//判斷藍芽是否開啟
        boolean originalBluetooth = (mBluetoothAdapter != null && mBluetoothAdapter.isEnabled());
        if (originalBluetooth) {
            mBluetoothAdapter.startDiscovery();
        } else if (originalBluetooth == false) {
            mBluetoothAdapter.enable();
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                flag = true;
                RequestPermission();
            }
        } else {
            flag = true;
            RequestPermission();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults[0] != 0) {
                Toast.makeText(this, "請先打開定位系統", Toast.LENGTH_LONG).show();
            } else {
                RequestPermission();
            }
        }
    }

}