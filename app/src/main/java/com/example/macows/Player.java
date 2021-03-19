package com.example.macows;

import java.util.Hashtable;

public class Player {
    private Hashtable<String, Integer> playerDict;

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
    public void setPlayerName(String a) {
        this.name = a;

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
    public void setCowsInBarn(int numCows) {
        this.cowsInBarn = numCows;

    }
    public void setZombieCows(int numCows) {
        this.zombieCows = numCows;

    }
    public void setNumCowsKilled(int numCows) {
        this.numCowsKilled = numCows;

    }
    public void setNumCowsLost(int numCows) {
        this.numCowsLost = numCows;

    }
    public void setNumCowsGained(int numCows) {
        this.numCowsGained = numCows;

    }
    public void setNumZombieCowsGained(int numCows) {
        this.numZombieCowsGained = numCows;

    }

    //Additive / Subtractive Methods
    public void addCowsToField(int numCows) {
        this.cowsInField += numCows;
        this.numCowsGained += numCows;

    }
    public void takeCowsFromField(int numCows) {
        this.cowsInField -= numCows;

    }
    public void addNumCowsKilled(int numCows) {
        this.numCowsKilled += numCows;

    }
    public void addNumCowsLost(int numCows) {
        this.numCowsLost += numCows;

    }
    public void addNumZombieCowsGained(int numCows) {
        this.numZombieCowsGained += numCows;

    }

    //Scoring methods
    public void addToField(int numCows) {
        this.cowsInField += numCows;
        this.numCowsGained += numCows;

    }
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
    public void resurrectZombieCows() {
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
    public boolean withdrawFromBarn(int numCows) {
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
    public void sawCemetery(Player other) {
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
