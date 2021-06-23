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
        return this.name;

    }
    public int getCowsInField() {
        return this.cowsInField;

    }
    public int getCowsInBarn() {
        return this.cowsInBarn;

    }
    public int getZombieCows() {
        return this.zombieCows;

    }
    public int getNumCowsKilled() {
        return this.numCowsKilled;

    }
    public int getNumCowsLost() {
        return this.numCowsLost;

    }
    public int getNumCowsGained() {
        return this.numCowsGained;

    }
    public int getNumZombieCowsGained() {
        return this.numZombieCowsGained;

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

    //Additive and Subtractive Methods
    public void addZombieCows(int numZombieCows) {
        this.zombieCows += numZombieCows;

    }
    public void takeZombieCows(int numCows) {
        this.zombieCows -= numCows;

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
    public void takeCowsFromField(int numCows) {
        this.cowsInField -= numCows;

    }

    //Scoring methods ------------------------------------------------------------------------------

    public void addCowsToField(int numCows) {
        CommonUtils.setCurrentScore(numCows);
        CommonUtils.setActionFlag(0);
        this.cowsInField += numCows;
        this.numCowsGained += numCows;

    }

    /**
     * Doubles the number of cows in the field
     */
    public void sawChurch() {
        CommonUtils.setCurrentScore(this.cowsInField);
        CommonUtils.setActionFlag(6);
        this.numCowsGained += this.cowsInField;
        this.cowsInField *= 2;

    }
    /**
     * Takes the current number of cows in the field and multiples it by 1.5
     */
    public void sawHostpital() {
        CommonUtils.setCurrentScore(this.cowsInField);
        CommonUtils.setActionFlag(5);
        this.numCowsGained = (int) (this.cowsInField*1.5) - this.cowsInField;
        this.cowsInField = (int) (cowsInField*1.5);

    }
    /**
     * Takes the current cows in the field and multiplies it by 1.XX, where XX is a random double
     */
    public void sawSchool() {
        CommonUtils.setCurrentScore(this.cowsInField);
        CommonUtils.setActionFlag(3);
        double percentage = Math.random();
        this.cowsInField = (int) (this.cowsInField*(1 + percentage));

    }

    /**
     * The function to convert zombie cows to cows in the field
     * This function additionally checks if the player has any cows in their barn or field
     */
    public String resurrectZombieCows(String input) {
        if (input.equals("13")) {
            CommonUtils.setCurrentScore(this.cowsInField);
            CommonUtils.setActionFlag(0);
            this.addCowsToField(1000000);
            return "Nice, you win a ton!";

        }
        else if (this.zombieCows > 0) {
            if (this.cowsInField == 0) {
                if (this.cowsInBarn == 0) {
                    CommonUtils.setCurrentScore(this.zombieCows);
                    CommonUtils.setActionFlag(12);
                    this.cowsInField += this.zombieCows;
                    this.zombieCows = 0;

                    return "You summon the dead to terrorize your opponents";

                }
                else {
                    return "You still have cows in your barn, use those!";

                }

            }
            else {
                return "You already have cows in your field!";

            }

        }
        else {
            return "You don't have any zombie cows!";

        }

    }

    /**
     * Adds 25 to a player's zombie cow score
     */
    public void sawRoadKill() {
        CommonUtils.setCurrentScore(this.zombieCows);
        CommonUtils.setActionFlag(4);
        this.numZombieCowsGained += 25;
        this.addZombieCows(25);

    }
    /**
     * @return true when cows can be deposited, false if not
     */
    public boolean depositInBarn(int numCows) {
        if ((this.cowsInField - 1) >= numCows) {
            CommonUtils.setCurrentScore(this.cowsInField);
            CommonUtils.setActionFlag(1);
            this.cowsInField -= numCows;
            this.cowsInBarn += numCows;
            return true;

        }
        else {
            return false;

        }

    }
    /**
     * @return true if cows can be deposited, false if not
     */
    public boolean withdrawFromBarn(int numCows) {
        if (this.cowsInBarn >= numCows) {
            CommonUtils.setCurrentScore(this.cowsInField);
            CommonUtils.setActionFlag(2);
            this.cowsInBarn -= numCows;
            this.cowsInField += numCows;
            return true;

        }
        else {
            return false;

        }

    }

    //Descoring methods ----------------------------------------------------------------------------

    /**
     * This will only steal 75% of a players cows up to 20 cows.
     * This method is unique to other descoring methods because it takes an integer as an input instead of a player object
     * This is to help with the undo framework.
     * @param player2 as an integer between 1-6 (inclusive of both).
     */
    public void sawPolice(int player2) {
        Player other = CommonUtils.playerList.get(player2 - 1);

        int numCows = (int) (other.getCowsInField()*0.75);

        if (numCows > 20) {
            numCows = 20;

        }

        CommonUtils.setCurrentScore(numCows);
        CommonUtils.setPlayer2Affected(player2);
        CommonUtils.setActionFlag(9);

        this.cowsInField += numCows;
        this.numCowsGained += numCows;
        other.takeCowsFromField(numCows);
        other.addNumCowsLost(numCows);

    }

    /**
     * Sets the number of cows in the other player's field to 0
     * @param other player object
     */
    public void sawCemetery(Player other) {
        int numBeingTaken = other.getCowsInField();

        CommonUtils.setCurrentScore(numBeingTaken);
        CommonUtils.setActionFlag(7);

        this.numCowsKilled += numBeingTaken;
        other.addNumCowsLost(numBeingTaken);
        other.addZombieCows((int) (numBeingTaken*0.25));
        other.setCowsInField(0);

    }

    /**
     * Removes 2 cows from the field of the other player
     * @param other player object
     */
    public void sawFastFood(Player other) {
        if (other.cowsInField > 0) {
            CommonUtils.setCurrentScore(2);
            CommonUtils.setActionFlag(8);

            other.addNumCowsLost(2);
            other.takeCowsFromField(2);
            this.numCowsKilled += 2;

        }

    }

    /**
     * Removes 10 cows form the field of the other player
     * @param other player object
     */
    public void sawStockTrailer(Player other) {
        if (other.cowsInField > 0) {
            CommonUtils.setCurrentScore(10);
            CommonUtils.setActionFlag(10);

            other.addNumCowsLost(10);
            other.takeCowsFromField(10);
            other.addZombieCows(2);
            this.numCowsKilled += 10;

        }

    }

    /**
     * Removes half of the cows from the other player's field
     * @param other player object
     */
    public void sawFuneralHome(Player other) {
        if (other.cowsInField > 0) {
            int numBeingTaken = (int)(other.getCowsInField()/2.0);

            CommonUtils.setCurrentScore(numBeingTaken);
            CommonUtils.setActionFlag(11);

            this.numCowsKilled += numBeingTaken;
            other.addNumCowsLost(numBeingTaken);
            other.takeCowsFromField(numBeingTaken);
            other.addZombieCows((int) (numBeingTaken*0.1));

        }

    }

}
