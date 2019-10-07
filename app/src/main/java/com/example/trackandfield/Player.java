package com.example.trackandfield;

public class Player {
    String playerName;
    double playerScore;
    public Player(String pName, double pScore){
        playerName = pName;
        playerScore = pScore;
    }

    public void storeInfo(String p, double s){
        playerName = p;
        playerScore = s;
    }

    public double tellScore(){
        return playerScore;
    }

    public String tellName(){
        return playerName;
    }

}
