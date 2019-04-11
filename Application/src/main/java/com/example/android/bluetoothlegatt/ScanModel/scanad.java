package com.example.android.bluetoothlegatt.ScanModel;


import android.os.Handler;

import android.os.Message;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;
import android.widget.TextView;


import com.example.android.bluetoothlegatt.R;

import org.w3c.dom.Text;

import java.util.ArrayList;



public class scanad extends RecyclerView.Adapter<scanad.ViewHolder> {



    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> time = new ArrayList<>();
    private ArrayList<String> devicename=new ArrayList<>();

    public scanad(ArrayList<String> title,ArrayList<String> devicename,ArrayList<String> time) {
this.devicename=devicename;
this.title=title;
this.time=time;
    }



    // 建立ViewHolder

    class ViewHolder extends RecyclerView.ViewHolder{
public final TextView textView;
        public final TextView textView2;
        ViewHolder(final View itemView) {

            super(itemView);
            textView=itemView.findViewById(R.id.textView2);
textView2=itemView.findViewById(R.id.textView3);

        }







    }





    @Override

    public scanad.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 連結項目布局檔list_item

        View view = LayoutInflater.from(parent.getContext())

                .inflate(R.layout.serchadapter, parent, false);





        return new scanad.ViewHolder(view);

    }



    boolean a=true;

    @Override

    public void onBindViewHolder(final scanad.ViewHolder holder, final int position) {
        holder.textView.setText(devicename.get(position)+":\n"+title.get(position));
        holder.textView2.setText(time.get(position));
    }





//    }





    @Override

    public int getItemCount() {

        return title.size();

    }

    private Handler handler=new Handler(){

        @Override

        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            switch (msg.what){



            }

        }

    };

}