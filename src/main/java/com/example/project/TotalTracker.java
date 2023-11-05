package com.example.project;

public class TotalTracker {
    private double totalIncome = 0.0;
    private int totalProductsSold = 0;

    public void addToTotalIncome(double amount) {
        totalIncome += amount;
    }

    public void addToTotalProductsSold(int quantity) {
        totalProductsSold += quantity;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public int getTotalProductsSold() {
        return totalProductsSold;
    }

    public void reset() {
        totalIncome = 0.0;
        totalProductsSold = 0;
    }
}

