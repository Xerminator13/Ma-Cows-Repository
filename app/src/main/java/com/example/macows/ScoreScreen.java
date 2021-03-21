package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.DisplayMetrics;
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
    private View scoreScreenLayout;

    private DisplayMetrics displayMetrics;

    //----------------------------------------------------------------------------------------------

    private void setAllTextViewsToPlayerData(int a) {
        int b = a - 1;
        playerNameLabel.setText(CommonUtils.playerList.get(b).getName());
        numCowsInField.setText("Cows In Your Field:  " + CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1).getCowsInField());
        errorLabel.setText(CommonUtils.formatPlayerPrefs(CommonUtils.getCurrentPlayer()));

    }
    private void initAllElements() {
        //TextViews
        playerNameLabel = findViewById(R.id.playerNameLabel);
        errorLabel = findViewById(R.id.errorLabel);
        numCowsInField = findViewById(R.id.numCowsInField);

        //EditText views
        scoreEntry = findViewById(R.id.scoreEntry);

        //Button views
        addToField = findViewById(R.id.addToField);
        addToBarn = findViewById(R.id.addToBarn);
        takeFromBarn = findViewById(R.id.takeFromBarn);
        school = findViewById(R.id.schoolButton);
        roadKill = findViewById(R.id.roadKillButton);
        hospital = findViewById(R.id.hospitalButton);
        church = findViewById(R.id.churchButton);
        cemetery = findViewById(R.id.cemeteryButton);
        fastFood = findViewById(R.id.fastFoodButton);
        police = findViewById(R.id.policeButton);
        stockTrailer = findViewById(R.id.stockTrailerButton);
        funeralHome = findViewById(R.id.funeralHomeButton);
        back = findViewById(R.id.scoreScreenToMainMenu);

        //Layout
        scoreScreenLayout = findViewById(R.id.scoreScreenLayout);

    }
    private void updateTextViews() {
        numCowsInField.setText("Cows In Your Field:  " + CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1).getCowsInField());

    }
    //According to Stackoverflow, this is the fastest way to find out if a string is an integer
    //https://stackoverflow.com/questions/237159/whats-the-best-way-to-check-if-a-string-represents-an-integer-in-java

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_screen);
        Log.d("ScoreScreen", "onCreate: started!");

        //------------------------------------------------------------------------------------------
        displayMetrics = getApplicationContext().getResources().getDisplayMetrics();

        initAllElements();
        setAllTextViewsToPlayerData(CommonUtils.getCurrentPlayer());

        //------------------------------------------------------------------------------------------

        addToField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input1 = scoreEntry.getText().toString();
                if (input1 != null && input1 != "") {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1).addCowsToField(input2);
                        CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1), CommonUtils.getCurrentPlayer() - 1);
                        updateTextViews();
                        errorLabel.setText(CommonUtils.formatPlayerPrefs(CommonUtils.getCurrentPlayer()));

                    }
                    else {
                        errorLabel.setText("You cannot input a negative number of cows.");

                    }

                }
                else {
                    errorLabel.setText("You did not input a whole number of cows, please try again.");

                }

            }

        });
        addToBarn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input1 = scoreEntry.getText().toString();
                if (input1 != null && input1 != "") {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        if (CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1).depositInBarn(input2)) {
                            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1), CommonUtils.getCurrentPlayer() - 1);
                            updateTextViews();
                            errorLabel.setText(CommonUtils.formatPlayerPrefs(CommonUtils.getCurrentPlayer()));

                        }
                        else {
                            errorLabel.setText("You cannot put more cows in your barn than you have in your field.");

                        }

                    }
                    else {
                        errorLabel.setText("You cannot input a negative number of cows.");

                    }

                }
                else {
                    errorLabel.setText("You did not input a whole number of cows, please try again.");

                }

            }

        });
        takeFromBarn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input1 = scoreEntry.getText().toString();
                if (input1 != null && input1 != "") {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        if (CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1).withdrawFromBarn(input2)) {
                            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(CommonUtils.getCurrentPlayer() - 1), CommonUtils.getCurrentPlayer() - 1);
                            updateTextViews();
                            errorLabel.setText(CommonUtils.formatPlayerPrefs(CommonUtils.getCurrentPlayer()));

                        }
                        else {
                            errorLabel.setText("You cannot withdraw more cows from your barn than you actually have.");

                        }

                    }
                    else {
                        errorLabel.setText("You cannot input a negative number of cows.");

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
