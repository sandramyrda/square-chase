package io.nology;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square {
    // type - player(0) or collision block(1)
    Rectangle element;
    int type;
    Random random = new Random();
    static ArrayList<Square> allBlocks = new ArrayList<>();

    public Square(int type) {
        this.type = type;
        this.element = new Rectangle(30, 30);
        generatePosition();
        if (type == 0) {
            this.element.setFill(Color.rgb(89, 121, 88));
        } else {
            allBlocks.add(this);
        }
    }

    public boolean doesItCollide(Square sq) {
        if (this.element.getBoundsInParent().intersects(sq.element.getBoundsInParent())) {
            System.out.println("Collision!");
            return true;
        } else {
            return false;
        }
    }

    public void generatePosition() {
        boolean positionValid = false;
        while (!positionValid) {
            int randomX = random.nextInt(270);
            int randomY = random.nextInt(270);
            this.element.setX(randomX);
            this.element.setY(randomY);
            positionValid = true;
            for (Square square : allBlocks) {
                if (this != square && this.doesItCollide(square)) {
                    positionValid = false;
                    break;
                }
            }
        }
    }

    // method for the player
    public void handleMove() {

        for (Square sq : allBlocks) {
            if (this.doesItCollide(sq)) {
                int g = random.nextInt(255);
                int b = random.nextInt(255);
                int r = random.nextInt(200);
                sq.element.setFill(Color.rgb(r, g, b));
                sq.generatePosition();
                this.element.setWidth(this.element.getWidth() + 3);
                this.element.setHeight(this.element.getHeight() + 3);
            }
        }
    }

}
