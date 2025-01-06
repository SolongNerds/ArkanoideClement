package org.example.miniprojetarkanoidclement;

import org.example.miniprojetarkanoidclement.function.MaBarre;
import org.example.miniprojetarkanoidclement.function.Balloche;
import org.example.miniprojetarkanoidclement.function.RectangleCassable;
import org.example.miniprojetarkanoidclement.function.Score;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Lejeuxoufautcasserdesbriques {

    private Score score;

    public Scene createGameScene(Stage primaryStage, MenuArcacanoide menu) {
        MaBarre vaisseau = new MaBarre();
        Balloche ball = new Balloche();
        score = new Score();
        Pane gameLayout = new Pane();
        gameLayout.setStyle("-fx-background-color: #ff15ff15;");
        Rectangle banner = new Rectangle(0, 0, 800, 50);
        banner.setFill(Color.LIGHTBLUE);
        Text title = new Text("HemoroidesClement");
        title.setFont(Font.font("Courier New", 24));
        title.setFill(Color.DARKBLUE);
        title.setX(20);
        title.setY(35);
        gameLayout.getChildren().addAll(banner, title);
        gameLayout.getChildren().addAll(vaisseau.getVaisseau(), ball.getBall());
        List<RectangleCassable> bricks = RectangleCassable.generateBricks(gameLayout, 4, 21, 80, 30);
        Text scoreText = new Text("Score: " + score.getScore());
        scoreText.setFont(Font.font("Arial", 18));
        scoreText.setFill(Color.DARKBLUE);
        scoreText.setX(650);
        scoreText.setY(30);
        gameLayout.getChildren().add(scoreText);
        Text livesText = new Text("Vies restantes: " + vaisseau.getVies());
        livesText.setFont(Font.font("Arial", 18));
        livesText.setFill(Color.DARKBLUE);
        livesText.setX(400);
        livesText.setY(30);
        gameLayout.getChildren().add(livesText);
        Scene gameScene = new Scene(gameLayout, 800, 600);
        boolean[] keys = new boolean[256];
        gameScene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.Q) {
                keys[KeyCode.Q.getCode()] = true;
            } else if (code == KeyCode.D) {
                keys[KeyCode.D.getCode()] = true;
            }
        });
        gameScene.setOnKeyReleased(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.Q) {
                keys[KeyCode.Q.getCode()] = false;
            } else if (code == KeyCode.D) {
                keys[KeyCode.D.getCode()] = false;
            }
        });
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                vaisseau.update();
                if (keys[KeyCode.Q.getCode()]) {
                    vaisseau.moveLeft();
                }
                if (keys[KeyCode.D.getCode()]) {
                    vaisseau.moveRight(gameScene.getWidth());
                }
                ball.move();
                ball.checkWallCollision(gameScene.getWidth(), gameScene.getHeight());
                ball.checkVaisseauCollision(vaisseau.getVaisseau().getX(), vaisseau.getVaisseau().getY(),
                        vaisseau.getVaisseau().getWidth(), vaisseau.getVaisseau().getHeight());
                for (RectangleCassable brick : new ArrayList<>(bricks)) {
                    if (ball.checkBrickCollision(brick.getBrick())) {
                        gameLayout.getChildren().remove(brick.getBrick());
                        bricks.remove(brick);
                        score.incrementScoreOnBreak();
                        break;
                    }
                }
                scoreText.setText("Score: " + score.getScore());
                livesText.setText("Vies restantes: " + vaisseau.getVies());
                if (ball.getBall().getCenterY() >= gameScene.getHeight()) {
                    vaisseau.perdreVie();
                    if (vaisseau.estMort()) {
                        this.stop();
                        showGameOverScreen(primaryStage, menu);
                    } else {
                        ball.resetPosition(gameScene.getWidth(), gameScene.getHeight());
                    }
                }
            }
        };
        gameLoop.start();
        return gameScene;
    }

    private void showGameOverScreen(Stage primaryStage, MenuArcacanoide menu) {
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setStyle("-fx-alignment: center; -fx-padding: 20; -fx-background-color: #f5f5f5;");

        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(Font.font("Courier New", 30));
        gameOverText.setFill(Color.RED);

        Text scoreText = new Text("Votre score: " + score.getScore());
        scoreText.setFont(Font.font("Arial", 20));
        scoreText.setFill(Color.BLACK);

        Button mainMenuButton = new Button("Retour au menu principal");
        mainMenuButton.setOnAction(e -> {
            primaryStage.setScene(menu.getMainMenuScene());
            primaryStage.setFullScreen(true);
        });
        gameOverLayout.getChildren().addAll(gameOverText, scoreText, mainMenuButton);
        Scene gameOverScene = new Scene(gameOverLayout, 400, 300);
        primaryStage.setScene(gameOverScene);
        primaryStage.setFullScreen(true);
    }
}
