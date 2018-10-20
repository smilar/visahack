package com.chase.money.predict;


import lombok.Data;

@Data
public class Prediction {
    private String in;
    private String out;

    public Prediction(String in) {
        this.in = in;
    }


}
