package com.example.macows;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class DescoreScreen extends AppCompatActivity {


    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descore_screen);
        Log.d("DescoreScreen", "onCreate: started!");

    }

}
