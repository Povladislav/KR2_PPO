package com.example.ppo_kr2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ppo_kr2.SQLite.UsersDbManager;

public class GameActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textViewScore;
    private TextView textViewPlayer;
    private ImageView imageViewTarget;
    private UsersDbManager usersDbManager;
    int score;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        imageView = findViewById(R.id.imageView);
        textViewScore = findViewById(R.id.textViewScore);
        textViewPlayer = findViewById(R.id.textViewPlayer);
        imageViewTarget = findViewById(R.id.imageViewTarget);
        textViewPlayer.setText(LoginActivity.user.getUsername() + ":");
        score = LoginActivity.user.getScore();
        textViewScore.setText(Integer.toString(score));
        usersDbManager = new UsersDbManager(this);
        usersDbManager.openDb();
        createSensors();
    }

    public void createSensors() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                float[] rotationMatrix = new float[16];
                SensorManager.getRotationMatrixFromVector(
                        rotationMatrix, event.values);
                float[] remappedRotationMatrix = new float[16];
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y,
                        SensorManager.AXIS_Z,
                        remappedRotationMatrix);
                float[] orientations = new float[3];
                SensorManager.getOrientation(remappedRotationMatrix, orientations);
                for(int i = 0; i < 3; i++) orientations[i] = (float)(Math.toDegrees(orientations[i]));

                int xPosition = ((int)orientations[1])*20;
                int yPosition = ((int)orientations[2]+90)*10;

                imageView.setX(360+xPosition);
                imageView.setY(yPosition);

                int imageViewX = (int)imageView.getX();
                int imageViewY = (int)imageView.getY();
                int imageViewTargetX = (int)imageViewTarget.getX();
                int imageViewTargetY = (int)imageViewTarget.getY();

                if(imageViewX >= imageViewTargetX-30 && imageViewX <= imageViewTargetX+30 &&
                        imageViewY >= imageViewTargetY+20 && imageViewY <= imageViewTargetY+100)
                {
                    isTarget();
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
    }

    @SuppressLint("SetTextI18n")
    public void isTarget() {
        Toast toast = Toast.makeText(getApplicationContext(),"Great!", Toast.LENGTH_SHORT);
        toast.show();
        if(imageViewTarget.getY()==10) {
            imageViewTarget.setY(925); }
        else {
            imageViewTarget.setY(10);
        }
        score++;
        textViewScore.setText(Integer.toString(score));
    }

    public void buttonBackClick(View view) {
        usersDbManager.updateDb(LoginActivity.user.getId(), LoginActivity.user.getUsername(), score);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        usersDbManager.updateDb(LoginActivity.user.getId(),LoginActivity.user.getUsername(), score);
        usersDbManager.closeDb();
    }
}