package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DescoreScreen extends AppCompatActivity {
    private Button player1, player2, player3, player4, player5, player6;
    private Button back, descore;
    private TextView p1Field, p1Barn, p2Field, p2Barn, p3Field, p3Barn, p4Field, p4Barn, p5Field, p5Barn, p6Field, p6Barn;
    private TextView label;

    int currentPlayer = CommonUtils.getCurrentPlayer(); //This returns the index of the player + 1
    int playerTakenFrom = -1;
    int descoreMethod = CommonUtils.getDescoreMethod();

    //----------------------------------------------------------------------------------------------

    private void initAllElements() {
        //Init UI elements here
        descore = findViewById(R.id.confirmationButton);

    }
    private void populateTextViews() {
        //Populate text views here

    }
    private boolean descorePlayer(int player) {
        int a = player - 1;

        if (player == -1) {
            //Set the text of the confirmation label to "No player has been selected yet!"
            return false;

        }
        else {
            //Implement descoring methods here

        }
        return true;

    }

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.descore_screen);
        Log.d("DescoreScreen", "onCreate: started!");

        //******************************************************************************************

        initAllElements();
        populateTextViews();

        //------------------------------------------------------------------------------------------

        descore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean shouldTransition = descorePlayer(playerTakenFrom);

                //If a player was selected, then that player will be descored
                //  and the screen will transition back with an error message confirming the descore
                if (shouldTransition) {
                    //Set CommonUtils error preference to hold a message to be transferred back
                    //  to the ScoreScreen error label
                    startActivity(new Intent(DescoreScreen.this, ScoreScreen.class));

                }

            }

        });

    }

}
