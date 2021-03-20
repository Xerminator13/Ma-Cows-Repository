package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreScreen extends AppCompatActivity {
    private Button addToField, addToBarn, takeFromBarn, roadKill, resurrectZombieCows, church, police, cemetery, fastFood, stockTrailer, funeralHome, hospital, school, back;
    private EditText scoreEntry;
    private TextView playerNameLabel, numCowsInField, numCowsInBarn, numZombieCows, herdManagement, animalHusbandry, errorLabel;

    //----------------------------------------------------------------------------------------------

    private void setAllTextViewsToPlayerData(int a) {
        int b = a - 1;
        playerNameLabel.setText(CommonUtils.playerList.get(b).getName());
        numCowsInField.setText(CommonUtils.formatPlayerPrefs(CommonUtils.getCurrentPlayer()));

    }
    private void initAllElements() {
        playerNameLabel = findViewById(R.id.playerNameLabel);
        scoreEntry = findViewById(R.id.scoreEntry);
        addToField = findViewById(R.id.addToField);
        addToBarn = findViewById(R.id.addToBarn);
        takeFromBarn = findViewById(R.id.takeFromBarn);

        errorLabel = findViewById(R.id.errorLabel);
        back = findViewById(R.id.scoreScreenToMainMenu);

        //Temporary arrangement
        numCowsInField = findViewById(R.id.errorLabel);

    }
    private boolean isInteger(String a) {
        if (a != null) {
            try {
                Integer.parseInt(a);
                return true;

            } catch (Exception e) {
                return false;

            }

        }
        else {
            return false;

        }

    }

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);
        Log.d("ScoreScreen", "onCreate: started!");

        //------------------------------------------------------------------------------------------
        
        initAllElements();
        setAllTextViewsToPlayerData(CommonUtils.getCurrentPlayer());

        //------------------------------------------------------------------------------------------

        addToField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input1 = scoreEntry.getText().toString();
                if (isInteger(input1)) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1).addCowsToField(input2);
                        CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1), CommonUtils.getCurrentPlayer() - 1);
                        errorLabel.setText(CommonUtils.formatPlayerPrefs(CommonUtils.getCurrentPlayer()));

                    }
                    else {
                        errorLabel.setText("You cannot input a negative number of cows");

                    }

                }
                else {
                    errorLabel.setText("You did not input a whole number of cows, please try again.");

                }


            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ScoreScreen.this, MainMenu.class));

            }

        });

    }

}
