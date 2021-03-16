package com.example.macows;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.renderscript.ScriptGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Random;
import java.util.ArrayList;
import android.content.Context;

public class Player {
    private static Random rand = new Random();

    private String name;
    private int cowsInField, cowsInBarn, zombieCows, numCowsKilled, numCowsLost, numCowsGained, numZombieCowsGained;

    public Player(String a) {
        name = a;
        cowsInField = 0;
        cowsInBarn = 0;
        zombieCows = 0;
        numCowsKilled = 0;
        numCowsLost = 0;
        numCowsGained = 0;
        numZombieCowsGained = 0;

    }

    //Getter methods
    public String getName() {
        return name;

    }
    public int getCowsInField() {
        return cowsInField;

    }
    public int getCowsInBarn() {
        return cowsInBarn;

    }
    public int getZombieCows() {
        return zombieCows;

    }
    public int getNumCowsKilled() {
        return numCowsKilled;

    }
    public int getNumCowsLost() {
        return numCowsLost;

    }
    public int getNumCowsGained() {
        return numCowsGained;

    }
    public int getNumZombieCowsGained() {
        return numZombieCowsGained;

    }

    //Setter methods
    public void addCowsToField(int numCows) {
        this.cowsInField += numCows;
        this.numCowsGained += numCows;

    }
    public void takeCowsFromField(int numCows) {
        this.cowsInField -= numCows;

    }
    public void setCowsInField(int numCows) {
        if (numCows < this.cowsInField) {
            this.numCowsLost += (this.cowsInField - numCows);

        }
        else {
            this.numCowsGained += (numCows - this.cowsInField);

        }
        this.cowsInField = numCows;

    }
    public void addNumCowsKilled(int numCows) {
        this.numCowsKilled += numCows;

    }
    public void addNumCowsLost(int numCows) {
        this.numCowsLost += numCows;

    }

    //Scoring methods
    public void sawChurch() {
        this.numCowsGained += this.cowsInField;
        this.cowsInField *= 2;

    }
    public void sawHostpital() {
        this.numCowsGained = (int)(this.cowsInField*1.5) - this.cowsInField;
        this.cowsInField = (int)(cowsInField*1.5);

    }
    //Random percentage of current cows in field is added to the cows in the field as additional cows
    public void sawSchool() {
       double percentage = Math.random();
       this.cowsInField +=(int)(this.cowsInField*(1 + percentage));

    }
    /*
    Checks for you if the player has any cows in their barn or field
     */
    public void ressurectZombieCows() {
        if (this.cowsInField == 0 && this.cowsInBarn == 0) {
            this.cowsInField = 25;
            this.numCowsGained += 25;
            this.zombieCows = 0;

        }

    }
    public void sawRoadKill() {
        if (this.zombieCows == 0) {
            this.numZombieCowsGained += 25;
            this.zombieCows = 25;

        }

    }
    /*
    @return true when cows can be deposited, false if not
     */
    public boolean depositInBarn(int numCows) {
        if ((this.cowsInField - 1) >= numCows) {
            this.cowsInField -= numCows;
            this.cowsInBarn += numCows;
            return true;

        }
        else {
            return false;

        }

    }
    /*
    @return true if cows can be deposited, false if not
     */
    public boolean withdrawlFromBarn(int numCows) {
        if (this.cowsInBarn >= numCows) {
            this.cowsInBarn -= numCows;
            this.cowsInField += numCows;
            return true;

        }
        else {
            return false;

        }

    }

    //Descoring methods
    /*
    This will only steal 75% of a players cows up to 20 cows.
     */
    public void sawPolice(Player other) {
        int numCows = (int)(other.getCowsInField()*0.75);
        if (numCows > 20) {
            numCows = 20;

        }

        this.cowsInField += numCows;
        this.numCowsGained += numCows;
        other.takeCowsFromField(numCows);
        other.addNumCowsLost(numCows);

    }
    public void sawCemetary(Player other) {
        other.addNumCowsLost(other.getCowsInField());
        other.setCowsInField(0);

    }
    public void sawFastFood(Player other) {
        other.addNumCowsLost(2);
        other.takeCowsFromField(2);

    }
    public void sawStockTrailer(Player other) {
        other.addNumCowsLost(10);
        other.takeCowsFromField(10);

    }
    public void sawFuneralHome(Player other) {
        other.addNumCowsLost((int)(other.getCowsInField()/2.0));
        other.takeCowsFromField((int)(other.getCowsInField()/2.0));

    }

}
