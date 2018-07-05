package com.example.lucaschen.soundswitch;

import android.content.Context;
import android.media.AudioManager;
import android.net.rtp.AudioCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AudioCodec[] audioCodecs;
    private static final int DEVICE_IN_WIRED_HEADSET = 0x400000;
    private static final int DEVICE_OUT_EARPIECE = 0x1;
    private static final int DEVICE_OUT_WIRED_HEADSET = 0x4;
    private static final int DEVICE_STATE_UNAVAILABLE = 0;
    private static final int DEVICE_STATE_AVAILABLE = 1;
    private AudioTest audioTest;
    private TextView textView;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioTest = new AudioTest();
        textView = (TextView) findViewById(R.id.textView);

//        audioCodecs = AudioCodec.getCodecs();
//
//        String a = "";
//
//        for (AudioCodec audioCodec : audioCodecs) {
//            Log.d("*********", "audiocodec=" + audioCodec.rtpmap);
//            a += audioCodec.rtpmap + "\n";
////            03-27 09:33:47.850 23049-23049/? D/*********: audiocodec=GSM-EFR/8000
////             03-27 09:33:47.850 23049-23049/? D/*********: audiocodec=AMR/8000
////             03-27 09:33:47.850 23049-23049/? D/*********: audiocodec=GSM/8000
////             03-27 09:33:47.850 23049-23049/? D/*********: audiocodec=PCMU/8000
////             03-27 09:33:47.850 23049-23049/? D/*********: audiocodec=PCMA/8000
//        }
//        textView.setText(a);
    }


    public void onButtonClicked(View view) {
        switch (view.getId()) {
            case R.id.usb:
//                audioTest.forceRouteHeadset(true);

                audioManager.setMode(AudioManager.MODE_NORMAL);
                audioManager.setSpeakerphoneOn(false);
                audioManager.setWiredHeadsetOn(true);
//                audioManager.setMode(AudioManager.MODE_NORMAL);
//                audioManager.setSpeakerphoneOn(true);

                break;
            case R.id.phone:
//                audioTest.forceRouteHeadset(false);

                audioManager.setWiredHeadsetOn(false);
                audioManager.setSpeakerphoneOn(true);
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
//                audioManager.setSpeakerphoneOn(false);
                break;
        }
        Log.d("*****", audioManager.isSpeakerphoneOn() ? "true" : "false");
    }
}
