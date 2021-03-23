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
    private TextView label, errorLabeling;

    int currentPlayer = CommonUtils.getCurrentPlayer(); //This returns the index of the player + 1
    int numCowsKilledBefore = CommonUtils.playerList.get(currentPlayer - 1).getNumCowsKilled();
    int numCowsKilledAfter = numCowsKilledBefore;
    int playerTakenFrom = -1; //This represents the index of the player + 1
    int descoreMethod = CommonUtils.getDescoreMethod();

    //----------------------------------------------------------------------------------------------

    private void initAllElements() {
        //Init UI elements here
        //Buttons
        descore = findViewById(R.id.confirmationButton);
        back = findViewById(R.id.cancelButton);

        //TextViews
        errorLabeling = findViewById(R.id.errorLabeling);

    }
    private void populateTextViews() {
        //Populate text views here
        errorLabeling.setText("No player has been selected yet!!");

    }
    private boolean descorePlayer(int player) {
        int a = player - 1;

        if (player == -1) {
            errorLabeling.setText("No player has been selected yet!!");
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
                //It is safe to use playerTakenFrom as an index indicator because shouldTransition
                //  will stop the class from using it as one if the variable = -1.
                boolean shouldTransition = descorePlayer(playerTakenFrom);

                //If a player was selected, then that player will be descored
                //  and the screen will transition back with an error message confirming the descore
                if (shouldTransition) {
                    numCowsKilledAfter = CommonUtils.playerList.get(currentPlayer - 1).getNumCowsKilled() - numCowsKilledBefore;
                    String msg = CommonUtils.playerList.get(currentPlayer - 1).getName()
                            + " killed " + numCowsKilledAfter + " of " + CommonUtils.playerList.get(playerTakenFrom - 1).getName()
                            + "'s cows.";
                    CommonUtils.setDescoreErrorMessage(msg);
                    startActivity(new Intent(DescoreScreen.this, ScoreScreen.class));

                }

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.setDescoreErrorMessage("No cows were killed.");
                startActivity(new Intent(DescoreScreen.this, ScoreScreen.class));

            }

        });

    }

}
