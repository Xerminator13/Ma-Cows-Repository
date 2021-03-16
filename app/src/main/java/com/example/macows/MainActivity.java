package com.example.macows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    //The tutorial that I am watching said it is good to have a tag in the class for logging
    private static final String TAG = "MainActivity";

    /*
    *  It is very important to me that I have a dark mode option for this application.
    *  Especially if we are riding in the car at night, it needs to be easy on the eyes.
    *  To that end, everything graphically must have the ability to be changed in code.
    *  This means everything from screen backgrounds to text and buttons.  Everything needs to change
    *       with the flick of a switch in the app.  This should not be too hard as I only need to render
    *       one image, however this means that I need to have all of this stuff implemented in code.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started!");

    }
}