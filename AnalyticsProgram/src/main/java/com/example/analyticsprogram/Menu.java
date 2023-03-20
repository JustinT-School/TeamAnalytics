package com.example.analyticsprogram;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Base Class for all menus

public class Menu
{
    // Build buttons
    protected static Button mainMenuButton = new Button("Main Menu");
    protected static Button gameButton = new Button("Game");
    protected static Button reportsButton = new Button("Reports");
    protected static Button exitButton = new Button("Exit");

    // Builds sidebar for buttons
    protected static VBox buildMenuBar()
    {
        VBox menuLayout = new VBox();

        mainMenuButton.setMinSize(200, 50);
        gameButton.setMinSize(200, 50);
        reportsButton.setMinSize(200, 50);
        exitButton.setMinSize(200, 50);

        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setMinSize(250, 800);
        menuLayout.setPadding(new Insets(50));
        menuLayout.getChildren().addAll(mainMenuButton, gameButton, reportsButton, exitButton);
        menuLayout.setSpacing(10);

        return menuLayout;
    }

    // Builds screen
    protected static HBox setStage(Stage stage)
    {
        HBox stageLayout = new HBox();

        mainMenuButtonAction(stage);
        gameButtonAction(stage);
        reportsButtonAction(stage);
        exitButtonAction();

        stageLayout.getChildren().addAll(buildMenuBar());

        return stageLayout;
    }

    // Actions for when buttons are pressed
    private static void mainMenuButtonAction(Stage stage)
    {
        mainMenuButton.setOnAction((event) ->
        {
            Scene scene = new Scene(StartPage.getSceneLayout(stage), 900, 800);
            stage.setScene(scene);
        });
    }

    private static void gameButtonAction(Stage stage)
    {
        gameButton.setOnAction((event) ->
        {
            Scene scene = new Scene(GamePage.getSceneLayout(stage), 900, 800);
            stage.setScene(scene);
        });
    }

    private static void reportsButtonAction(Stage stage)
    {
        reportsButton.setOnAction((event) ->
        {
            Scene scene = new Scene(ReportsPage.getSceneLayout(stage), 900, 800);
            stage.setScene(scene);
        });
    }

    private static void exitButtonAction()
    {
        exitButton.setOnAction((event) ->
        {
            Platform.exit();
        });
    }
}
