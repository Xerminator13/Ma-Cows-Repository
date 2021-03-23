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

    private int currentPlayer = CommonUtils.getCurrentPlayer();

    //----------------------------------------------------------------------------------------------

    private void initAllElements() {
        //TextViews
        playerNameLabel = findViewById(R.id.playerNameLabel);
        errorLabel = findViewById(R.id.errorLabel);
        numCowsInField = findViewById(R.id.numCowsInField);
        numCowsInBarn = findViewById(R.id.numCowsInBarn);
        numZombieCows = findViewById(R.id.numZombieCows);

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
        resurrectZombieCows = findViewById(R.id.resurrectZombieCows);
        back = findViewById(R.id.scoreScreenToMainMenu);

        //Layout
        scoreScreenLayout = findViewById(R.id.scoreScreenLayout);

    }
    private void updateTextViews(int a) {
        int b = a - 1;
        playerNameLabel.setText(CommonUtils.playerList.get(b).getName());

        numCowsInField.setText("Cows In Your Field:  " + CommonUtils.playerList.get(currentPlayer - 1).getCowsInField());
        numCowsInBarn.setText("Cows In Your Barn:  " + CommonUtils.playerList.get(currentPlayer - 1).getCowsInBarn());
        numZombieCows.setText("Your Zombie Cows:  " + CommonUtils.playerList.get(currentPlayer - 1).getZombieCows());

        errorLabel.setText(CommonUtils.getDescoreErrorMessage());

    }
    private void runScoringMethods(int a) {
        //School
        if (a == 1) {
            CommonUtils.playerList.get(currentPlayer - 1).sawSchool();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));


        }
        //Road Kill
        else if (a == 2) {
            CommonUtils.playerList.get(currentPlayer - 1).sawRoadKill();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

        }
        //Hospital
        else if (a == 3) {
            CommonUtils.playerList.get(currentPlayer - 1).sawHostpital();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

        }
        //Church
        else if (a == 4) {
            CommonUtils.playerList.get(currentPlayer - 1).sawChurch();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

        }
        //Cemetery
        else if (a == 5) {
            CommonUtils.setDescoreMethod(1);
            startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

        }
        //Fast Food
        else if (a == 6) {
            CommonUtils.setDescoreMethod(2);
            startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

        }
        //Police
        else if (a == 7) {
            CommonUtils.setDescoreMethod(3);
            startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

        }
        //Stock Trailer
        else if (a == 8) {
            CommonUtils.setDescoreMethod(4);
            startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

        }
        //Funeral Home
        else if (a == 9) {
            CommonUtils.setDescoreMethod(5);
            startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

        }
        //Resurrect Zombie Cows
        else if (a == 10) {
            String error = CommonUtils.playerList.get(currentPlayer - 1).resurrectZombieCows();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            errorLabel.setText(error);

        }

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

        currentPlayer = currentPlayer;
        initAllElements();
        updateTextViews(currentPlayer);

        //------------------------------------------------------------------------------------------

        addToField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input1 = scoreEntry.getText().toString();

                if (input1 != null && input1.length() > 0) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        CommonUtils.playerList.get(currentPlayer - 1).addCowsToField(input2);
                        CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                        updateTextViews(currentPlayer);
                        errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

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

                if (input1 != null && input1.length() > 0) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        if (CommonUtils.playerList.get(currentPlayer - 1).depositInBarn(input2)) {
                            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                            updateTextViews(currentPlayer);
                            errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

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

                if (input1 != null && input1.length() > 0) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        if (CommonUtils.playerList.get(currentPlayer - 1).withdrawFromBarn(input2)) {
                            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                            updateTextViews(currentPlayer);
                            errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

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
        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(1);

            }

        });
        roadKill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(2);

            }

        });
        hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(3);

            }

        });
        church.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(4);

            }

        });
        cemetery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(5);

            }

        });
        fastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(6);

            }

        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(7);

            }

        });
        stockTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(8);

            }

        });
        funeralHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(9);

            }

        });
        resurrectZombieCows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScoringMethods(10);

            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(ScoreScreen.this, MainMenu.class));

            }

        });

    }

}
