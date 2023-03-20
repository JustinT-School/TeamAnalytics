package com.example.analyticsprogram;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Page for viewing different score data

public class ReportsPage extends Menu
{
    private static int[][] qtrAverage = new int[2][4];
    private static int[][] qtrTotal = new int[2][4];
    private static int[] qtrDiff = new int[4];
    private static int[] halfDiff = new int[2];
    private static int gameDiff = 0;
    private static int qtrHighest = 0;
    private static int qtrLowest = 0;

    private static int[] gameTotal = new int[2];
    private static int[] gameAverage = new int[2];
    private static int[][] halfTotal = new int[2][2];
    private static int[][] halfAverage = new int[2][2];

    // Loads scores and creates the correct values to view
    public static void loadScores()
    {
        // Initialize qtrLowest so it is no longer set to 0
        qtrLowest = Program.teamData.get(0).getQtrScore(0);

        // Parses data to return game, half, and quarter totals
        // Also set qtrLowest and qtrHighest
        for (int i = 0; i < Program.teamData.size(); i++)
        {
            gameTotal[Program.teamData.get(i).getTeam()] = gameTotal[Program.teamData.get(i).getTeam()] + Program.teamData.get(i).getGameScore();

            for (int half = 0; half < 2; half++)
            {
                if (half == 0)
                {
                    halfTotal[Program.teamData.get(i).getTeam()][half] = halfTotal[Program.teamData.get(i).getTeam()][half] + Program.teamData.get(i).getFirstHalfScore();
                }
                else
                {
                    halfTotal[Program.teamData.get(i).getTeam()][half] = halfTotal[Program.teamData.get(i).getTeam()][half] + Program.teamData.get(i).getSecondHalfScore();
                }
            }

            for (int qtr = 0; qtr < 4; qtr++)
            {
                qtrTotal[Program.teamData.get(i).getTeam()][qtr] = qtrTotal[Program.teamData.get(i).getTeam()][qtr] + Program.teamData.get(i).getQtrScore(qtr);

                if (Program.teamData.get(i).getQtrScore(qtr) < qtrLowest)
                {
                    qtrLowest = Program.teamData.get(i).getQtrScore(qtr);
                } else if (Program.teamData.get(i).getQtrScore(qtr) > qtrHighest)
                {
                    qtrHighest = Program.teamData.get(i).getQtrScore(qtr);
                }
            }
        }
        
        /* Scores will need to be update beyond default in order for differences show more than 0
            All scores are set to the same for each team and game during create file
         */

        // Finds game score difference between teams
        for (int game = 0; game < 42; game++)
        {
            gameDiff = gameTotal[0] - gameTotal[1];
        }

        // Finds half score difference between teams
        for (int half = 0; half < 2; half++)
        {
            halfDiff[half] = halfTotal[0][half] - halfTotal[1][half];
        }

        // Finds quarter score difference between teams
        for (int qtr = 0; qtr < 4; qtr++)
        {
            qtrDiff[qtr] = qtrTotal[0][qtr] - qtrTotal[1][qtr];
        }

        // Finds averages for each team for game, half, and quarter
        for (int team = 0; team < 2; team++)
        {
            gameAverage[team] = gameTotal[team] / (Program.teamData.size() / 2);

            for (int half = 0; half < 2; half++)
            {
                halfAverage[team][half] = halfTotal[team][half] / 42;
            }

            for (int qtr = 0; qtr < 4; qtr++)
            {
                qtrAverage[team][qtr] = qtrTotal[team][qtr] / 42;
            }
        }
    }

    // Implementation of Menu method
    protected static HBox buildScene()
    {
        loadScores();

        HBox sceneLayout = new HBox();

        VBox teamOneLayout = new VBox();
        VBox teamTwoLayout = new VBox();
        VBox diffLayout = new VBox();

        Label teamOne = new Label("Team One");
        Text averageQ1TeamOne = new Text("First Quarter Average:\n" + qtrAverage[0][0]);
        Text averageQ2TeamOne = new Text("Second Quarter Average:\n" + qtrAverage[0][1]);
        Text averageQ3TeamOne = new Text("Third Quarter Average:\n" + qtrAverage[0][2]);
        Text averageQ4TeamOne = new Text("Fourth Quarter Average:\n" + qtrAverage[0][3]);
        Text averageFirstHalfTeamOne = new Text("Average First Half:\n" + halfAverage[0][0]);
        Text averageSecondHalfTeamOne = new Text("Average Second Half:\n" + halfAverage[0][1]);
        Text averageGameTeamOne = new Text("Average Game:\n" + gameAverage[0]);

        Label teamTwo = new Label("Team Two");
        Text averageQ1TeamTwo = new Text("First Quarter Average:\n" + qtrAverage[1][0]);
        Text averageQ2TeamTwo = new Text("Second Quarter Average:\n" + qtrAverage[1][1]);
        Text averageQ3TeamTwo = new Text("Third Quarter Average:\n" + qtrAverage[1][2]);
        Text averageQ4TeamTwo = new Text("Fourth Quarter Average:\n" + qtrAverage[1][3]);
        Text averageFirstHalfTeamTwo = new Text("Average First Half:\n" + halfAverage[1][0]);
        Text averageSecondHalfTeamTwo = new Text("Average Second Half:\n" + halfAverage[1][1]);
        Text averageGameTeamTwo = new Text("Average Game:\n" + gameAverage[1]);

        Text averageQ1Diff = new Text("First Quarter Difference:\n" + qtrDiff[0]);
        Text averageQ2Diff = new Text("Second Quarter Difference:\n" + qtrDiff[1]);
        Text averageQ3Diff = new Text("Third Quarter Difference:\n" + qtrDiff[2]);
        Text averageQ4Diff = new Text("Fourth Quarter Difference:\n" + qtrDiff[3]);
        Text averageFirstHalfDiff = new Text("Average First Half Difference:\n" + halfDiff[0]);
        Text averageSecondHalfDiff = new Text("Average Second Half Difference:\n" + halfDiff[1]);
        Text averageGameDiff = new Text("Average Game Difference:\n" + gameDiff);

        Text lowestQtr = new Text("Lowest Quarter:\n" + qtrLowest);
        Text highestQtr = new Text("Highest Quarter:\n" + qtrHighest);


        teamOneLayout.setAlignment(Pos.CENTER);
        teamOneLayout.setSpacing(25);
        teamOneLayout.getChildren().addAll(teamOne, averageQ1TeamOne, averageQ2TeamOne, averageQ3TeamOne, averageQ4TeamOne,
                averageFirstHalfTeamOne, averageSecondHalfTeamOne, averageGameTeamOne);

        teamTwoLayout.setAlignment(Pos.CENTER);
        teamTwoLayout.setSpacing(25);
        teamTwoLayout.getChildren().addAll(teamTwo, averageQ1TeamTwo, averageQ2TeamTwo, averageQ3TeamTwo, averageQ4TeamTwo,
                averageFirstHalfTeamTwo, averageSecondHalfTeamTwo, averageGameTeamTwo);

        diffLayout.setAlignment(Pos.CENTER);
        diffLayout.setSpacing(25);
        diffLayout.getChildren().addAll(averageQ1Diff, averageQ2Diff, averageQ3Diff, averageQ4Diff, averageFirstHalfDiff,
                averageSecondHalfDiff, averageGameDiff, lowestQtr, highestQtr);

        sceneLayout.setAlignment(Pos.CENTER);
        sceneLayout.setSpacing(25);
        sceneLayout.setMinSize(400, 800);
        sceneLayout.getChildren().addAll(teamOneLayout, teamTwoLayout, diffLayout);

        return sceneLayout;
    }

    // Creates window for everything not in the menu bar
    public static HBox getSceneLayout(Stage stage)
    {
        HBox stageLayout = new HBox();
        stageLayout.setMinSize(800, 800);
        stageLayout.getChildren().addAll(setStage(stage), buildScene());

        return stageLayout;
    }
}
