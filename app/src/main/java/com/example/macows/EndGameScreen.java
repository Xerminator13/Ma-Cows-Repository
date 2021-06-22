package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EndGameScreen extends AppCompatActivity {
    private TextView winningPlayerLabel;
    private Button restartGame, continueGame, backButton;

    //----------------------------------------------------------------------------------------------

    private void initAllElements() {
        backButton = findViewById(R.id.backButton);

    }
    private void findWinningPlayer() {


    }
    private void populateAllElements() {


    }

    //**********************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_game_screen);
        Log.d("EndGameScreen", "onCreate: started!");

        //==========================================================================================

        initAllElements();
        findWinningPlayer();
        populateAllElements();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(EndGameScreen.this, MainMenu.class));

            }

        });

    }

}
