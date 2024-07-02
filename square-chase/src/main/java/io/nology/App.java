package io.nology;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class App extends Application {
    double movePX = 20;
    Random random = new Random();

    @Override
    public void start(Stage stage) throws IOException {

        // stage is the native window
        // scene is where all the content is
        stage.setTitle("square chase");

        int randomX = random.nextInt(300);
        int randomY = random.nextInt(300);
        // handle the situation when both random positions are the same - generate a new
        // random
        int randomX2 = random.nextInt(300);
        int randomY2 = random.nextInt(300);

        Rectangle rec1 = new Rectangle(randomX, randomY, 30, 30);
        rec1.setFill(Color.rgb(89, 121, 88));
        Rectangle rec2 = new Rectangle(randomX2, randomY2, 30, 30);

        Pane layout = new Pane();
        // StackPane centres all the children automatically
        layout.getChildren().addAll(rec1, rec2);

        Scene scene = new Scene(layout, 300, 300);
        scene.setOnKeyPressed(event -> {
            handleKeyPress(event, rec1, rec2);
        });
        stage.setScene(scene);
        stage.show();
    }

    private void checkCollision(Rectangle rectangle1, Rectangle rectangle2) {
        if (rectangle1.getBoundsInParent().intersects(rectangle2.getBoundsInParent())) {
            System.out.println("Collision!");
            rectangle1.setFill(Color.RED);
        } else {
            rectangle1.setFill(Color.rgb(89, 121, 88));
        }
    }

    private void handleKeyPress(KeyEvent event, Rectangle rectangle, Rectangle staticRec) {
        double x = rectangle.getX();
        double y = rectangle.getY();

        if (event.getCode() == KeyCode.UP) {
            rectangle.setY(y - movePX);
            checkCollision(rectangle, staticRec);
        } else if (event.getCode() == KeyCode.DOWN) {
            rectangle.setY(y + movePX);
            checkCollision(rectangle, staticRec);
        } else if (event.getCode() == KeyCode.LEFT) {
            rectangle.setX(x - movePX);
            checkCollision(rectangle, staticRec);
        } else if (event.getCode() == KeyCode.RIGHT) {
            rectangle.setX(x + movePX);
            checkCollision(rectangle, staticRec);
        }
    }

    public static void main(String[] args) {
        launch();
    }

}