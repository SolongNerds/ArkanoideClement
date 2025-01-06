package org.example.miniprojetarkanoidclement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

public class MenuArcacanoide extends Application {
    private Scene mainMenuScene;
    @Override
    public void start(Stage primaryStage) {
        mainMenuScene = createMainMenuScene(primaryStage);
        primaryStage.setTitle("Arkanoide");
        primaryStage.setScene(mainMenuScene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();

        primaryStage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode().toString().equals("ESCAPE")) {
                event.consume();
            }
        });
    }

    private Scene createMainMenuScene(Stage primaryStage) {

        Button playButton = new Button("Jouer");
        Button quitButton = new Button("Quitter");
        playButton.setOnAction(e -> {
            Lejeuxoufautcasserdesbriques jeuArkanoid = new Lejeuxoufautcasserdesbriques();
            Scene gameScene = jeuArkanoid.createGameScene(primaryStage, this);
            primaryStage.setScene(gameScene);
            primaryStage.setFullScreen(true);
        });
        quitButton.setOnAction(e -> primaryStage.close());
        VBox vbox = new VBox(20, playButton, quitButton);
        vbox.setStyle("-fx-alignment: center;");
        return new Scene(vbox, 400, 300);
    }

    public Scene getMainMenuScene() {
        return mainMenuScene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
