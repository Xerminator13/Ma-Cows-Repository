package com.example.macows;

import androidx.appcompat.app.AppCompatActivity;

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
    private SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPrefs", 0);// 0 is for private mode
    private SharedPreferences.Editor editor = prefs.edit();

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
    /*
    * Ya so this is not working at all and is complete garbage.  I need to fix it later
    * The issue is that I have no starting data in the file initially and so it is confused on how to write to the file
    * I am as confused as the program is... However, I am getting some results, which is good.
     */
    private static void addNewOrgHighScore(Player player, File file) throws IOException {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Writes a .txt file for saving information to device memory such as:
        //  High scores, past player names, and other data.
        File container = new File(MainActivity.this.getFilesDir(), "text");
        Log.d(FLAG, MainActivity.this.getFilesDir().toString());
        if (!container.exists()) {
            container.mkdir();

        }
        File highScores = new File(container, file);
        if (!highScores.exists()) {
            try {
                highScores.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        Log.d(FLAG, highScores.getAbsolutePath());

        //------------------------------------------------------------------------------------------

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started!");

        //------------------------------------------------------------------------------------------

        //Testing player methods
        initPlayers();

        try {
            for (int a = 0; a < playerList.size(); a++) {
                playerList.get(a).addCowsToField((int)(1090901*Math.random()));
                addNewOrgHighScore(playerList.get(a), highScores);

            }

            Log.d(TEST, "Succeeded to add");
        } catch (IOException e) {
            Log.d(TEST, "Failed to add");
        }

        try {
            displayText = findViewById(R.id.textView);
            displayText.setText(getAllHighScores(highScores));

            Log.d(TEST, "Succeeded to read");
        } catch (IOException e) {
            Log.d(TEST, "Failed to read");
        }
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