package com.example.uloha_2d_grafika;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

import static com.example.uloha_2d_grafika.Direction.getRandomD;
import static com.example.uloha_2d_grafika.Direction.up;


public class MainApplication extends Application {
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 920;
    public boolean stop = false;
    GraphicsContext graphicsContext;

    double X;
    double Y;

    Random random = new Random();
    Ballon ballon;
    Direction direction = Direction.down;
    int counter = 0;

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();
        Canvas canvas = new Canvas(SCREEN_WIDTH, SCREEN_HEIGHT);
        root.getChildren().add(canvas);
        graphicsContext = canvas.getGraphicsContext2D();
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Klasifikovaná úloha");
        stage.show();

        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
             X = event.getX();
             Y = event.getY();
            System.out.println(String.format("Clicked X: %s, Y: %s", X,Y));
        });

        ballon = new Ballon(random.nextInt(SCREEN_WIDTH), random.nextInt(SCREEN_HEIGHT));

    AnimationTimer animationTimer = new AnimationTimer() {
        long lastTick = 0;

        @Override
        public void handle(long now) {
            if (now - lastTick > 1000000000l/ ballon.speed) {
                lastTick = now;
                tick();
                }
            }
        };
            animationTimer.start();
    }

    private void tick(){
        if (ballon.tick == 19){
            ballon.tick = 0;
        }
        ballon.loadTextures();
        if(stop){
            direction = Direction.getRandomD();
            stop = false;
            return;
        }

        clearScreen();

        switch (direction){
            case up:
                stop = ballon.getY() < 0;
                if (!stop) ballon.decrementY();
                break;
            case down:
                stop = ballon.getY() > SCREEN_HEIGHT - ballon.height;
                if (!stop) ballon.incrementY();
                break;
            case left:
                stop = ballon.getX() < 0;
                if (!stop) ballon.decrementX();
                break;
            case right:
                stop = ballon.getX() > SCREEN_WIDTH - ballon.height;
                if (!stop) ballon.incrementX();
                break;
        }

        if (ballon.pop == 11){
            ballon.pop = 0;
        if(X >= ballon.x && X <= ballon.x + ballon.width && Y >= ballon.y && Y <= ballon.y + ballon.height){
            X = 0;
            Y = 0;
            ballon.x = random.nextInt(SCREEN_WIDTH - ballon.width);
            ballon.y = random.nextInt(SCREEN_HEIGHT - ballon.height);
            direction = Direction.getRandomD();
            ballon.popTextures();
            }
        }
        ballon.pop++;

        graphicsContext.drawImage(ballon.image, ballon.x, ballon.y, ballon.width, ballon.height);
        ballon.tick++;
    }

    private void clearScreen() {
        graphicsContext.clearRect(0,0, SCREEN_WIDTH, SCREEN_WIDTH);
    }

    public static void main(String[] args) {
        launch();
    }
}