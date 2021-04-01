package com.example.macows;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

public class CommonUtils {
    private static Context mContext;
    private static SharedPreferences prefs;
    private static SharedPreferences.Editor editor;
    public static ArrayList<Player> playerList  = new ArrayList<Player>();
    public static ArrayList<EconomyPlayer> econPlayerList = new ArrayList<EconomyPlayer>();

    public int currentPlayerNum = 1;

    //For using shared preferences >>
    //  https://www.journaldev.com/9412/android-shared-preferences-example-tutorial
    public CommonUtils(Context context) {
        mContext = context;
        prefs = mContext.getSharedPreferences("MyPrefs", 0);// 0 is for private mode
        editor = prefs.edit();

    }

    //Setter methods
    public static void initPlayers() {
        for (int a = 0; a < 6; a++) {
            playerList.add(new Player(""));

        }

    }
    //This method is attached to the "Play economy mode" button
    //I need to save the following data:  Highest score, player name, most funds
    public static void initEconPlayers() {
        for (int a = 0; a < 6; a++) {
            econPlayerList.add(new EconomyPlayer(""));

        }

    }
    public static void initPrefs(Context context) {
        for (int a = 0; a < 6; a++) {
            editor.putString("p" + a + "Name", "Player " + a);
            editor.putInt("p" + a + "InField", 0);
            editor.putInt("p" + a + "InBarn", 0);
            editor.putInt("p" + a + "Zombies", 0);
            editor.putInt("p" + a + "NumKilled", 0);
            editor.putInt("p" + a + "NumLost", 0);
            editor.putInt("p" + a + "NumGained", 0);
            editor.putInt("p" + a + "ZombiesGained", 0);

        }
        for (int a = 1; a < 11; a++) {
            editor.putString("p" + a + "LeaderboardName", "");
            editor.putInt("p" + a + "LeaderboardScore", 0);

        }

        editor.putInt("currentPlayer", 1);
        /*
        * 1 = Cemetery
        * 2 = Fast Food
        * 3 = Police
        * 4 = Stock Trailer
        * 5 = Funeral Home
        */
        editor.putInt("descoreMethod", 1);
        editor.putString("descoreErrorMessage", "Descored...");

        editor.commit();

    }

    public static void setCurrentPlayer(int player) {
        editor.putInt("currentPlayer", player);
        editor.commit();

    }
    public static void setDescoreMethod(int a) {
        editor.putInt("descoreMethod", a);
        editor.commit();

    }
    public static void setDescoreErrorMessage(String msg) {
        editor.putString("descoreErrorMessage", msg);
        editor.commit();

    }
    public static int getCurrentPlayer() {
        return prefs.getInt("currentPlayer", 1);

    }
    public static int getDescoreMethod() {
        return prefs.getInt("descoreMethod", 1);

    }
    public static String getDescoreErrorMessage() {
        return prefs.getString("descoreErrorMessage", "Descored...");

    }

    public static void updateAllPlayerPreferences(ArrayList<Player> playerList) {
        int a = 1;

        for (Player p : playerList) {
            editor.putString("p" + a + "Name", p.getName());
            editor.putInt("p" + a + "InField", p.getCowsInField());
            editor.putInt("p" + a + "InBarn", p.getCowsInBarn());
            editor.putInt("p" + a + "Zombies", p.getZombieCows());
            editor.putInt("p" + a + "NumKilled", p.getNumCowsKilled());
            editor.putInt("p" + a + "NumLost", p.getNumCowsLost());
            editor.putInt("p" + a + "NumGained", p.getNumCowsGained());
            editor.putInt("p" + a + "ZombiesGained", p.getNumZombieCowsGained());

            a++;

        }

        editor.commit();

    }
    public static void updatePlayerPrefs(Player p, int index) {
        int a = index + 1;

        editor.putString("p" + a + "Name", p.getName());
        editor.putInt("p" + a + "InField", p.getCowsInField());
        editor.putInt("p" + a + "InBarn", p.getCowsInBarn());
        editor.putInt("p" + a + "Zombies", p.getZombieCows());
        editor.putInt("p" + a + "NumKilled", p.getNumCowsKilled());
        editor.putInt("p" + a + "NumLost", p.getNumCowsLost());
        editor.putInt("p" + a + "NumGained", p.getNumCowsGained());
        editor.putInt("p" + a + "ZombiesGained", p.getNumZombieCowsGained());

        editor.commit();

    }
    public static void setAllPlayerVarsToPrefs(ArrayList<Player> playerList) {
        int a = 1;

        for (Player p : playerList) {
            p.setPlayerName(prefs.getString("p" + a + "Name", "Player" + a));
            p.setCowsInField(prefs.getInt("p" + a + "InField", 0));
            p.setCowsInBarn(prefs.getInt("p" + a + "InBarn", 0));
            p.setZombieCows(prefs.getInt("p" + a + "Zombies", 0));
            p.setNumCowsKilled(prefs.getInt("p" + a + "NumKilled", 0));
            p.setNumCowsLost(prefs.getInt("p" + a + "NumLost", 0));
            p.setNumCowsGained(prefs.getInt("p" + a + "NumGained", 0));
            p.setNumZombieCowsGained(prefs.getInt("p" + a + "ZombiesGained", 0));

            a++;

        }

    }
    public static void setPlayerVarsToPrefs(Player p, int index) {
        int a = index + 1;

        p.setPlayerName(prefs.getString("p" + a + "Name", "Player" + a));
        p.setCowsInField(prefs.getInt("p" + a + "InField", 0));
        p.setCowsInBarn(prefs.getInt("p" + a + "InBarn", 0));
        p.setZombieCows(prefs.getInt("p" + a + "Zombies", 0));
        p.setNumCowsKilled(prefs.getInt("p" + a + "NumKilled", 0));
        p.setNumCowsLost(prefs.getInt("p" + a + "NumLost", 0));
        p.setNumCowsGained(prefs.getInt("p" + a + "NumGained", 0));
        p.setNumZombieCowsGained(prefs.getInt("p" + a + "ZombiesGained", 0));

    }
    public static void resetAllScores() {
        for (int a = 1; a < 7; a++) {
            editor.putInt("p" + a + "InField", 0);
            editor.putInt("p" + a + "InBarn", 0);
            editor.putInt("p" + a + "Zombies", 0);
            editor.putInt("p" + a + "NumKilled", 0);
            editor.putInt("p" + a + "NumLost", 0);
            editor.putInt("p" + a + "NumGained", 0);
            editor.putInt("p" + a + "ZombiesGained", 0);

        }

        editor.putInt("currentPlayer", 1);
        /*
         * 1 = Cemetery
         * 2 = Fast Food
         * 3 = Police
         * 4 = Stock Trailer
         * 5 = Funeral Home
         */
        editor.putInt("descoreMethod", 1);
        editor.putString("descoreErrorMessage", "Descored...");

        editor.commit();

    }
    public static void resetAllNames() {
        for (int a = 1; a < 7; a++) {
            editor.putString("p" + a + "Name", "");

        }

        editor.commit();

    }

    //Return Methods  ******************************************************************************
    public static String formatPlayerPrefs(int a) {
        String formattedString = "";

        formattedString += "Player Name: " + prefs.getString("p" + a + "Name", "Player" + a);
        formattedString += ", Cows in field: " + prefs.getInt("p" + a + "InField", 0);
        formattedString += "\nCows in barn: " + prefs.getInt("p" + a + "InBarn", 0);
        formattedString += ", Zombie Cows: " + prefs.getInt("p" + a + "Zombies", 0);
        formattedString += ", Cows this player killed: " + prefs.getInt("p" + a + "NumKilled", 0);
        formattedString += "\nCows this player has lost: " + prefs.getInt("p" + a + "NumLost", 0);
        formattedString += ", Cows this player has gained: " + prefs.getInt("p" + a + "NumGained", 0);
        formattedString += "\nZombie cows this player has gained: " + prefs.getInt("p" + a + "ZombiesGained", 0);

        return formattedString;

    }

}
