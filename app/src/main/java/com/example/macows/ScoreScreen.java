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
    private Button switchPlayerButton1, switchPlayerButton2, switchPlayerButton3, switchPlayerButton4, switchPlayerButton5;
    private Button undoActionButton;
    private EditText scoreEntry;
    private TextView playerNameLabel, numCowsInField, numCowsInBarn, numZombieCows, errorLabel;

    private int[] players = new int[5];

    private int currentPlayer = CommonUtils.getCurrentPlayer();

    //Visual element handlers ----------------------------------------------------------------------

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
        undoActionButton = findViewById(R.id.undoActionButton);

        switchPlayerButton1 = findViewById(R.id.switchPlayerButton1);
        switchPlayerButton2 = findViewById(R.id.switchPlayerButton2);
        switchPlayerButton3 = findViewById(R.id.switchPlayerButton3);
        switchPlayerButton4 = findViewById(R.id.switchPlayerButton4);
        switchPlayerButton5 = findViewById(R.id.switchPlayerButton5);

    }
    private void updateTextViews(int a) {
        int b = a - 1;
        playerNameLabel.setText(CommonUtils.playerList.get(b).getName());

        numCowsInField.setText("Cows In Your Field:  " + CommonUtils.playerList.get(currentPlayer - 1).getCowsInField());
        numCowsInBarn.setText("Cows In Your Barn:  " + CommonUtils.playerList.get(currentPlayer - 1).getCowsInBarn());
        numZombieCows.setText("Your Zombie Cows:  " + CommonUtils.playerList.get(currentPlayer - 1).getZombieCows());

        errorLabel.setText(CommonUtils.getDescoreErrorMessage());
        scoreEntry.setText("");

    }
    private void updateSwitchPlayerButtons() {
        //Of note, the players[] list is populated with integers between 0-5 (inclusive), NOT 1-6 (inclusive).
        //This is so it is easier to use with the .get() method for the later block of .setText() methods.
        int b = 0;
        for (int a = 0; a < 6; a++) {
            if (a != currentPlayer - 1) {
                players[b] = a;
                b++;

            }

        }

        switchPlayerButton1.setText(CommonUtils.playerList.get(players[0]).getName());
        switchPlayerButton2.setText(CommonUtils.playerList.get(players[1]).getName());
        switchPlayerButton3.setText(CommonUtils.playerList.get(players[2]).getName());
        switchPlayerButton4.setText(CommonUtils.playerList.get(players[3]).getName());
        switchPlayerButton5.setText(CommonUtils.playerList.get(players[4]).getName());

    }

    //Scoring and undo handlers --------------------------------------------------------------------

    private void runScoringMethods(int a) {
        //School
        if (a == 1) {
            CommonUtils.playerList.get(currentPlayer - 1).sawSchool();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));


        }
        //Road Kill
        else if (a == 2) {
            CommonUtils.playerList.get(currentPlayer - 1).sawRoadKill();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

        }
        //Hospital
        else if (a == 3) {
            CommonUtils.playerList.get(currentPlayer - 1).sawHostpital();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

        }
        //Church
        else if (a == 4) {
            CommonUtils.playerList.get(currentPlayer - 1).sawChurch();
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

        }
        //Resurrect Zombie Cows
        else if (a == 10) {
            String error = CommonUtils.playerList.get(currentPlayer - 1).resurrectZombieCows(scoreEntry.getText().toString());
            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
            updateTextViews(currentPlayer);
            errorLabel.setText(error);

        }

    }
    /**
     * 0 = Add to field;
     * 1 = Put in barn;
     * 2 = Take from barn;
     * 3 = School;
     * 4 = Road kill;
     * 5 = Hospital;
     * 6 = Church;
     * 7 = Cemetery;
     * 8 = Fast food;
     * 9 = Police;
     * 10 = Stock trailer;
     * 11 = Funeral home;
     * 12 = Resurrect zombie cows.
     *
     * Some things still are not working right:
     *  - Spamming undo after cemetery actually doubles your cows...
     *  - Police only affects one player at a time after double-undoing...
     *  - Possibly make a popup, "Are you sure you want to undo?" to reduce spamming and other issues.
     */
    private String undoLastAction(int action) {
        int cowsInFieldP1 = CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).getCowsInField();
        int cowsInBarnP1 = CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).getCowsInBarn();
        int zombieCowsP1 = CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).getZombieCows();

        int cowsInFieldP2 = CommonUtils.playerList.get(CommonUtils.getPlayer2Affected() - 1).getCowsInField();

        if (action == 0) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and took " + temp + " cows from the field";

        }
        //Of note, action flags needed to be changed so that ideally hitting the undo button multiple times...
        //Will cause the score to bounce back and forth between the barn and the field... ideally, this is yet to be tested.
        else if (action == 1) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).addCowsToField(temp);
            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).withdrawFromBarn(temp);
            CommonUtils.setActionFlag(2);

            return "Undid action and took " + temp + " cows from the barn and put them back into the field";

        }
        else if (action == 2) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).depositInBarn(temp);
            CommonUtils.setActionFlag(1);

            return "Undid action and took " + temp + " cows from the field and put them back into the barn";

        }
        else if (action == 3) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and took " + temp + " cows from the field";

        }
        else if (action == 4) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeZombieCows(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and took " + temp + " cows from the zombie cows score";

        }
        else if (action == 5) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and took " + temp + " cows from the field";

        }
        else if (action == 6) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and took " + temp + " cows from the field";

        }
        else if (action == 7) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).addCowsToField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and added " + temp + " cows to the field";

        }
        else if (action == 8) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).addCowsToField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and added " + temp + " cows to the field";

        }
        else if (action == 9) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.playerList.get(CommonUtils.getPlayer2Affected() - 1).addCowsToField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid the action;\n" + temp + " cows were given back to " + CommonUtils.playerList.get(CommonUtils.getPlayer2Affected() - 1).getName() + "\n" + temp + " were taken back from " + CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).getName();

        }
        else if (action == 10) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).addCowsToField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and added " + temp + " cows to the field";

        }
        else if (action == 11) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).addCowsToField(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and added " + temp + " cows to the field";

        }
        else if (action == 12) {
            int temp = CommonUtils.getCurrentScore();

            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).takeCowsFromField(temp);
            CommonUtils.playerList.get(CommonUtils.getPlayer1Affected() - 1).addZombieCows(temp);
            CommonUtils.setCurrentScore(-1*temp);

            return "Undid action and zombified " + temp + " cows";

        }
        else if (action == -1) {
            return "No actions have been taken yet";

        }
        else {
            return "There was a mis-input and an action flag needs to be changed somewhere...";

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

        initAllElements();
        updateTextViews(currentPlayer);
        updateSwitchPlayerButtons();
        CommonUtils.setPlayer1Affected(currentPlayer);

        //------------------------------------------------------------------------------------------

        addToField.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String input1 = scoreEntry.getText().toString();

                if (input1.length() > 0) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        CommonUtils.playerList.get(currentPlayer - 1).addCowsToField(input2);
                        CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                        updateTextViews(currentPlayer);
                        //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

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

                if (input1.length() > 0) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        if (CommonUtils.playerList.get(currentPlayer - 1).depositInBarn(input2)) {
                            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                            updateTextViews(currentPlayer);
                            //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

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

                if (input1.length() > 0) {
                    int input2 = Integer.parseInt(input1);

                    if (input2 > 0) {
                        if (CommonUtils.playerList.get(currentPlayer - 1).withdrawFromBarn(input2)) {
                            CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(currentPlayer - 1), currentPlayer - 1);
                            updateTextViews(currentPlayer);
                            //errorLabel.setText(CommonUtils.formatPlayerPrefs(currentPlayer));

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
                CommonUtils.setDescoreMethod(1);
                System.out.println(CommonUtils.getDescoreMethod());
                startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

            }

        });
        fastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.setDescoreMethod(2);
                System.out.println(CommonUtils.getDescoreMethod());
                startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

            }

        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.setDescoreMethod(3);
                System.out.println(CommonUtils.getDescoreMethod());
                startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

            }

        });
        stockTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.setDescoreMethod(4);
                System.out.println(CommonUtils.getDescoreMethod());
                startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

            }

        });
        funeralHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.setDescoreMethod(5);
                System.out.println(CommonUtils.getDescoreMethod());
                startActivity(new Intent(ScoreScreen.this, DescoreScreen.class));

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
        undoActionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temp = undoLastAction(CommonUtils.getActionFlag());
                updateTextViews(currentPlayer);
                errorLabel.setText(temp);

            }

        });

        switchPlayerButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPlayer = players[0] + 1;
                CommonUtils.setCurrentPlayer(currentPlayer);

                updateTextViews(currentPlayer);
                updateSwitchPlayerButtons();
                CommonUtils.setPlayer1Affected(currentPlayer);

            }

        });
        switchPlayerButton2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPlayer = players[1] + 1;
                CommonUtils.setCurrentPlayer(currentPlayer);

                updateTextViews(currentPlayer);
                updateSwitchPlayerButtons();
                CommonUtils.setPlayer1Affected(currentPlayer);

            }

        });
        switchPlayerButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPlayer = players[2] + 1;
                CommonUtils.setCurrentPlayer(currentPlayer);

                updateTextViews(currentPlayer);
                updateSwitchPlayerButtons();
                CommonUtils.setPlayer1Affected(currentPlayer);

            }

        });
        switchPlayerButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPlayer = players[3] + 1;
                CommonUtils.setCurrentPlayer(currentPlayer);

                updateTextViews(currentPlayer);
                updateSwitchPlayerButtons();
                CommonUtils.setPlayer1Affected(currentPlayer);

            }

        });
        switchPlayerButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentPlayer = players[4] + 1;
                CommonUtils.setCurrentPlayer(currentPlayer);

                updateTextViews(currentPlayer);
                updateSwitchPlayerButtons();
                CommonUtils.setPlayer1Affected(currentPlayer);

            }

        });

    }

}
