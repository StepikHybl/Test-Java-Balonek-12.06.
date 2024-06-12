package com.example.uloha_2d_grafika;

import java.util.Random;

public enum Direction {
    up, down, left, right;


    public static Direction getRandomD(){
        Random r = new Random();
       return Direction.values()[r.nextInt(4)];

    }
}
