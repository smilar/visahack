package com.chase.money.predict;


import com.chase.money.algorithm.ArimaCalc;
import lombok.Data;

import java.util.Arrays;

@Data
public class Prediction {
    private String in;
    private String out;

    public Prediction(String in) {
        this.in = in;
        System.out.println("Init with:"+in);
        in=in.replace("{","");
        in=in.replace("}","");
        System.out.println("String is:"+in);
        double[] numbers = Arrays.asList(in.split(","))
                .stream()
                .map(String::trim)
                .mapToDouble(Double::parseDouble).toArray();

        out= ArimaCalc.getResults(numbers);
    }


}
