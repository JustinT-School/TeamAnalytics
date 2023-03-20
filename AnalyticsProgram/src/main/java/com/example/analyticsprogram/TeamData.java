package com.example.analyticsprogram;

import java.util.ArrayList;

// Class for holding score information

public class TeamData
{
    private int team = 0;
    private int game = 0;
    private ArrayList<Integer> scores = new ArrayList<>();

    public TeamData(int inTeam, int inGame, ArrayList<Integer> inScores)
    {
        team = inTeam;
        game = inGame;
        scores = inScores;
    }

    public int getTeam() {
        return team;
    }

    public int getQtrScore(int inQtr)
    {
        return scores.get(inQtr);
    }

    public int getFirstHalfScore()
    {
        return scores.get(0) + scores.get(1);
    }

    public int getSecondHalfScore()
    {
        return scores.get(2) + scores.get(3);
    }

    public int getGameScore()
    {
        return scores.get(0) + scores.get(1) + scores.get(2) + scores.get(3);
    }

    public void setScore(int qtr, int score)
    {
        scores.set(qtr, score);
    }

    @Override
    public String toString() {
        return String.valueOf(team) + "," + String.valueOf(game) + "," + scores.toString().replace("[", "").replace("]", "").replace(" ", "");
    }
}
