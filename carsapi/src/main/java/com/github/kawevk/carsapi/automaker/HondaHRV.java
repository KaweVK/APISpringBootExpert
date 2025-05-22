package com.github.kawevk.carsapi.automaker;

import java.awt.*;

public class HondaHRV extends Car{

    public HondaHRV(Engine engine) {
        super(engine);
        setModel("HRV");
        setColor(Color.DARK_GRAY);
        setAutomaker(Automaker.HONDA);
    }


}
