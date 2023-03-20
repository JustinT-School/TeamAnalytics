package com.example.analyticsprogram;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Page for entering scores for each team per game and quarter

public class GamePage extends Menu
{
    private static int currentGame = 1;
    private static int currentQuarter = 1;
    private static int currentScore = 0;

    private static Button quarterButton = new Button("Next Quarter");

    private static Text game = new Text("Game: " +currentGame);
    private static Text quarter = new Text("Quarter: " +currentQuarter);

    private static TextField teamOneField = new TextField();
    private static TextField teamTwoField = new TextField();

    private static Text teamOneScore = new Text();
    private static Text teamTwoScore = new Text();

    private static TextField gameField = new TextField();

    // Implementation of Menu method
    protected static HBox buildScene()
    {
        HBox sceneLayout = new HBox();

        VBox screneLayout = new VBox();

        Label gameQuestion = new Label("Which game is this?");

        screneLayout.setAlignment(Pos.CENTER);
        screneLayout.setSpacing(10);
        screneLayout.getChildren().addAll(gameQuestion, gameField);

        sceneLayout.setAlignment(Pos.CENTER);
        sceneLayout.setMinSize(500, 800);
        sceneLayout.getChildren().addAll(screneLayout);

        return sceneLayout;
    }

    // Creates window for everything not in the menu bar
    public static HBox getSceneLayout(Stage stage)
    {
        HBox stageLayout = new HBox();

        stageLayout.setMinSize(900, 800);
        stageLayout.getChildren().addAll(setStage(stage), buildScene());

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                currentGame = Integer.parseInt(gameField.getText());
                gameField.clear();
                Scene scene = new Scene(getScoreSceneLayout(stage), 900, 800);
                stage.setScene(scene);
            }
        };

        gameField.setOnAction(event);

        return stageLayout;
    }

    // Builds landing screen to select which game you would like to enter scores for
    protected static HBox buildScoreScene()
    {
        HBox sceneLayout = new HBox();
        HBox scoreLayout = new HBox();
        HBox gameInfoLayout = new HBox();
        HBox quarterButtonLayout = new HBox();

        VBox screneLayout = new VBox();
        VBox teamOneLayout = new VBox();
        VBox teamTwoLayout = new VBox();

        Label teamOneLabel = new Label("Team 1");
        Label teamTwoLabel = new Label("Team 2");
        Label versusLabel = new Label("vs.");

        game.setText("Game: " + currentGame);

        versusLabel.setPadding(new Insets(10));

        gameInfoLayout.setAlignment(Pos.CENTER);
        gameInfoLayout.setSpacing(50);
        gameInfoLayout.setPadding(new Insets(25));
        gameInfoLayout.getChildren().addAll(game, quarter);

        teamOneLayout.setAlignment(Pos.CENTER);
        teamOneLayout.getChildren().addAll(teamOneLabel, teamOneField, teamOneScore);

        teamTwoLayout.setAlignment(Pos.CENTER);
        teamTwoLayout.getChildren().addAll(teamTwoLabel, teamTwoField, teamTwoScore);

        scoreLayout.setAlignment(Pos.CENTER);
        scoreLayout.getChildren().addAll(teamOneLayout, versusLabel, teamTwoLayout);

        quarterButtonLayout.setAlignment(Pos.CENTER_RIGHT);
        quarterButtonLayout.setPadding(new Insets(25));
        quarterButtonLayout.getChildren().addAll(quarterButton);

        screneLayout.setAlignment(Pos.CENTER);
        screneLayout.getChildren().addAll(gameInfoLayout, scoreLayout, quarterButtonLayout);

        sceneLayout.setAlignment(Pos.CENTER);
        sceneLayout.setMinSize(400, 800);
        sceneLayout.getChildren().addAll(screneLayout);

        return sceneLayout;
    }

    // Loads actual game page
    public static HBox getScoreSceneLayout(Stage stage)
    {
        HBox stageLayout = new HBox();

        teamOneScore.setText("Score: " + currentScore);
        teamTwoScore.setText("Score: " + currentScore);

        stageLayout.setMinSize(800, 800);
        stageLayout.getChildren().addAll(setStage(stage), buildScoreScene());

        quarterButtonAction();
        teamOneField.setOnAction(teamOneScoreAction());
        teamTwoField.setOnAction(teamTwoScoreAction());

        return stageLayout;
    }

    // Quarter button action increases the current quarter
    // Will increase current game if quarter > 4
    // Will set game to 1 if current game > 42
    private static void quarterButtonAction()
    {
        quarterButton.setOnAction((event) ->
        {
            if (currentQuarter < 4)
            {
                currentQuarter = currentQuarter + 1;
                quarter.setText("Quarter: " + currentQuarter);
            }
            else
            {
                if (currentGame < 42)
                {
                    currentGame = currentGame + 1;
                }
                else
                {
                    currentGame = 1;
                }
                currentQuarter = 1;
                quarter.setText("Quarter: " + currentQuarter);
                game.setText("Game: " + currentGame);
            }
        });
    }

    // Field actions to update quarter scores per team
    public static EventHandler<ActionEvent> teamOneScoreAction()
    {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                currentScore = Integer.parseInt(teamOneField.getText());
                Program.teamData.get(currentGame - 1).setScore(currentQuarter - 1, currentScore);
                teamOneScore.setText("Score: " + currentScore);
                teamOneField.clear();
            }
        };

        return event;
    }

    public static EventHandler<ActionEvent> teamTwoScoreAction()
    {
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                currentScore = Integer.parseInt(teamTwoField.getText());
                Program.teamData.get(currentGame + 42).setScore(currentQuarter - 1, currentScore);
                teamTwoScore.setText("Score: " + currentScore);
                teamTwoField.clear();
            }
        };

        return event;
    }
}
