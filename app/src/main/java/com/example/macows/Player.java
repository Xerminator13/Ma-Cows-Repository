package com.example.macows;

import java.util.Random;

//Add setter methods for numCowsLost through totalFundsLost
//Properly implement math logic for Police descore
//Implement all other descore methods up to economy mode (Those TBD)

public class Player {
    private static Random rand = new Random();

    private String name;
    private int cowsInField, cowsInBarn, zombieCows, numCowsKilled, numCowsLost, numCowsGained;
    private double currentFunds, totalFundsGained, totalFundsLost;

    public Player(String a) {
        name = a;
        cowsInField = 0;
        cowsInBarn = 0;
        zombieCows = 0;
        numCowsKilled = 0;
        numCowsLost = 0;
        numCowsGained = 0;

        currentFunds = 0;
        totalFundsGained = 0;
        totalFundsLost = 0;

    }

    //Getter methods
    public int getCowsInField() {
        return cowsInField;

    }
    public int getCowsInBarn() {
        return cowsInBarn;

    }
    public int getZombieCows() {
        return zombieCows;

    }

    //Setter methods
    public void addCowsToField(int numCows) {
        this.cowsInField += numCows;

    }
    public void takeCowsFromField(int numCows) {
        this.cowsInField -= numCows;

    }
    public void setCowsInField(int numCows) {
        this.cowsInField = numCows;

    }
    public void addNumCowsKilled(int numCows) {
        this.numCowsKilled += numCows;

    }

    //Scoring methods
    public void sawChurch() {
        this.cowsInField *= 2;

    }
    public void sawHostpital() {
        this.cowsInField = (int)(cowsInField*1.5);

    }
    //Each cow in field has 50% chance of producing another cow
    public void sawSchool() {
        for (int a = 0; a < this.cowsInField; a++) {
            if (rand.nextInt(10) > 5) {
                this.cowsInField++;

            }

        }

    }
    /*
    Checks for you if the player has any cows in their barn or field
     */
    public void ressurectZombieCows() {
        if (this.cowsInField == 0 && this.cowsInBarn == 0) {
            this.cowsInField = 25;
            this.zombieCows = 0;

        }

    }
    public void sawRoadKill() {
        if (this.zombieCows == 0) {
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
        int numCows = (int)(other.getCowsInField());
        if (numCows > 20) {
            numCows = 20;

        }

        this.cowsInField += numCows;
        other.cowsInField -= numCows;

    }
    public void sawCemetary(Player other) {
        other.setCowsInField(0);

    }
    public void sawFastFood(Player other) {
        other.takeCowsFromField(2);

    }
    public void sawStockTrailer(Player other) {
        other.takeCowsFromField(10);

    }

}
