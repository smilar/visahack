package com.visa.money.predict;




import org.springframework.web.bind.annotation.*;

@RestController
public class PredictController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Prediction test(@RequestParam(value="in", defaultValue="10") String in) {
        return new Prediction(in);
    }

    @RequestMapping(
            value = "/predict",
            method = RequestMethod.POST,
            consumes = "text/plain")
    public Prediction predict(@RequestBody String payload) throws Exception {



        Prediction pred=new Prediction(payload);
        return pred;


    }
}