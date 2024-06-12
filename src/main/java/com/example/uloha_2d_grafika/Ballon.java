package com.example.uloha_2d_grafika;

import javafx.scene.image.Image;

import java.net.URL;
import java.util.List;
import java.util.Random;

public class Ballon extends Rectangle {

    public static final List<String> STANDARD_TEXTURES = List.of("pohyb1.png", "pohyb2.png", "pohyb3.png", "pohyb4.png", "pohyb5.png", "pohyb6.png", "pohyb7.png", "pohyb8.png", "pohyb9.png", "pohyb10.png", "pohyb11.png", "pohyb12.png", "pohyb13.png", "pohyb14.png", "pohyb15.png", "pohyb16.png", "pohyb17.png", "pohyb18.png", "pohyb19.png", "pohyb20.png");
    public static final List<String> POP_TEXTURES = List.of("pop1.png","pop2.png", "pop3.png", "pop4.png", "pop5.png", "pop6.png", "pop7.png", "pop8.png", "pop9.png", "pop10.png", "pop11.png");
    Image image;
    int tick;
    int pop;

    public Ballon(int x, int y) {
        super(x, y);
        loadTextures();
        popTextures();
    }

    public void loadTextures() {
        URL url = getClass().getResource("/images/" + STANDARD_TEXTURES.get(tick));
        if (url != null) {
            image = new Image(url.toString());
            setWidth((int) image.getWidth() / 2);
            setHeight((int) image.getHeight() / 2);

        }
    }

    public void popTextures() {
        URL url = getClass().getResource("/images/" + POP_TEXTURES.get(pop));
        if (url != null) {
            image = new Image(url.toString());
            setWidth((int) image.getWidth() / 2);
            setHeight((int) image.getHeight() / 2);

        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }
}
