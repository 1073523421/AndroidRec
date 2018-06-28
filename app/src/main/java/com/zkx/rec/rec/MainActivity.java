package com.zkx.rec.rec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Button button1=(Button)findViewById(R.id.button);
        final Button button2=(Button)findViewById(R.id.button2);
        final Intent a=new Intent(MainActivity.this,tonghua.class);
        final Intent b=new Intent(MainActivity.this,yaoyiyao.class);
        setContentView(R.layout.activity_main);
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startService(a);
                startService(b);

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(a);
                stopService(b);
            }
        });
    }

}
