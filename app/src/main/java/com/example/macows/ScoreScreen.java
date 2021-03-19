package com.example.macows;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScoreScreen extends AppCompatActivity {
    private Button addToField, addToBarn, roadKill, resurrectZombieCows, church, police, cemetery, fastFood, stockTrailer, funeralHome, hospital, school, back;
    private EditText scoreEntry;
    private TextView playerNameLabel, numCowsInField, numCowsInBarn, numZombieCows, herdManagement, animalHusbandry, errorLabel;

    //----------------------------------------------------------------------------------------------

    private void setAllTextViewsToPlayerData() {
        //Type code here

    }
    private void initAllElements() {
        //Type code here

    }

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d("MainMenu", "onCreate: started!");

        //------------------------------------------------------------------------------------------
        
        initAllElements();
        setAllTextViewsToPlayerData();

    }

}
