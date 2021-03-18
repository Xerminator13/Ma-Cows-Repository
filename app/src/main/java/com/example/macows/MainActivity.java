package com.example.macows;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button goToMainMenu, resetScores, populate;
    private EditText scoreEntry;
    private TextView displayText;

    private static Context mContext;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    //The tutorial that I am watching said it is good to have a tag in the class for logging
    private static final String TAG = "MainActivity";
    private static final String FLAG = "HighScore";
    private static final String TEST = "FirstTest";

    private String file = "high_scores";

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

    public Player getPlayerFromList(int index) {
        return CommonUtils.playerList.get(index);

    }

    //**********************************************************************************************

    //This method is attached to the "Play normal mode" button
    //I need to save the following data:  Highest score, player name
    public void runInitSetup(Context context) {
        editor.putBoolean("savedData", true);
        editor.commit();

        CommonUtils.initPrefs(context);
        CommonUtils.initPlayers();

    }
    /*
    * Meant to run every other time the app is opened besides the first
     */
    private void reInitPlayers(Context context) {
        CommonUtils.initPrefs(context);
        CommonUtils.setAllPlayerVarsToPrefs(CommonUtils.playerList);

    }

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: started!");

        //------------------------------------------------------------------------------------------

        mContext = getApplicationContext();
        prefs = mContext.getSharedPreferences("MyPrefs", 0);// 0 is for private mode
        editor = prefs.edit();
        editor.apply();

        boolean createdOnce = prefs.getBoolean("savedData", false);
        if (createdOnce) {
            reInitPlayers(mContext);

        }
        //Call stuff that initializes with the app and should only run once in here
        else {
            runInitSetup(mContext);

        }

        //------------------------------------------------------------------------------------------

        displayText = findViewById(R.id.textView);
        displayText.setText(CommonUtils.formatPlayerPrefs(1));

        resetScores = findViewById(R.id.resetScores);
        goToMainMenu = findViewById(R.id.mainMenuTrans);
        populate = findViewById(R.id.populatePlayers);

        populate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommonUtils.playerList.get(0).setCowsInField(19109);
                CommonUtils.playerList.get(0).setCowsInBarn(767400);
                CommonUtils.playerList.get(0).setZombieCows(25);
                CommonUtils.playerList.get(0).setNumCowsLost(87864);

                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(0), 0);

                displayText.setText(CommonUtils.formatPlayerPrefs(1));

            }

        });
        resetScores.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //This is formatting listeners for later
                //scores = scoreEntry.getText().toString();
                System.out.println("Button pressed");

                //Deletes all preferences and player objects
                CommonUtils.resetAllSavedData();
                CommonUtils.playerList.clear();
                //Need to create new references and player objects
                CommonUtils.initPlayers();
                //Creates necessary prefs for one player
                CommonUtils.updateAllPlayerPreferences(CommonUtils.playerList);
                displayText.setText(CommonUtils.formatPlayerPrefs(1));
                prefs.getBoolean("savedData", false);

            }

        });
        goToMainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainMenu.class));

            }

        });

    }
    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", "This message is saved in the instance state");

    }
     */

}