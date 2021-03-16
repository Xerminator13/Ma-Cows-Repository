package com.example.macows;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<Player> playerList  = new ArrayList<Player>();
    private static ArrayList<EconomyPlayer> econPlayerList = new ArrayList<EconomyPlayer>();

    //The tutorial that I am watching said it is good to have a tag in the class for logging
    private static final String TAG = "MainActivity";
    private static final String FLAG = "HighScore";
    private static boolean isEconMode = false;

    /*
    *  It is very important to me that I have a dark mode option for this application.
    *  Especially if we are riding in the car at night, it needs to be easy on the eyes.
    *  To that end, everything graphically must have the ability to be changed in code.
    *  This means everything from screen backgrounds to text and buttons.  Everything needs to change
    *       with the flick of a switch in the app.  This should not be too hard as I only need to render
    *       one image, however this means that I need to have all of this stuff implemented in code.
     */

    //This method is attached to the "Play normal mode" button
    private static void initPlayers() {
        for (int a = 0; a < 6; a++) {
            playerList.add(new Player("Player " + (a + 1)));

        }

    }
    //This method is attached to the "Play economy mode" button
    private static void initEconPlayers() {
        for (int a = 0; a < 6; a++) {
            econPlayerList.add(new EconomyPlayer("Player " + (a + 1)));

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started!");

    }
}