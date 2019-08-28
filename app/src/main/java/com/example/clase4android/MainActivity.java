package com.example.clase4android;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textoActivado;
    private TextView textoDesactivado;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoActivado = (TextView) findViewById(R.id.lblAdios);
        textoDesactivado = (TextView) findViewById(R.id.lblHola);

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensor.equals(null)){
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if (sensorEvent.values[0] < sensor.getMaximumRange()){
                    textoActivado.setVisibility(View.VISIBLE);
                    textoDesactivado.setVisibility(View.INVISIBLE);
                }else{
                    textoActivado.setVisibility(View.INVISIBLE);
                    textoDesactivado.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        start();
    }

    public void start(){
        sensorManager.registerListener(sensorEventListener, sensor, 2000*1000);
    }
    public void stop(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}
