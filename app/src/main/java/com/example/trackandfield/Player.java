package com.example.trackandfield;

public class Player {
    String playerName;
    double playerScore;
    public Player(String pName, double pScore){
        playerName = pName;
        playerScore = pScore;
    }


    public double getScore(){
        return playerScore;
    }

    public String getName(){
        return playerName;
    }

}
