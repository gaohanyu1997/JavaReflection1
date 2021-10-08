package com.atghy.reflection2;

import java.io.Serializable;

public class CreaSure<T> implements Serializable {
    private char gender;
    public double weight;

    private void breath(){
        System.out.println("生物呼吸");
    }

    public void eat(){
        System.out.println("生物吃东西");
    }

}
