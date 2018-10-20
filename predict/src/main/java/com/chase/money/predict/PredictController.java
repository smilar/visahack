package com.chase.money.predict;




import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PredictController {

    @RequestMapping("/predict")
    public Prediction predict(@RequestParam(value="in", defaultValue="10") String in) {
        return new Prediction(in);
    }
}