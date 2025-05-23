package com.github.kawevk.carsapi.automaker.api;

import com.github.kawevk.carsapi.automaker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cars")
public class TestFactoryController {

    @Autowired
    private Engine engine;

    @PostMapping
    public CarStats startCar(@RequestBody Key key) {
        var car = new Car(engine);
        car.setAutomaker(Automaker.HONDA);
        return car.ignition(key);
    }


}
