package com.example.macows;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AllScoresScreen extends AppCompatActivity {
    private TextView allScoresScreenLabel;
    private TextView p1InFieldLabel, p1InBarnLabel, p1ZombiesLabel, p1NameLabel;
    private TextView p2InFieldLabel, p2InBarnLabel, p2ZombiesLabel, p2NameLabel;
    private TextView p3InFieldLabel, p3InBarnLabel, p3ZombiesLabel, p3NameLabel;
    private TextView p4InFieldLabel, p4InBarnLabel, p4ZombiesLabel, p4NameLabel;
    private TextView p5InFieldLabel, p15nBarnLabel, p5ZombiesLabel, p5NameLabel;
    private TextView p6InFieldLabel, p6InBarnLabel, p6ZombiesLabel, p6NameLabel;

    //----------------------------------------------------------------------------------------------

    private void initAllElements() {
        //TextViews
        allScoresScreenLabel = findViewById(R.id.allScoresScreenLabel);

    }

    //**********************************************************************************************

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_scores_screen);
        Log.d("AllScoresScreen", "onCreate: started!");

        //==========================================================================================

        initAllElements();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    }

}
