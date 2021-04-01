package com.example.macows;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainMenu extends AppCompatActivity {
    private Button player1, player2, player3, player4, player5, player6, back, endGameButton, allScoresButton, backButton1;
    private EditText name1, name2, name3, name4, name5, name6;
    private TextView appLabel, timerLabel;

    private ArrayList<Button> playerButtonList = new ArrayList<Button>();

    private int switcher = 1;

    //**********************************************************************************************

    private void populateTextEntries() {
        name1.setText(CommonUtils.playerList.get(0).getName());
        name2.setText(CommonUtils.playerList.get(1).getName());
        name3.setText(CommonUtils.playerList.get(2).getName());
        name4.setText(CommonUtils.playerList.get(3).getName());
        name5.setText(CommonUtils.playerList.get(4).getName());
        name6.setText(CommonUtils.playerList.get(5).getName());

    }
    /*
    * Gets text from EditText views and assigns it to the player names variables
     */
    private void setAllPlayerNames() {
        CommonUtils.playerList.get(0).setPlayerName(name1.getText().toString());
        CommonUtils.playerList.get(1).setPlayerName(name2.getText().toString());
        CommonUtils.playerList.get(2).setPlayerName(name3.getText().toString());
        CommonUtils.playerList.get(3).setPlayerName(name4.getText().toString());
        CommonUtils.playerList.get(4).setPlayerName(name5.getText().toString());
        CommonUtils.playerList.get(5).setPlayerName(name6.getText().toString());

        CommonUtils.updateAllPlayerPreferences(CommonUtils.playerList);

    }
    private void goToScoreScreen(int player) {
        CommonUtils.setCurrentPlayer(player);
        CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(player - 1), player - 1);
        startActivity(new Intent(MainMenu.this, ScoreScreen.class));

    }
    private void initAllElements() {
        backButton1 = findViewById(R.id.backButton1);
        endGameButton = findViewById(R.id.endGameButton);
        allScoresButton = findViewById(R.id.allScoresButton);
        appLabel = findViewById(R.id.appLabelView);
        timerLabel = findViewById(R.id.timerLabel);

        name1 = findViewById(R.id.player1NameInput);
        name2 = findViewById(R.id.player2NameInput);
        name3 = findViewById(R.id.player3NameInput);
        name4 = findViewById(R.id.player4NameInput);
        name5 = findViewById(R.id.player5NameInput);
        name6 = findViewById(R.id.player6NameInput);

        player1 = findViewById(R.id.p1Button);
        player2 = findViewById(R.id.p2Button);
        player3 = findViewById(R.id.p3Button);
        player4 = findViewById(R.id.p4Button);
        player5 = findViewById(R.id.p5Button);
        player6 = findViewById(R.id.p6Button);

        playerButtonList.add(player1);
        playerButtonList.add(player2);
        playerButtonList.add(player3);
        playerButtonList.add(player4);
        playerButtonList.add(player5);
        playerButtonList.add(player6);

    }

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d("MainMenu", "onCreate: started!");

        //------------------------------------------------------------------------------------------

        initAllElements();
        populateTextEntries();
        timerLabel.setVisibility(View.INVISIBLE);

        //------------------------------------------------------------------------------------------

        for (int a = 0; a < 6; a++) {
            int b = a + 1;
            playerButtonList.get(a).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setAllPlayerNames();
                    goToScoreScreen(b);
                    //For some reason it does not like using "a" because of the way it is defined,
                    //  something to do with back scope.  Instead, I have to define and use "b"...

                }

            });

        }

        //------------------------------------------------------------------------------------------

        backButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setAllPlayerNames();
                CommonUtils.updateAllPlayerPreferences(CommonUtils.playerList);

                //Starts the MainActivity;  A transition between the two activities
                startActivity(new Intent(MainMenu.this, MainActivity.class));

            }

        });
        endGameButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (switcher == 1) {
                    timerLabel.setVisibility(View.VISIBLE);
                    switcher *= -1;

                }
                else {
                    timerLabel.setVisibility(View.INVISIBLE);
                    switcher *= -1;

                }

                setAllPlayerNames();
                startActivity(new Intent(MainMenu.this, EndGameScreen.class));

            }

        });
        allScoresButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setAllPlayerNames();
                startActivity(new Intent(MainMenu.this, AllScoresScreen.class));

            }

        });

    }
    @Override
    protected void onStop() {
        super.onStop();

    }

}
