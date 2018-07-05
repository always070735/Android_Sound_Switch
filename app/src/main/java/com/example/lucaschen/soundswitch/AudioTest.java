package com.example.lucaschen.soundswitch;

import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by LucasChen on 2018/3/30.
 */

public class AudioTest {
    private final String TAG = "AudioTest";
    // Constants copied from AudioSystem
    private static final int DEVICE_IN_WIRED_HEADSET = 0x400000;
    private static final int DEVICE_OUT_EARPIECE = 0x1;
    private static final int DEVICE_OUT_WIRED_HEADSET = 0x4;
    private static final int DEVICE_STATE_UNAVAILABLE = 0;
    private static final int DEVICE_STATE_AVAILABLE = 1;

    /* force route function through AudioSystem */
    private void setDeviceConnectionState(final int device, final int state, final String address) {
        try {
            Class<?> audioSystem = Class.forName("android.media.AudioSystem");
            Method setDeviceConnectionState = audioSystem.getMethod("setDeviceConnectionState", int.class, int.class, String.class);

            setDeviceConnectionState.invoke(audioSystem, device, state, address);
        } catch (Exception e) {
            Log.e(TAG, "setDeviceConnectionState failed: " + e);
        }


    }

    public void forceRouteHeadset(boolean enable) {
        if (enable) {
            Log.i(TAG, "force route to Headset");
            setDeviceConnectionState(DEVICE_IN_WIRED_HEADSET, DEVICE_STATE_AVAILABLE, "");
            setDeviceConnectionState(DEVICE_OUT_WIRED_HEADSET, DEVICE_STATE_AVAILABLE, "");
        } else {
            Log.i(TAG, "force route to Earpirce");
            setDeviceConnectionState(DEVICE_IN_WIRED_HEADSET, DEVICE_STATE_UNAVAILABLE, "");
            setDeviceConnectionState(DEVICE_OUT_WIRED_HEADSET, DEVICE_STATE_UNAVAILABLE, "");
            setDeviceConnectionState(DEVICE_OUT_EARPIECE, DEVICE_STATE_AVAILABLE, "");
        }
    }
}
