package com.example.macows;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AllScoresScreen extends AppCompatActivity {
    private TextView[] textViews = new TextView[24];
    private Button back;

    //----------------------------------------------------------------------------------------------

    private void initAllElements() {
        //Buttons
        back = findViewById(R.id.backToMainMenuFromAllscoresScreen);

        //TextViews
        textViews[0] = findViewById(R.id.p1InFieldLabel);
        textViews[1] = findViewById(R.id.p1InBarnLabel);
        textViews[2] = findViewById(R.id.p1ZombiesLabel);
        textViews[3] = findViewById(R.id.p1NameLabel);

        textViews[4] = findViewById(R.id.p2InFieldLabel);
        textViews[5] = findViewById(R.id.p2InBarnLabel);
        textViews[6] = findViewById(R.id.p2ZombiesLabel);
        textViews[7] = findViewById(R.id.p2NameLabel);

        textViews[8] = findViewById(R.id.p3InFieldLabel);
        textViews[9] = findViewById(R.id.p3InBarnLabel);
        textViews[10] = findViewById(R.id.p3ZombiesLabel);
        textViews[11] = findViewById(R.id.p3NameLabel);

        textViews[12] = findViewById(R.id.p4InFieldLabel);
        textViews[13] = findViewById(R.id.p4InBarnLabel);
        textViews[14] = findViewById(R.id.p4ZombiesLabel);
        textViews[15] = findViewById(R.id.p4NameLabel);

        textViews[16] = findViewById(R.id.p5InFieldLabel);
        textViews[17] = findViewById(R.id.p5InBarnLabel);
        textViews[18] = findViewById(R.id.p5ZombiesLabel);
        textViews[19] = findViewById(R.id.p5NameLabel);

        textViews[20] = findViewById(R.id.p6InFieldLabel);
        textViews[21] = findViewById(R.id.p6InBarnLabel);
        textViews[22] = findViewById(R.id.p6ZombiesLabel);
        textViews[23] = findViewById(R.id.p6NameLabel);

    }
    private void populateAllElements() {
        int b = 0;
        for (int a = 0; a < textViews.length; a += 4) {
            //TextViews can only be passed Strings, not primitives.
            textViews[a].setText("" + CommonUtils.playerList.get(b).getCowsInField());
            textViews[a + 1].setText("" + CommonUtils.playerList.get(b).getCowsInBarn());
            textViews[a + 2].setText("" + CommonUtils.playerList.get(b).getZombieCows());
            textViews[a + 3].setText("" + CommonUtils.playerList.get(b).getName());
            b++;

        }

    }

    //**********************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_scores_screen);
        Log.d("AllScoresScreen", "onCreate: started!");

        //==========================================================================================

        initAllElements();
        CommonUtils.setAllPlayerVarsToPrefs(CommonUtils.playerList);
        populateAllElements();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllScoresScreen.this, MainMenu.class));

            }

        });

    }

}
