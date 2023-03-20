package com.example.analyticsprogram;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Page loaded on program start

public class StartPage extends Menu
{
    // Implementation of Menu method
    protected static HBox buildScene()
    {
        HBox sceneLayout = new HBox();
        VBox verticalLayout = new VBox();
        Text welcomeText = new Text("Basketball Team Analytics");


        verticalLayout.setAlignment(Pos.CENTER);
        verticalLayout.getChildren().addAll(welcomeText);

        sceneLayout.setAlignment(Pos.CENTER);
        sceneLayout.setMinSize(400, 800);
        sceneLayout.getChildren().addAll(verticalLayout);

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
