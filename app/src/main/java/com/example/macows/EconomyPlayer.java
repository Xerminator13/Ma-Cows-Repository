package com.example.macows;

public class EconomyPlayer extends Player{
    private double currentFunds, totalFundsGained, totalFundsLost;

    public EconomyPlayer(String a) {
        super(a);

        currentFunds = 0;
        totalFundsGained = 0;
        totalFundsLost = 0;

    }

    //Getter methods
    public double getCurrentFunds() {
        return currentFunds;

    }
    public double getTotalFundsGained() {
        return totalFundsGained;

    }
    public double getTotalFundsLost() {
        return totalFundsLost;

    }

    //Setter methods
    public void addToCurrentFunds(double amount) {
        this.currentFunds += amount;
        this.totalFundsGained += amount;

    }
    public void removeFromCurrentFunds(double amount) {
        this.currentFunds -= amount;
        this.totalFundsLost += amount;

    }

}
