package com.example.handwash_reminder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.Channel;

public class MainActivity extends AppCompatActivity {
    NotificationHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b=findViewById(R.id.b);
       final EditText et=findViewById(R.id.et);
        final TextView tv=findViewById(R.id.tv);
        final EditText et2=findViewById(R.id.et2);
        helper=new NotificationHelper(this);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                long t=Long.parseLong(et.getText().toString());
                t=t*60*1000;
                Toast.makeText(MainActivity.this, "Reminder is set."+t, Toast.LENGTH_SHORT).show();

               new CountDownTimer(t,1000)
                {

                    @Override
                    public void onTick(long millisUntilFinished) {
                        //tv.setText((int)millisUntilFinished);
                        et2.setText(millisUntilFinished+"");
                    }

                    @Override
                    public void onFinish() {
                        tv.setText("Wash your hand.");
                        Notification.Builder builder=helper.getChannelNotification("wash","immediately");
                        helper.getManager().notify(0,builder.build());
                        start();


                    }
                }.start();
            }

        });

    }
}
