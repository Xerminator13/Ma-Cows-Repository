package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    private Button player1, player2, player3, player4, player5, player6, back, endGameButton, allScoresButton, backButton1;
    private EditText name1, name2, name3, name4, name5, name6;
    private TextView appLabel, timerLabel;

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
        String temp1 = name1.getText().toString();
        String temp2 = name2.getText().toString();
        String temp3 = name3.getText().toString();
        String temp4 = name4.getText().toString();
        String temp5 = name5.getText().toString();
        String temp6 = name6.getText().toString();

        CommonUtils.playerList.get(0).setPlayerName(temp1);
        CommonUtils.playerList.get(1).setPlayerName(temp2);
        CommonUtils.playerList.get(2).setPlayerName(temp3);
        CommonUtils.playerList.get(3).setPlayerName(temp4);
        CommonUtils.playerList.get(4).setPlayerName(temp5);
        CommonUtils.playerList.get(5).setPlayerName(temp6);

    }
    private void goToScoreScreen(int player) {
        setAllPlayerNames();
        CommonUtils.setCurrentPlayer(player);
        CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(player - 1), player - 1);
        startActivity(new Intent(MainMenu.this, ScoreScreen.class));

    }
    private void initAllElements() {
        backButton1 = findViewById(R.id.backButton1);
        endGameButton = findViewById(R.id.endGameButton);
        allScoresButton = findViewById(R.id.allScoresButton);

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

    }

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d("MainMenu", "onCreate: started!");

        //------------------------------------------------------------------------------------------

        appLabel = findViewById(R.id.appLabelView);
        timerLabel = findViewById(R.id.timerLabel);
        timerLabel.setVisibility(View.INVISIBLE);

        //------------------------------------------------------------------------------------------

        initAllElements();
        populateTextEntries();

        //------------------------------------------------------------------------------------------

        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToScoreScreen(1);

            }

        });
        player2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToScoreScreen(2);

            }

        });
        player3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToScoreScreen(3);

            }

        });
        player4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToScoreScreen(4);

            }

        });
        player5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToScoreScreen(5);

            }

        });
        player6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToScoreScreen(6);

            }

        });

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

                startActivity(new Intent(MainMenu.this, EndGameScreen.class));

            }

        });
        allScoresButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, AllScoresScreen.class));

            }

        });

    }

}
