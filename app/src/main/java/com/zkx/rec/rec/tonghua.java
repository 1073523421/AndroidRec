package com.zkx.rec.rec;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by asus on 2018/6/21.
 */

public class tonghua extends Service{
    private TelephonyManager yy=null;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        yy=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        yy.listen(new jtq(),jtq.LISTEN_CALL_STATE);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        yy=null;
    }
    class jtq extends PhoneStateListener
    {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            MediaRecorder ly=null;
            switch(state)
            {
                case TelephonyManager.CALL_STATE_IDLE:
                    if(ly!=null)
                    {
                        ly.stop();
                        ly.release();
                    }
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    ly=new MediaRecorder();
                    ly.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
                    ly.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    ly.setOutputFile(Environment.getExternalStorageDirectory()+incomingNumber+"录音.3gp");
                    ly.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
                    try {
                        ly.prepare();ly.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }

        }
    }
}
