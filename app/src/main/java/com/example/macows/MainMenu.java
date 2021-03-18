package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    private Button player1, player2, player3, player4, player5, player6, back, endGame, goToAllScores, backButton1;
    private EditText name1, name2, name3, name4, name5, name6;
    private TextView appLabel;

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d("MainMenu", "onCreate: started!");

        //------------------------------------------------------------------------------------------

        appLabel = findViewById(R.id.appLabelView);
        backButton1 = findViewById(R.id.backButton1);
        name1 = findViewById(R.id.player1NameInput);
        name2 = findViewById(R.id.player2NameInput);
        name3 = findViewById(R.id.player3NameInput);
        name4 = findViewById(R.id.player4NameInput);
        name5 = findViewById(R.id.player5NameInput);
        name6 = findViewById(R.id.player6NameInput);

        //------------------------------------------------------------------------------------------

        //Stackoverflow article on editText view listeners
        //https://stackoverflow.com/questions/8699569/implementing-text-watcher-for-edittext
        name1.setImeOptions(EditorInfo.IME_ACTION_DONE);
        name1.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //Does not pass data back to activity because in MainActivity the prefs were not loaded yet
                    CommonUtils.playerList.get(0).setPlayerName(name1.getText().toString());
                    CommonUtils.updatePlayerPrefs(CommonUtils.playerList.get(0), 0);
                    System.out.println("Set player name");
                    return true;

                }
                else {
                    System.out.println("Did not set player name");
                    return false;

                }

            }

        });
        backButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CommonUtils.playerList.get(0).setPlayerName(name1.getText().toString());
                startActivity(new Intent(MainMenu.this, MainActivity.class));

            }

        });

        //TODO write all listeners for text boxes or find a better way to implement the naming feature
        //TODO configure existing structure to test new naming system

    }

}
