package io.nology;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class App extends Application {
    double movePX = 20;
    Random random = new Random();
    ArrayList<Square> blocks = new ArrayList<>();

    Square block1 = new Square(1);

    Square block2 = new Square(1);
    Square block3 = new Square(1);

    Square player = new Square(0);

    @Override
    public void start(Stage stage) throws IOException {

        // stage is the native window
        // scene is where all the content is
        stage.setTitle("square chase");

        Pane layout = new Pane();
        // StackPane centres all the children automatically
        layout.getChildren().addAll(player.element, block1.element, block2.element, block3.element);

        Scene scene = new Scene(layout, 300, 300);
        scene.setOnKeyPressed(event -> {
            handleKeyPress(event, player.element, block1.element);
        });
        stage.setScene(scene);
        stage.show();
    }

    private void handleKeyPress(KeyEvent event, Rectangle rectangle, Rectangle staticRec) {
        double x = rectangle.getX();
        double y = rectangle.getY();

        if (event.getCode() == KeyCode.UP) {
            rectangle.setY(y - movePX);
            player.doesItCollide(block1);
            player.handleMove();
        } else if (event.getCode() == KeyCode.DOWN) {
            rectangle.setY(y + movePX);
            player.doesItCollide(block1);
            player.handleMove();
        } else if (event.getCode() == KeyCode.LEFT) {
            rectangle.setX(x - movePX);
            player.doesItCollide(block1);
            player.handleMove();
        } else if (event.getCode() == KeyCode.RIGHT) {
            rectangle.setX(x + movePX);
            player.doesItCollide(block1);
            player.handleMove();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}