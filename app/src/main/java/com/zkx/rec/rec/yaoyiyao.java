package com.zkx.rec.rec;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by asus on 2018/6/21.
 */

public class yaoyiyao extends Service implements SensorEventListener{
    SensorManager yy=null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        yy=(SensorManager)getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        yy=null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        boolean flag=false;
        MediaRecorder ly=null;
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
        {
            if(Math.abs(event.values[0])>30 ||Math.abs(event.values[1])>30 ||Math.abs(event.values[2])>30)
            {
                if(flag==false)
                {
                    ly = new MediaRecorder();
                    ly.setAudioSource(MediaRecorder.AudioSource.MIC);//麦克风
                    ly.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    ly.setOutputFile(Environment.getExternalStorageDirectory() + "录音.3gp");
                    ly.setVideoEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                    try {
                        ly.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ly.start();
                    flag = true;
                }
                else
                {
                    ly.stop();
                    ly.release();
                    ly=null;
                    flag=false;
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        return;
    }
}
