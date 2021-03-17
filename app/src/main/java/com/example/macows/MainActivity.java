package com.example.macows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button saveScore, searchScore;
    private EditText scoreEntry;
    private TextView displayText;

    private static ArrayList<Player> playerList  = new ArrayList<Player>();
    private static ArrayList<EconomyPlayer> econPlayerList = new ArrayList<EconomyPlayer>();
    //For using shared preferences >>
    //  https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
    private static Context mContext;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    //The tutorial that I am watching said it is good to have a tag in the class for logging
    private static final String TAG = "MainActivity";
    private static final String FLAG = "HighScore";
    private static final String TEST = "FirstTest";

    private String file = "high_scores";
    private String scores;

    private static boolean isEconMode = false;

    /*
    *  It is very important to me that I have a dark mode option for this application.
    *  Especially if we are riding in the car at night, it needs to be easy on the eyes.
    *  To that end, everything graphically must have the ability to be changed in code.
    *  This means everything from screen backgrounds to text and buttons.  Everything needs to change
    *       with the flick of a switch in the app.  This should not be too hard as I only need to render
    *       one image, however this means that I need to have all of this stuff implemented in code.
     */
    public MainActivity() {


    }

    //This method is attached to the "Play normal mode" button
    //I need to save the following data:  Highest score, player name
    private static void initPlayers() {
        for (int a = 0; a < 6; a++) {
            playerList.add(new Player("Player " + (a + 1)));

        }

    }
    //This method is attached to the "Play economy mode" button
    //I need to save the following data:  Highest score, player name, most funds
    private static void initEconPlayers() {
        for (int a = 0; a < 6; a++) {
            econPlayerList.add(new EconomyPlayer("Player " + (a + 1)));

        }

    }
    private static String getAllHighScores(File file) throws IOException {
        String allHighScores = "";
        int numLines = 0;

        return allHighScores + "\nTotal number of Lines was: " + numLines;

    }
    private static void addNewOrgHighScore(Player player, File file) throws IOException {


    }

    //**********************************************************************************************
    //These methods are how I save data

    private void updateAllPlayerPreferences() {
        int a = 1;

        for (Player p : playerList) {
            editor.putInt("p" + a + "InField", p.getCowsInField());
            editor.putInt("p" + a + "InBarn", p.getCowsInBarn());
            editor.putInt("p" + a + "Zombies", p.getZombieCows());
            editor.putInt("p" + a + "NumKilled", p.getNumCowsKilled());
            editor.putInt("p" + a + "NumLost", p.getNumCowsLost());
            editor.putInt("p" + a + "NumGained", p.getNumCowsGained());
            editor.putInt("p" + a + "ZombiesGained", p.getNumZombieCowsGained());

            a++;

        }

        editor.commit();

    }
    private void updatePlayerPrefs(Player p) {
        int a = playerList.indexOf(p) - 1;
        editor.putInt("p" + a + "InField", p.getCowsInField());
        editor.putInt("p" + a + "InBarn", p.getCowsInBarn());
        editor.putInt("p" + a + "Zombies", p.getZombieCows());
        editor.putInt("p" + a + "NumKilled", p.getNumCowsKilled());
        editor.putInt("p" + a + "NumLost", p.getNumCowsLost());
        editor.putInt("p" + a + "NumGained", p.getNumCowsGained());
        editor.putInt("p" + a + "ZombiesGained", p.getNumZombieCowsGained());

        editor.commit();

    }
    private void reInitPlayers() {
        int a = 1;

        for (Player p : playerList) {
            p.setCowsInField(prefs.getInt("p" + a + "InField", 0));
            p.setCowsInBarn(prefs.getInt("p" + a + "InBarn", 0));
            p.setZombieCows(prefs.getInt("p" + a + "Zombies", 0));
            p.setNumCowsKilled(prefs.getInt("p" + a + "NumKilled", 0));
            p.setNumCowsLost(prefs.getInt("p" + a + "NumLost", 0));
            p.setNumCowsGained(prefs.getInt("p" + a + "NumGained", 0));
            p.setNumZombieCowsGained(prefs.getInt("p" + a + "ZombiesGained", 0));

            a++;

        }

    }
    private String formatPlayerPrefs(int a) {
        String formattedString = "";

        formattedString += "Cows in field: " + prefs.getInt("p" + a + "InField", 0);
        formattedString += "\nCows in barn: " + prefs.getInt("p" + a + "InBarn", 0);
        formattedString += "\nZombie Cows: " + prefs.getInt("p" + a + "Zombies", 0);
        formattedString += "\nNumber of cows this player killed: " + prefs.getInt("p" + a + "NumKilled", 0);
        formattedString += "\nNumber of cows this player has lost: " + prefs.getInt("p" + a + "NumLost", 0);
        formattedString += "\nNumber of cows this player has gained: " + prefs.getInt("p" + a + "NumGained", 0);
        formattedString += "\nNumber of zombie cows this player has gained: " + prefs.getInt("p" + a + "ZombiesGained", 0);

        return formattedString;

    }
    private void removeAllSavedData() {
        int a = 1;

        for (Player p : playerList) {
            editor.remove("p" + a + "InField");
            editor.remove("p" + a + "InBarn");
            editor.remove("p" + a + "Zombies");
            editor.remove("p" + a + "NumKilled");
            editor.remove("p" + a + "NumLost");
            editor.remove("p" + a + "NumGained");
            editor.remove("p" + a + "ZombiesGained");

            a++;

        }

        editor.remove("savedData");
        editor.remove("player1");
        editor.remove("player2");
        editor.remove("player3");
        editor.remove("player4");
        editor.remove("player5");
        editor.remove("player6");

        editor.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mContext = getApplicationContext();
        prefs = mContext.getSharedPreferences("MyPrefs", 0);// 0 is for private mode
        editor = prefs.edit();

        boolean createdOnce = prefs.getBoolean("savedData", false);
        if (createdOnce) {
            reInitPlayers();

        }
        //------------------------------------------------------------------------------------------

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: started!");

        //------------------------------------------------------------------------------------------

        //Testing player methods
        displayText = findViewById(R.id.textView);

        //Call stuff that initiatlizes with the app and should only run once in here
        if (!createdOnce) {
            editor.putBoolean("savedData", true);
            editor.putInt("player1", 1);
            editor.putInt("player2", 2);
            editor.putInt("player3", 3);
            editor.putInt("player4", 4);
            editor.putInt("player5", 5);
            editor.putInt("player6", 6);
            initPlayers();

            for (int a = 0; a < playerList.size(); a++) {
                playerList.get(a).addCowsToField((int)(1090901*Math.random()));
                updatePlayerPrefs(playerList.get(a));

            }

        }

        displayText.setText(formatPlayerPrefs(prefs.getInt("player1", 1)));
/*
        //scoreEntry = findViewById(R.id.insert_id_here);
        scoreEntry.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //This is formatting listeners for later
                //scores = scoreEntry.getText().toString();
                try {
                    Player.getAllHighScores(classContext);

                } catch (IOException e) {
                    e.printStackTrace();

                }

            }

        });

 */

    }
}