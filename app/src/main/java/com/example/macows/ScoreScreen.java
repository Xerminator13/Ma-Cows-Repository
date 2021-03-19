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
    private Button addToField, addToBarn, roadKill, resurrectZombieCows, church, police, cemetery, fastFood, stockTrailer, funeralHome, hospital, school, back;
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
        back = findViewById(R.id.scoreScreenToMainMenu);

        //Temporary arrangement
        numCowsInField = findViewById(R.id.textView4);

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

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ScoreScreen.this, MainMenu.class));

            }

        });

    }

}
