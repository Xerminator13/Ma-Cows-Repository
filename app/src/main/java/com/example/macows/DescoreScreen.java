package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DescoreScreen extends AppCompatActivity {
    private ArrayList<TextView> textViewList = new ArrayList<TextView>();
    private ArrayList<Button> buttonList = new ArrayList<Button>();
    int[] players = new int[5]; //holds as indices in CommonUtils.playerList ArrayList

    private Button player1, player2, player3, player4, player5;
    private Button back, descore;
    private TextView p1Field, p1Barn, p2Field, p2Barn, p3Field, p3Barn, p4Field, p4Barn, p5Field, p5Barn;
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
        player1 = findViewById(R.id.player1ConfirmButton);
        player2 = findViewById(R.id.player2ConfirmButton);
        player3 = findViewById(R.id.player3ConfirmButton);
        player4 = findViewById(R.id.player4ConfirmButton);
        player5 = findViewById(R.id.player5ConfirmButton);

        buttonList.add(player1);
        buttonList.add(player2);
        buttonList.add(player3);
        buttonList.add(player4);
        buttonList.add(player5);

        //TextViews
        errorLabeling = findViewById(R.id.errorLabeling);
        p1Field = findViewById(R.id.player1FieldView);
        p1Barn = findViewById(R.id.player1BarnView);
        p2Field = findViewById(R.id.player2FieldView);
        p2Barn = findViewById(R.id.player2BarnView);
        p3Field = findViewById(R.id.player3FieldView);
        p3Barn = findViewById(R.id.player3BarnView);
        p4Field = findViewById(R.id.player4FieldView);
        p4Barn = findViewById(R.id.player4BarnView);
        p5Field = findViewById(R.id.player5FieldView);
        p5Barn = findViewById(R.id.player5BarnView);

        textViewList.add(p1Field);
        textViewList.add(p1Barn);
        textViewList.add(p2Field);
        textViewList.add(p2Barn);
        textViewList.add(p3Field);
        textViewList.add(p3Barn);
        textViewList.add(p4Field);
        textViewList.add(p4Barn);
        textViewList.add(p5Field);
        textViewList.add(p5Barn);


    }
    private void populateTextViews() {
        //Populate text views here
        errorLabeling.setText("No player has been selected yet!!");

        //Buttons;  Extra code for naming the buttons with player names
        int b = 0;
        for (int a = 0; a < 6; a++) {
            if (a != currentPlayer - 1) {
                players[b] = a;
                b++;

            }
            else {
                Log.d("PopulateList", "Skipped index" + b);

            }

        }

        for (int a : players) {
            System.out.println(a);

        }

        player1.setText(CommonUtils.playerList.get(players[0]).getName());
        player2.setText(CommonUtils.playerList.get(players[1]).getName());
        player3.setText(CommonUtils.playerList.get(players[2]).getName());
        player4.setText(CommonUtils.playerList.get(players[3]).getName());
        player5.setText(CommonUtils.playerList.get(players[4]).getName());

        b = 0;
        for (int a = 0; a < textViewList.size(); a += 2) {
            textViewList.get(a).setText("Cows in field:  " + CommonUtils.playerList.get(b).getCowsInField());
            textViewList.get(a + 1).setText("Cows in barn:  " + CommonUtils.playerList.get(b).getCowsInBarn());
            b++;

        }

    }
    private boolean descorePlayer(int player) { //player == playerTakenFrom > represents index of player + 1
        int a = player - 1;

        if (player == -1) {
            errorLabeling.setText("No player has been selected yet!!");
            return false;

        }
        else {
            if (descoreMethod == 1) {
                //Cemetery
                CommonUtils.playerList.get(currentPlayer - 1).sawCemetery(CommonUtils.playerList.get(a));
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(a), a);

            }
            else if (descoreMethod == 2) {
                //Fast Food
                CommonUtils.playerList.get(currentPlayer - 1).sawFastFood(CommonUtils.playerList.get(a));
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(a), a);

            }
            else if (descoreMethod == 3) {
                //Police
                CommonUtils.playerList.get(currentPlayer - 1).sawPolice(CommonUtils.playerList.get(a));
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(a), a);

            }
            else if (descoreMethod == 4) {
                //Stock Trailer
                CommonUtils.playerList.get(currentPlayer - 1).sawStockTrailer(CommonUtils.playerList.get(a));
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(a), a);

            }
            else if (descoreMethod == 5) {
                //funeral Home
                CommonUtils.playerList.get(currentPlayer - 1).sawFuneralHome(CommonUtils.playerList.get(a));
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(a), a);

            }

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

        for (int a = 0; a < buttonList.size(); a++) {
            int zA = a;
            buttonList.get(a).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    playerTakenFrom = players[zA] + 1;
                    errorLabeling.setText("You are taking cows from " + CommonUtils.playerList.get(players[zA]).getName());

                }

            });

        }

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
