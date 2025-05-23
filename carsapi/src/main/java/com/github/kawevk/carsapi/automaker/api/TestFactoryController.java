package com.github.kawevk.carsapi.automaker.api;

import com.github.kawevk.carsapi.automaker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class TestFactoryController {

    @Autowired
    @Turbo //anotação própria
    private Engine engine;

    @PostMapping
    public CarStats startCar(@RequestBody Key key) {
        var car = new Car(engine);
        car.setAutomaker(Automaker.HONDA);
        return car.ignition(key);
    }


}
