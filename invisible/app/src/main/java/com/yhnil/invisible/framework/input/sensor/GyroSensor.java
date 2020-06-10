package com.yhnil.invisible.framework.input.sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.yhnil.invisible.framework.main.UiBridge;

public class GyroSensor {
    private static final double NS2S = 1.0 / 1_000_000_000.0;
    private static final double RAD_TO_DEGREE = 180.0 / Math.PI;

    private static GyroSensor singleton;
    private SensorManager sm;
    private Sensor gyro;

    private double timestamp;
    private double roll, pitch, yaw;

    public static boolean isCreated() {
        return singleton != null;
    }
    public static GyroSensor get() {
        if (singleton == null) {
            singleton = new GyroSensor();
        }
        return singleton;
    }

    private GyroSensor() {
        sm = (SensorManager) UiBridge.getActivity().getSystemService(Context.SENSOR_SERVICE);
        gyro = sm.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        register();
    }

    public void reset() {
        roll = 0;
        pitch = 0;
        yaw = 0;
    }

    public float getRollDegree() {
        return (float) (roll * RAD_TO_DEGREE);
    }

    public float getPitchDegree() {
        return (float) (pitch * RAD_TO_DEGREE);
    }

    public float getYawDegree() {
        return (float) (yaw * RAD_TO_DEGREE);
    }
    public void destroy() {
        unregister();
        gyro = null;
        sm = null;
        singleton = null;
    }

    public void register() {
        sm.registerListener(listener, gyro, SensorManager.SENSOR_DELAY_GAME);
    }
    public void unregister() {
        sm.unregisterListener(listener);
    }

    private void accumulateSensorValues(SensorEvent event) {
        if (timestamp == 0) {
            timestamp = event.timestamp;
            return;
        }
        double dt = (event.timestamp - timestamp) * NS2S;
        roll += event.values[0] * dt;
        pitch += event.values[1] * dt;
        yaw += event.values[2] * dt;


        timestamp = event.timestamp;
    }

    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            accumulateSensorValues(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
