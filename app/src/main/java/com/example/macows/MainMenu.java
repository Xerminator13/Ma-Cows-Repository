package com.example.macows;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {
    private Button player1, player2, player3, player4, player5, player6, back, endGame, goToAllScores;
    private EditText name1, name2, name3, name4, name5, name6;
    private TextView appLabel;

    //**********************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        Log.d("MainMenu", "onCreate: started!");

        //------------------------------------------------------------------------------------------

        appLabel.findViewById(R.id.appLabelView);

    }

}
