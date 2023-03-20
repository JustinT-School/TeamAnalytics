/* =============================================================
Author : Justin Tuder
Class : ITN261
Class Section : 201
Date : 11/26/2022
Assignment : Final Project
Notes : A local high school wants to keep track of statistics for the basketball team. The coach wants to get insight
        into average scores, score differentials, etc.  The coach would like to be able to run reports on demand using
        a menu-driven application.

============================================================= */

package com.example.analyticsprogram;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

// Main class for storing teamData
// Called first when application opens, then loads start page

public class Program extends Application
{
    // Data file that stores scores
    String filePath = "team_scores.txt";
    static ArrayList<TeamData> teamData = new ArrayList<>();

    // Loads data from file
    public void loadData()
    {
        try
        {
            // If no file exists, create new file and populate with random numbers
            File newFile = new File(filePath);
            if (newFile.createNewFile())
            {
                System.out.println("Created " + newFile);

                ArrayList<Integer> qtrScores = new ArrayList<>();

                for (int team = 0; team < 2; team++)
                {
                    for (int game = 0; game < 42; game++)
                    {
                        for (int qtr = 0; qtr < 4; qtr++)
                        {
                            // We will assign a random number between 5 and 30 for each quarter)
                            int score = (int)(Math.random()*25) + 5;
                            qtrScores.add(score);
                        }

                        teamData.add(new TeamData(team, game, qtrScores));
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not create file.");
        }

        try
        {
            // If file exists, load file
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = "";

            while ((line = reader.readLine()) != null)
            {
                String[] convertedLine = line.split(",");
                int team = Integer.parseInt(convertedLine[0]);
                int game = Integer.parseInt(convertedLine[1]);
                ArrayList<Integer> qtrScores = new ArrayList<>();

                for (int qtr = 2; qtr < convertedLine.length; qtr++)
                {
                    qtrScores.add(Integer.parseInt(convertedLine[qtr]));
                }

                teamData.add(new TeamData(team, game, qtrScores));
            }

            reader.close();
        }
        catch (Exception e)
        {
            System.out.println("No file loaded.");
        }
    }

    // Save data to file
    // Data is saved as Team, Game, Quarter Scores
    public static void storeData()
    {
        try
        {
            Path fileName = Path.of("team_scores.txt");
            BufferedWriter writer = Files.newBufferedWriter(fileName);
            writer.flush();
            for (int i = 0; i < teamData.size(); i++)
            {
                writer.write(teamData.get(i).toString());
                writer.newLine();
            }

            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("Could not write to file.");
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        loadData();
        Scene scene = new Scene(StartPage.getSceneLayout(stage), 900, 800);
        stage.setTitle("Team Analytics");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Ensures data is saved even if program is not exited from the exit button
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                storeData();
            }
        }));

        launch();
    }
}
