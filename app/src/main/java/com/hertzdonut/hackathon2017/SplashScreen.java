package com.hertzdonut.hackathon2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by junshuaifeng on 4/1/17.
 */

public class SplashScreen extends AppCompatActivity {

    private GifImageView gifImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        InputStream inputStream = null;
        try {
            inputStream = getAssets().open("moving_car.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gifImageView = (GifImageView)findViewById(R.id.car_gif);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Thread myThread = new Thread() {
            @Override
            public void run () {
                try {
                    sleep(3000);

                    Intent startMainScreen = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(startMainScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
