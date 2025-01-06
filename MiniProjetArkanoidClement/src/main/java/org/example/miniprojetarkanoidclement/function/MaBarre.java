package org.example.miniprojetarkanoidclement.function;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MaBarre {
    private Rectangle vaisseau;
    private double vitesse = 7;
    private int vies = 4;
    public MaBarre() {
        vaisseau = new Rectangle(100, 20, Color.GREEN);
        vaisseau.setX(350);
        vaisseau.setY(920);
    }
    public Rectangle getVaisseau() {
        return vaisseau;
    }
    public void moveLeft() {
        if (vaisseau.getX() > 0) {
            vaisseau.setX(vaisseau.getX() - vitesse);
        }
    }

    public void moveRight(double sceneWidth) {
        if (vaisseau.getX() + vaisseau.getWidth() < sceneWidth) {
            vaisseau.setX(vaisseau.getX() + vitesse);
        }
    }
    public void update() {
        long currentTime = System.nanoTime();
    }
    public int getVies() {
        return vies;
    }

    public void perdreVie() {
        if (vies > 0) {
            vies--;
        }
    }
    public boolean estMort() {
        return vies <= 0;
    }
}
